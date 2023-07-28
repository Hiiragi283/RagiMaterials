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

        RMResourcePack.addItemModel(getIdentifier(), ModelUtil.createSimple("item/iron_pickaxe"))

        RMResourcePack.addRecipe(
            getIdentifier(),
            ShapedRecipeJsonBuilder.create(item)
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .input('A', HiiragiPart(ShapeRegistry.INGOT, material).getTagKey(Registry.ITEM_KEY))
                .input('B', Items.STICK)
                .criterionFromMaterial(ShapeRegistry.INGOT, material)
        )

        RMResourcePack.addItemTag(ConventionalItemTags.PICKAXES) {
            this.add(getIdentifier())
        }

        return item
    }

}