package hiiragi283.material.api.shape

import hiiragi283.material.common.RMTags
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.*
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.util.registry.Registry
import pers.solid.brrp.v1.model.ModelJsonBuilder

object ShapeRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiShape> = linkedMapOf()
    private var isLocked: Boolean = false

    @JvmStatic
    fun getShapes(): Collection<HiiragiShape> = REGISTRY.values

    @JvmStatic
    fun getShape(name: String) = REGISTRY.getOrDefault(name, HiiragiShape.EMPTY)

    @JvmStatic
    fun registerShape(shape: HiiragiShape) {

        //ロックされている場合はパス
        if (isLocked) {
            RagiMaterials.LOGGER.error("ShapeRegistry is already locked!")
            RagiMaterials.LOGGER.error("Shapes should be registered at \"ragi_materials\" entrypoint")
            return
        }

        //EMPTYを渡された場合はパス
        if (shape == HiiragiShape.EMPTY) return
        val name = shape.name

        //名前が空の場合はパス
        if (name.isEmpty()) {
            RagiMaterials.LOGGER.warn("The name of $shape is empty!")
            return
        }

        val resultName = REGISTRY[name]
        //同じ名前で登録されていた場合，登録せずに警告を表示する
        if (resultName !== null) {
            RagiMaterials.LOGGER.warn("The name of $shape was already registered by $resultName!")
            return
        }

        //重複しなかった場合のみ登録を行う
        REGISTRY[name] = shape
    }

    //    Shapes    //

    private val dustFake = shapeOf("dust", 1.0, "@_dusts")
    private val ingotFake = shapeOf("ingot", 1.0, "@_ingots")
    private val gemFake = shapeOf("gems", 1.0, "@_gems")

    //    Shapes - Block   //

    @JvmField
    val BLOCK_METAL = shapeOf(
        "block_metal",
        9.0,
        "@_blocks",
        blockSettings = AbstractBlock.Settings.copy(Blocks.IRON_BLOCK),
        model = ModelJsonBuilder().parent(hiiragiId("block/block_metal")),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shaped") to ShapedRecipeJsonBuilder.create(it)
                    .get3x3('A')
                    .input('A', it.asPart().setShape(ingotFake).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(ingotFake, it.asPart().material)
            )
        },
        state = hiiragiId("block/block_metal"),
        type = ShapeType.BLOCK
    )

    @JvmField
    val BLOCK_GEM = shapeOf(
        "block_gem",
        9.0,
        "@_blocks",
        blockSettings = AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK),
        model = ModelJsonBuilder().parent(hiiragiId("block/block_gem")),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shaped") to ShapedRecipeJsonBuilder.create(it)
                    .get3x3('A')
                    .input('A', it.asPart().setShape(gemFake).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(gemFake, it.asPart().material)
            )
        },
        state = hiiragiId("block/block_gem"),
        type = ShapeType.BLOCK
    )

    @JvmField
    val ORE_STONE = shapeOf(
        "ore_stone",
        1.0,
        "@_ores",
        blockSettings = AbstractBlock.Settings.copy(Blocks.STONE),
        model = ModelJsonBuilder().parent(hiiragiId("block/ore_stone")),
        state = hiiragiId("block/ore_stone"),
        type = ShapeType.BLOCK
    )

    @JvmField
    val ORE_NETHER = shapeOf(
        "ore_nether",
        1.0,
        "nether_@_ore",
        "@_ores",
        blockSettings = AbstractBlock.Settings.copy(Blocks.NETHERRACK)
            .hardness(3.0f)
            .nonOpaque()
            .resistance(6.0f),
        model = ModelJsonBuilder().parent(hiiragiId("block/ore_nether")),
        state = hiiragiId("block/ore_nether"),
        type = ShapeType.BLOCK
    )

    @JvmField
    val ORE_END = shapeOf(
        "ore_end",
        1.0,
        "end_@_ore",
        "@_ores",
        blockSettings = AbstractBlock.Settings.copy(Blocks.END_STONE)
            .hardness(3.0f)
            .nonOpaque()
            .resistance(6.0f),
        model = ModelJsonBuilder().parent(hiiragiId("block/ore_end")),
        state = hiiragiId("block/ore_end"),
        type = ShapeType.BLOCK
    )

    @JvmField
    val ORE_DEEP = shapeOf(
        "ore_deep",
        1.0,
        "deep_@_ore",
        "@_ores",
        blockSettings = AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
            .hardness(3.0f)
            .nonOpaque()
            .resistance(6.0f),
        model = ModelJsonBuilder().parent(hiiragiId("block/ore_deep")),
        state = hiiragiId("block/ore_deep"),
        type = ShapeType.BLOCK
    )

    @JvmField
    val BLOCK_RAW_ORE = shapeOf(
        "raw_block",
        9.0,
        "raw_@_blocks",
        type = ShapeType.BLOCK
    )

    //    Shapes - Item   //

    @JvmField
    val DUST = shapeOf(
        "dust",
        1.0,
        "@_dusts",
        model = ModelUtil.createSimple(hiiragiId("item/dust")),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shaped") to ShapedRecipeJsonBuilder.create(it)
                    .get3x3('A')
                    .input('A', it.asPart().setShape(DUST_TINY).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(DUST_TINY, it.asPart().material)
            )
        }
    )

    @JvmField
    val DUST_TINY = shapeOf(
        "dust_tiny",
        0.1,
        "@_tiny_dusts",
        model = ModelUtil.createSimple(hiiragiId("item/dust_tiny")),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shapeless") to ShapelessRecipeJsonBuilder.create(it, 9)
                    .input(it.asPart().setShape(dustFake).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(dustFake, it.asPart().material)
            )
        }
    )

    @JvmField
    val GEAR = shapeOf(
        "gear",
        4.0,
        "@_gears",
        model = ModelUtil.createSimple(hiiragiId("item/gear")),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shaped") to ShapedRecipeJsonBuilder.create(it)
                    .pattern(" A ")
                    .pattern("ABA")
                    .pattern(" A ")
                    .input('A', it.asPart().setShape(ingotFake).getTagKey(Registry.ITEM_KEY))
                    .input('B', RMTags.HAMMERS)
                    .criterionFromMaterial(ingotFake, it.asPart().material)
            )
        }
    )

    @JvmField
    val GEM = shapeOf(
        "gem",
        1.0,
        "@_gems",
        model = ModelUtil.createSimple("item/quartz"),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shapeless") to ShapelessRecipeJsonBuilder.create(it, 9)
                    .input(it.asPart().setShape(BLOCK_GEM).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(BLOCK_GEM, it.asPart().material)
            )
        }
    )

    @JvmField
    val INGOT = shapeOf(
        "ingot",
        1.0,
        "@_ingots",
        recipes = {
            mapOf(
                it.asPart().getId().append("_shaped") to ShapedRecipeJsonBuilder.create(it)
                    .get3x3('A')
                    .input('A', it.asPart().setShape(NUGGET).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(NUGGET, it.asPart().material),
                it.asPart().getId().append("_shapeless") to ShapelessRecipeJsonBuilder.create(it, 9)
                    .input(it.asPart().setShape(BLOCK_METAL).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(BLOCK_METAL, it.asPart().material)
            )
        }
    )

    @JvmField
    val NUGGET = shapeOf(
        "nugget",
        0.1,
        "@_nuggets",
        model = ModelUtil.createSimple("item/iron_nugget"),
        recipes = {
            mapOf(
                it.asPart().getId().append("_shapeless") to ShapelessRecipeJsonBuilder.create(it, 9)
                    .input(it.asPart().setShape(ingotFake).getTagKey(Registry.ITEM_KEY))
                    .criterionFromMaterial(ingotFake, it.asPart().material)
            )
        }
    )

    @JvmField
    val PLATE = shapeOf(
        "plate",
        1.0,
        "@_plates",
        model = ModelUtil.createSimple(hiiragiId("item/plate")),
        recipes = {
            val material = it.asPart().material
            val base = if (material.isGem()) gemFake else ingotFake
            mapOf(
                it.asPart().getId().append("_shapeless") to ShapelessRecipeJsonBuilder.create(it)
                    .input(it.asPart().setShape(base).getTagKey(Registry.ITEM_KEY))
                    .input(RMTags.HAMMERS)
                    .criterionFromMaterial(base, material)
            )
        }
    )

    @JvmField
    val RAW_ORE = shapeOf("raw_ore", 1.0, "raw_@_ores")

    @JvmField
    val ROD = shapeOf(
        "rod",
        0.5,
        "@_rods",
        model = ModelUtil.createSimple(hiiragiId("item/rod")),
        recipes = {
            val material = it.asPart().material
            val base = if (material.isGem()) gemFake else ingotFake
            mapOf(
                it.asPart().getId().append("_shaped") to ShapedRecipeJsonBuilder.create(it, 4)
                    .pattern("AB")
                    .pattern("A ")
                    .input('A', it.asPart().setShape(base).getTagKey(Registry.ITEM_KEY))
                    .input('B', RMTags.FILES)
                    .criterionFromMaterial(base, material)
            )
        }
    )

    fun init() {

        REGISTRY.clear()

        registerShape(BLOCK_METAL)
        registerShape(ORE_STONE)
        registerShape(ORE_NETHER)
        registerShape(ORE_END)
        registerShape(ORE_DEEP)

        registerShape(DUST)
        registerShape(DUST_TINY)
        registerShape(GEAR)
        registerShape(GEM)
        registerShape(INGOT)
        registerShape(NUGGET)
        registerShape(PLATE)
        //registerShape(RAW_ORE)
        registerShape(ROD)
    }

    fun lock() {
        isLocked = true
    }

}