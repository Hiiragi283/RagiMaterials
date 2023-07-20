package hiiragi283.material.api.shape

import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.item.ForgeFileItem
import hiiragi283.material.common.item.ForgeHammerItem
import hiiragi283.material.common.util.*
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe

object ShapeRegistry {

    private val REGISTRY: HashMap<String, HiiragiShape> = hashMapOf()

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
    val BLOCK_METAL = shapeOf("block", "@_blocks", 9.0) {
        model = JModel.model().parent(hiiragiId("block/block_metal").toString())
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", ingotFake.getCommonTag(it)),
                    this.getResult(it)
                )
            )
        }
        state = JState.state(
            JState.variant(
                JState.model(
                    hiiragiId("block/block_metal")
                )
            )
        )
        type = HiiragiShape.Type.BLOCK
    }

    @JvmField
    val BLOCK_GEM = shapeOf("block", "@_blocks", 9.0) {
        model = JModel.model().parent(hiiragiId("block/block_gem").toString())
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", gemFake.getCommonTag(it)),
                    this.getResult(it)
                )
            )
        }
        state = JState.state(
            JState.variant(
                JState.model(
                    hiiragiId("block/block_gem")
                )
            )
        )
        type = HiiragiShape.Type.BLOCK
    }


    @JvmField
    val ORE = shapeOf("ore", "@_ores", 1.0) {
        type = HiiragiShape.Type.BLOCK
    }

    @JvmField
    val ORE_BLOCK = shapeOf("raw_block", "raw_@_blocks", 9.0) {
        type = HiiragiShape.Type.BLOCK
    }

    //    Shapes - Item   //

    @JvmField
    val DUST = shapeOf("dust", "@_dusts", 1.0) {
        model = itemModelLayered { layer0("minecraft:item/sugar") }
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", DUST_TINY.getCommonTag(it)),
                    this.getResult(it)
                )
            )
        }

    }

    @JvmField
    val DUST_TINY = shapeOf("dust_tiny", "@_tiny_dusts", 0.1) {
        model = itemModelLayered { layer0("ragi_materials:item/dust_tiny") }
        recipes = {
            mapOf(
                this.getId(it).append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(dustFake.getCommonTag(it)),
                    this.getResult(it, 9)
                )
            )
        }
    }

    @JvmField
    val GEAR = shapeOf("gear", "@_gears", 4.0) {
        model = itemModelLayered { layer0("ragi_materials:item/gear") }
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    JPattern.pattern(" A ", "ABA", " A "),
                    JKeys.keys()
                        .addTag("A", ingotFake.getCommonTag(it))
                        .addItem("B", ForgeHammerItem),
                    this.getResult(it)
                )
            )
        }
    }

    @JvmField
    val GEM = shapeOf("gem", "@_gems", 1.0)

    @JvmField
    val INGOT = shapeOf("ingot", "@_ingots", 1.0) {
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", NUGGET.getCommonTag(it)),
                    this.getResult(it)
                ),
                this.getId(it).append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(BLOCK_METAL.getCommonTag(it)),
                    this.getResult(it, 9)
                )
            )
        }
    }

    @JvmField
    val NUGGET = shapeOf("nugget", "@_nuggets", 0.1) {
        model = itemModelLayered { layer0("minecraft:item/iron_nugget") }
        recipes = {
            mapOf(
                this.getId(it).append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(ingotFake.getCommonTag(it)),
                    this.getResult(it, 9)
                )
            )
        }
    }

    @JvmField
    val PLATE = shapeOf("plate", "@_plates", 1.0) {
        model = itemModelLayered { layer0("ragi_materials:item/plate") }
        recipes = {
            mapOf(
                this.getId(it).append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients()
                        .addTag(ingotFake.getCommonTag(it))
                        .addItem(ForgeHammerItem),
                    this.getResult(it)
                )
            )

        }
    }

    @JvmField
    val RAW_ORE = shapeOf("raw_ore", "raw_@_ores", 1.0)

    @JvmField
    val ROD = shapeOf("rod", "@_rods", 0.5) {
        model = itemModelLayered { layer0("ragi_materials:item/rod") }
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    JPattern.pattern("AB", "A "),
                    JKeys.keys()
                        .addTag("A", ingotFake.getCommonTag(it))
                        .addItem("B", ForgeFileItem),
                    this.getResult(it, 4)
                )
            )
        }
    }

    fun init() {

        REGISTRY.clear()

        registerShape(BLOCK_METAL)
        //registerShape(BLOCK_GEM)
        //registerShape(ORE)
        //registerShape(ORE_BLOCK)

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