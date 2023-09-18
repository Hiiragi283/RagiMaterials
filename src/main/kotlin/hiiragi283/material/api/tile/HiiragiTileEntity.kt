package hiiragi283.material.api.tile

import hiiragi283.material.HiiragiGuiHandler
import hiiragi283.material.RagiMaterials
import hiiragi283.material.network.HiiragiMessage
import hiiragi283.material.network.HiiragiNetworkWrapper
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

abstract class HiiragiTileEntity : TileEntity() {

    //    General    //

    fun getState(): IBlockState = if (hasWorld()) world.getBlockState(pos) else Blocks.AIR.defaultState

    fun openGui(player: EntityPlayer, world: World = this.world, pos: BlockPos = this.pos) {
        if (!world.isRemote) player.openGui(
            RagiMaterials.Instance,
            HiiragiGuiHandler.TILE_ENTITY,
            world,
            pos.x,
            pos.y,
            pos.z
        )
    }

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound = writeToNBT(NBTTagCompound()) //オーバーライドしないと正常に更新されない

    //    Packet    //

    override fun getUpdatePacket(): SPacketUpdateTileEntity = SPacketUpdateTileEntity(pos, 0, updateTag) //NBTタグの情報を送る

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    /**
     * Thanks to defeatedcrow!
     * [: Source](https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93)
     */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean =
        oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue

    fun syncData(vararg messages: IMessage = arrayOf(HiiragiMessage.Client(pos, updateTag))) {
        messages.forEach { HiiragiNetworkWrapper.sendToAll(it) }
    }

    //    CommonEvent    //

    open fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean = false

    open fun onTilePlaced(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
    }

    fun readNBTFromStack(stack: ItemStack) {
        //ItemStackに保存されたNBTタグを取得し，nullでない場合
        stack.getSubCompound("BlockEntityTag")?.let { tag: NBTTagCompound ->
            //TileEntityからNBTタグを取得
            val tagTile: NBTTagCompound = updateTag
            //tagTileにtagStackを合併
            tagTile.merge(tag)
            //座標を修正する
            tagTile.setInteger("x", this.pos.x)
            tagTile.setInteger("y", this.pos.y)
            tagTile.setInteger("z", this.pos.z)
            //座標が異なる場合
            if (tagTile != updateTag.copy()) {
                readFromNBT(tagTile)
                markDirty()
            }
        }
    }

    open fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {}

    abstract class Tickable(var maxCount: Int) : HiiragiTileEntity(), ITickable {

        private var currentCount = 0

        fun getProgress(): Double = currentCount.toDouble() / maxCount.toDouble()

        override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
            compound.setInteger("current", currentCount)
            compound.setInteger("max", maxCount)
            return super.writeToNBT(compound)
        }

        override fun readFromNBT(compound: NBTTagCompound) {
            currentCount = compound.getInteger("current")
            maxCount = compound.getInteger("max")
            super.readFromNBT(compound)
        }

        //    ITickable    //

        override fun update() {
            onUpdateTick()
            if (currentCount >= maxCount) {
                onUpdate()
                if (!world.isRemote) {
                    onUpdateServer()
                } else {
                    onUpdateClient()
                }
                currentCount = 0
            } else currentCount++
        }

        //1tickごとにサーバー側とクライアント側で同時に実行するメソッド
        open fun onUpdateTick() {}

        //20tickごとにサーバー側とクライアント側で同時に実行するメソッド
        open fun onUpdate() {}

        //20tickごとにサーバー側で実行するメソッド
        open fun onUpdateServer() {}

        //20tickごとにクライアント側で実行するメソッド
        open fun onUpdateClient() {}

    }

}