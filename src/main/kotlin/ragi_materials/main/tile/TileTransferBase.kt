package ragi_materials.main.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import ragi_materials.core.block.RagiProperty
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.RagiFacing
import ragi_materials.core.util.failed
import ragi_materials.core.util.succeeded
import ragi_materials.main.block.BlockTransferBase
import ragi_materials.main.block.EnumTransferMode

abstract class TileTransferBase<T : Any> : TileBase(), ITickable {

    private var tileFrom: TileEntity? = null
    private var tileTo: TileEntity? = null
    abstract var storageFrom: T?
    abstract var storageTo: T?
    private var count = 0

    //    General    //

    fun getFacing(): EnumFacing = if (getState().block is BlockTransferBase<*>) getState().getValue(RagiFacing.HORIZONTAL) else EnumFacing.NORTH

    fun getMode() = if (getState().block is BlockTransferBase<*>) getState().getValue(RagiProperty.MODE2) else EnumTransferMode.NEAREST

    //    Capability    //

    override fun <R : Any?> getCapability(capability: Capability<R>, facing: EnumFacing?): R? {
        return if (tileFrom !== null && tileFrom !is TileTransferBase<*>) tileFrom!!.getCapability(capability, facing) else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return if (tileFrom !== null && tileFrom !is TileTransferBase<*>) tileFrom!!.hasCapability(capability, facing) else false
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            //スニークしている場合，モードを切り変える
            //そうでない場合は接続を試みる
            if (player.isSneaking) {
                world.setBlockState(pos, getState().withProperty(RagiProperty.MODE2, getMode().reverse()))
            } else {
                getConnection()
                if (hasConnection()) succeeded(this, player) else failed(this, player)
            }
        }
        return true
    }

    //    ITickable    //

    override fun update() {
        if (!world.isRemote) {
            if (count >= 20) {
                //送信元と送信先がともに存在する場合
                if (hasConnection()) {
                    getConnection()
                    doProcess()
                }
                count = 0
            } else count++
        }
    }

    abstract fun doProcess()

    //    Energy    //

    private fun hasConnection() = tileFrom !== null && tileTo !== null && storageFrom !== null && storageTo !== null

    private fun getConnection() {
        //変数を初期化
        tileFrom = null
        tileTo = null
        storageFrom = null
        storageTo = null
        //tileFromを取得
        tileFrom = world.getTileEntity(pos.offset(getFacing().opposite))
        //tileToを取得
        for (i in 1..16) {
            //modeの値によって検索順を反転させる
            val j = if (getMode() == EnumTransferMode.NEAREST) i else 16 - i
            tileTo = world.getTileEntity(pos.offset(getFacing(), j))
            if (tileTo !== null && tileTo !is TileTransferBase<*>) break
        }
        //storageFromを取得
        tileFrom?.getCapability(getCapabilityType(), getFacing())?.let { storageFrom = it }
        //storageToを取得
        tileTo?.getCapability(getCapabilityType(), getFacing().opposite)?.let { storageTo = it }
    }

    abstract fun getCapabilityType(): Capability<T>

}