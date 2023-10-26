package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiProperties
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.setModelSame
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

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

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack = itemBlock.itemStack(state.getValue(HiiragiProperties.TYPE16))

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

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    HiiragiEntry    //

    override fun registerModel() {
        this.setModelSame()
    }

}