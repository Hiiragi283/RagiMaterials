package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.util.ModelUtil
import hiiragi283.material.common.util.addTag
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.minecraft.util.Identifier

open class MaterialHammerItem(material: HiiragiMaterial) : MaterialCraftingToolItem("hammer", material) {

    override fun getModel(): JModel = ModelUtil.getItemModel {
        layer0("minecraft:item/oak_sign")
        layer1("ragi_materials:item/hammer")
    }

    override fun getRecipes(): Map<Identifier, JRecipe> = mapOf(
        getIdentifier() to JRecipe.shaped(
            JPattern.pattern("AAA", "AAA", " B "),
            JKeys.keys()
                .addTag("A", HiiragiPart(ShapeRegistry.INGOT, material).getCommonId().toString())
                .addTag("B", "minecraft:signs"),
            JResult.item(this)
        )
    )

}