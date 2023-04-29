package ragi_materials.main.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.item.ItemStack
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
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiTank
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.tile.TileItemHandlerBase
import ragi_materials.core.util.dropItemFromTile
import ragi_materials.core.util.getBottle
import ragi_materials.main.container.ContainerFullBottle

class TileFullBottleStation : TileItemHandlerBase(), ITickable {

    val output = RagiItemHandler(1).setIOType(EnumIOType.OUTPUT)
    val inventory = RagiItemHandlerWrapper(output)
    private val tank = RagiTank(64000)
    var count = 0

    init {
        tank.setTileEntity(this)
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound) = tag.also {
        super.writeToNBT(it)
        it.setTag(keyInventory, inventory.serializeNBT()) //インベントリをtagに書き込む
        it.setTag(keyTank, tank.serializeNBT())
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory)) //tagからインベントリを読み込む
        tank.deserializeNBT(tag.getCompoundTag(keyTank)) //tagから液体タンクを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory)
            CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY -> CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank)
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

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        val fluidHandler = FluidUtil.getFluidHandler(player.getHeldItem(hand))
        return if (fluidHandler !== null) FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing) else {
            player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
            return true
        }
    }

    override fun onTilePlaced(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        readNBTFromStack(stack)
    }

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropItemFromTile(world, pos, ItemStack(RagiRegistry.BlockFullBottleStation), this)
    }

    //    ITickable    //

    override fun update() {
        if (!world.isRemote) {
            //countが20以上の場合
            if (count >= 20) {
                val amountRemain = tank.fluidAmount % 1000 //タンクに残る液体量
                val countBottle = tank.fluidAmount / 1000 //生成するフルボトルの個数
                //作成個数が0より多い場合
                if (countBottle > 0 && tank.fluid !== null && output.getStackInSlot(0).isEmpty) {
                    output.insertItem(0, getBottle(FluidStack(tank.fluid!!, 1000), countBottle), false) //フルボトルを製造
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

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerFullBottle(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:fullbottle_station"

}