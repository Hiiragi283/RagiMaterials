package hiiragi283.material.api.item

import hiiragi283.material.api.*
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.addItem
import hiiragi283.material.common.util.addTag
import hiiragi283.material.common.util.hiiragiId
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

fun createMaterialAxe(material: HiiragiMaterial, init: HiiragiAxeItem.() -> Unit = {}): HiiragiAxeItem {
    val axe = object : HiiragiAxeItem(material.toolProperty), RMItemColorProvider {

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

        override fun getIdentifier(): Identifier = hiiragiId("${material.name}_axe")

        override fun getName(stack: ItemStack): Text =
            TranslatableText("item.ragi_materials.axe", material.getTranslatedName())

        override fun register(): Item {

            val item = super.register()

            RMRegistry.addToolTag("axes", this)

            RMResourcePack.addItemModel(getIdentifier(), itemModelLayered { layer0("minecraft:item/iron_axe") })

            RMResourcePack.addRecipe(
                getIdentifier(), JRecipe.shaped(
                    JPattern.pattern("AA", "AB", " B"),
                    JKeys.keys()
                        .addTag("A", HiiragiPart.of(ShapeRegistry.INGOT, material).getTadId().toString())
                        .addItem("B", Items.STICK),
                    JResult.item(item)
                )
            )

            return item
        }

    }
    axe.init()
    return axe
}

fun createMaterialFile(
    material: HiiragiMaterial,
    init: HiiragiCraftingToolItem.() -> Unit = {}
): HiiragiCraftingToolItem {
    val file = object : HiiragiCraftingToolItem(material.toolProperty), RMItemColorProvider {

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = if (tintIndex == 1) material.color else -1

        override fun getIdentifier(): Identifier = hiiragiId("${material.name}_file")

        override fun getName(stack: ItemStack): Text =
            TranslatableText("item.ragi_materials.file", material.getTranslatedName())

        override fun register(): Item {

            val item = super.register()

            RMRegistry.addToolTag("files", this)

            RMResourcePack.addItemModel(getIdentifier(), itemModelLayered {
                layer0("minecraft:item/stick")
                layer1("ragi_materials:item/file")
            })

            RMResourcePack.addRecipe(
                getIdentifier(), JRecipe.shaped(
                    JPattern.pattern("A", "A", "B"),
                    JKeys.keys()
                        .addTag("A", HiiragiPart.of(ShapeRegistry.PLATE, material).getTadId().toString())
                        .addItem("B", Items.STICK),
                    JResult.item(this)
                )
            )

            return item
        }

    }
    file.init()
    return file
}


fun createMaterialHammer(
    material: HiiragiMaterial,
    init: HiiragiCraftingToolItem.() -> Unit = {}
): HiiragiCraftingToolItem {
    val hammer = object : HiiragiCraftingToolItem(material.toolProperty), RMItemColorProvider {

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = if (tintIndex == 1) material.color else -1

        override fun getIdentifier(): Identifier = hiiragiId("${material.name}_hammer")

        override fun getName(stack: ItemStack): Text =
            TranslatableText("item.ragi_materials.hammer", material.getTranslatedName())

        override fun register(): Item {

            val item = super.register()

            RMRegistry.addToolTag("hammers", this)

            RMResourcePack.addItemModel(getIdentifier(), itemModelLayered {
                layer0("minecraft:item/oak_sign")
                layer1("ragi_materials:item/hammer")
            })

            RMResourcePack.addRecipe(
                getIdentifier(), JRecipe.shaped(
                    JPattern.pattern("AAA", "AAA", " B "),
                    JKeys.keys()
                        .addTag("A", HiiragiPart.of(ShapeRegistry.INGOT, material).getTadId().toString())
                        .addTag("B", "minecraft:signs"),
                    JResult.item(this)
                )
            )

            return item
        }

    }
    hammer.init()
    return hammer
}


fun createMaterialHoe(material: HiiragiMaterial, init: HiiragiHoeItem.() -> Unit = {}): HiiragiHoeItem {
    val hoe = object : HiiragiHoeItem(material.toolProperty), RMItemColorProvider {

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

        override fun getIdentifier(): Identifier = hiiragiId("${material.name}_hoe")

        override fun getName(stack: ItemStack): Text =
            TranslatableText("item.ragi_materials.hoe", material.getTranslatedName())

        override fun register(): Item {

            val item = super.register()

            RMRegistry.addToolTag("hoes", this)

            RMResourcePack.addItemModel(getIdentifier(), itemModelLayered { layer0("minecraft:item/iron_hoe") })

            RMResourcePack.addRecipe(
                getIdentifier(), JRecipe.shaped(
                    JPattern.pattern("AA", "B ", "B "),
                    JKeys.keys()
                        .addTag("A", HiiragiPart.of(ShapeRegistry.INGOT, material).getTadId().toString())
                        .addItem("B", Items.STICK),
                    JResult.item(item)
                )
            )

            return item
        }

    }
    hoe.init()
    return hoe
}

fun createMaterialPickaxe(material: HiiragiMaterial, init: HiiragiPickaxeItem.() -> Unit = {}): HiiragiPickaxeItem {
    val pickaxe = object : HiiragiPickaxeItem(material.toolProperty), RMItemColorProvider {

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

        override fun getIdentifier(): Identifier = hiiragiId("${material.name}_pickaxe")

        override fun getName(stack: ItemStack): Text =
            TranslatableText("item.ragi_materials.pickaxe", material.getTranslatedName())

        override fun register(): Item {

            val item = super.register()

            RMRegistry.addToolTag("pickaxes", this)

            RMResourcePack.addItemModel(getIdentifier(), itemModelLayered { layer0("minecraft:item/iron_pickaxe") })

            RMResourcePack.addRecipe(
                getIdentifier(), JRecipe.shaped(
                    JPattern.pattern("AAA", " B ", " B "),
                    JKeys.keys()
                        .addTag("A", HiiragiPart.of(ShapeRegistry.INGOT, material).getTadId().toString())
                        .addItem("B", Items.STICK),
                    JResult.item(item)
                )
            )

            return item
        }

    }
    pickaxe.init()
    return pickaxe
}

fun createMaterialShovel(material: HiiragiMaterial, init: HiiragiShovelItem.() -> Unit = {}): HiiragiShovelItem {
    val shovel = object : HiiragiShovelItem(material.toolProperty), RMItemColorProvider {

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

        override fun getIdentifier(): Identifier = hiiragiId("${material.name}_shovel")

        override fun getName(stack: ItemStack): Text =
            TranslatableText("item.ragi_materials.shovel", material.getTranslatedName())

        override fun register(): Item {

            val item = super.register()

            RMRegistry.addToolTag("shovels", this)

            RMResourcePack.addItemModel(getIdentifier(), itemModelLayered { layer0("minecraft:item/iron_shovel") })

            RMResourcePack.addRecipe(
                getIdentifier(), JRecipe.shaped(
                    JPattern.pattern("A", "B", "B"),
                    JKeys.keys()
                        .addTag("A", HiiragiPart.of(ShapeRegistry.INGOT, material).getTadId().toString())
                        .addItem("B", Items.STICK),
                    JResult.item(item)
                )
            )

            return item
        }

    }
    shovel.init()
    return shovel
}