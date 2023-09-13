package hiiragi283.material.api.tile

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class MaterialTileEntity : HiiragiTileEntity() {

    var material: HiiragiMaterial? = null

    //    HiiragiTileEntity    //

    override fun readFromNBT(compound: NBTTagCompound) {
        material = HiiragiRegistry.getMaterial(compound.getString(TileKey.MATERIAL))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        material?.let { compound.setString(TileKey.MATERIAL, it.name) }
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