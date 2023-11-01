package hiiragi283.material.compat.jei.ingredients

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.drawSquareTexture
import mezz.jei.api.ingredients.IIngredientRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.util.ResourceLocation

object MaterialStackRenderer : IIngredientRenderer<HiiragiMaterial> {

    override fun render(minecraft: Minecraft, xPosition: Int, yPosition: Int, material: HiiragiMaterial?) {
        GlStateManager.enableBlend()
        GlStateManager.enableAlpha()

        drawMaterial(minecraft, xPosition, yPosition, material)
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)

        GlStateManager.disableAlpha()
        GlStateManager.disableBlend()
    }

    private fun drawMaterial(minecraft: Minecraft, xPosition: Int, yPosition: Int, material: HiiragiMaterial?) {
        if (material !== null) {
            minecraft.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
            HiiragiColor.setGLColor(material.color)
            drawSquareTexture(
                minecraft,
                xPosition.toDouble(),
                yPosition.toDouble(),
                ResourceLocation("blocks/concrete_white")
            )
        }
    }

    override fun getTooltip(
        minecraft: Minecraft,
        ingredient: HiiragiMaterial,
        tooltipFlag: ITooltipFlag
    ): MutableList<String> = mutableListOf<String>().also(ingredient::addTooltip)

}