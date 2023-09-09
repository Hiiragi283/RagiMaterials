package hiiragi283.material.api.gui;

import hiiragi283.material.api.container.HiiragiContainer;
import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.util.HiiragiColor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public abstract class HiiragiGuiContainer<T extends HiiragiTileEntity> extends GuiContainer {

    protected ResourceLocation backGround;

    protected final HiiragiContainer<T> container;

    public HiiragiGuiContainer(HiiragiContainer<T> container) {
        super(container);
        this.container = container;
    }

    protected int getOriginX() {
        return (width - xSize) / 2;
    }

    protected int getOriginY() {
        return (height - ySize) / 2;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(Optional.ofNullable(container.tile.getDisplayName()).map(ITextComponent::getUnformattedText).orElse(""), 8, 6, HiiragiColor.DARK_GRAY.getRGB());
        fontRenderer.drawString(container.inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, HiiragiColor.DARK_GRAY.getRGB());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(backGround);
        this.drawTexturedModalRect(getOriginX(), getOriginY(), 0, 0, xSize, ySize);
    }

}