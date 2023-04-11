package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.capability.EnumIOType
import hiiragi283.ragi_materials.capability.item.RagiItemHandler
import hiiragi283.ragi_materials.capability.item.RagiItemHandlerWrapper
import hiiragi283.ragi_materials.container.ContainerLaboTable
import hiiragi283.ragi_materials.proxy.CommonProxy
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class TileLaboBase(type: Int) : TileItemHandlerBase(type) {

    val inputs = RagiItemHandler(5).setIOType(EnumIOType.INPUT)
    val catalyst = RagiItemHandler(1).setIOType(EnumIOType.GENERAL)
    val inventory = RagiItemHandlerWrapper(inputs, catalyst)

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, inventory.serializeNBT())
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory))
    }

    //    ITileActivatable    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerLaboTable(player, this)


}