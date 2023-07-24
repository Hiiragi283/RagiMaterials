package hiiragi283.material.api.item

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.util.addItem
import hiiragi283.material.common.util.addTag
import hiiragi283.material.common.util.itemModelLayered
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JPattern
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.minecraft.item.Items
import net.minecraft.util.Identifier

open class MaterialFileItem(material: HiiragiMaterial) : MaterialCraftingToolItem("file", material) {

    override fun getModel(): JModel = itemModelLayered {
        layer0("minecraft:item/stick")
        layer1("ragi_materials:item/$type")
    }

    override fun getRecipes(): Map<Identifier, JRecipe> = mapOf(
        getIdentifier() to JRecipe.shaped(
            JPattern.pattern("A", "A", "B"),
            JKeys.keys()
                .addTag("A", HiiragiPart(ShapeRegistry.PLATE, material).getTadId().toString())
                .addItem("B", Items.STICK),
            JResult.item(this)
        )
    )

}