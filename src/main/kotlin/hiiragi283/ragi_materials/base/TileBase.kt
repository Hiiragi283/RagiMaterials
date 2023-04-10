package hiiragi283.ragi_materials.base

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class TileBase(open val type: Int) : TileEntity(), ITileActivatable {

    val keyInventory = "inventory"
    val keyTank = "tank"
    val keyEnergy = "energy"

    //    General    //

    fun getState(): IBlockState = world.getBlockState(pos)

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound = writeToNBT(NBTTagCompound()) //オーバーライドしないと正常に更新されない

    //    Packet    //

    override fun getUpdatePacket(): SPacketUpdateTileEntity = SPacketUpdateTileEntity(pos, type, this.updateTag) //NBTタグの情報を送る

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        this.readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    /**
    Thanks to defeatedcrow!
    Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93
     */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean = oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue

    //    ITIleActivatable    //

    abstract override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean

}