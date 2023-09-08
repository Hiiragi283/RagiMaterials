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
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MaterialTileEntity extends HiiragiTileEntity implements IMaterialTile {

    @Nullable
    private HiiragiMaterial MATERIAL;

    //    HiiragiTileEntity    //

    @Override
    public void readFromNBT(@NotNull NBTTagCompound compound) {
        HiiragiMaterial.REGISTRY.getValue(compound.getString(HiiragiUtil.MATERIAL))
                .ifPresent(material -> MATERIAL = material);
        super.readFromNBT(compound);
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        Optional.ofNullable(MATERIAL)
                .map(material -> material.name)
                .ifPresent(name -> compound.setString(HiiragiUtil.MATERIAL, name));
        return super.writeToNBT(compound);
    }

    @Override
    public void onTilePlaced(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .ifPresent(material -> MATERIAL = material);
    }

    //    IMaterialTile    //

    @Override
    public Optional<HiiragiMaterial> getMaterial() {
        return Optional.ofNullable(MATERIAL);
    }

}