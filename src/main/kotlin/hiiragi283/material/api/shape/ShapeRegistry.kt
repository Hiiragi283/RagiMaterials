package hiiragi283.material.api.shape

import hiiragi283.material.RMItems
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.CrystalType
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.oredict.OreDictionary

object ShapeRegistry {

    private val REGISTRY: HashMap<String, HiiragiShape> = hashMapOf()

    @JvmStatic
    fun getShapes(): Collection<HiiragiShape> = REGISTRY.values

    @JvmStatic
    fun getShape(name: String) = REGISTRY.getOrDefault(name, HiiragiShape.EMPTY)

    @JvmStatic
    fun registerShape(shape: HiiragiShape) {
        val name = shape.name
        REGISTRY[name]?.let { RagiMaterials.LOGGER.warn("The shape: $name will be overrided!") }
        REGISTRY[name] = shape
    }

    //    Shapes    //

    @JvmField
    val COMMON: (Double) -> HiiragiShape = { shapeOf("common", it) }

    @JvmField
    val BALL = shapeOf("ball", 0.2)

    @JvmField
    val BLOCK = shapeOf("block", 9.0) {
        model = {
            val common = ModelResourceLocation(it.registryName!!.append("_material"), "inventory")
            val gem = ModelResourceLocation(it.registryName!!.append("_gem"), "inventory")
            val metal = ModelResourceLocation(it.registryName!!.append("_metal"), "inventory")

            ModelLoader.registerItemVariants(it, common, gem, metal)

            ModelLoader.setCustomMeshDefinition(it) { stack ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                if (material.isMetal()) metal
                else if (material.isGem()) gem
                else common
            }
        }
        recipe = { item, material ->
            if (material.isSolid()) {
                if (material.isGem()) {
                    CraftingBuilder(ItemStack(item, 1, material.index))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "gem${material.getOreDictName()}")
                        .buildShaped()
                } else {
                    CraftingBuilder(ItemStack(item, 1, material.index))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "ingot${material.getOreDictName()}")
                        .buildShaped()
                }
            }
        }
    }

    @JvmField
    val BOTTLE = shapeOf("bottle", 1.0)

    @JvmField
    val CLUMP = shapeOf("clump", 1.0)

    @JvmField
    val CLUSTER = shapeOf("cluster", 2.0)

    @JvmField
    val COIN = shapeOf("coin", 0.3)

    @JvmField
    val CRUSHED = shapeOf("crushed", 1.0)

    @JvmField
    val CRYSTAL = shapeOf("crystal", 1.0)

    @JvmField
    val DUST = shapeOf("dust", 1.0) {
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "dustTiny${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @JvmField
    val DUST_DIRTY = shapeOf("dust_dirty", 1.0)

    @JvmField
    val DUST_TINY = shapeOf("dust_tiny", 0.1) {
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("dust${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val GEAR = shapeOf("gear", 4.0) {
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern(" A ", "A A", " A ")
                .setIngredient('A', "ingot${material.getOreDictName()}")
                .buildShaped()
        }
    }

    @JvmField
    val GEM = shapeOf("gem", 1.0) {
        model = { item ->

            val locations: MutableList<ResourceLocation> = mutableListOf()

            CrystalType.values()
                .filter { it.texture.isNotEmpty() }
                .map { item.registryName!!.append("_" + it.texture) }
                .forEach { locations.add(it) }

            ModelLoader.registerItemVariants(item, *locations.toTypedArray())

            ModelLoader.setCustomMeshDefinition(item) { stack ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                val type = if (material.isGem()) material.crystalType else CrystalType.CUBIC
                type.getLocation(item)
            }
        }
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
                .buildShapeless()
        }
    }


    @JvmField
    val INGOT = shapeOf("ingot", 1.0) {
        recipe = { item, material ->
            //nugget -> ingot
            CraftingBuilder(ItemStack(item, 1, material.index))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', "nugget${material.getOreDictName()}")
                .buildShaped()
            //block -> ingot
            val ingot9 = ItemStack(item, 9, material.index)
            CraftingBuilder(ingot9.toLocation().append("_alt"), ingot9)
                .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val LOG = shapeOf("log", 4.0)

    @JvmField
    val NUGGET = shapeOf("nugget", 0.1) {
        recipe = { item, material ->
            CraftingBuilder(ItemStack(item, 9, material.index))
                .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
                .buildShapeless()
        }
    }

    @JvmField
    val ORE = shapeOf("ore", -1.0)

    @JvmField
    val PLANK = shapeOf("plank", 1.0)

    @JvmField
    val PLATE = shapeOf("plate", 1.0) {
        recipe = { item, material ->
            if (material.isMetal()) {
                CraftingBuilder(ItemStack(item, 1, material.index))
                    .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
                    .addIngredient(RagiIngredient(ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE)))
                    .buildShapeless()
            }
        }
    }

    @JvmField
    val PLATE_DENSE = shapeOf("plate_dense", 9.0)

    @JvmField
    val PURIFIED = shapeOf("crushed_purified", 1.0)

    @JvmField
    val SHARD = shapeOf("shard", 1.0)

    @JvmField
    val STICK = shapeOf("stick", 0.5) {
        recipe = { item, material ->
            if (material.isMetal()) {
                CraftingBuilder(ItemStack(item, 4, material.index))
                    .setPattern("AB", "A ")
                    .setIngredient('A', "ingot${material.getOreDictName()}")
                    .setIngredient('B', ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE))
                    .buildShaped()
            }
        }
    }

    @JvmField
    val STONE = shapeOf("stone", 1.0)

    fun init() {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiShape>()
            .forEach(::registerShape)
    }
}