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

open class MaterialAxeItem(
    val material: HiiragiMaterial,
    toolMaterial: ToolMaterial = material.toolProperty,
    attackDamage: Float = 0.0f,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : AxeItem(toolMaterial, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM.TOOL, RMItemColorProvider {

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

    override fun getIdentifier(): Identifier = hiiragiId("${material.name}_axe")

    override fun getName(stack: ItemStack): Text =
        TranslatableText("item.ragi_materials.axe", material.getTranslatedName())

    override fun register(): Item {

        val item = super.register()

        RMResourcePack.addItemModel(getIdentifier(), itemModelLayered { layer0("minecraft:item/iron_axe") })

        RMResourcePack.addRecipe(
            getIdentifier(), JRecipe.shaped(
                JPattern.pattern("AA", "AB", " B"),
                JKeys.keys()
                    .addTag("A", HiiragiPart(ShapeRegistry.INGOT, material).getTadId().toString())
                    .addItem("B", Items.STICK),
                JResult.item(item)
            )
        )

        RMResourcePack.addItemTag(commonId("axes"), getIdentifier())
        RMResourcePack.addItemTag(hiiragiId(material.name), getIdentifier())

        return item
    }

}