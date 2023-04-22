package ragi_materials.main.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumFacing
import ragi_materials.core.RagiMaterials
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.metallurgy.container.ContainerFirebox

class TileFireboxPrimitive : TileFireboxBase(1800 * 64) {

    val input = RagiItemHandler(1).setIOType(EnumIOType.INPUT)
    val inventory = RagiItemHandlerWrapper(input)

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

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerFirebox(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:firebox_primitive"

    //    ITickable    //

    override fun update() {
        if (!world.isRemote) {
            //外部への熱の搬出は1秒ごと
            if (count % 20 == 0) world.getTileEntity(pos.add(0, 1, 0))?.let { heat.extractHeatTo(it, EnumFacing.DOWN, false) }
            //燃料の補給は5秒ごと
            if (count >= 100) {
                val stack = input.getStackInSlot(0)
                if (!stack.isEmpty) {
                    val burnTime = TileEntityFurnace.getItemBurnTime(stack)
                    if (burnTime > 0) {
                        input.extractItem(0, 1, false)
                        heat.receiveHeat(burnTime, false)
                    }
                }
                count = 0
            } else count++
        }
    }
}