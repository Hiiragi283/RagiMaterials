package hiiragi283.material.block;

import hiiragi283.material.api.block.HiiragiBlockContainer;
import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.tile.TileEntityTestMachine;
import net.minecraft.block.material.Material;

public class BlockTestMachine extends HiiragiBlockContainer.Holdable<TileEntityTestMachine> {

    public BlockTestMachine() {
        super(Material.IRON, "test_machine", TileEntityTestMachine.class);
        this.itemBlock = new HiiragiItemBlock(this, 0);
    }

}