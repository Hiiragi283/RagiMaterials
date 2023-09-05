package hiiragi283.material.api.tile;

import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class MaterialTileEntity extends HiiragiTileEntity {

    public HiiragiMaterial MATERIAL = HiiragiMaterial.EMPTY;

    //    HiiragiTileEntity    //

    @Override
    public void readFromNBT(@NotNull NBTTagCompound compound) {
        MATERIAL = HiiragiMaterial.REGISTRY.getValue(compound.getString(HiiragiUtil.MATERIAL));
        super.readFromNBT(compound);
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        compound.setString(HiiragiUtil.MATERIAL, MATERIAL.name());
        return super.writeToNBT(compound);
    }

    @Override
    public void onTilePlaced(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        MATERIAL = HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata());
    }

}