package hiiragi283.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.item.createItemMaterial
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.RMConfig
import hiiragi283.material.item.ItemBookRespawn
import hiiragi283.material.item.MaterialItemCasing
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.HiiragiIngredient
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader

fun getRecipeBlock(): (HiiragiEntry<*>, HiiragiMaterial) -> Unit = { entry, material ->
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

object HiiragiItems : HiiragiEntry.ITEM {

    @JvmField
    val BOOK_RESPAWN = HiiragiRegistries.ITEM.register(ItemBookRespawn)

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK = HiiragiRegistries.ITEM.registerOptional(
        createItemMaterial(
            HiiragiShapes.BLOCK,
            recipe = getRecipeBlock()
        )
    ) { !RMConfig.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_BOTTLE = HiiragiRegistries.ITEM.register(createItemMaterial(HiiragiShapes.BOTTLE).also {
        it.setCreativeTab(HiiragiCreativeTabs.BOTTLE)
    })

    @JvmField
    val MATERIAL_CASING =
        HiiragiRegistries.ITEM.registerOptional(MaterialItemCasing) { !RMConfig.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_DUST = HiiragiRegistries.ITEM.register(createItemMaterial(
        HiiragiShapes.DUST,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.DUST_TINY.getOreDict(material))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_DUST_TINY = HiiragiRegistries.ITEM.register(createItemMaterial(
        HiiragiShapes.DUST_TINY,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient.ofOreDict(HiiragiShapes.DUST.getOreDicts(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_FRAME = HiiragiRegistries.ITEM.registerOptional(createItemMaterial(
        HiiragiShapes.FRAME,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "A A", "AAA")
                .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
                .build()
        }
    )) { !RMConfig.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_GEAR = HiiragiRegistries.ITEM.register(createItemMaterial(HiiragiShapes.GEAR))

    @JvmField
    val MATERIAL_GEM = HiiragiRegistries.ITEM.register(createItemMaterial(
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
    ))

    @JvmField
    val MATERIAL_INGOT = HiiragiRegistries.ITEM.register(createItemMaterial(
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
    ))

    @JvmField
    val MATERIAL_NUGGET = HiiragiRegistries.ITEM.register(createItemMaterial(
        HiiragiShapes.NUGGET,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(HiiragiIngredient.ofOreDict(HiiragiShapes.INGOT.getOreDicts(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_PLATE: MaterialItem = HiiragiRegistries.ITEM.register(createItemMaterial(HiiragiShapes.PLATE))

    @JvmField
    val MATERIAL_STICK: MaterialItem = HiiragiRegistries.ITEM.register(createItemMaterial(HiiragiShapes.STICK))

    @JvmField
    val MATERIAL_WIRE: MaterialItem = HiiragiRegistries.ITEM.register(createItemMaterial(HiiragiShapes.WIRE))

    //    Common    //

}