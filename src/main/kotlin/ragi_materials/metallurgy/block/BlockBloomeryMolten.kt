package ragi_materials.metallurgy.block

import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import ragi_materials.core.block.BlockBase
import ragi_materials.core.block.property.EnumBloomeryType
import ragi_materials.core.block.property.RagiProperty
import ragi_materials.core.material.IMaterialBlock
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.util.ColorUtil.mixColor
import ragi_materials.core.util.RagiColor
import ragi_materials.core.util.getPart
import java.util.*

class BlockBloomeryMolten : BlockBase("bloomery_molten", Material.IRON, 2), IMaterialBlock {

    override val itemBlock = null

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        defaultState = blockState.baseState.withProperty(RagiProperty.BLOOMERY, EnumBloomeryType.EMPTY)
        setHarvestLevel("pickaxe", 0)
        soundType = SoundType.STONE
    }

    //    General    //

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        //1 <= 1 + (0 ~ 8) + fortune <= 9 に制限
        val count = 9.coerceAtMost(1 + Random().nextInt(8) + fortune)
        val material = state.getValue(RagiProperty.BLOOMERY).material
        drops.add(getPart(PartRegistry.INGOT, material, count))
    }

    override fun quantityDropped(random: Random) = 0

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, RagiProperty.BLOOMERY)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(RagiProperty.BLOOMERY).index

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(RagiProperty.BLOOMERY, EnumBloomeryType.getType(meta))", "ragi_materials.core.block.property.RagiProperty", "ragi_materials.core.block.property.EnumBloomeryType"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(RagiProperty.BLOOMERY, EnumBloomeryType.getType(meta))

    //    IMaterialBlock    //

    override fun getColor(world: IBlockAccess, pos: BlockPos, state: IBlockState, tintIndex: Int) = mixColor(getMaterialBlock(world, pos, state).color to 1, RagiColor.BLACK to 3)

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): RagiMaterial = state.getValue(RagiProperty.BLOOMERY).material

}