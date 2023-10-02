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
import hiiragi283.material.util.*
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
            recipe = { item: MaterialItem, material: HiiragiMaterial ->
                if (HiiragiShapes.INGOT.isValid(material)) {
                    CraftingBuilder(item.itemStack(material))
                        .setPattern("AAA", "AAA", "AAA")
                        .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                        .build()
                } else if (HiiragiShapes.GEM.isValid(material)) {
                    CraftingBuilder(item.itemStack(material))
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
        recipe = { item: MaterialItem, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(item.itemStack(material))
                .setPattern(" A ", "ABA", " A ")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', WRENCH, true)
                .build()
        }
    ))

    @JvmField
    val MATERIAL_GEM = HiiragiRegistries.ITEM.register(
        MaterialItem(
        HiiragiShapes.GEM,
            model = { item: MaterialItem ->

            ModelLoader.registerItemVariants(
                item,
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_AMORPHOUS.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_COAL.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_CUBIC.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_DIAMOND.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_EMERALD.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_LAPIS.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_QUARTZ.name),
                item.registryName!!.append("_" + HiiragiShapeTypes.GEM_RUBY.name)
            )

            ModelLoader.setCustomMeshDefinition(item) { stack: ItemStack ->
                HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)?.shapeType
                    ?.let { shapeType: HiiragiShapeType ->
                        ModelResourceLocation(
                            item.registryName!!.append("_" + shapeType.name), "inventory"
                        )
                    }
                    ?: ModelResourceLocation(item.registryName!!, "inventory")
            }

        },
            recipe = { entry: MaterialItem, material: HiiragiMaterial ->
            if (!HiiragiShapes.BLOCK.isValid(material)) return@MaterialItem
                CraftingBuilder(entry.itemStack(material, 9))
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.BLOCK.getOreDict(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_INGOT = HiiragiRegistries.ITEM.register(
        MaterialItem(
        HiiragiShapes.INGOT,
            recipe = { item: MaterialItem, material: HiiragiMaterial ->
            //nugget -> ingot
            if (!HiiragiShapes.NUGGET.isValid(material)) return@MaterialItem
                CraftingBuilder(item.itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.NUGGET.getOreDict(material))
                .build()
            //block -> ingot
            if (!HiiragiShapes.BLOCK.isValid(material)) return@MaterialItem
                val ingot9 = item.itemStack(material, 9)
            CraftingBuilder(ingot9.toLocation("_").append("_alt"), ingot9)
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.BLOCK.getOreDict(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_NUGGET = HiiragiRegistries.ITEM.register(
        MaterialItem(
        HiiragiShapes.NUGGET,
            recipe = { item: MaterialItem, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
                CraftingBuilder(item.itemStack(material, 9))
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.INGOT.getOreDict(material)))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_PLATE: MaterialItem = HiiragiRegistries.ITEM.register(MaterialItem(
        HiiragiShapes.PLATE,
        recipe = { item: MaterialItem, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(item.itemStack(material))
                .addIngredient(CraftingHelper.getIngredient(HiiragiShapes.INGOT.getOreDict(material)))
                .addIngredient(Ingredient.fromStacks(WRENCH.itemStackWild()))
                .build()
        }
    ))

    @JvmField
    val MATERIAL_STICK: MaterialItem = HiiragiRegistries.ITEM.register(MaterialItem(
        HiiragiShapes.STICK,
        recipe = { item: MaterialItem, material: HiiragiMaterial ->
            if (!HiiragiShapes.INGOT.isValid(material)) return@MaterialItem
            CraftingBuilder(item.itemStack(material))
                .setPattern("AB", "A ")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', WRENCH, true)
                .build()
        }
    ))

    //    Module Item   //

    @JvmField
    val MODULE_MOTOR = HiiragiRegistries.ITEM.register(ItemMotor)

    //    Common    //

    @JvmField
    val MINECART_TANK = HiiragiRegistries.ITEM.register(ItemMinecartTank)

    @JvmField
    val SHAPE_PATTERN = HiiragiRegistries.ITEM.register(ItemShapePattern)

    @JvmField
    val WRENCH = HiiragiRegistries.ITEM.register(ItemWrench)

}