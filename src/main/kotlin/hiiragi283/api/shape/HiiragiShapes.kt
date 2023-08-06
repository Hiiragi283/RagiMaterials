package hiiragi283.api.shape

import hiiragi283.api.material.CrystalType
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.core.util.CraftingBuilder
import hiiragi283.core.util.HiiragiIngredient
import hiiragi283.core.util.append
import hiiragi283.core.util.toLocation
import net.minecraftforge.client.model.ModelLoader
import java.util.function.Function

object HiiragiShapes {

    @JvmField
    val COMMON: Function<Double, HiiragiShape> = Function { shapeOf("common", it) }

    @JvmField
    val BALL = shapeOf("ball", 0.2)

    @JvmField
    val BLOCK = shapeOf(
        "block",
        9.0,
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
            .addIngredient(HiiragiIngredient("dust${material.getOreDictName()}"))
            .buildShapeless()
    })

    @JvmField
    val GEAR = shapeOf("gear", 4.0)

    @JvmField
    val GEM = shapeOf(
        "gem",
        1.0,
        model = { entry ->
            ModelLoader.registerItemVariants(entry.asItem(), *CrystalType.values()
                .filter { it.texture.isNotEmpty() }
                .map { entry.getLocation()!!.append("_" + it.texture) }
                .toTypedArray())

            ModelLoader.setCustomMeshDefinition(entry.asItem()) { stack ->
                val material = MaterialRegistry.getMaterial(stack.metadata)
                val type = if (material.isGem()) material.crystalType else CrystalType.CUBIC
                type.getLocation(entry.asItem())
            }
        },
        recipe = { item, material ->
            CraftingBuilder(item.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient("block${material.getOreDictName()}"))
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
            .addIngredient(HiiragiIngredient("block${material.getOreDictName()}"))
            .buildShapeless()
    })

    @JvmField
    val LOG = shapeOf("log", 4.0)

    @JvmField
    val NUGGET = shapeOf("nugget", 0.1, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material, 9))
            .addIngredient(HiiragiIngredient("ingot${material.getOreDictName()}"))
            .buildShapeless()
    })

    @JvmField
    val ORE = shapeOf("ore", 2.0)

    @JvmField
    val ORE_POOR = shapeOf("ore_poor", 0.3)

    @JvmField
    val PLANK = shapeOf("plank", 1.0)

    @JvmField
    val PLATE = shapeOf("plate", 1.0)

    @JvmField
    val PLATE_DENSE = shapeOf("plate_dense", 9.0)

    @JvmField
    val PURIFIED = shapeOf("crushed_purified", 1.0)

    @JvmField
    val SAND = shapeOf("sand", 1.0)

    @JvmField
    val SHARD = shapeOf("shard", 1.0)

    @JvmField
    val STICK = shapeOf("stick", 0.5)

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