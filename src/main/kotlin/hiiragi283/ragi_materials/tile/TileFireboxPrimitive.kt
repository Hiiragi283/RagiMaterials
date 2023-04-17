package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.capability.EnumIOType
import hiiragi283.ragi_materials.api.capability.heat.HeatStorage
import hiiragi283.ragi_materials.api.capability.item.RagiItemHandler
import hiiragi283.ragi_materials.api.capability.item.RagiItemHandlerWrapper
import hiiragi283.ragi_materials.container.ContainerFirebox
import hiiragi283.ragi_materials.proxy.CommonProxy
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TileFireboxPrimitive: TileItemHandlerBase(), ITickable {

    val input = RagiItemHandler(1).setIOType(EnumIOType.INPUT)
    val inventory = RagiItemHandlerWrapper(input)
    var count = 0
    val heat = object : HeatStorage(10000) {

        override fun onOverheated() {
            if (hasWorld() && !world.isRemote) {
                world.destroyBlock(pos, false) //自身を破壊
                world.removeTileEntity(pos) //Tile Entityも削除
                world.createExplosion(null, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 4.0f, false) //爆破オチなんてサイテー!
            }
        }
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, inventory.serializeNBT())
        tag.setTag(keyHeat, heat.serializeNBT())
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory))
        heat.deserializeNBT(tag.getCompoundTag(keyHeat))
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    TileItemHandlerBase    //

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.firebox"

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerFirebox(player, this)

    override fun getGuiID() =  "${RagiMaterials.MOD_ID}:firebox_primitive"

    //    ITickable    //

    override fun update() {
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