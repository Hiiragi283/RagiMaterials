package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.item.*
import net.minecraft.tag.TagKey
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import pers.solid.brrp.v1.model.ModelJsonBuilder

abstract class MaterialCraftingToolItem(
    val type: String,
    val material: HiiragiMaterial,
    toolMaterial: ToolMaterial = material.toolProperty,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : ToolItem(toolMaterial, settings), HiiragiEntry.ITEM.TOOL, RMItemColorProvider {

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = if (tintIndex == 1) material.color else -1

    override fun getIdentifier(): Identifier = hiiragiId("${material.name}_$type")

    override fun getName(stack: ItemStack): Text =
        TranslatableText("item.ragi_materials.$type", material.getTranslatedName())

    override fun register(): Item {

        val item = super.register()

        RMResourcePack.addItemModel(getIdentifier(), getModel())

        getRecipes().forEach(RMResourcePack::addRecipe)

        RMResourcePack.addItemTag(getTag()) {
            this.add(getIdentifier())
        }

        return item
    }

    abstract fun getModel(): ModelJsonBuilder

    abstract fun getRecipes(): Map<Identifier, CraftingRecipeJsonBuilder>

    abstract fun getTag(): TagKey<Item>

}