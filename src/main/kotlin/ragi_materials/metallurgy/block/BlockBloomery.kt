package ragi_materials.metallurgy.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemFlintAndSteel
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiRegistry
import ragi_materials.core.block.BlockBase
import ragi_materials.core.block.property.EnumBloomeryType
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.item.ItemBlockBase
import ragi_materials.core.item.ItemMaterialOre
import java.util.*

class BlockBloomery : BlockBase("bloomery", Material.ROCK, 2) {

    override val itemBlock = ItemBlockBase(this)

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(RagiProperty.BLOOMERY, EnumBloomeryType.EMPTY)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(world: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return when (face) {
            //下 -> SOLID
            EnumFacing.DOWN -> BlockFaceShape.SOLID
            //それ以外 -> UNDEFINED
            else -> BlockFaceShape.UNDEFINED
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    override fun damageDropped(state: IBlockState) = getMetaFromState(state)

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiProperty.BLOOMERY)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(RagiProperty.BLOOMERY).index

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(RagiProperty.BLOOMERY, EnumBloomeryType.getType(meta))", "ragi_materials.core.block.property.RagiProperty", "ragi_materials.core.block.property.EnumBloomeryType"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(RagiProperty.BLOOMERY, EnumBloomeryType.getType(meta))

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val stack = player.getHeldItem(hand)
        //プレイヤーが持っているアイテムが鉱石の場合
        return when (val item = stack.item) {
            is ItemMaterialOre -> {
                val type = EnumBloomeryType.getType(item.getMaterial(stack))
                //中身を更新
                if (type != EnumBloomeryType.EMPTY) {
                    world.setBlockState(pos, state.withProperty(RagiProperty.BLOOMERY, type), 2)
                    //鉱石を1つ減らす
                    stack.shrink(1)
                }
                true
            }
            //火打石の場合
            is ItemFlintAndSteel -> {
                //1分後に精錬が完了するように設定
                world.scheduleUpdate(pos, this, 20 * 60)
                //耐久地を1つ減らす
                stack.itemDamage += 1
                true
            }
            else -> false
        }
    }

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        world.destroyBlock(pos, false)
        world.setBlockState(pos, RagiRegistry.BlockBloomeryMolten.defaultState.withProperty(RagiProperty.BLOOMERY, state.getValue(RagiProperty.BLOOMERY)))
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun randomDisplayTick(state: IBlockState, world: World, pos: BlockPos, rand: Random) {
        world.spawnParticle(EnumParticleTypes.FLAME, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 0.0, 0.0, 0.0)
    }

}