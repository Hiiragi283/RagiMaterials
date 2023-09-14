package hiiragi283.material.api.tile

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.util.HiiragiNBTKey
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
        material = HiiragiRegistries.MATERIAL.getValue(compound.getString(HiiragiNBTKey.MATERIAL))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        material?.let { compound.setString(HiiragiNBTKey.MATERIAL, it.name) }
        return super.writeToNBT(compound)
    }

    override fun onTilePlaced(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
        material = HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
    }

}