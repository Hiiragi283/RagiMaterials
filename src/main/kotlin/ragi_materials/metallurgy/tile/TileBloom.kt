package ragi_materials.metallurgy.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.tile.TileBase

class TileBloom : TileBase() {

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound) = tag.also {
        super.writeToNBT(it)
        material.writeToNBT(it)
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        material = RagiMaterial.readFromNBT(tag)
    }

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        return false
    }

}