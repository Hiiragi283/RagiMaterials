package hiiragi283.material.api.block;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.registry.HiiragiEntry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class HiiragiBlock extends Block implements HiiragiEntry.BLOCK {

    public HiiragiBlock(Material materialIn, String id) {
        super(materialIn);
        setCreativeTab(CreativeTabs.MISC);
        setRegistryName(RMReference.MOD_ID, id);
        setTranslationKey(id);
    }

    @Override
    public @NotNull Item getItemDropped(@NotNull IBlockState state, @NotNull Random rand, int fortune) {
        return asItem();
    }

}