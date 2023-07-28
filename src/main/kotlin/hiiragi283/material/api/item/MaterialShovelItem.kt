package hiiragi283.material.api.item

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.util.ModelUtil
import hiiragi283.material.common.util.criterionFromMaterial
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.*
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

open class MaterialShovelItem(
    val material: HiiragiMaterial,
    toolMaterial: ToolMaterial = material.toolProperty,
    attackDamage: Float = 0.0f,
    attackSpeed: Float = -3.0f,
    settings: FabricItemSettings = FabricItemSettings().group(ItemGroup.TOOLS)
) : ShovelItem(toolMaterial, attackDamage, attackSpeed, settings), HiiragiEntry.ITEM.TOOL, RMItemColorProvider {

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

    override fun getIdentifier(): Identifier = hiiragiId("${material.name}_shovel")

    override fun getName(stack: ItemStack): Text =
        TranslatableText("item.ragi_materials.shovel", material.getTranslatedName())

    override fun register(): Item {

        val item = super.register()

        RMResourcePack.addItemModel(getIdentifier(), ModelUtil.createSimple("item/iron_shovel"))

        RMResourcePack.addRecipe(
            getIdentifier(),
            ShapedRecipeJsonBuilder.create(item)
                .pattern("A")
                .pattern("B")
                .pattern("B")
                .input('A', HiiragiPart(ShapeRegistry.INGOT, material).getTagKey(Registry.ITEM_KEY))
                .input('B', Items.STICK)
                .criterionFromMaterial(ShapeRegistry.INGOT, material)
        )

        RMResourcePack.addItemTag(ConventionalItemTags.AXES) {
            this.add(getIdentifier())
        }

        return item
    }

}