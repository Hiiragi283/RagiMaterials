package hiiragi283.material.api.registry;

import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface HiiragiEntry<T extends IForgeRegistryEntry<T>> {

    //    Item    //

    @NotNull
    Item asItem();

    @NotNull
    default ItemStack asItemStack(HiiragiMaterial material) {
        return asItemStack(material, 1);
    }

    @NotNull
    default ItemStack asItemStack(HiiragiMaterial material, int count) {
        return new ItemStack(asItem(), count, material.index());
    }

    @NotNull
    default ItemStack asItemStack(HiiragiPart part) {
        return part.getShape()
                .map(HiiragiShape::scale)
                .filter(scale -> scale >= 144)
                .map(scale -> part.getMaterial()
                        .map(material -> asItemStack(material, scale / 144))
                        .orElse(ItemStack.EMPTY))
                .orElse(ItemStack.EMPTY);
    }

    //    Register    //

    @NotNull
    T getObject();

    @NotNull
    default ResourceLocation getLocation() {
        return Objects.requireNonNull(getObject().getRegistryName());
    }

    default void register(IForgeRegistry<T> registry) {
        registry.register(getObject());
    }

    default void registerOreDict() {
    }

    default void registerRecipe() {
    }

    @SideOnly(Side.CLIENT)
    default void registerBlockColor(BlockColors blockColors) {
    }

    @SideOnly(Side.CLIENT)
    default void registerItemColor(ItemColors itemColors) {
    }

    @SideOnly(Side.CLIENT)
    default void registerModel() {
    }

    interface BLOCK extends HiiragiEntry<Block> {

        @Nullable
        HiiragiItemBlock getItemBlock();

        @Override
        default @NotNull Item asItem() {
            return getItemBlock() != null ? getItemBlock() : Item.getItemFromBlock(getObject());
        }

        @NotNull
        @Override
        default Block getObject() {
            return (Block) this;
        }

        @Override
        @SideOnly(Side.CLIENT)
        default void registerModel() {
            HiiragiUtil.setModel(getObject());
        }

    }

    interface ITEM extends HiiragiEntry<Item> {

        @NotNull
        @Override
        default Item asItem() {
            return getObject();
        }

        @NotNull
        @Override
        default Item getObject() {
            return (Item) this;
        }

        @Override
        @SideOnly(Side.CLIENT)
        default void registerModel() {
            HiiragiUtil.setModel(getObject());
        }

    }

}