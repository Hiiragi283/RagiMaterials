package hiiragi283.material.api.machine;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public interface IModuleMachineTile extends IMachineProperty {

    int getProcessTime(World world, BlockPos pos);

    int getEnergyRate(World world, BlockPos pos);

    int getItemSlotCounts(World world, BlockPos pos);

    int getFluidSlotCounts(World world, BlockPos pos);

    Set<ModuleTraits> getModuleTraits(World world, BlockPos pos);

}