package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.base.TileLockableBase
import hiiragi283.ragi_materials.init.RagiGuiHandler
import hiiragi283.ragi_materials.inventory.RagiInventory
import hiiragi283.ragi_materials.inventory.container.ContainerLaboTable
import hiiragi283.ragi_materials.packet.MessageTile
import hiiragi283.ragi_materials.packet.PacketManager
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiResult
import hiiragi283.ragi_materials.util.RagiUtil
import hiiragi283.ragi_materials.util.RagiUtil.toBracket
import hiiragi283.ragi_materials.util.SoundManager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper

class TileLaboTable : TileLockableBase(100), ISidedInventory {

    override val inventory = RagiInventory("gui.ragi_materials.labo_table", 5)
    private val inventorySide = SidedInvWrapper(this, EnumFacing.NORTH)

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        ItemStackHelper.saveAllItems(tag, inventory.inventory) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        ItemStackHelper.loadAllItems(tag, inventory.inventory) //tagからインベントリを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing !== null && facing != EnumFacing.UP && facing != EnumFacing.DOWN) inventorySide as T else super.getCapability(capability, facing)
        } else super.getCapability(capability, facing)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            facing !== null && facing != EnumFacing.UP && facing != EnumFacing.DOWN
        } else false
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterialsCore.INSTANCE!!, RagiGuiHandler.RagiID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    Recipe    //

    fun chemicalReaction(world: World, pos: BlockPos) {
        var isFailed = true
        //サーバー側，かつインベントリが空でない場合
        if (!world.isRemote && !inventory.isEmpty) {
            //レシピチェック
            for (recipe in LaboRecipe.Registry.list) {
                if (recipe.match(inventorySide, true)) {
                    isFailed = false
                    for (i in 0..4) {
                        RagiUtil.dropItem(world, pos.add(0, 1, 0), recipe.getOutput(i), 0.0, 0.25, 0.0)
                        RagiLogger.infoDebug("The output is ${recipe.getOutput(i).toBracket()}")
                    }
                    SoundManager.playSoundHypixel(this)
                    RagiResult.succeeded(this)
                    break
                }
            }
            RagiLogger.infoDebug("$isFailed")
            //失敗時の処理
            if (isFailed) {
                RagiUtil.dropItem(world, pos.add(0, 1, 0), ItemStack(RagiRegistry.ITEM.ItemWaste, 1, 0))
                SoundManager.playSound(this, SoundManager.getSound("minecraft:entity.generic.explode"))
                RagiResult.failed(this)
            }
        }
        inventory.clear() //反応結果によらずインベントリを空にする
        PacketManager.wrapper.sendToAll(MessageTile(this.pos)) //クライアント側にパケットを送る
    }

    //    TileLockableBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerLaboTable(player, this)

    override fun getGuiID() = "ragi_materials:laboratory_table"

    //    ISidedInventory    //

    override fun getSlotsForFace(side: EnumFacing): IntArray {
        //上面または下面の場合，どのスロットも干渉できない
        return if (side == EnumFacing.UP || side == EnumFacing.DOWN) {
            intArrayOf()
        } else intArrayOf(0, 1, 2, 3, 4)
    }

    override fun canInsertItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = direction != EnumFacing.UP && direction != EnumFacing.DOWN //上面または下面でない場合，搬入可能

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = false //いかなる場合でも搬出不可能

}