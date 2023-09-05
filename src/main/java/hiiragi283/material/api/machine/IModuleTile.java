package hiiragi283.material.api.machine;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IModuleTile extends IModuleBase {

    int getProcessTime(World world, BlockPos pos);

    int getEnergyRate(World world, BlockPos pos);

    int getEnergyCapacity(World world, BlockPos pos);

    int getItemSlotCounts(World world, BlockPos pos);

    int getFluidSlotCounts(World world, BlockPos pos);

}