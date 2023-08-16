package hiiragi283.api.shape

import hiiragi283.api.material.CrystalType
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.HiiragiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraftforge.client.model.ModelLoader
import java.util.function.Function

object HiiragiShapes {

    @JvmField
    val COMMON: Function<Int, HiiragiShape> = Function { shapeOf("common", it) }

    @JvmField
    val BALL = shapeOf("ball", 30)

    @JvmField
    val BLOCK = shapeOf(
        "block",
        144 * 9,
        recipe = { entry, material ->
            if (material.isSolid()) {
                if (material.isGem()) {
                    CraftingBuilder(entry.getItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "gem${material.getOreDictName()}")
                        .build()
                } else {
                    CraftingBuilder(entry.getItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', "ingot${material.getOreDictName()}")
                        .build()
                }
            }
        }
    )

    @JvmField
    val BOTTLE = shapeOf("bottle", 144)

    @JvmField
    val CLUMP = shapeOf("clump", 144)

    @JvmField
    val CLUSTER = shapeOf("cluster", 144 * 2)

    @JvmField
    val COIN = shapeOf("coin", 144 / 3)

    @JvmField
    val CRUSHED = shapeOf("crushed", 144)

    @JvmField
    val CRYSTAL = shapeOf("crystal", 144)

    @JvmField
    val DUST = shapeOf("dust", 144, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("AAA", "AAA", "AAA")
            .setIngredient('A', "dustTiny${material.getOreDictName()}")
            .build()
    })

    @JvmField
    val DUST_DIRTY = shapeOf("dust_dirty", 144)

    @JvmField
    val DUST_TINY = shapeOf("dust_tiny", 144 / 9, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material, 9))
            .addIngredient(HiiragiIngredient("dust${material.getOreDictName()}"))
            .build()
    })

    @JvmField
    val GEAR = shapeOf("gear", 144 * 4)

    @JvmField
    val GEM = shapeOf(
        "gem",
        144,
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
                .build()
        }
    )

    @JvmField
    val INGOT = shapeOf("ingot", 144, recipe = { entry, material ->
        //nugget -> ingot
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("AAA", "AAA", "AAA")
            .setIngredient('A', "nugget${material.getOreDictName()}")
            .build()
        //block -> ingot
        val ingot9 = entry.getItemStack(material, 9)
        CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
            .addIngredient(HiiragiIngredient("block${material.getOreDictName()}"))
            .build()
    })

    @JvmField
    val LOG = shapeOf("log", 144 * 4)

    @JvmField
    val NUGGET = shapeOf("nugget", 144 / 9, recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material, 9))
            .addIngredient(HiiragiIngredient("ingot${material.getOreDictName()}"))
            .build()
    })

    @JvmField
    val ORE = shapeOf("ore", 144 * 2)

    @JvmField
    val ORE_POOR = shapeOf("ore_poor", 144 / 3)

    @JvmField
    val PLANK = shapeOf("plank", 144)

    @JvmField
    val PLATE = shapeOf("plate", 144)

    @JvmField
    val PLATE_DENSE = shapeOf("plate_dense", 144 * 9)

    @JvmField
    val PURIFIED = shapeOf("crushed_purified", 144)

    @JvmField
    val SAND = shapeOf("sand", 144)

    @JvmField
    val SHARD = shapeOf("shard", 144)

    @JvmField
    val STICK = shapeOf("stick", 144 / 2)

    @JvmField
    val STONE = shapeOf("stone", 144)

    fun register(registry: MutableList<HiiragiShape>) {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiShape>()
            .forEach { registry.add(it) }
    }

}