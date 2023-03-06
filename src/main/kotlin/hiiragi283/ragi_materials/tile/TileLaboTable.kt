package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.packet.MessageLabo
import hiiragi283.ragi_materials.packet.RagiPacket
import hiiragi283.ragi_materials.recipe.laboratory.LTRegistry
import hiiragi283.ragi_materials.util.RagiInventory
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper

class TileLaboTable : TileEntity(), ISidedInventory {

    val invLabo = RagiInventory("gui.ragi_materials.laboratory_table", 5)
    private val handlerSide = SidedInvWrapper(this, EnumFacing.NORTH)

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound {
        //オーバーライドしないと正常に更新されない
        return writeToNBT(NBTTagCompound())
    }

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        ItemStackHelper.saveAllItems(tag, invLabo.inventory) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        ItemStackHelper.loadAllItems(tag, invLabo.inventory) //tagからインベントリを読み込む
    }

    //    Packet    //

    override fun getUpdatePacket(): SPacketUpdateTileEntity {
        return SPacketUpdateTileEntity(pos, 100, this.updateTag) //NBTタグの情報を送る
    }

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        this.readFromNBT(pkt.nbtCompound) //受け取ったパケットのNBTタグを書き込む
    }

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93
    */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean {
        return oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue
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

    //    Event    //

    fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand) {
        val stack = player.getHeldItem(hand)
        //手持ちのItemStackが空の場合
        if (stack.isEmpty) {/*for (i in 0 until inventory.slots) {
            val numReverse = inventory.slots - (i + 1)
            //スロット内のItemStackを取得 (逆順)
            val stackSlot = inventory.getStackInSlot(numReverse)
            //ItemStackが空でない場合
            if (!stackSlot.isEmpty) {
                //プレイヤーの手持ちを上書き
                player.setHeldItem(hand, inventory.extractItem(i, stackSlot.count, false))
                RagiLogger.infoDebug("Stack extracted from slot$numReverse!")
                break
            } else RagiLogger.infoDebug("The slot$numReverse is empty!")
        }*/
        } else {
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
        }
    }

    //    Recipe    //

    fun chemicalReaction(world: World, pos: BlockPos) {
        var isFailed = true
        //サーバー側，かつインベントリが空でない場合
        if (!world.isRemote && !this.invLabo.isEmpty) {
            //レシピチェック
            for (recipe in LTRegistry.list) {
                if (recipe.match(this.handlerSide)) {
                    isFailed = false
                    val drop = EntityItem(world, pos.x.toDouble() + 0.5, pos.y.toDouble() + 1.0, pos.z.toDouble() + 0.5, recipe.output)
                    drop.setPickupDelay(0) //即座に回収できるようにする
                    drop.motionX = 0.0
                    drop.motionY = 0.25
                    drop.motionZ = 0.0 //ドロップ時の飛び出しを防止
                    world.spawnEntity(drop) //ドロップアイテムをスポーン
                    RagiUtil.soundHypixel(world, pos)
                    RagiLogger.infoDebug("Succeeded!")
                    break
                }
            }
            RagiLogger.infoDebug("$isFailed")
            //失敗時の処理
            if (isFailed) {
                val drop = EntityItem(world, pos.x.toDouble() + 0.5, pos.y.toDouble() + 1.0, pos.z.toDouble() + 0.5, ItemStack(RagiInit.ItemWaste, 1, 0))
                drop.setPickupDelay(0) //即座に回収できるようにする
                drop.motionX = 0.0
                drop.motionY = 0.25
                drop.motionZ = 0.0 //ドロップ時の飛び出しを防止
                world.spawnEntity(drop) //ドロップアイテムをスポーン
                world.playSound(null, pos, RagiUtil.getSound("minecraft:entity.generic.explode"), SoundCategory.BLOCKS, 1.0f, 1.0f)
                RagiLogger.infoDebug("Failed...!")
            }
            this.invLabo.clear() //反応結果によらずインベントリを空にする
        }
        RagiPacket.wrapper.sendToAll(MessageLabo(this.pos)) //クライアント側にパケットを送る
    }

    //    ISidedInventory    //
    //基本的にRagiInventoryと同じ

    override fun getName(): String {
        return this.invLabo.title
    }

    override fun hasCustomName(): Boolean {
        return false
    }

    override fun getSizeInventory(): Int {
        return this.invLabo.sizeInventory
    }

    override fun isEmpty(): Boolean {
        return this.invLabo.isEmpty
    }

    override fun getStackInSlot(index: Int): ItemStack {
        return this.invLabo.getStackInSlot(index)
    }

    override fun decrStackSize(index: Int, count: Int): ItemStack {
        return this.invLabo.decrStackSize(index, count)
    }

    override fun removeStackFromSlot(index: Int): ItemStack {
        return this.invLabo.removeStackFromSlot(index)
    }

    override fun setInventorySlotContents(index: Int, stack: ItemStack) {
        return this.invLabo.setInventorySlotContents(index, stack)
    }

    override fun getInventoryStackLimit(): Int {
        return this.invLabo.inventoryStackLimit
    }

    override fun isUsableByPlayer(player: EntityPlayer): Boolean {
        return this.invLabo.isUsableByPlayer(player)
    }

    override fun openInventory(player: EntityPlayer) {}

    override fun closeInventory(player: EntityPlayer) {}

    override fun isItemValidForSlot(index: Int, stack: ItemStack): Boolean {
        return this.invLabo.isItemValidForSlot(index, stack)
    }

    override fun getField(id: Int): Int {
        return this.invLabo.getField(id)
    }

    override fun setField(id: Int, value: Int) {}

    override fun getFieldCount(): Int {
        return this.invLabo.fieldCount
    }

    override fun clear() {
        this.invLabo.clear()
    }

    override fun getSlotsForFace(side: EnumFacing): IntArray {
        //上面または下面の場合，どのスロットも干渉できない
        return if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
            intArrayOf()
        } else intArrayOf(0, 1, 2, 3, 4)
    }

    override fun canInsertItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean {
        //上面または下面でない場合，搬入可能
        return direction != EnumFacing.UP && direction != EnumFacing.DOWN
    }

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean {
        //いかなる場合でも搬出不可能
        return false
    }
}