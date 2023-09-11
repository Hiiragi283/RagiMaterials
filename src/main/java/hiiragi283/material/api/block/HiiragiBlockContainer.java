package hiiragi283.material.api.block;

import hiiragi283.material.RagiMaterials;
import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
public abstract class HiiragiBlockContainer<T extends HiiragiTileEntity> extends HiiragiBlock implements ITileEntityProvider {

    public final Class<T> tileClazz;

    public HiiragiBlockContainer(Material materialIn, String id, Class<T> tileClazz) {
        super(materialIn, id);
        this.tileClazz = tileClazz;
    }

    public void registerTileEntity() {
        GameRegistry.registerTileEntity(tileClazz, HiiragiUtil.getLocation("te_" + Objects.requireNonNull(getRegistryName()).getPath()));
    }

    //    Event    //

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        OptionalUtil.getTile(world, pos, tileClazz).ifPresent(tile -> tile.onTileRemoved(world, pos, state));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return hand == EnumHand.MAIN_HAND ? OptionalUtil.getTile(world, pos, tileClazz).map(t -> t.onTileActivated(world, pos, player, hand, facing)).orElse(false) : false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        OptionalUtil.getTile(world, pos, tileClazz).ifPresent(tile -> tile.onTilePlaced(world, pos, state, placer, stack));
    }

    //    ITileEntityProvider    //


    @Nullable
    @Override
    public TileEntity createNewTileEntity(@NotNull World world, int meta) {
        try {
            return tileClazz.getConstructor().newInstance();
        } catch (Exception e) {
            RagiMaterials.LOGGER.error(e);
            return null;
        }
    }

    @ParametersAreNonnullByDefault
    public static abstract class Holdable<T extends HiiragiTileEntity> extends HiiragiBlockContainer<T> {

        public Holdable(Material materialIn, String id, Class<T> tileClazz) {
            super(materialIn, id, tileClazz);
        }

        @Override
        public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
            var stack = new ItemStack(this);
            OptionalUtil.getTile(world, pos, tileClazz).ifPresent(tile -> stack.getOrCreateSubCompound("BlockEntityTag").merge(tile.getUpdateTag()));
            drops.add(stack);
        }

        @Override
        public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
            super.harvestBlock(world, player, pos, state, te, stack);
            world.setBlockToAir(pos);
        }

        @Override
        public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
            return willHarvest || super.removedByPlayer(state, world, pos, player, false);
        }

    }

}