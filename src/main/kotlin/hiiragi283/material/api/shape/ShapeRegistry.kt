package hiiragi283.material.api.shape

import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.*
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe

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

    private val dustFake = shapeOf("dust", "@_dusts", 1.0)
    private val ingotFake = shapeOf("ingot", "@_ingots", 1.0)
    private val gemFake = shapeOf("gems", "@_gems", 1.0)

    //    Shapes - Block   //

    @JvmField
    val BLOCK_METAL = shapeOf("block", "@_blocks", 9.0)

    @JvmField
    val BLOCK_GEM = shapeOf(
        "block",
        "@_blocks",
        9.0,
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
        /*settings = {
            FabricBlockSettings.of(Material.AMETHYST)
                .requiresTool()
                .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                .strength(1.5f)
        },
        state = JState.state(
            JState.variant(
                JState.model(
                    hiiragiId("block/block_gem")
                )
            )
        ),
        type = ShapeType.BLOCK*/
    )

    @JvmField
    val ORE = shapeOf(
        "ore",
        "@_ores",
        1.0,
        model = JModel.model().parent(hiiragiId("block/ore_stone").toString()),
        /*state = JState.state(
            JState.variant(
                JState.model(
                    hiiragiId("block/ore_stone")
                )
            )
        ),
        type = ShapeType.BLOCK*/
    )

    @JvmField
    val ORE_BLOCK = shapeOf("raw_block", "raw_@_blocks", 9.0)

    //    Shapes - Item   //

    @JvmField
    val DUST = shapeOf(
        "dust",
        "@_dusts",
        1.0,
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
        "@_tiny_dusts",
        0.1,
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
        "@_gears",
        4.0,
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
    val GEM = shapeOf("gem", "@_gems", 1.0)

    @JvmField
    val INGOT = shapeOf(
        "ingot",
        "@_ingots",
        1.0,
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
        "@_nuggets",
        0.1,
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
        "@_plates",
        1.0,
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
    val RAW_ORE = shapeOf("raw_ore", "raw_@_ores", 1.0)

    @JvmField
    val ROD = shapeOf(
        "rod",
        "@_rods",
        0.5,
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