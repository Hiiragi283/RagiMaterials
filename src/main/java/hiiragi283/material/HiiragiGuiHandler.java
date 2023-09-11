package hiiragi283.material;

import hiiragi283.material.tile.TileEntityModuleMachine;
import hiiragi283.material.container.ContainerModuleInstaller;
import hiiragi283.material.container.ContainerModuleMachine;
import hiiragi283.material.gui.GuiModuleInstaller;
import hiiragi283.material.gui.GuiModuleMachine;
import hiiragi283.material.tile.TileEntityModuleInstaller;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import org.jetbrains.annotations.Nullable;

public class HiiragiGuiHandler implements IGuiHandler {

    public static int TILE_ENTITY = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Container container = null;
        if (ID == TILE_ENTITY) {
            TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
            if (tile != null) {
                if (tile instanceof TileEntityModuleInstaller)
                    container = new ContainerModuleInstaller((TileEntityModuleInstaller) tile, player);
                if (tile instanceof TileEntityModuleMachine)
                    container = new ContainerModuleMachine((TileEntityModuleMachine) tile, player);
            }
        }
        return container;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Gui gui = null;
        if (ID == TILE_ENTITY) {
            TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
            if (tile != null) {
                if (tile instanceof TileEntityModuleInstaller)
                    gui = new GuiModuleInstaller((TileEntityModuleInstaller) tile, player);
                if (tile instanceof TileEntityModuleMachine)
                    gui = new GuiModuleMachine((TileEntityModuleMachine) tile, player);
            }
        }
        return gui;
    }

}