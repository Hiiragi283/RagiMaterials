package hiiragi283.material.block;

import hiiragi283.material.api.block.HiiragiBlockContainer;
import hiiragi283.material.api.item.ModuleMachineItemBlock;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.tile.TileEntityModuleMachine;
import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BlockModuleMachine extends HiiragiBlockContainer.Holdable<TileEntityModuleMachine> {

    public BlockModuleMachine(String id) {
        super(Material.IRON, id, TileEntityModuleMachine.class);
        this.itemBlock = new ModuleMachineItemBlock(this);
        setDefaultState(getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH));
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(getStackWithProperty(world, pos));
    }

    @NotNull
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return getStackWithProperty(world, pos);
    }

    protected ItemStack getStackWithProperty(IBlockAccess world, BlockPos pos) {
        var stack = new ItemStack(this, 1, 0);
        OptionalUtil.getTile(world, pos, tileClazz).ifPresent(tile -> {
            NBTTagCompound tagInner = stack.getOrCreateSubCompound(HiiragiUtil.MACHINE_PROPERTY);
            tagInner.setInteger(IMachineProperty.KEY_PROCESS, tile.getProcessTime());
            tagInner.setInteger(IMachineProperty.KEY_RATE, tile.getEnergyRate());
            tagInner.setInteger(IMachineProperty.KEY_ITEM, tile.getItemSlotCounts());
            tagInner.setInteger(IMachineProperty.KEY_FLUID, tile.getFluidSlotCounts());
            var tag = new NBTTagCompound();
            tag.setTag(HiiragiUtil.MACHINE_PROPERTY, tagInner);
            stack.setTagCompound(tag);
        });
        return stack;
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