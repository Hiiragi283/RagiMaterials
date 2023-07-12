package hiiragi283.material.api.shape

import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.*
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JKeys
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

    private val ingotFake = shapeOf("ingot", "@_ingots", 1.0)
    private val gemFake = shapeOf("gems", "@_gems", 1.0)

    //    Shapes - Block   //

    @JvmField
    val BLOCK_METAL = shapeOf("block", "@_blocks", 9.0) {
        model = JModel.model().parent(hiiragiId("item/metal_block").toString())
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", ingotFake.getTag(it).toString()),
                    this.getResult(it)
                )
            )
        }
        state = JState.state(
            JState.variant(
                JState.model(
                    hiiragiId("block/metal_block")
                )
            )
        )
        type = HiiragiShape.Type.BLOCK
    }

    @JvmField
    val BLOCK_GEM = shapeOf("block", "@_blocks", 9.0) {
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", gemFake.getTag(it).toString()),
                    this.getResult(it)
                )
            )
        }
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
    }

    @JvmField
    val DUST_TINY = shapeOf("dust_tiny", "@_tiny_dusts", 0.1) {
        model = itemModelLayered { layer0("minecraft:item/sugar") }
    }

    @JvmField
    val GEAR = shapeOf("gears", "@_gears", 4.0) {
        model = itemModelLayered { layer0("ragi_materials:item/gear") }
    }

    @JvmField
    val GEM = shapeOf("gem", "@_gems", 1.0)

    @JvmField
    val INGOT = shapeOf("ingot", "@_ingots", 1.0) {
        recipes = {
            mapOf(
                this.getId(it).append("_shaped") to JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", NUGGET.getTag(it).toString()),
                    this.getResult(it)
                ),
                this.getId(it).append("_shapeless") to JRecipe.shapeless(
                    JIngredients.ingredients().addTag(BLOCK_METAL.getTag(it).toString()),
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
                    JIngredients.ingredients().addTag(ingotFake.getTag(it).toString()),
                    this.getResult(it, 9)
                )
            )
        }
    }

    @JvmField
    val PLATE = shapeOf("plate", "@_plates", 1.0) {
        model = itemModelLayered { layer0("ragi_materials:item/plate") }
    }

    @JvmField
    val RAW_ORE = shapeOf("raw_ore", "raw_@_ores", 1.0)

    @JvmField
    val ROD = shapeOf("rod", "@_rods", 0.5) {
        model = itemModelLayered { layer0("ragi_materials:item/rod") }
    }

    fun init() {

        REGISTRY.clear()

        registerShape(BLOCK_METAL)
        //registerPart(BLOCK_GEM)
        //registerPart(ORE)
        //registerPart(ORE_BLOCK)

        registerShape(DUST)
        //registerPart(DUST_TINY)
        registerShape(GEAR)
        //registerPart(GEM)
        registerShape(INGOT)
        registerShape(NUGGET)
        registerShape(PLATE)
        //registerPart(RAW_ORE)
        registerShape(ROD)
    }

}