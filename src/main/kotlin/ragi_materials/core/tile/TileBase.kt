package ragi_materials.core.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.network.MessageTile
import ragi_materials.core.network.RagiNetworkWrapper
import ragi_materials.core.util.dropItem

abstract class TileBase : TileEntity() {

    var material: RagiMaterial = RagiMaterial.EMPTY

    val keyEnergy = "energy"
    val keyGas = "gas"
    val keyHeat = "heat"
    val keyInventory = "inventory"
    val keyMass = "mass"
    val keyMaterial = "material"
    val keyTank = "tank"

    //    General    //

    fun getState(): IBlockState = getBlockType().getStateFromMeta(blockMetadata)

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound = writeToNBT(NBTTagCompound()) //オーバーライドしないと正常に更新されない

    //    Packet    //

    override fun getUpdatePacket() = SPacketUpdateTileEntity(pos, 0, updateTag) //NBTタグの情報を送る

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    /**
     * Thanks to defeatedcrow!
     * Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93
     */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean = oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue

    fun syncData() {
        RagiNetworkWrapper.sendToAll(MessageTile(pos))
    }

    //    Event    //

    abstract fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean

    open fun onTilePlaced(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {}

    fun readNBTFromStack(stack: ItemStack) {
        if (stack.tagCompound !== null) {
            val tag = stack.getOrCreateSubCompound("BlockEntityTag").also {
                it.setInteger("x", this.pos.x)
                it.setInteger("y", this.pos.y)
                it.setInteger("z", this.pos.z)
            }
            readFromNBT(tag) //NBTタグから読み込む
        }
    }

    fun readMaterialFromStack(stack: ItemStack) {
        material = RagiMaterial.readFromNBT(stack.getOrCreateSubCompound("BlockEntityTag"))
    }

    open fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {}

    fun getDropWithNBT() {
        val stack = ItemStack(getBlockType())
        stack.getOrCreateSubCompound("BlockEntityTag").merge(updateTag)
        dropItem(world, pos, stack)
    }

}