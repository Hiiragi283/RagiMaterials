package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.block.property.HiiragiProperty
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World

object BlockCasing : HiiragiBlock(Material.IRON, "casing") {

    //    Material    //

    private val MAP: Map<Int, HiiragiMaterial> = mapOf(
        0 to MaterialCommon.STONE,
        1 to MaterialCommon.STEEL,
        2 to MaterialCommon.STAINLESS_STEEL,
        3 to MaterialElements.ALUMINIUM,
        4 to MaterialElements.TITANIUM,
        5 to MaterialCommon.TUNGSTEN_STEEL,
        6 to MaterialElements.PLATINUM
    )

    @JvmStatic
    fun getMaterialFromMeta(meta: Int): HiiragiMaterial = MAP[meta % MAP.size]!!

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

    //    HiiragiEntry    //

    override fun registerRecipe() {
        //Stone Casing
        CraftingBuilder(ItemStack(this, 1, 0))
            .setPattern("ABA", "ACA", "DED")
            .setIngredient('A', "cobblestone")
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(0)))
            .setIngredient('D', ItemStack(Blocks.STONE_SLAB))
            .setIngredient('E', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(0)))
            .build()
        //Steel Casing
        CraftingBuilder(ItemStack(this, 1, 1))
            .setPattern("ABA", "ACA", "ADA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(1)))
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(1)))
            .setIngredient('D', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(1)))
            .build()
        //Stainless Steel Casing
        CraftingBuilder(ItemStack(this, 1, 2))
            .setPattern("ABA", "ACA", "ADA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(2)))
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(2)))
            .setIngredient('D', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(2)))
            .build()
        //Aluminum Casing
        CraftingBuilder(ItemStack(this, 1, 3))
            .setPattern("ABA", "ACA", "ADA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(3)))
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(3)))
            .setIngredient('D', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(3)))
            .build()
        //Titanium Casing
        CraftingBuilder(ItemStack(this, 1, 4))
            .setPattern("ABA", "ACA", "ADA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(4)))
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(4)))
            .setIngredient('D', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(4)))
            .build()
        //Tungsten Steel Casing
        CraftingBuilder(ItemStack(this, 1, 5))
            .setPattern("ABA", "ACA", "ADA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(5)))
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(5)))
            .setIngredient('D', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(5)))
            .build()
        //Iridescent Casing
        CraftingBuilder(ItemStack(this, 1, 6))
            .setPattern("ABA", "ACA", "ADA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(getMaterialFromMeta(6)))
            .setIngredient('B', ItemStack(Blocks.STONE_BUTTON))
            .setIngredient('C', HiiragiShapes.SCAFFOLDING.getOreDict(BlockScaffolding.getMaterialFromMeta(6)))
            .setIngredient('D', HiiragiShapes.GEAR.getOreDict(getMaterialFromMeta(6)))
            .build()
    }

    init {
        blockHardness = 5.0f
        blockResistance = 5.0f
        defaultState = defaultState.withProperty(HiiragiProperty.TYPE16, 0)
        setHarvestLevel("pickaxe", 0, getStateFromMeta(0))
        setHarvestLevel("pickaxe", 1, getStateFromMeta(1))
        setHarvestLevel("pickaxe", 2, getStateFromMeta(2))
        setHarvestLevel("pickaxe", 1, getStateFromMeta(3))
        setHarvestLevel("pickaxe", 2, getStateFromMeta(4))
        setHarvestLevel("pickaxe", 3, getStateFromMeta(5))
        setHarvestLevel("pickaxe", 3, getStateFromMeta(6))
    }

}