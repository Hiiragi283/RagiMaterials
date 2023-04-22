package ragi_materials.main.tile

import ragi_materials.core.RagiMaterials
import ragi_materials.core.capability.heat.HeatStorage
import ragi_materials.core.proxy.CommonProxy
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import ragi_materials.core.tile.TileItemHandlerBase

abstract class TileFireboxBase(heat: Int) : TileItemHandlerBase(), ITickable {

    var count = 0
    val heat = object : HeatStorage(heat) {

        override fun onOverheated() {
            if (hasWorld() && !world.isRemote) {
                world.createExplosion(null, pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5, 4.0f, false) //爆破オチなんてサイテー!
                world.destroyBlock(pos, true) //自身を破壊
                world.removeTileEntity(pos) //Tile Entityも削除
            }
        }
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyHeat, heat.serializeNBT())
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        heat.deserializeNBT(tag.getCompoundTag(keyHeat))
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    TileItemHandlerBase    //

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.firebox"

}