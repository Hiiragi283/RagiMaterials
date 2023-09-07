package hiiragi283.material.compat.jei.ingredient;

import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.material.MaterialStack;
import hiiragi283.material.util.HiiragiColor;
import hiiragi283.material.util.HiiragiUtil;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class MaterialStackRenderer implements IIngredientRenderer<MaterialStack> {

    @Override
    public void render(@NotNull Minecraft minecraft, int xPosition, int yPosition, @Nullable MaterialStack materialStack) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        drawMaterial(minecraft, xPosition, yPosition, materialStack);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    private void drawMaterial(@NotNull Minecraft minecraft, int xPosition, int yPosition, @Nullable MaterialStack materialStack) {
        if (materialStack == null) return;
        TextureMap textureMap = minecraft.getTextureMapBlocks();
        minecraft.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        materialStack.getMaterial().map(HiiragiMaterial::color).ifPresent(HiiragiColor::setGLColor);
        HiiragiUtil.drawFluid(minecraft, xPosition, yPosition, getSprite(textureMap));
    }

    @NotNull
    private TextureAtlasSprite getSprite(TextureMap textureMap) {
        TextureAtlasSprite ret = textureMap.getTextureExtry(new ResourceLocation("blocks/concrete_white").toString());
        return ret != null ? ret : textureMap.getMissingSprite();
    }

    @NotNull
    @Override
    @ParametersAreNonnullByDefault
    public List<String> getTooltip(Minecraft minecraft, MaterialStack ingredient, ITooltipFlag tooltipFlag) {
        List<String> list = new ArrayList<>();
        ingredient.addTooltip(list);
        return list;
    }

}