package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.*
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.*
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier

open class MaterialPickaxeItem(
    val material: HiiragiMaterial,
    toolMaterial: ToolMaterial = material.toolProperty,
    attackDamage: Int = 0,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : PickaxeItem(toolMaterial, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM.TOOL, RMItemColorProvider {

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

    override fun getIdentifier(): Identifier = hiiragiId("${material.name}_pickaxe")

    override fun getName(stack: ItemStack): Text =
        TranslatableText("item.ragi_materials.pickaxe", material.getTranslatedName())

    override fun register(): Item {

        val item = super.register()

        RMResourcePack.addItemModel(
            getIdentifier(),
            ModelUtil.getItemModel { layer0("minecraft:item/iron_pickaxe") }
        )

        RMResourcePack.addRecipe(
            getIdentifier(), JRecipe.shaped(
                JPattern.pattern("AAA", " B ", " B "),
                JKeys.keys()
                    .addTag("A", HiiragiPart(ShapeRegistry.INGOT, material).getCommonId().toString())
                    .addItem("B", Items.STICK),
                JResult.item(item)
            )
        )

        RMResourcePack.addItemTag(commonId("hoes"), getIdentifier())

        return item
    }

}