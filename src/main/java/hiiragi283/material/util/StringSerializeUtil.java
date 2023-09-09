package hiiragi283.material.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Objects;

@ParametersAreNonnullByDefault
public abstract class StringSerializeUtil {

    //    ItemStack    //

    //new ItemStack(Items.IRON_INGOT, 1, 0) -> "minecraft:iron_ingot:1:0"
    public static String serialize(ItemStack stack) {
        return Objects.requireNonNull(stack.getItem().getRegistryName()) + ":" + stack.getCount() + ":" + stack.getMetadata();
    }

    //"minecraft:iron_ingot:1:0" -> new ItemStack(Items.IRON_INGOT, 1, 0)
    public static ItemStack deserializeToItemStack(String value) {
        String[] valueSplit = value.split(":");
        if (valueSplit.length != 4) return ItemStack.EMPTY;
        return HiiragiUtil.getItemStack(new ResourceLocation(valueSplit[0], valueSplit[1]), Integer.parseInt(valueSplit[2]), Integer.parseInt(valueSplit[3]));
    }

    public static String serialize(ItemStack... stacks) {
        return Arrays.stream(stacks).map(StringSerializeUtil::serialize).collect(HiiragiCollectors.STRING_SERIALIZATION_COLLECTOR);
    }

    public static ItemStack[] deserializeToItemStacks(String value) {
        return Arrays.stream(value.split(";"))
                .map(StringSerializeUtil::deserializeToItemStack)
                .toArray(ItemStack[]::new);
    }

}