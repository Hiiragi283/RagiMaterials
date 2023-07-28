@file:JvmName("RecipeUtil")

package hiiragi283.material.common.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.util.registry.Registry
import pers.solid.brrp.v1.recipe.RecipeJsonBuilderExtension

//    Pattern    //

fun ShapedRecipeJsonBuilder.get3x3(key: Char) = also {
    it.pattern("$key$key$key")
    it.pattern("$key$key$key")
    it.pattern("$key$key$key")
}

//    Criterion    //

fun CraftingRecipeJsonBuilder.criterionFromMaterial(
    shape: HiiragiShape,
    material: HiiragiMaterial
) = also {
    if (it is RecipeJsonBuilderExtension<*>) it.criterionFromItemTag(
        "has_${shape.name}",
        HiiragiPart(shape, material).getTagKey(Registry.ITEM_KEY)
    )
}