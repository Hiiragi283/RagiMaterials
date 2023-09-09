package hiiragi283.material.gui;

import hiiragi283.material.api.gui.HiiragiGuiContainer;
import hiiragi283.material.api.tile.TileEntityModuleMachine;
import hiiragi283.material.container.ContainerModuleMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiModuleMachine extends HiiragiGuiContainer<TileEntityModuleMachine> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");

    public GuiModuleMachine(TileEntityModuleMachine tile, EntityPlayer player) {
        super(new ContainerModuleMachine(tile, player));
        this.backGround = TEXTURE;
    }

}