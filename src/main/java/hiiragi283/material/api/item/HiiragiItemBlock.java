package hiiragi283.material.api.item;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.block.HiiragiBlock;
import hiiragi283.material.api.registry.HiiragiEntry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HiiragiItemBlock extends ItemBlock implements HiiragiEntry.ITEM {

    public final int maxMeta;

    public HiiragiItemBlock(HiiragiBlock block, int maxMeta) {
        super(block);
        setCreativeTab(CreativeTabs.MISC);
        setHasSubtypes(maxMeta > 0);
        setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        this.maxMeta = Math.max(0, maxMeta);
    }

    //    General    //

    @Nullable
    @Override
    public String getCreatorModId(@NotNull ItemStack itemStack) {
        return RMReference.MOD_ID;
    }

    @Override
    public int getMetadata(int damage) {
        return damage >= 0 && damage <= maxMeta ? damage : maxMeta;
    }

    @Override
    public @NotNull String getTranslationKey(@NotNull ItemStack stack) {
        var builder = new StringBuilder();
        builder.append(super.getTranslationKey());
        if (hasSubtypes) {
            builder.append("_");
            builder.append(stack.getMetadata());
        }
        return builder.toString();
    }

    //    Client    //

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
            if (hasSubtypes) {
                for (int i = 0; i < maxMeta; i++) {
                    items.add(new ItemStack(this, 1, i));
                }
            }
        }
    }

    //    HiiragiEntry    //


    @Override
    public void registerModel() {
    }

}