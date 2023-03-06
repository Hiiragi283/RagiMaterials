package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.ITileBase
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class TileFullBottleStation: TileEntity(), ITileBase, ITickable {

    val inventory = ItemStackHandler(1)
    private val tank = object: FluidTank(60000) {

        override fun canFillFluidType(fluid: FluidStack?): Boolean {
            //液体の名前から取得した素材が空でないならtrue
            return if (fluid !== null) !MaterialUtil.getMaterial(fluid.fluid.name).isEmpty() else false
        }
    }
    private var count = 0

    init {
        tank.setTileEntity(this)
    }

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound {
        //オーバーライドしないと正常に更新されない
        return writeToNBT(NBTTagCompound())
    }

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag("inventory", this.inventory.serializeNBT()) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        this.inventory.deserializeNBT(tag.getCompoundTag("inventory")) //tagからインベントリを読み込む
    }

    //    Packet    //

    override fun getUpdatePacket(): SPacketUpdateTileEntity {
        return SPacketUpdateTileEntity(pos, 101, this.updateTag) //NBTタグの情報を送る
    }

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        this.readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean {
        return oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue
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

    //    Event    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing) {
        FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing)
    }

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            val amount = tank.fluidAmount //タンク内の液体量
            val amountRemain = tank.fluidAmount % 1000 //タンクに残る液体量
            val countBottle = tank.fluidAmount / 1000 //生成するフルボトルの個数
            //作成個数が0より多い場合
            if (countBottle > 0 && tank.fluid !== null && inventory.getStackInSlot(0).isEmpty) {
                inventory.insertItem(0, RagiUtil.getFilledBottle(countBottle, FluidStack(tank.fluid!!, 1000)), false) //フルボトルを製造
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