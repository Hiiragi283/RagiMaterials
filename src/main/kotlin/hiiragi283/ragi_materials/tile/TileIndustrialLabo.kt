package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.capability.RagiEnergyStorage
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import hiiragi283.ragi_materials.util.SoundManager
import hiiragi283.ragi_materials.util.toBracket
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.items.CapabilityItemHandler

class TileIndustrialLabo : TileLaboBase(104), ITickable {

    private val battery = RagiEnergyStorage(64000)

    private var count = 0
    var front = EnumFacing.NORTH

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyEnergy, battery.serializeNBT()) //バッテリーをNBTタグに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        battery.deserializeNBT(tag.getCompoundTag(keyEnergy)) //NBTタグからバッテリーを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return when (capability) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> {
                when (facing) {
                    front -> null
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

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //サーバー側，かつインベントリが空でない場合
            if (!world.isRemote && !inventory.isEmpty() && battery.energyStored >= 1000) {
                //レシピチェック
                for (recipe in LaboRecipe.Registry.list) {
                    if (recipe.match(inventory, false)) {
                        for (i in 0..4) {
                            val input = recipe.getInput(i)
                            val output = recipe.getOutput(i)
                            if (!inputs.getStackInSlot(i).isEmpty && !input.isEmpty) {
                                inputs.extractItem(i, input.count, false) //入力スロットからアイテムを減らす
                                RagiLogger.infoDebug("The slot$i is decreased!")
                            }
                            RagiUtil.dropItem(world, pos.offset(front), output)
                            RagiLogger.infoDebug("The output is ${output.toBracket()}")
                        }
                        battery.extractEnergy(1000, false)
                        SoundManager.playSound(this, SoundManager.getSound("minecraft:block.piston.extend"))
                        break
                    }
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }

    //    TileItemHandlerBase    //

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:industrial_labo"

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.industrial_labo"

}