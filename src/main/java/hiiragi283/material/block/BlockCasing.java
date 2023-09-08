package hiiragi283.material.block;

import hiiragi283.material.api.block.HiiragiBlock;
import hiiragi283.material.api.block.property.HiiragiProperty;
import hiiragi283.material.api.item.HiiragiItemBlock;
import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class BlockCasing extends HiiragiBlock {

    //    Material    //

    private static final Map<Integer, HiiragiMaterial> MAP = new HashMap<>() {{

    }};

    public static @NotNull HiiragiMaterial getMaterialFromMeta(int meta) {
        return MAP.get(meta % MAP.size());
    }

    //    General    //

    public BlockCasing() {
        super(Material.IRON, "casing");
        setDefaultState(getDefaultState().withProperty(HiiragiProperty.TYPE16, 0));
        setHarvestLevel("pickaxe", 0, getStateFromMeta(0));
        setHarvestLevel("pickaxe", 1, getStateFromMeta(1));
        setHarvestLevel("pickaxe", 2, getStateFromMeta(2));
        setHarvestLevel("pickaxe", 1, getStateFromMeta(3));
        setHarvestLevel("pickaxe", 2, getStateFromMeta(4));
        setHarvestLevel("pickaxe", 3, getStateFromMeta(5));
        setHarvestLevel("pickaxe", 3, getStateFromMeta(6));
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    //    BlockState    //

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HiiragiProperty.TYPE16);
    }

    @Override
    public int getMetaFromState(@NotNull IBlockState state) {
        return state.getValue(HiiragiProperty.TYPE16);
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(HiiragiProperty.TYPE16, meta % MAP.size());
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getStateFromMeta(placer.getHeldItem(hand).getMetadata());
    }

    //    HiiragiEntry    //

    @Override
    public @Nullable HiiragiItemBlock getItemBlock() {
        return new HiiragiItemBlock(this, MAP.size() - 1);
    }

    @Override
    public void registerRecipe() {
        super.registerRecipe();
    }
}