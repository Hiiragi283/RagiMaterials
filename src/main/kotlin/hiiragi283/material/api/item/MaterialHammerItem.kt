package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMTags
import hiiragi283.material.common.util.ModelUtil
import hiiragi283.material.common.util.criterionFromMaterial
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.tag.ItemTags
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import pers.solid.brrp.v1.model.ModelJsonBuilder

open class MaterialHammerItem(material: HiiragiMaterial) : MaterialCraftingToolItem("hammer", material) {

    override fun getModel(): ModelJsonBuilder = ModelUtil.createSimple("item/oak_sign", "ragi_materials:item/hammer")

    override fun getRecipes(): Map<Identifier, CraftingRecipeJsonBuilder> = mapOf(
        getIdentifier() to ShapedRecipeJsonBuilder.create(this)
            .group("ragi_materials:hammer")
            .pattern("AAA")
            .pattern("AAA")
            .pattern(" B ")
            .input('A', HiiragiPart(ShapeRegistry.INGOT, material).getTagKey(Registry.ITEM_KEY))
            .input('B', ItemTags.SIGNS)
            .criterionFromMaterial(ShapeRegistry.INGOT, material)
    )

    override fun getTag(): TagKey<Item> = RMTags.HAMMERS

}