package hiiragi283.material.api.gui;

import hiiragi283.material.api.container.HiiragiContainer;
import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.util.HiiragiColor;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import javax.annotation.Nullable;
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
        //drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        //renderHoveredToolTip(mouseX, mouseY);
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

    protected void drawFluid(IFluidTank tank, int x, int y) {
        drawFluid(tank.getFluid(), x, y);
    }

    protected void drawFluid(@Nullable FluidStack fluidStack, int x, int y) {
        if (fluidStack == null) return;
        drawFluid(fluidStack.getFluid(), x, y);
    }

    protected void drawFluid(Fluid fluid, int x, int y) {
        HiiragiColor.setGLColor(fluid.getColor());
        HiiragiUtil.drawSquareTexture(mc, getOriginX() + x, getOriginY() + y, fluid.getStill());
        HiiragiColor.setGLColor(HiiragiColor.WHITE);
    }

}