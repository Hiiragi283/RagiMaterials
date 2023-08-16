package hiiragi283.integration.jei.ingredients

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.shape.HiiragiShape
import hiiragi283.material.RMItems
import mezz.jei.api.ingredients.IIngredientRenderer
import mezz.jei.plugins.vanilla.ingredients.item.ItemStackRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack

object HiiragiMaterialRendererJEI : IIngredientRenderer<HiiragiMaterial> {

    private val itemRenderer: IIngredientRenderer<ItemStack> = ItemStackRenderer()

    override fun render(minecraft: Minecraft, x: Int, y: Int, ingredient: HiiragiMaterial?) {
        itemRenderer.render(minecraft, x, y,
            ingredient?.let { RMItems.MATERIAL_BOTTLE.getItemStack(it) }
        )
    }

    override fun getTooltip(
        minecraft: Minecraft,
        ingredient: HiiragiMaterial,
        tooltipFlag: ITooltipFlag
    ): MutableList<String> = mutableListOf<String>().also {
        it.add(ingredient.getTranslatedName())
        HiiragiPart(HiiragiShape.EMPTY, ingredient).addTooltip(it)
    }

}