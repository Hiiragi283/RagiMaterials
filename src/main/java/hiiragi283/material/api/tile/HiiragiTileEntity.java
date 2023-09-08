package hiiragi283.material.api.tile;

import hiiragi283.material.HiiragiGuiHandler;
import hiiragi283.material.RagiMaterials;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper;
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class HiiragiTileEntity extends TileEntity {

    public HiiragiItemHandlerWrapper inventory;
    public HiiragiFluidTankWrapper tank;

    //    General    //

    public @NotNull IBlockState getState() {
        return hasWorld() ? world.getBlockState(pos) : Objects.requireNonNull(Blocks.AIR).getDefaultState();
    }

    protected void openGui(EntityPlayer player, World world, BlockPos pos) {
        player.openGui(RagiMaterials.INSTANCE, HiiragiGuiHandler.TILE_ENTITY, world, pos.getX(), pos.getY(), pos.getZ());
    }

    //    NBT    //

    @Override
    public @NotNull NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    //    Packet    //

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(@NotNull NetworkManager net, @NotNull SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState oldState, @NotNull IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    //    Event    //

    public boolean onTileActivated(World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing) {
        return false;
    }

    public void onTilePlaced(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    }

    public void onTileRemoved(World world, BlockPos pos, IBlockState state) {
    }

    //    Tickable    //

    public static abstract class Tickable extends HiiragiTileEntity implements ITickable {

        public int maxCount;
        private int currentCount = 0;

        public Tickable(int maxCount) {
            this.maxCount = maxCount;
        }

        @Override
        public void update() {
            updatePerTick();
            if (currentCount >= maxCount) {
                if (world.isRemote) {
                    updateClientSide();
                } else {
                    updateServerSide();
                }
                currentCount = 0;
            } else {
                currentCount++;
            }
        }

        public void updatePerTick() {
        }

        public void updateServerSide() {
        }

        public void updateClientSide() {
        }

    }

}