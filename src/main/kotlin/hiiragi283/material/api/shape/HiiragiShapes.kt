package hiiragi283.material.api.shape

import hiiragi283.material.api.material.CrystalType
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader

object HiiragiShapes {

    @JvmField
    val COMMON: (Double) -> HiiragiShape = { shapeOf("common", it) }

    @JvmField
    val BALL = shapeOf("ball", 0.2)

    @JvmField
    val BLOCK = shapeOf(
        "block",
        9.0,
        model = {
            val common = ModelResourceLocation(it.getLocation()!!.append("_material"), "inventory")
            val gem = ModelResourceLocation(it.getLocation()!!.append("_gem"), "inventory")
            val metal = ModelResourceLocation(it.getLocation()!!.append("_metal"), "inventory")

            ModelLoader.registerItemVariants(it.getItem(), common, gem, metal)

            ModelLoader.setCustomMeshDefinition(it.getItem()) { stack ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                if (material.isMetal()) metal
                else if (material.isGem()) gem
                else common
            }
        },
        recipe = { entry, material ->
            if (material.isSolid()) {
                if (material.isGem()) {
                    CraftingBuilder(entry.getItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "gem${material.getOreDictName()}")
                        .buildShaped()
                } else {
                    CraftingBuilder(entry.getItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "ingot${material.getOreDictName()}")
                        .buildShaped()
                }
            }
        }
    )

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
    val DUST = shapeOf("dust", 1.0, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("AAA", "AAA", "AAA")
            .setIngredient('A', "dustTiny${material.getOreDictName()}")
            .buildShaped()
    })

    @JvmField
    val DUST_DIRTY = shapeOf("dust_dirty", 1.0)

    @JvmField
    val DUST_TINY = shapeOf("dust_tiny", 0.1, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material, 9))
            .addIngredient(RagiIngredient("dust${material.getOreDictName()}"))
            .buildShapeless()
    })

    @JvmField
    val GEAR = shapeOf(
        "gear", 4.0,
        /*recipe = { item, material ->
               CraftingBuilder(item.getItemStack(material))
                   .setPattern(" A ", "A A", " A ")
                   .setIngredient('A', "ingot${material.getOreDictName()}")
                   .buildShaped()
           }*/
    )

    @JvmField
    val GEM = shapeOf(
        "gem",
        1.0,
        model = { entry ->
            ModelLoader.registerItemVariants(entry.getItem(), *CrystalType.values()
                .filter { it.texture.isNotEmpty() }
                .map { entry.getLocation()!!.append("_" + it.texture) }
                .toTypedArray())

            ModelLoader.setCustomMeshDefinition(entry.getItem()) { stack ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                val type = if (material.isGem()) material.crystalType else CrystalType.CUBIC
                type.getLocation(entry.getItem())
            }
        },
        recipe = { item, material ->
            CraftingBuilder(item.getItemStack(material, 9))
                .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
                .buildShapeless()
        }
    )

    @JvmField
    val INGOT = shapeOf("ingot", 1.0, recipe = { entry, material ->
        //nugget -> ingot
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("AAA", "AAA", "AAA")
            .setIngredient('A', "nugget${material.getOreDictName()}")
            .buildShaped()
        //block -> ingot
        val ingot9 = entry.getItemStack(material, 9)
        CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
            .addIngredient(RagiIngredient("block${material.getOreDictName()}"))
            .buildShapeless()
    })

    @JvmField
    val LOG = shapeOf("log", 4.0)

    @JvmField
    val NUGGET = shapeOf("nugget", 0.1, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material, 9))
            .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
            .buildShapeless()
    })

    @JvmField
    val ORE = shapeOf("ore", -1.0)

    @JvmField
    val ORE_POOR = shapeOf("ore_poor", -1.0)

    @JvmField
    val PLANK = shapeOf("plank", 1.0)

    @JvmField
    val PLATE = shapeOf(
        "plate", 1.0,
        /*recipe = { item, material ->
               if (material.isMetal()) {
                   CraftingBuilder(item.getItemStack(material))
                       .addIngredient(RagiIngredient("ingot${material.getOreDictName()}"))
                       .addIngredient(RagiIngredient(ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE)))
                       .buildShapeless()
               }
           }*/
    )

    @JvmField
    val PLATE_DENSE = shapeOf("plate_dense", 9.0)

    @JvmField
    val PURIFIED = shapeOf("crushed_purified", 1.0)

    @JvmField
    val SAND = shapeOf("sand", 1.0)

    @JvmField
    val SHARD = shapeOf("shard", 1.0)

    @JvmField
    val STICK = shapeOf(
        "stick", 0.5,
        /*recipe = { item, material ->
               if (material.isMetal()) {
                   CraftingBuilder(item.getItemStack(material, 4))
                       .setPattern("AB", "A ")
                       .setIngredient('A', "ingot${material.getOreDictName()}")
                       .setIngredient('B', ItemStack(RMItems.FORGE_HAMMER, 1, OreDictionary.WILDCARD_VALUE))
                       .buildShaped()
               }
           }*/
    )

    @JvmField
    val STONE = shapeOf("stone", 1.0)

    fun register(registry: MutableList<HiiragiShape>) {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiShape>()
            .forEach { registry.add(it) }
    }

}