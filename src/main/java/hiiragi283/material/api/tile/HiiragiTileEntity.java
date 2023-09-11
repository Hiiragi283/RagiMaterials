package hiiragi283.material.api.tile;

import hiiragi283.material.HiiragiGuiHandler;
import hiiragi283.material.RagiMaterials;
import hiiragi283.material.network.HiiragiMessage;
import hiiragi283.material.network.HiiragiNetworkManager;
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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
public abstract class HiiragiTileEntity extends TileEntity {

    //    General    //

    public @NotNull IBlockState getState() {
        return hasWorld() ? world.getBlockState(pos) : Objects.requireNonNull(Blocks.AIR).getDefaultState();
    }

    protected void openGui(EntityPlayer player, World world, BlockPos pos) {
        player.openGui(RagiMaterials.INSTANCE, HiiragiGuiHandler.TILE_ENTITY, world, pos.getX(), pos.getY(), pos.getZ());
    }

    //    NBT    //

    @NotNull
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    //    Packet    //

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    public void sendToClient() {
        sendToClient(new HiiragiMessage.Client(getPos(), getUpdateTag()));
    }

    public void sendToClient(IMessage message) {
        HiiragiNetworkManager.INSTANCE.sendToAll(message);
    }

    public void sendToServer() {
        sendToServer(new HiiragiMessage.Server(getPos(), getUpdateTag()));
    }

    public void sendToServer(IMessage message) {
        HiiragiNetworkManager.INSTANCE.sendToServer(message);
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

    @ParametersAreNonnullByDefault
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