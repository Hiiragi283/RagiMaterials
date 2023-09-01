package hiiragi283.material;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HiiragiCreativeTabs {

    public static final CreativeTabs COMMON = new CreativeTabs(RMReference.MOD_ID + ".common") {

        @Override
        public @NotNull ItemStack createIcon() {
            return ItemStack.EMPTY;
        }

    };

    public static final CreativeTabs MATERIAL_BLOCK = new CreativeTabs(RMReference.MOD_ID + ".material_block") {

        @Override
        public @NotNull ItemStack createIcon() {
            return new ItemStack(Objects.requireNonNull(Blocks.IRON_BLOCK));
        }

    };

    public static final CreativeTabs MATERIAL_ITEM = new CreativeTabs(RMReference.MOD_ID + ".material_item") {

        @Override
        public @NotNull ItemStack createIcon() {
            return new ItemStack(Objects.requireNonNull(Items.IRON_INGOT));
        }

    };

}