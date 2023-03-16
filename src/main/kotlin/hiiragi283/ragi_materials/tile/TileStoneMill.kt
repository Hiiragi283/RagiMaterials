package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.block.BlockStoneMill
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiResult
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class TileStoneMill: TileBase(105) {

    val inventory = ItemStackHandler(1)

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, this.inventory.serializeNBT())
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        this.inventory.deserializeNBT(tag.getCompoundTag(keyInventory))
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) this.inventory as T else super.getCapability(capability, facing)
    }

    //上面からの干渉は不可能 (完成品をドロップするため)
    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != EnumFacing.UP

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            if (!stack.isEmpty) {
                player.setHeldItem(hand, this.inventory.insertItem(0, stack, false)) //アイテムを搬入
                markDirty()
            } else {
                val state = world.getBlockState(pos)
                val count = state.getValue(BlockStoneMill.COUNT)
                if (count == 7) doProcess()
                world.setBlockState(pos, state.withProperty(BlockStoneMill.COUNT, (count + 1) % 8), 2) //8で割った余りにすることでオーバーフローを防止
            }
        }
        return true
    }

    //    Recipe    //

    private fun doProcess() {
        val stack = this.inventory.getStackInSlot(0)
        val result = getResult(stack)
        if (!result.isEmpty) {
            this.inventory.extractItem(0, 1, false)
            markDirty()
            RagiUtil.dropItem(world, pos.add(0, 1, 0), result)
            RagiResult.succeeded(this)
        } else RagiResult.failed(this)
    }

    private fun getResult(stack: ItemStack): ItemStack {
        val item = stack.item
        var result = ItemStack.EMPTY
        if (item is IMaterialItem && item is ItemMaterial) {
            val part = item.part
            //粉末素材は粉砕不可能
            if (part.type != EnumMaterialType.DUST) {
                val scale = part.scale
                if (scale >= 1) result = MaterialUtil.getPart(PartRegistry.DUST, item.getMaterial(stack), scale.toInt())
            }
        }
        return result
    }
}