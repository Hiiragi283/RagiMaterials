package hiiragi283.material.api.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Thanks to SkyTheory!
 * <a href="https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/gui/AbstractButton.java">: Source</a>
 */

public class HiiragiGuiButton extends GuiButton {

    protected final ResourceLocation texture;
    protected final int texU;
    protected final int texV;

    public HiiragiGuiButton(HiiragiGuiContainer<?> gui, int buttonId, int x, int y, int texU, int texV, int widthIn, int heightIn, ResourceLocation texture) {
        super(buttonId, gui.getGuiLeft() + x, gui.getGuiTop() + y, widthIn, heightIn, "");
        this.texU = texU;
        this.texV = texV;
        this.texture = texture;
    }

    @Override
    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            this.hovered = mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
            mc.getTextureManager().bindTexture(texture);
            GlStateManager.disableDepth();
            drawTexturedModalRect(x, y, texU, texV, width, height);
            GlStateManager.enableDepth();
        }
    }

}