package hiiragi283.material.util;

import com.github.bsideup.jabel.Desugar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Desugar
public record ItemStackComparable(Item item, int count, int meta) {

    public ItemStackComparable(ItemStack stack) {
        this(stack.getItem(), stack.getCount(), stack.getMetadata());
    }

    public ItemStack toStack() {
        return new ItemStack(item, count, meta);
    }

}