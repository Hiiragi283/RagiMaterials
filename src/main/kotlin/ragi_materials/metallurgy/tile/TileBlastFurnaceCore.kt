package ragi_materials.metallurgy.tile

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.failed
import ragi_materials.core.util.succeeded
import ragi_materials.metallurgy.block.BlockBlastFurnaceInterface

class TileBlastFurnaceCore : TileBase(), ITickable {

    private var isValid = false
    private var front = EnumFacing.NORTH
    private var count = 0

    //    General    //

    fun getPosInterface(): BlockPos = pos.offset(front.opposite).up()

    fun getStateInterface(): IBlockState = world.getBlockState(getPosInterface())

    private fun getTileInterface() = world.getTileEntity(getPosInterface())

    private fun isTileInterface(tile: TileEntity?) = tile !== null && tile is TileBlastFurnaceInterface

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        val tile = getTileInterface()
        return if (isTileInterface(tile) && hasCapability(capability, facing)) tile!!.getCapability(capability, facing) else super.getCapability(capability, facing)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        val tile = getTileInterface()
        return if (isTileInterface(tile)) tile!!.hasCapability(capability, facing) else super.hasCapability(capability, facing)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        return if (!world.isRemote) {
            initFront() //向きを再保存
            isValid = isValidStructure(world, pos) //マルチブロックの構造が適切か判定
            if (isValid) {
                if (getStateInterface().block !is BlockBlastFurnaceInterface && world.isAirBlock(getPosInterface())) world.setBlockState(getPosInterface(), RagiRegistry.BlockBlastFurnaceInterface.defaultState, 2)
                player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, getPosInterface().x, getPosInterface().y, getPosInterface().z)
                succeeded(this, player)
            } else failed(this, player)
            true
        } else false
    }

    private fun isValidStructure(world: World, pos: BlockPos): Boolean {
        var result = 0
        val opposite = front.opposite
        val pos0 = pos.offset(opposite)
        //底面のチェック
        val pos1 = pos0.down()
        if (isValidWall(world, pos1)) result++
        //背面のチェック
        val pos2 = pos0.offset(opposite)
        if (isValidWall(world, pos2)) result++
        if (isValidWall(world, pos2.up())) result++
        //正面右側のチェック
        val pos3 = pos0.offset(front.rotateY())
        if (isValidWall(world, pos3)) result++
        if (isValidWall(world, pos3.up())) result++
        //正面左側のチェック
        val pos4 = pos0.offset(front.rotateYCCW())
        if (isValidWall(world, pos4)) result++
        if (isValidWall(world, pos4.up())) result++
        //真上のチェック
        val pos5 = pos.up()
        if (isValidWall(world, pos5)) result++
        //中央のチェック
        if (world.isAirBlock(pos0)) result++
        if (world.isAirBlock(pos0.up()) || world.getBlockState(pos0.up()).block is BlockBlastFurnaceInterface) result++
        return result == 10
    }

    private fun isValidWall(world: World, pos: BlockPos): Boolean = world.getBlockState(pos).material == Material.ROCK

    override fun onTilePlaced(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        initFront()
    }

    private fun initFront() {
        front = getState().getValue(RagiProperty.HORIZONTAL) //タイルエンティティに向きを保存させる
    }

    //    ITickable    //

    override fun update() {
        //10秒ごとに実行する
        if (count > 200) {
            if (!world.isRemote) {
                //構造体が有効な場合
                if (isValidStructure(world, pos)) {
                    //Interfaceを取得
                    val tile = getTileInterface()
                    if (tile !== null && tile is TileBlastFurnaceInterface) tile.doProcess()
                }
            }
            count = 0
        } else count++
    }
}