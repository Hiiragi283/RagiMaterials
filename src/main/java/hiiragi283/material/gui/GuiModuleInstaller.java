package hiiragi283.material.gui;

import hiiragi283.material.api.gui.HiiragiGuiButton;
import hiiragi283.material.api.gui.HiiragiGuiContainer;
import hiiragi283.material.container.ContainerModuleInstaller;
import hiiragi283.material.tile.TileEntityModuleInstaller;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.NotNull;

public class GuiModuleInstaller extends HiiragiGuiContainer<TileEntityModuleInstaller> {

    public static final ResourceLocation TEXTURE = HiiragiUtil.getLocation("textures/gui/module_installer.png");

    private final EntityPlayer player;

    public GuiModuleInstaller(TileEntityModuleInstaller tile, EntityPlayer player) {
        super(new ContainerModuleInstaller(tile, player));
        this.player = player;
        this.backGround = TEXTURE;
    }

    @Override
    public void initGui() {
        super.initGui();
        addButton(new HiiragiGuiButton(this, 0, 8 + 18 * 4, 36 + 18, this.xSize, this.ySize, 16, 16, TEXTURE));
    }

    @Override
    protected void actionPerformed(@NotNull GuiButton button) {
        if (button.id == 0) {
            player.sendStatusMessage(new TextComponentString("Button Pressed!"), false);
        }
    }

}