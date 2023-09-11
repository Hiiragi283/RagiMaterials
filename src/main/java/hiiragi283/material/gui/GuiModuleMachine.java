package hiiragi283.material.gui;

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.gui.HiiragiGuiContainer;
import hiiragi283.material.container.ContainerModuleMachine;
import hiiragi283.material.tile.TileEntityModuleMachine;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiModuleMachine extends HiiragiGuiContainer<TileEntityModuleMachine> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");

    public GuiModuleMachine(TileEntityModuleMachine tile, EntityPlayer player) {
        super(new ContainerModuleMachine(tile, player));
        this.backGround = TEXTURE;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        //Input Tanks
        if (isPointInRegion(8 + 18 + 1, 18 + 1, 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput0, mouseX, mouseY);
        } else if (isPointInRegion(8 + 18 * 2 + 1, 18 * 2 + 1, 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput1, mouseX, mouseY);
        } else if (isPointInRegion(8 + 18 * 3 + 1, 18 * 3 + 1, 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput2, mouseX, mouseY);
        }
        renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawFluidTooltip(HiiragiFluidTank tank, int mouseX, int mouseY) {
        tank.getFluidOptional().ifPresent(fluidStack -> {
            List<String> list = new ArrayList<>();
            list.add(I18n.format(fluidStack.getFluid().getUnlocalizedName()));
            list.add(fluidStack.amount + " mB");
            drawHoveringText(list, mouseX, mouseY);
        });
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        drawFluid(container.tile.tankInput0, 8 + 18, 18 * 3);
        drawFluid(container.tile.tankInput1, 8 + 18 * 2, 18 * 3);
        drawFluid(container.tile.tankInput2, 8 + 18 * 3, 18 * 3);
    }

}