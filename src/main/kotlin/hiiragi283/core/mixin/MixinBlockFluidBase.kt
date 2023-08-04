package hiiragi283.core.mixin

import hiiragi283.api.block.IHeatSource
import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fluids.BlockFluidBase
import net.minecraftforge.fluids.IFluidBlock
import org.spongepowered.asm.mixin.Mixin

@Mixin(BlockFluidBase::class)
abstract class MixinBlockFluidBase : IHeatSource {

    override fun getHeat(world: IBlockAccess, pos: BlockPos, state: IBlockState): Int =
        (this as IFluidBlock).fluid.temperature

}