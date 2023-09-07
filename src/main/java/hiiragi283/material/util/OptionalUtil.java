package hiiragi283.material.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class OptionalUtil {

    //    BlockState    //

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getBlockImplemented(@NotNull IBlockState state, @NotNull Class<T> clazz) {
        return clazz.isInstance(state.getBlock()) ? (Optional<T>) Optional.of(state.getBlock()) : Optional.empty();
    }

    //    Capability    //

    public static <T> Optional<T> getCapability(@NotNull ICapabilityProvider provider, @NotNull Capability<T> capability, @Nullable EnumFacing facing) {
        return Optional.ofNullable(provider.getCapability(capability, facing));
    }

    //    ItemStack    //

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getItemImplemented(@NotNull ItemStack stack, @NotNull Class<T> clazz) {
        return clazz.isInstance(stack.getItem()) ? (Optional<T>) Optional.of(stack.getItem()) : Optional.empty();
    }

    public static Optional<ItemStack> getStackNotEmpty(@NotNull ItemStack stack) {
        return Optional.of(stack).filter(s -> !s.isEmpty());
    }

    //    Registry    //

    public static <T extends IForgeRegistryEntry<T>> @NotNull Optional<T> getEntry(@NotNull IForgeRegistry<T> registry, @NotNull String location) {
        return getEntry(registry, new ResourceLocation(location));
    }

    public static <T extends IForgeRegistryEntry<T>> @NotNull Optional<T> getEntry(@NotNull IForgeRegistry<T> registry, @NotNull ResourceLocation location) {
        return Optional.ofNullable(registry.getValue(location));
    }

    //    TileEntity    //

    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> Optional<T> getTile(@Nullable IBlockAccess world, @Nullable BlockPos pos, @NotNull Class<T> clazz) {
        if (world == null || pos == null) return Optional.empty();
        return (Optional<T>) Optional.ofNullable(world.getTileEntity(pos)).filter(clazz::isInstance);
    }

}