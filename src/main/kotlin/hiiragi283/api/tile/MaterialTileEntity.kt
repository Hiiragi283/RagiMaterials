package hiiragi283.api.tile

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.HiiragiMaterial
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
        material = HiiragiRegistry.getMaterial(compound.getString(TileKey.MATERIAL))
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
        material = HiiragiRegistry.getMaterial(stack.metadata)
    }

}