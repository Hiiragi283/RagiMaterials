package hiiragi283.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.item.MaterialItemCasing
import hiiragi283.material.api.item.createItemMaterial
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.RMConfig
import hiiragi283.material.item.ItemBookRespawn
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.HiiragiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import java.util.function.BiConsumer

fun getRecipeBlock(): BiConsumer<HiiragiEntry<*>, HiiragiMaterial> =
    BiConsumer { entry, material ->
        if (material.isSolid()) {
            if (material.isGem()) {
                CraftingBuilder(entry.getItemStack(material))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                    .build()
            } else {
                CraftingBuilder(entry.getItemStack(material))
                    .setPattern("AAA", "AAA", "AAA")
                    .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                    .build()
            }
        }
    }

object HiiragiItems : HiiragiEntry.ITEM {

    @JvmField
    val BOOK_RESPAWN: ItemBookRespawn = ItemBookRespawn

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK: MaterialItem = createItemMaterial(
        HiiragiShapes.BLOCK,
        recipe = getRecipeBlock()
    )

    @JvmField
    val MATERIAL_BOTTLE: MaterialItem =
        createItemMaterial(HiiragiShapes.BOTTLE).also { it.setCreativeTab(HiiragiCreativeTabs.BOTTLE) }

    @JvmField
    val MATERIAL_CASING: MaterialItemCasing = MaterialItemCasing

    @JvmField
    val MATERIAL_DUST: MaterialItem = createItemMaterial(
        HiiragiShapes.DUST,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.DUST_TINY.getOreDict(material))
                .build()
        }
    )

    @JvmField
    val MATERIAL_DUST_TINY: MaterialItem = createItemMaterial(
        HiiragiShapes.DUST_TINY,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient.ofOreDict(HiiragiShapes.DUST.getOreDicts(material)))
                .build()
        }
    )

    @JvmField
    val MATERIAL_FRAME: MaterialItem = createItemMaterial(
        HiiragiShapes.FRAME,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 2))
                .setPattern("AAA", "A A", "AAA")
                .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
                .build()
        }
    )

    @JvmField
    val MATERIAL_GEAR: MaterialItem = createItemMaterial(HiiragiShapes.GEAR)

    @JvmField
    val MATERIAL_GEM: MaterialItem = createItemMaterial(
        HiiragiShapes.GEM,
        model = { entry: HiiragiEntry<*> ->

            ModelLoader.registerItemVariants(
                entry.asItem(),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_AMORPHOUS.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_COAL.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_CUBIC.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_DIAMOND.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_EMERALD.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_LAPIS.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_QUARTZ.name),
                entry.getLocation().append("_" + HiiragiShapeTypes.GEM_RUBY.name)
            )

            ModelLoader.setCustomMeshDefinition(entry.asItem()) { stack: ItemStack ->
                HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)?.shapeType
                    ?.let { shapeType: HiiragiShapeType ->
                        ModelResourceLocation(
                            entry.getLocation().append("_" + shapeType.name), "inventory"
                        )
                    }
                    ?: ModelResourceLocation(entry.getLocation(), "inventory")
            }

        },
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient("block${material.getOreDictName()}"))
                .build()
        }
    )

    @JvmField
    val MATERIAL_INGOT: MaterialItem = createItemMaterial(
        HiiragiShapes.INGOT,
        recipe = { entry, material ->
            //nugget -> ingot
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.NUGGET.getOreDict(material))
                .build()
            //block -> ingot
            val ingot9 = entry.getItemStack(material, 9)
            CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
                .addIngredient(HiiragiIngredient.ofOreDict(HiiragiShapes.BLOCK.getOreDicts(material)))
                .build()
        }
    )

    @JvmField
    val MATERIAL_NUGGET: MaterialItem = createItemMaterial(
        HiiragiShapes.NUGGET,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient.ofOreDict(HiiragiShapes.INGOT.getOreDicts(material)))
                .build()
        }
    )

    @JvmField
    val MATERIAL_PLATE: MaterialItem = createItemMaterial(HiiragiShapes.PLATE)

    @JvmField
    val MATERIAL_STICK: MaterialItem = createItemMaterial(HiiragiShapes.STICK)

    @JvmField
    val MATERIAL_WIRE: MaterialItem = createItemMaterial(HiiragiShapes.WIRE)

    //    Common    //

    fun init() {

        HiiragiRegistries.ITEM.register(BOOK_RESPAWN)
        if (!RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            HiiragiRegistries.ITEM.registerAll(
                MATERIAL_BLOCK,
                MATERIAL_CASING,
                MATERIAL_FRAME
            )
        }
        HiiragiRegistries.ITEM.registerAll(
            MATERIAL_BOTTLE,
            MATERIAL_DUST,
            MATERIAL_DUST_TINY,
            MATERIAL_GEAR,
            MATERIAL_GEM,
            MATERIAL_INGOT,
            MATERIAL_NUGGET,
            MATERIAL_PLATE,
            MATERIAL_STICK,
            MATERIAL_WIRE
        )

    }

}