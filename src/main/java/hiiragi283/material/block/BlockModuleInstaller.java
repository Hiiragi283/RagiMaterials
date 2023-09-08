package hiiragi283.material.block;

import hiiragi283.material.api.block.HiiragiBlockContainer;
import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.tile.TileEntityModuleInstaller;
import net.minecraft.block.material.Material;

public class BlockModuleInstaller extends HiiragiBlockContainer<TileEntityModuleInstaller> {

    public BlockModuleInstaller() {
        super(Material.IRON, "module_installer", TileEntityModuleInstaller.class);
        this.itemBlock = new HiiragiItemBlock(this, 0);
    }

}