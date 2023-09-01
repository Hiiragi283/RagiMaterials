package hiiragi283.material.api.tile;

import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class MaterialTileEntity extends HiiragiTileEntity {

    public HiiragiMaterial material = HiiragiMaterial.EMPTY;

    //    HiiragiTileEntity    //

    @Override
    public void readFromNBT(@NotNull NBTTagCompound compound) {
        material = HiiragiMaterial.REGISTRY.getValue(compound.getString(TileKeys.MATERIAL));
        super.readFromNBT(compound);
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        compound.setString(TileKeys.MATERIAL, material.name());
        return super.writeToNBT(compound);
    }

    @Override
    public void onTilePlaced(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        material = HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata());
    }

}