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
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public abstract class OptionalUtil {

    //    BlockState    //

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getBlockImplemented(IBlockState state, Class<T> clazz) {
        return (Optional<T>) Optional.of(state).map(IBlockState::getBlock).filter(clazz::isInstance);
    }

    //    Capability    //

    public static <T> Optional<T> getCapability(ICapabilityProvider provider, Capability<T> capability, @Nullable EnumFacing facing) {
        return Optional.ofNullable(provider.getCapability(capability, facing));
    }

    //    ItemStack    //

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> getItemImplemented(ItemStack stack, Class<T> clazz) {
        return (Optional<T>) Optional.of(stack).map(ItemStack::getItem).filter(clazz::isInstance);
    }

    public static Optional<ItemStack> getStackNotEmpty(ItemStack stack) {
        return Optional.of(stack).filter(s -> !s.isEmpty());
    }

    //    Registry    //

    public static <T extends IForgeRegistryEntry<T>> Optional<T> getEntry(IForgeRegistry<T> registry, String location) {
        return getEntry(registry, new ResourceLocation(location));
    }

    public static <T extends IForgeRegistryEntry<T>> Optional<T> getEntry(IForgeRegistry<T> registry, ResourceLocation location) {
        return Optional.ofNullable(registry.getValue(location));
    }

    //    TileEntity    //

    public static Optional<TileEntity> getTile(@Nullable IBlockAccess world, @Nullable BlockPos pos) {
        return getTile(world, pos, TileEntity.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> Optional<T> getTile(@Nullable IBlockAccess world, @Nullable BlockPos pos, Class<T> clazz) {
        if (world == null || pos == null) return Optional.empty();
        return (Optional<T>) Optional.ofNullable(world.getTileEntity(pos)).filter(clazz::isInstance);
    }

}