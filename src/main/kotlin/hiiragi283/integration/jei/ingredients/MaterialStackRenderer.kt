package hiiragi283.integration.jei.ingredients

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialStack
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.drawFluid
import mezz.jei.api.ingredients.IIngredientRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.util.ResourceLocation

object MaterialStackRenderer : IIngredientRenderer<MaterialStack> {

    override fun render(minecraft: Minecraft, xPosition: Int, yPosition: Int, stack: MaterialStack?) {
        GlStateManager.enableBlend()
        GlStateManager.enableAlpha()

        drawMaterial(minecraft, xPosition, yPosition, stack)
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)

        GlStateManager.disableAlpha()
        GlStateManager.disableBlend()
    }

    private fun drawMaterial(minecraft: Minecraft, xPosition: Int, yPosition: Int, stack: MaterialStack?) {
        if (stack !== null) {
            val material: HiiragiMaterial = stack.material
            val textureMapBlocks: TextureMap = minecraft.textureMapBlocks
            minecraft.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
            HiiragiColor.setGLColor(material.color)
            drawFluid(
                minecraft,
                xPosition.toDouble(),
                yPosition.toDouble(),
                textureMapBlocks.getTextureExtry(ResourceLocation("blocks/concrete_white").toString())
                    ?: textureMapBlocks.missingSprite
            )
        }
    }

    override fun getTooltip(
        minecraft: Minecraft,
        ingredient: MaterialStack,
        tooltipFlag: ITooltipFlag
    ): MutableList<String> = mutableListOf<String>().also {
        ingredient.addTooltip(it)
    }

}