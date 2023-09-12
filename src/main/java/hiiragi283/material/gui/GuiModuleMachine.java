package hiiragi283.material.gui;

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.gui.HiiragiGuiContainer;
import hiiragi283.material.container.ContainerModuleMachine;
import hiiragi283.material.tile.TileEntityModuleMachine;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiModuleMachine extends HiiragiGuiContainer<TileEntityModuleMachine> {

    public static final ResourceLocation TEXTURE = HiiragiUtil.getLocation("textures/gui/module_machine.png");

    public GuiModuleMachine(TileEntityModuleMachine tile, EntityPlayer player) {
        super(new ContainerModuleMachine(tile, player));
        this.backGround = TEXTURE;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        //Input Tanks
        if (isPointInRegion(getSlotPositionX(1), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput0, mouseX, mouseY);
        }
        if (isPointInRegion(getSlotPositionX(2), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput1, mouseX, mouseY);
        }
        if (isPointInRegion(getSlotPositionX(3), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankInput2, mouseX, mouseY);
        }
        //Output Tanks
        if (isPointInRegion(getSlotPositionX(5), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankOutput0, mouseX, mouseY);
        }
        if (isPointInRegion(getSlotPositionX(6), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankOutput1, mouseX, mouseY);
        }
        if (isPointInRegion(getSlotPositionX(7), getSlotPositionY(2), 16, 16, mouseX, mouseY)) {
            drawFluidTooltip(container.tile.tankOutput2, mouseX, mouseY);
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
        //Input Tanks
        drawFluid(container.tile.tankInput0, getSlotPositionX(1), getSlotPositionY(2));
        drawFluid(container.tile.tankInput1, getSlotPositionX(2), getSlotPositionY(2));
        drawFluid(container.tile.tankInput2, getSlotPositionX(3), getSlotPositionY(2));
        //Output Tanks
        drawFluid(container.tile.tankOutput0, getSlotPositionX(5), getSlotPositionY(2));
        drawFluid(container.tile.tankOutput1, getSlotPositionX(6), getSlotPositionY(2));
        drawFluid(container.tile.tankOutput2, getSlotPositionX(7), getSlotPositionY(2));
        //Progress Arrow
        drawTexturedModalRect(getSlotPositionX(4), getSlotPositionY(1), xSize, 0, (int) (18 * container.tile.getProgress()), 18);
    }

}