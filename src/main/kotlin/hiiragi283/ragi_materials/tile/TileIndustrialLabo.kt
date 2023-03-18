package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.capability.RagiBattery
import hiiragi283.ragi_materials.capability.RagiItemHandler
import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipeRegistry
import hiiragi283.ragi_materials.util.*
import hiiragi283.ragi_materials.util.RagiUtil.toBracket
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.items.CapabilityItemHandler

class TileIndustrialLabo : TileBase(104), ITickable {

    private val battery = RagiBattery(64000)
    private var count = 0
    val inventory = RagiItemHandler(5)
    var front = EnumFacing.NORTH

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, inventory.serializeNBT()) //インベントリをNBTタグに書き込む
        tag.setTag(keyBattery, battery.serializeNBT()) //バッテリーをNBTタグに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory)) //NBTタグからインベントリを読み込む
        battery.deserializeNBT(tag.getCompoundTag(keyBattery)) //NBTタグからバッテリーを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> {
                when (facing) {
                    front -> super.getCapability(capability, facing)
                    else -> inventory as T
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

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean = false

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //サーバー側，かつインベントリが空でない場合
            if (!world.isRemote && !inventory.isEmpty() && battery.energyStored >= 1000) {
                //レシピチェック
                for (recipe in LaboRecipeRegistry.map.values) {
                    if (recipe.match(inventory, false)) {
                        for (i in 0 until inventory.slots) {
                            if (!inventory.getStackInSlot(i).isEmpty && !recipe.inputs[i].isEmpty) {
                                inventory.extractItem(i, recipe.inputs[i].count, false) //入力スロットからアイテムを減らす
                                RagiLogger.infoDebug("The slot$i is decreased!")
                            }
                        }
                        for (output in recipe.outputs) {
                            RagiUtil.dropItem(world, pos.offset(front), output)
                            RagiLogger.infoDebug("The output is ${output.toBracket()}")
                        }
                        battery.extractEnergy(1000, false)
                        RagiSoundEvent.playSound(this, RagiSoundEvent.getSound("minecraft:block.piston.extend"))
                        break
                    }
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }
}