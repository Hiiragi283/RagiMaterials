package hiiragi283.material.util;

import com.github.bsideup.jabel.Desugar;
import hiiragi283.material.RMReference;
import hiiragi283.material.RagiMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Desugar
public abstract class HiiragiUtil {

    //    Drop    //

    public static void dropItemAtPlayer(EntityPlayer player, ItemStack stack, Vec3d motion) {
        dropItem(player.getEntityWorld(), player.getPosition(), stack, motion);
    }

    public static void dropInventoryItems(World world, BlockPos pos, IItemHandler inventory, Vec3d motion) {
        for (int slot = 0; slot < inventory.getSlots(); slot++) {
            dropItem(world, pos, inventory.getStackInSlot(slot), motion);
        }
    }

    public static void dropItemFromTile(World world, BlockPos pos, ItemStack stack, Vec3d motion, TileEntity tile) {
        stack.getOrCreateSubCompound("BlockEntityTag").merge(tile.getUpdateTag());
        dropItem(world, pos, stack, motion);
    }

    public static void dropItem(World world, BlockPos pos, ItemStack stack, Vec3d motion) {
        if (!world.isRemote && !stack.isEmpty()) {
            var drop = new EntityItem(world, pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f, stack);
            drop.setPickupDelay(0);
            drop.motionX = motion.x;
            drop.motionY = motion.y;
            drop.motionZ = motion.z;
            world.spawnEntity(drop);
        }
    }

    //    FML    //

    public static boolean isClient() {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public static boolean isDeobf() {
        return FMLLaunchHandler.isDeobfuscatedEnvironment();
    }

    //    ItemStack    //

    public static ItemStack getItemStack(ResourceLocation location, int amount, int meta) {
        Optional<Block> block = getEntry(ForgeRegistries.BLOCKS, location);
        Optional<Item> item = getEntry(ForgeRegistries.ITEMS, location);
        return block.map(b -> new ItemStack(b, amount, meta)).orElseGet(() -> item.map(i -> new ItemStack(i, amount, meta)).orElse(ItemStack.EMPTY));
    }

    public static boolean isSameWithoutCount(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == stack2.getMetadata() || stack1.getMetadata() == 32767 || stack2.getMetadata() == 32767);
    }

    public static boolean isSame(ItemStack stack1, ItemStack stack2) {
        return isSameWithoutCount(stack1, stack2) && stack1.getCount() == stack2.getCount();
    }

    public static boolean isSameWithNBT(ItemStack stack1, ItemStack stack2) {
        return isSame(stack1, stack2) && stack1.getTagCompound() == stack2.getTagCompound();
    }

    public static ItemStack toItemStack(IBlockState state) {
        return toItemStack(state, 1);
    }

    public static ItemStack toItemStack(IBlockState state, int count) {
        return new ItemStack(state.getBlock(), count, state.getBlock().getMetaFromState(state));
    }

    //    Model    //

    public static void setModel(Block block) {
        Item item = Item.getItemFromBlock(block);
        if (item != Items.AIR) {
            setModel(item);
        }
    }

    public static void setModel(Item item) {
        //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
        if (item.getMaxDamage(new ItemStack(item)) == 0 && item.getHasSubtypes()) {
            //メタデータが最大値になるまで処理を繰り返す
            for (int i = 0; i <= item.getMetadata(32768); i++) {
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(appendPath(Objects.requireNonNull(item.getRegistryName()), "_" + i), "inventory"));
            }
        } else {
            //1つだけ登録する
            setModelSame(item);
        }
    }

    public static void setModelSame(Block block) {
        Item item = Item.getItemFromBlock(block);
        if (item != Items.AIR) {
            setModelSame(item);
        }
    }

    public static void setModelSame(Item item) {
        ModelLoader.setCustomMeshDefinition(item, stack -> new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }

    //    Ore Dictionary    //

    public static ItemStack findItemStack(String oreDict, String primalMod, String secondaryMod) {
        return findItemStack(OreDictionary.getOres(oreDict), primalMod, secondaryMod);
    }

    public static ItemStack findItemStack(List<ItemStack> stacks, String primalMod, String secondaryMod) {
        if (stacks.isEmpty()) return ItemStack.EMPTY;
        Optional<ItemStack> primalStack = stacks.stream()
                .filter(stack -> !stack.isEmpty())
                .filter(stack -> Objects.requireNonNull(stack.getItem().getCreatorModId(stack)).equals(primalMod))
                .findFirst();
        Optional<ItemStack> secondaryStack = stacks.stream()
                .filter(stack -> !stack.isEmpty())
                .filter(stack -> Objects.requireNonNull(stack.getItem().getCreatorModId(stack)).equals(secondaryMod))
                .findFirst();
        return primalStack.orElseGet(() -> secondaryStack.orElse(stacks.get(0)));
    }

    public static void registerOreDict(String oreDict, ItemStack stack, @Nullable String shareDict) {
        OreDictionary.registerOre(oreDict, stack);
        if (shareDict != null) shareOreDict(oreDict, shareDict);
    }

    public static void shareOreDict(String oreDict1, String oreDict2) {
        OreDictionary.getOres(oreDict1).forEach(stack1 -> OreDictionary.registerOre(oreDict2, stack1));
        OreDictionary.getOres(oreDict2).forEach(stack2 -> OreDictionary.registerOre(oreDict1, stack2));
    }

    //    Registry    //

    public static <T extends IForgeRegistryEntry<T>> @NotNull Optional<T> getEntry(IForgeRegistry<T> registry, String location) {
        return getEntry(registry, new ResourceLocation(location));
    }

    public static <T extends IForgeRegistryEntry<T>> @NotNull Optional<T> getEntry(IForgeRegistry<T> registry, ResourceLocation location) {
        T ret = registry.getValue(location);
        return ret == null ? Optional.empty() : Optional.of(ret);
    }

    public static <T extends IForgeRegistryEntry<T>> boolean removeEntry(IForgeRegistry<T> registry, String location) {
        return removeEntry(registry, new ResourceLocation(location));
    }

    public static <T extends IForgeRegistryEntry<T>> boolean removeEntry(IForgeRegistry<T> registry, ResourceLocation location) {
        if (registry instanceof IForgeRegistryModifiable<?>) {
            ((IForgeRegistryModifiable<?>) registry).remove(location);
            RagiMaterials.LOGGER.info("The entry " + location + "was removed from " + registry.getClass().getName() + "!");
            return true;
        } else {
            RagiMaterials.LOGGER.warn("The registry " + registry.getClass().getName() + " is not implementing IForgeRegistryModifiable!");
            return false;
        }
    }

    //    ResourceLocation    //

    public static @NotNull ResourceLocation appendPath(@NotNull ResourceLocation location, String path) {
        return new ResourceLocation(location.getNamespace(), location.getPath() + path);
    }

    public static @NotNull ResourceLocation appendPathBefore(@NotNull ResourceLocation location, String path) {
        return new ResourceLocation(location.getNamespace(), path + location.getPath());
    }

    public static @NotNull ResourceLocation getLocation(String path) {
        return new ResourceLocation(RMReference.MOD_ID, path);
    }

    public static @NotNull ResourceLocation toLocation(ItemStack stack) {
        return toLocation(stack, ':');
    }

    public static @NotNull ResourceLocation toLocation(ItemStack stack, Character split) {
        return appendPath(Objects.requireNonNull(stack.getItem().getRegistryName()), split.toString() + stack.getMetadata());
    }

    //    TileEntity    //

    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> @NotNull Optional<T> getTile(@Nullable IBlockAccess world, @Nullable BlockPos pos, Class<T> clazz) {
        if (world == null || pos == null) return Optional.empty();
        TileEntity tile = world.getTileEntity(pos);
        return clazz.isInstance(tile) ? (Optional<T>) Optional.of(tile) : Optional.empty();
    }

    //    Misc    //

    public static void executeCommand(ICommandSender sender, String command) {
        IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
        if (server != null) {
            server.commandManager.executeCommand(sender, command);
        }
    }

}