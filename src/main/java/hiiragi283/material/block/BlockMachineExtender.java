package hiiragi283.material.block;

import hiiragi283.material.api.block.HiiragiBlockContainer;
import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.tile.TileEntityMachineExtender;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BlockMachineExtender extends HiiragiBlockContainer<TileEntityMachineExtender> {

    public BlockMachineExtender() {
        super(Material.IRON, "machine_extender", TileEntityMachineExtender.class);
        this.itemBlock = new HiiragiItemBlock(this, 0);
        setDefaultState(getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
    }

    @Override
    public int quantityDropped(Random random) {
        return super.quantityDropped(random);
    }

    //    BlockState    //

    @NotNull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BlockHorizontal.FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
    }

    @NotNull
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @NotNull
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(BlockHorizontal.FACING)));
    }

    @NotNull
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(BlockHorizontal.FACING, rot.rotate(state.getValue(BlockHorizontal.FACING)));
    }

}