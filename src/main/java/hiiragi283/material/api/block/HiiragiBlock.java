package hiiragi283.material.api.block;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.api.registry.HiiragiEntry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public abstract class HiiragiBlock extends Block implements HiiragiEntry.BLOCK {

    protected HiiragiItemBlock itemBlock;

    public HiiragiBlock(Material materialIn, String id) {
        super(materialIn);
        setCreativeTab(CreativeTabs.MISC);
        setRegistryName(RMReference.MOD_ID, id);
        setTranslationKey(id);
    }

    @NotNull
    @Override
    public Item getItemDropped(@NotNull IBlockState state, @NotNull Random rand, int fortune) {
        return asItem();
    }

    //    HiiragiEntry    //

    @Nullable
    @Override
    public HiiragiItemBlock getItemBlock() {
        return itemBlock;
    }

}