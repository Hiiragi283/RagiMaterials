package hiiragi283.material.block

import hiiragi283.api.block.HiiragiBlock
import hiiragi283.api.block.property.HiiragiProperty
import hiiragi283.api.item.HiiragiItemBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object BlockStoneCommon : HiiragiBlock(Material.ROCK, "stone") {

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, 15)

    init {
        blockHardness = 1.5f
        blockResistance = 10.0f
        defaultState = defaultState.withProperty(HiiragiProperty.TYPE16, 0)
        soundType = SoundType.STONE
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, HiiragiProperty.TYPE16)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(HiiragiProperty.TYPE16)

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "defaultState.withProperty(HiiragiProperty.TYPE16, meta % 16)",
            "hiiragi283.api.block.property.HiiragiProperty"
        )
    )
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(HiiragiProperty.TYPE16, meta % 16)

    override fun getStateForPlacement(
        world: World,
        pos: BlockPos,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        meta: Int,
        placer: EntityLivingBase,
        hand: EnumHand
    ): IBlockState = defaultState.withProperty(HiiragiProperty.TYPE16, placer.getHeldItem(hand).metadata % 16)

}