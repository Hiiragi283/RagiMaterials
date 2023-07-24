package hiiragi283.material.api.shape

import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.*
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks

object ShapeRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiShape> = linkedMapOf()

    @JvmStatic
    fun getShapes(): Collection<HiiragiShape> = REGISTRY.values

    @JvmStatic
    fun getShape(name: String) = REGISTRY.getOrDefault(name, HiiragiShape.EMPTY)

    @JvmStatic
    fun registerShape(shape: HiiragiShape) {

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
        model = JModel.model().parent(hiiragiId("block/block_metal").toString()),
        recipes = {
            mapOf(
                it.getId().append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", it.setShape(ingotFake).getTadId()),
                    it.getResult()
                )
            )
        },
        state = JState.state(JState.variant(JState.model(hiiragiId("block/block_metal")))),
        type = ShapeType.BLOCK
    )

    @JvmField
    val BLOCK_GEM = shapeOf(
        "block_gem",
        9.0,
        "@_blocks",
        blockSettings = AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK),
        model = JModel.model().parent(hiiragiId("block/block_gem").toString()),
        recipes = {
            mapOf(
                it.getId().append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", it.setShape(gemFake).getTadId()),
                    it.getResult()
                )
            )
        },
        state = JState.state(JState.variant(JState.model(hiiragiId("block/block_gem")))),
        type = ShapeType.BLOCK
    )

    @JvmField
    val ORE_STONE = shapeOf(
        "ore_stone",
        1.0,
        "@_ores",
        blockSettings = AbstractBlock.Settings.copy(Blocks.STONE),
        model = JModel.model().parent(hiiragiId("block/ore_stone").toString()),
        state = JState.state(JState.variant(JState.model(hiiragiId("block/ore_stone")))),
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
        model = JModel.model().parent(hiiragiId("block/ore_nether").toString()),
        state = JState.state(JState.variant(JState.model(hiiragiId("block/ore_nether")))),
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
        model = JModel.model().parent(hiiragiId("block/ore_end").toString()),
        state = JState.state(JState.variant(JState.model(hiiragiId("block/ore_end")))),
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
        model = JModel.model().parent(hiiragiId("block/ore_deep").toString()),
        state = JState.state(JState.variant(JState.model(hiiragiId("block/ore_deep")))),
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
        model = itemModelLayered { layer0("ragi_materials:item/dust") },
        recipes = {
            mapOf(
                it.getId().append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", it.setShape(DUST_TINY).getTadId()),
                    it.getResult()
                )
            )
        }
    )

    @JvmField
    val DUST_TINY = shapeOf(
        "dust_tiny",
        0.1,
        "@_tiny_dusts",
        model = itemModelLayered { layer0("ragi_materials:item/dust_tiny") },
        recipes = {
            mapOf(
                it.getId().append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(it.setShape(dustFake).getTadId()),
                    it.getResult(9)
                )
            )
        }
    )

    @JvmField
    val GEAR = shapeOf(
        "gear",
        4.0,
        "@_gears",
        model = itemModelLayered { layer0("ragi_materials:item/gear") },
        recipes = {
            mapOf(
                it.getId().append("_shaped") to JRecipe.shaped(
                    JPattern.pattern(" A ", "ABA", " A "),
                    JKeys.keys()
                        .addTag("A", it.setShape(ingotFake).getTadId())
                        .addTag("B", commonId("hammers")),
                    it.getResult()
                )
            )
        }
    )

    @JvmField
    val GEM = shapeOf("gem", 1.0, "@_gems")

    @JvmField
    val INGOT = shapeOf(
        "ingot",
        1.0,
        "@_ingots",
        recipes = {
            mapOf(
                it.getId().append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", it.setShape(NUGGET).getTadId()),
                    it.getResult()
                ),
                it.getId().append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(it.setShape(BLOCK_METAL).getTadId()),
                    it.getResult(9)
                )
            )
        }
    )

    @JvmField
    val NUGGET = shapeOf(
        "nugget",
        0.1,
        "@_nuggets",
        model = itemModelLayered { layer0("minecraft:item/iron_nugget") },
        recipes = {
            mapOf(
                it.getId().append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(it.setShape(ingotFake).getTadId()),
                    it.getResult(9)
                )
            )
        }
    )

    @JvmField
    val PLATE = shapeOf(
        "plate",
        1.0,
        "@_plates",
        model = itemModelLayered { layer0("ragi_materials:item/plate") },
        recipes = {
            mapOf(
                it.getId().append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients()
                        .addTag(it.setShape(ingotFake).getTadId())
                        .addTag(commonId("hammers")),
                    it.getResult()
                )
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
        model = itemModelLayered { layer0("ragi_materials:item/rod") },
        recipes = {
            mapOf(
                it.getId().append("_shaped") to JRecipe.shaped(
                    JPattern.pattern("AB", "A "),
                    JKeys.keys()
                        .addTag("A", it.setShape(ingotFake).getTadId())
                        .addTag("B", commonId("files")),
                    it.getResult(4)
                )
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
        //registerShape(GEM)
        registerShape(INGOT)
        registerShape(NUGGET)
        registerShape(PLATE)
        //registerShape(RAW_ORE)
        registerShape(ROD)
    }

}