package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterialsMod
import hiiragi283.ragi_materials.base.TileLockableBase
import hiiragi283.ragi_materials.capability.RagiEnergyStorage
import hiiragi283.ragi_materials.capability.RagiInventory
import hiiragi283.ragi_materials.container.ContainerLaboTable
import hiiragi283.ragi_materials.init.RagiGuiHandler
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiSoundUtil
import hiiragi283.ragi_materials.util.RagiUtil
import hiiragi283.ragi_materials.util.RagiUtil.toBracket
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper

class TileIndustrialLabo : TileLockableBase(104), ISidedInventory, ITickable {

    override val inventory = RagiInventory("gui.ragi_materials.industrial_labo", 5)
    private val battery = RagiEnergyStorage(64000)
    private val inventorySide = SidedInvWrapper(this, EnumFacing.NORTH)

    private var count = 0
    var front = EnumFacing.NORTH

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        ItemStackHelper.saveAllItems(tag, inventory.inventory) //インベントリをtagに書き込む
        tag.setTag(keyBattery, battery.serializeNBT()) //バッテリーをNBTタグに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        ItemStackHelper.loadAllItems(tag, inventory.inventory) //tagからインベントリを読み込む
        battery.deserializeNBT(tag.getCompoundTag(keyBattery)) //NBTタグからバッテリーを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> {
                when (facing) {
                    front -> super.getCapability(capability, facing)
                    else -> inventorySide as T
                }
            }

            CapabilityEnergy.ENERGY -> battery as T
            else -> super.getCapability(capability, facing)
        }
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return when (capability) {
            //アイテムのcapabilityは正面のみ干渉不可能
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> facing != front
            CapabilityEnergy.ENERGY -> true
            else -> false
        }
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterialsMod.INSTANCE!!, RagiGuiHandler.RagiID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //サーバー側，かつインベントリが空でない場合
            if (!world.isRemote && !inventory.isEmpty && battery.energyStored >= 1000) {
                //レシピチェック
                for (recipe in LaboRecipe.Registry.list) {
                    if (recipe.match(inventorySide, false)) {
                        for (i in 0..4) {
                            val input = recipe.getInput(i)
                            val output = recipe.getOutput(i)
                            if (!inventory.getStackInSlot(i).isEmpty && !input.isEmpty) {
                                inventory.decrStackSize(i, input.count) //入力スロットからアイテムを減らす
                                RagiLogger.infoDebug("The slot$i is decreased!")
                            }
                            RagiUtil.dropItem(world, pos.offset(front), output)
                            RagiLogger.infoDebug("The output is ${output.toBracket()}")
                        }
                        battery.extractEnergy(1000, false)
                        RagiSoundUtil.playSound(this, RagiSoundUtil.getSound("minecraft:block.piston.extend"))
                        break
                    }
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }

    //    TileLockableBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerLaboTable(player, this)

    override fun getGuiID() = "ragi_materials:industrial_labo"

    //    ISidedInventory    //

    override fun getSlotsForFace(side: EnumFacing): IntArray {
        //正面の場合，どのスロットも干渉できない
        return if (side == front) intArrayOf() else intArrayOf(0, 1, 2, 3, 4)
    }

    override fun canInsertItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = direction != front //正面でない場合，搬入可能

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing): Boolean = false //いかなる場合でも搬出不可能

}