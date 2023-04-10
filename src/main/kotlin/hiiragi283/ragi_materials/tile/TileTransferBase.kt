package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.block.BlockTransferBase
import hiiragi283.ragi_materials.util.RagiFacing
import hiiragi283.ragi_materials.util.RagiResult
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability

abstract class TileTransferBase<T : Any>(type: Int) : TileBase(type), ITickable {

    abstract var storageFrom: T?
    abstract var storageTo: T?
    private var count = 0

    //    General    //

    private fun getFacing() = if (getState().block is BlockTransferBase<*>) getState().getValue(RagiFacing.HORIZONTAL) else EnumFacing.NORTH

    private fun getTileFrom() = world.getTileEntity(pos.offset(getFacing().opposite))

    private fun getMode() = if (getState().block is BlockTransferBase<*>) getState().getValue(BlockTransferBase.MODE) else BlockTransferBase.EnumTransferMode.NEAREST

    //    Capability    //

    override fun <R : Any?> getCapability(capability: Capability<R>, facing: EnumFacing?): R? {
        val tileFrom = getTileFrom()
        return if (tileFrom !== null && tileFrom !is TileTransferBase<*>) tileFrom.getCapability(capability, facing) else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        val tileFrom = getTileFrom()
        return if (tileFrom !== null && tileFrom !is TileTransferBase<*>) tileFrom.hasCapability(capability, facing) else false
    }

    //    ITileActivatable    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            //スニークしている場合，モードを切り変える
            //そうでない場合は接続を試みる
            if (player.isSneaking) {
                world.setBlockState(pos, getState().withProperty(BlockTransferBase.MODE, getMode().reverse()))
            } else {
                getConnection().also {
                    if (it) RagiResult.succeeded(this, player) else RagiResult.failed(this, player)
                }
            }
        }
        return true
    }

    //    ITickable    //

    override fun update() {
        if (count >= 20) {
            //送信元と送信先がともに存在する場合
            if (hasConnection()) doProcess()
            count = 0
        } else count++
    }

    abstract fun doProcess()

    //    Energy    //

    private fun hasConnection() = storageFrom !== null && storageTo !== null

    private fun getConnection(): Boolean {
        //送信元と送信先を初期化
        storageFrom = null
        storageTo = null
        //storageToを取得
        getTileFrom()?.let { storageFrom = it.getCapability(getCapabilityType(), getFacing()) }
        //storageFromを取得
        for (i in 1..16) {
            //modeの値によって検索順を反転させる
            val j = if (getMode() == BlockTransferBase.EnumTransferMode.NEAREST) i else 16 - i
            val posTo = pos.offset(getFacing(), j)
            val tileTo = world.getTileEntity(posTo)
            if (tileTo !== null) {
                val capability = tileTo.getCapability(getCapabilityType(), getFacing().opposite)
                if (capability !== null) {
                    storageTo = capability
                    break
                }
            }
        }
        return hasConnection()
    }

    abstract fun getCapabilityType(): Capability<T>

}