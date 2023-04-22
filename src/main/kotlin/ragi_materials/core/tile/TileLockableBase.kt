package ragi_materials.core.tile

import ragi_materials.core.container.RagiInventory
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntityLockable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class TileLockableBase(val type: Int) : TileEntityLockable() {

    abstract val inventory: RagiInventory

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound = writeToNBT(NBTTagCompound()) //オーバーライドしないと正常に更新されない

    //    Packet    //

    override fun getUpdatePacket(): SPacketUpdateTileEntity = SPacketUpdateTileEntity(pos, type, this.updateTag) //NBTタグの情報を送る

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        this.readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    /**
     * Thanks to defeatedcrow!
     * Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93
     */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean = oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue

    //    IInventory    //

    override fun getName() = inventory.title

    override fun hasCustomName() = false

    override fun getSizeInventory() = inventory.sizeInventory

    override fun isEmpty() = inventory.isEmpty

    override fun getStackInSlot(index: Int) = inventory.getStackInSlot(index)

    override fun decrStackSize(index: Int, count: Int) = inventory.decrStackSize(index, count)

    override fun removeStackFromSlot(index: Int) = inventory.removeStackFromSlot(index)

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        inventory.setInventorySlotContents(index, stack)
    }

    override fun getInventoryStackLimit() = inventory.inventoryStackLimit

    override fun isUsableByPlayer(player: EntityPlayer) = inventory.isUsableByPlayer(player)

    override fun openInventory(player: EntityPlayer) {}

    override fun closeInventory(player: EntityPlayer) {}

    override fun isItemValidForSlot(index: Int, stack: ItemStack) = inventory.isItemValidForSlot(index, stack)

    override fun getField(id: Int) = inventory.getField(id)

    override fun setField(id: Int, value: Int) {
        inventory.setField(id, value)
    }

    override fun getFieldCount() = inventory.fieldCount

    override fun clear() {
        inventory.clear()
    }

    abstract override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer): Container

    abstract override fun getGuiID(): String
}