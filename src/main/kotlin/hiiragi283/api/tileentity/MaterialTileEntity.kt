package hiiragi283.api.tileentity

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class MaterialTileEntity : HiiragiTileEntity() {

    var material: HiiragiMaterial = HiiragiMaterial.EMPTY

    //    HiiragiTileEntity    //

    override fun readFromNBT(compound: NBTTagCompound) {
        material = MaterialRegistry.getMaterial(compound.getString(TileKey.MATERIAL))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setString(TileKey.MATERIAL, material.name)
        return super.writeToNBT(compound)
    }

    override fun onTilePlaced(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
        material = MaterialRegistry.getMaterial(stack.metadata)
    }

}