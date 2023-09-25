package hiiragi283.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapeType
import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.item.*
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.append
import hiiragi283.material.util.toLocation
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.crafting.CraftingHelper

object HiiragiItems : HiiragiEntry.ITEM {

    @JvmField
    val BOOK_RESPAWN = HiiragiRegistries.ITEM.register(ItemBookRespawn)

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK = HiiragiRegistries.ITEM.registerOptional(
        MaterialItem(
            HiiragiShapes.BLOCK,
            recipe = { entry: HiiragiEntry<*>, material: HiiragiMaterial ->
                if (HiiragiShapes.INGOT.isValid(material)) {
                    CraftingBuilder(entry.getItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                        .build()
                } else if (HiiragiShapes.GEM.isValid(material)) {
                    CraftingBuilder(entry.getItemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                        .build()
                }
            }
        )
    ) { !HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_BOTTLE = HiiragiRegistries.ITEM.register(MaterialItem(HiiragiShapes.BOTTLE))

    @JvmField
    val MATERIAL_CASING = HiiragiRegistries.ITEM.registerOptional(MaterialItemCasing)
    { !HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_DUST = HiiragiRegistries.ITEM.register(MaterialItem(HiiragiShapes.DUST))

    @JvmField
    val MATERIAL_GEAR = HiiragiRegistries.ITEM.register(MaterialItem(
        HiiragiShapes.GEAR,
        recipe = { entry: HiiragiEntry<*>, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(entry.getItemStack(material))
                .setPattern(" A ", "ABA", " A ")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', WRENCH.getItemStackWild())
                .build()
        }
    ))

    @JvmField
    val MATERIAL_GEM = HiiragiRegistries.ITEM.register(
        MaterialItem(
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
            if (!HiiragiShapes.BLOCK.isValid(material)) return@MaterialItem
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.BLOCK.getOreDict(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_INGOT = HiiragiRegistries.ITEM.register(
        MaterialItem(
        HiiragiShapes.INGOT,
        recipe = { entry, material ->
            //nugget -> ingot
            if (!HiiragiShapes.NUGGET.isValid(material)) return@MaterialItem
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.NUGGET.getOreDict(material))
                .build()
            //block -> ingot
            if (!HiiragiShapes.BLOCK.isValid(material)) return@MaterialItem
            val ingot9 = entry.getItemStack(material, 9)
            CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.BLOCK.getOreDict(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_NUGGET = HiiragiRegistries.ITEM.register(
        MaterialItem(
        HiiragiShapes.NUGGET,
        recipe = { entry, material ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(entry.getItemStack(material, 9))
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.INGOT.getOreDict(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_PLATE: MaterialItem = HiiragiRegistries.ITEM.register(MaterialItem(
        HiiragiShapes.PLATE,
        recipe = { entry: HiiragiEntry<*>, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(entry.getItemStack(material))
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.INGOT.getOreDict(material)))
                .addIngredient(Ingredient.fromStacks(WRENCH.getItemStackWild()))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_STICK: MaterialItem = HiiragiRegistries.ITEM.register(MaterialItem(
        HiiragiShapes.STICK,
        recipe = { entry: HiiragiEntry<*>, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(entry.getItemStack(material))
                .setPattern("AB", "A ")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', WRENCH.getItemStackWild())
                .build()
        }
    ))

    //    Recipe Module    //

    @JvmField
    val RECIPE_EXTRACTOR: ItemRecipeModule = HiiragiRegistries.ITEM.register(ItemRecipeModule.Extractor)

    @JvmField
    val RECIPE_FREEZER: ItemRecipeModule = HiiragiRegistries.ITEM.register(ItemRecipeModule.Freezer)

    @JvmField
    val RECIPE_INFUSER: ItemRecipeModule = HiiragiRegistries.ITEM.register(ItemRecipeModule.Infuser)

    @JvmField
    val RECIPE_MELTER: ItemRecipeModule = HiiragiRegistries.ITEM.register(ItemRecipeModule.Melter)

    @JvmField
    val RECIPE_ROCK_GENERATOR: ItemRecipeModule = HiiragiRegistries.ITEM.register(ItemRecipeModule.RockGenerator)

    @JvmField
    val RECIPE_SMELTER: ItemRecipeModule = HiiragiRegistries.ITEM.register(ItemRecipeModule.Smelter)

    //    Module Item   //

    @JvmField
    val MODULE_MOTOR = HiiragiRegistries.ITEM.register(ItemMotor)

    //    Common    //

    @JvmField
    val MINECART_TANK = HiiragiRegistries.ITEM.register(ItemMinecartTank)

    @JvmField
    val WRENCH = HiiragiRegistries.ITEM.register(ItemWrench)

}