package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.util.RagiTank
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class TileFullBottleStation : TileBase(101), ITickable {

    val inventory = ItemStackHandler(1)
    private val tank = RagiTank(64000)
    private var count = 0

    init {
        tank.setTileEntity(this)
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, this.inventory.serializeNBT()) //インベントリをtagに書き込む
        tag.setTag(keyTank, this.tank.serializeNBT())
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        this.inventory.deserializeNBT(tag.getCompoundTag(keyInventory)) //tagからインベントリを読み込む
        this.tank.deserializeNBT(tag.getCompoundTag(keyTank)) //tagから液体タンクを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> this.inventory as T
            CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY -> this.tank as T
            else -> super.getCapability(capability, facing)
        }
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> true
            CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY -> true
            else -> false
        }
    }

    //    ITileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean = FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing)

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            val amountRemain = tank.fluidAmount % 1000 //タンクに残る液体量
            val countBottle = tank.fluidAmount / 1000 //生成するフルボトルの個数
            //作成個数が0より多い場合
            if (countBottle > 0 && tank.fluid !== null && inventory.getStackInSlot(0).isEmpty) {
                inventory.insertItem(0, RagiUtil.getFilledBottle(FluidStack(tank.fluid!!, 1000), countBottle), false) //フルボトルを製造
                if (amountRemain > 0) {
                    tank.fluid = FluidStack(tank.fluid!!, amountRemain) //タンクの内容量を上書き
                } else {
                    tank.fluid = null //液体量が0ならば空にする
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }
}