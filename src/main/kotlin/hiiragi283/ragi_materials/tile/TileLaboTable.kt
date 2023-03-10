package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.packet.MessageLabo
import hiiragi283.ragi_materials.packet.RagiPacket
import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipeRegistry
import hiiragi283.ragi_materials.util.RagiInventory
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import hiiragi283.ragi_materials.util.RagiUtil.toBracket
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper

class TileLaboTable : TileBase(100), ISidedInventory {

    val invLabo = RagiInventory("gui.ragi_materials.laboratory_table", 5)
    private val handlerSide = SidedInvWrapper(this, EnumFacing.NORTH)

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        ItemStackHelper.saveAllItems(tag, invLabo.inventory) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        ItemStackHelper.loadAllItems(tag, invLabo.inventory) //tagからインベントリを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing !== null && facing != EnumFacing.UP && facing != EnumFacing.DOWN) this.handlerSide as T else super.getCapability(capability, facing)
        } else super.getCapability(capability, facing)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            facing !== null && facing != EnumFacing.UP && facing != EnumFacing.DOWN
        } else false
    }

    //    ITileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        val stack = player.getHeldItem(hand)
        var result = false
        //手持ちのItemStackが空の場合
        if (!stack.isEmpty) {
            for (i in 0 until invLabo.slots) {
                //スロットにItemStackを入れた際の余りを取得
                val stackRemain = handlerSide.insertItem(i, stack, true)
                //投入の前後でItemStackが変化しない -> スロットは埋まっている
                //投入の前後でItemStackが変化した -> スロットに空きがある
                if (!RagiUtil.isSameStack(stack, stackRemain, true)) {
                    player.setHeldItem(hand, handlerSide.insertItem(i, stack, false)) //プレイヤーの手持ちを上書き
                    world.playSound(null, pos, RagiUtil.getSound("minecraft:entity.itemframe.add_item"), SoundCategory.BLOCKS, 1.0f, 1.0f)
                    markDirty()
                    RagiLogger.infoDebug("Stack Inserted to slot$i!")
                    break
                } else RagiLogger.infoDebug("The slot$i is full!")
            }
            result = true
        }
        return result
    }

    //    Recipe    //

    fun chemicalReaction(world: World, pos: BlockPos) {
        var isFailed = true
        //サーバー側，かつインベントリが空でない場合
        if (!world.isRemote && !this.invLabo.isEmpty) {
            //レシピチェック
            for (recipe in LaboRecipeRegistry.map.values) {
                if (recipe.match(this.handlerSide, true)) {
                    isFailed = false
                    for (output in recipe.outputs) {
                        RagiUtil.dropItem(world, pos, output)
                        RagiLogger.infoDebug("The stack is ${output.toBracket()}")
                    }
                    RagiUtil.soundHypixel(world, pos)
                    RagiLogger.infoDebug("Succeeded!")
                    break
                }
            }
            RagiLogger.infoDebug("$isFailed")
            //失敗時の処理
            if (isFailed) {
                RagiUtil.dropItem(world, pos, ItemStack(RagiInit.ItemWaste, 1, 0))
                world.playSound(null, pos, RagiUtil.getSound("minecraft:entity.generic.explode"), SoundCategory.BLOCKS, 1.0f, 1.0f)
                RagiLogger.infoDebug("Failed...!")
            }
            this.invLabo.clear() //反応結果によらずインベントリを空にする
        }
        RagiPacket.wrapper.sendToAll(MessageLabo(this.pos)) //クライアント側にパケットを送る
    }

    //    ISidedInventory    //
    //基本的にRagiInventoryと同じ

    override fun getName(): String = this.invLabo.title

    override fun hasCustomName(): Boolean = false

    override fun getSizeInventory(): Int = this.invLabo.sizeInventory

    override fun isEmpty(): Boolean = this.invLabo.isEmpty

    override fun getStackInSlot(index: Int): ItemStack = this.invLabo.getStackInSlot(index)

    override fun decrStackSize(index: Int, count: Int): ItemStack = this.invLabo.decrStackSize(index, count)

    override fun removeStackFromSlot(index: Int): ItemStack = this.invLabo.removeStackFromSlot(index)

    override fun setInventorySlotContents(index: Int, stack: ItemStack) = this.invLabo.setInventorySlotContents(index, stack)

    override fun getInventoryStackLimit(): Int = this.invLabo.inventoryStackLimit

    override fun isUsableByPlayer(player: EntityPlayer): Boolean = this.invLabo.isUsableByPlayer(player)

    override fun openInventory(player: EntityPlayer) {}

    override fun closeInventory(player: EntityPlayer) {}

    override fun isItemValidForSlot(index: Int, stack: ItemStack): Boolean = this.invLabo.isItemValidForSlot(index, stack)

    override fun getField(id: Int): Int = this.invLabo.getField(id)

    override fun setField(id: Int, value: Int) {}

    override fun getFieldCount(): Int = this.invLabo.fieldCount

    override fun clear() {
        this.invLabo.clear()
    }

    override fun getSlotsForFace(side: EnumFacing): IntArray {
        //上面または下面の場合，どのスロットも干渉できない
        return if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
            intArrayOf()
        } else intArrayOf(0, 1, 2, 3, 4)
    }

    override fun canInsertItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = direction != EnumFacing.UP && direction != EnumFacing.DOWN //上面または下面でない場合，搬入可能

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = false //いかなる場合でも搬出不可能

}