package hiiragi283.material.block

import hiiragi283.material.RMReference
import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.block.property.HiiragiProperty
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.setModelSame
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
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

object BlockScaffolding : HiiragiBlock(Material.IRON, "scaffolding") {

    //    Material    //

    private val MAP: Map<Int, HiiragiMaterial> = mapOf(
        0 to MaterialCommon.WOOD,
        1 to MaterialElements.IRON,
        2 to MaterialCommon.STEEL,
        3 to MaterialCommon.PLASTIC,
        4 to MaterialCommon.STAINLESS_STEEL,
        5 to MaterialElements.TUNGSTEN,
        6 to MaterialElements.IRIDIUM
    )

    @JvmStatic
    fun getMaterialFromMeta(meta: Int): HiiragiMaterial = MAP[meta % MAP.size]!!

    @JvmStatic
    fun getMetaFromMaterial(material: HiiragiMaterial): Int =
        MAP.entries.associateBy({ it.value }) { it.key }.getOrDefault(material, 0)

    //    General    //

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, MAP.size - 1)

    override fun getPickBlock(
        state: IBlockState,
        target: RayTraceResult,
        world: World,
        pos: BlockPos,
        player: EntityPlayer
    ): ItemStack {
        return ItemStack(this, 1, getMetaFromState(state))
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, HiiragiProperty.TYPE16)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(HiiragiProperty.TYPE16) % MAP.size

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState =
        defaultState.withProperty(HiiragiProperty.TYPE16, meta % MAP.size)

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
    ): IBlockState = getStateFromMeta(placer.getHeldItem(hand).metadata)

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    MaterialBlock    //

    override fun registerOreDict() {
        (0 until MAP.size).forEach {
            hiiragi283.material.util.registerOreDict(
                HiiragiShapes.SCAFFOLDING.getOreDict(getMaterialFromMeta(it)),
                this,
                it
            )
        }
    }

    override fun registerRecipe() {
        (0 until MAP.size).forEach {
            CraftingBuilder(ItemStack(this, 6, it))
                .setPattern("AAA", " B ", "B B")
                .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(it)))
                .setIngredient('B', HiiragiShapes.STICK.getOreDict(getMaterialFromMeta(it)))
                .build()
        }
        CraftingBuilder("${RMReference.MOD_ID}:scaffolding_0_alt", ItemStack(this, 6, 0))
            .setPattern("AAA", " B ", "B B")
            .setIngredient('A', HiiragiShapes.PLANK.getOreDict(MaterialCommon.WOOD))
            .setIngredient('B', HiiragiShapes.STICK.getOreDict(getMaterialFromMeta(0)))
            .build()
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            getMaterialFromMeta(getMetaFromState(state)).color
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            getMaterialFromMeta(stack.metadata).color
        }, this)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        this.setModelSame()
    }

    init {
        blockHardness = 5.0f
        blockResistance = 5.0f
        defaultState = defaultState.withProperty(HiiragiProperty.TYPE16, 0)
        setHarvestLevel("axe", 0, getStateFromMeta(0))
        setHarvestLevel("pickaxe", 1, getStateFromMeta(1))
        setHarvestLevel("pickaxe", 1, getStateFromMeta(2))
        setHarvestLevel("pickaxe", 0, getStateFromMeta(3))
        setHarvestLevel("pickaxe", 2, getStateFromMeta(4))
        setHarvestLevel("pickaxe", 3, getStateFromMeta(5))
        setHarvestLevel("pickaxe", 3, getStateFromMeta(6))
    }

}