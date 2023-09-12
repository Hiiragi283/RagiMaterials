package hiiragi283.material.block;

import hiiragi283.material.api.block.HiiragiBlockContainer;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.item.ModuleMachineItemBlock;
import hiiragi283.material.tile.TileEntityModuleMachine;
import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
    protected NBTTagCompound getTileNBT(TileEntityModuleMachine tile) {
        var tag = new NBTTagCompound();
        tag.setTag(HiiragiUtil.MACHINE_PROPERTY, super.getTileNBT(tile).getCompoundTag(HiiragiUtil.MACHINE_PROPERTY));
        return tag;
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

    //    HiiragiEntry    //

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockColor(BlockColors blockColors) {
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> OptionalUtil.getTile(world, pos, tileClazz)
                .flatMap(TileEntityModuleMachine::getMaterial)
                .map(material -> material.color)
                .orElse(-1), this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemColor(ItemColors itemColors) {
        itemColors.registerItemColorHandler((stack, tintIndex) -> HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .map(material -> material.color)
                .orElse(-1), this);
    }

}