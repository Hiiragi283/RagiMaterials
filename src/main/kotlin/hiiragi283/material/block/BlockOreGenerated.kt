package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiProperties
import hiiragi283.material.init.materials.MaterialCommons
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

@Suppress("OVERRIDE_DEPRECATION")
object BlockOreGenerated : HiiragiBlock(Material.ROCK, "ore_generated") {

    @JvmField
    val MATERIAL_MAP: Map<Int, List<HiiragiMaterial>> = mapOf(
        0 to listOf(MaterialCommons.MAGNETITE, MaterialCommons.PYROLUSITE, MaterialCommons.CHROMITE),
        1 to listOf(MaterialCommons.HEMATITE, MaterialCommons.BAUXITE, MaterialCommons.RUTILE),
        2 to listOf(MaterialCommons.MALACHITE, MaterialCommons.GARNIERITE, MaterialCommons.COBALTITE),
        3 to listOf(MaterialCommons.CHALCOCITE, MaterialCommons.PYRITE, MaterialCommons.CINNABAR),
        4 to listOf(MaterialCommons.CASSITERITE, MaterialCommons.GALENA, MaterialCommons.ARGENTITE),
        5 to listOf(MaterialCommons.SPHALERITE, MaterialCommons.BISMUTHINITE, MaterialCommons.MOLYBDENITE)
    )

    @JvmStatic
    fun getMaterialsFromMeta(index: Int): List<HiiragiMaterial> = MATERIAL_MAP[index % MATERIAL_MAP.size]!!

    @JvmStatic
    fun getPrimalMaterial(index: Int): HiiragiMaterial? = getMaterialsFromMeta(index).getOrNull(0)

    init {
        defaultState = blockState.baseState.withProperty(HiiragiProperties.TYPE16, 0)
    }

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, HiiragiProperties.TYPE16.allowedValues.size - 1)

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, HiiragiProperties.TYPE16)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(HiiragiProperties.TYPE16)

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
    ): IBlockState = defaultState.withProperty(HiiragiProperties.TYPE16, placer.getHeldItem(hand).metadata % 16)

    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(HiiragiProperties.TYPE16, meta % 16)

    //    HiiragiEntry    //

    override fun getBlockColor(): IBlockColor =
        IBlockColor { state: IBlockState, _: IBlockAccess?, _: BlockPos?, _: Int ->
            getPrimalMaterial(state.getValue(HiiragiProperties.TYPE16))?.color ?: -1
        }

    override fun getItemColor(): IItemColor = IItemColor { stack: ItemStack, _: Int ->
        getPrimalMaterial(stack.metadata)?.color ?: -1
    }

}