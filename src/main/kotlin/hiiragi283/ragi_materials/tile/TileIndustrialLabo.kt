package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.capability.RagiBattery
import hiiragi283.ragi_materials.capability.RagiInventory
import hiiragi283.ragi_materials.client.gui.ContainerIndustrialLabo
import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipeRegistry
import hiiragi283.ragi_materials.util.*
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ILockableContainer
import net.minecraft.world.LockCode
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper

class TileIndustrialLabo : TileBase(104), ILockableContainer, ISidedInventory, ITickable {

    val inventory = RagiInventory("gui.ragi_materials.industrial_labo", 10)
    private val battery = RagiBattery(64000)
    private var count = 0
    private var lockCode = LockCode.EMPTY_CODE

    private val inventoryUp = SidedInvWrapper(this, EnumFacing.UP)
    private val inventorySide = SidedInvWrapper(this, EnumFacing.NORTH)
    private val inventoryDown = SidedInvWrapper(this, EnumFacing.DOWN)

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        ItemStackHelper.saveAllItems(tag, inventory.inventory) //インベントリをNBTタグに書き込む
        tag.setTag(keyBattery, this.battery.serializeNBT()) //バッテリーをNBTタグに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        ItemStackHelper.loadAllItems(tag, inventory.inventory) //NBTタグからインベントリを読み込む
        this.battery.deserializeNBT(tag.getCompoundTag(keyBattery)) //NBTタグからバッテリーを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> {
                when (facing) {
                    EnumFacing.UP -> inventoryUp as T
                    EnumFacing.DOWN -> inventoryDown as T
                    else -> inventorySide as T
                }
            }

            CapabilityEnergy.ENERGY -> this.battery as T
            else -> super.getCapability(capability, facing)
        }
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> true
            CapabilityEnergy.ENERGY -> true
            else -> false
        }
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean = false

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //サーバー側，かつインベントリが空でない場合
            if (!world.isRemote && !this.inventory.isEmpty) {
                RagiLogger.infoDebug("Inventory is not empty!")
                for (recipe in LaboRecipeRegistry.map.values) {
                    //レシピチェック&バッテリーも残量が1000 RF以上の場合
                    if (recipe.match(this.inventorySide, false) && battery.energyStored >= 1000) {
                        RagiLogger.infoDebug("can Process!")
                        //出力スロットがすべて空いている場合
                        if (isEmptySlots(5, 6, 7, 8, 9)) {
                            for (i in 0..4) {
                                decrStackSize(i, recipe.inputs[i].count) //入力スロットからアイテムを減らす
                                RagiLogger.infoDebug("The slot$i is decreased!")
                                setInventorySlotContents(i + 5, recipe.outputs[i]) //完成品をスロットに代入
                                RagiLogger.infoDebug("The slot${i + 5} is filled!")
                            }
                            battery.extractEnergy(1000, false)
                            RagiLogger.infoDebug("Succeeded!")
                        } else RagiLogger.infoDebug("The output slot is filled...")
                    }
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }

    //    ILockableContainer    //

    override fun createContainer(inventory: InventoryPlayer, player: EntityPlayer): Container = ContainerIndustrialLabo(this, player)

    override fun getGuiID(): String = "ragi_materials:industrial_labo"

    override fun isLocked(): Boolean = this.lockCode !== null && this.lockCode.isEmpty

    override fun setLockCode(code: LockCode) {
        this.lockCode = code
    }

    override fun getLockCode(): LockCode = this.lockCode

    //    ISidedInventory    //
    //基本的にRagiInventoryと同じ

    override fun getName(): String = this.inventory.title

    override fun hasCustomName(): Boolean = false

    override fun getSizeInventory(): Int = this.inventory.sizeInventory

    override fun isEmpty(): Boolean = this.inventory.isEmpty

    override fun getStackInSlot(index: Int): ItemStack = this.inventory.getStackInSlot(index)

    override fun decrStackSize(index: Int, count: Int): ItemStack = this.inventory.decrStackSize(index, count)

    override fun removeStackFromSlot(index: Int): ItemStack = this.inventory.removeStackFromSlot(index)

    override fun setInventorySlotContents(index: Int, stack: ItemStack) = this.inventory.setInventorySlotContents(index, stack)

    override fun getInventoryStackLimit(): Int = this.inventory.inventoryStackLimit

    override fun isUsableByPlayer(player: EntityPlayer): Boolean = this.inventory.isUsableByPlayer(player)

    override fun openInventory(player: EntityPlayer) {}

    override fun closeInventory(player: EntityPlayer) {}

    override fun isItemValidForSlot(index: Int, stack: ItemStack): Boolean = this.inventory.isItemValidForSlot(index, stack)

    override fun getField(id: Int): Int = this.inventory.getField(id)

    override fun setField(id: Int, value: Int) {}

    override fun getFieldCount(): Int = this.inventory.fieldCount

    override fun clear() {
        this.inventory.clear()
    }

    override fun getSlotsForFace(side: EnumFacing): IntArray {
        return when (side) {
            EnumFacing.UP -> intArrayOf(0, 1, 2, 3, 4) //上面 -> 搬入のみ
            EnumFacing.DOWN -> intArrayOf(5, 6, 7, 8, 9) //下面 -> 搬出のみ
            else -> intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) //それ以外 -> すべて許可
        }
    }

    override fun canInsertItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = direction != EnumFacing.DOWN //下面でない場合，搬入可能

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = direction != EnumFacing.UP //上面でない場合，搬出可能

    //指定したスロットがすべて空か判定するメソッド
    private fun isEmptySlots(vararg slots: Int): Boolean {
        var count = 0
        for (slot in slots) {
            if (getStackInSlot(slot).isEmpty) count++
        }
        return count == slots.size
    }

}