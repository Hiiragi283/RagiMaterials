package hiiragi283.material.tile;

import hiiragi283.material.api.tile.HiiragiTileEntity;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidUtil;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TileEntityMachineExtender extends HiiragiTileEntity {

    //    Capability    //

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return OptionalUtil.getTile(world, getPos().offset(getState().getValue(BlockHorizontal.FACING)))
                .map(tile -> tile.hasCapability(capability, facing))
                .orElse(super.hasCapability(capability, facing));
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return OptionalUtil.getTile(world, getPos().offset(getState().getValue(BlockHorizontal.FACING)))
                .map(tile -> tile.getCapability(capability, facing))
                .orElse(super.getCapability(capability, facing));
    }

    //    Event    //

    @Override
    public boolean onTileActivated(World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing) {
        if (!world.isRemote) {
            return FluidUtil.interactWithFluidHandler(player, hand, world, pos.offset(getState().getValue(BlockHorizontal.FACING)), getState().getValue(BlockHorizontal.FACING).getOpposite());
        }
        return true;
    }

}