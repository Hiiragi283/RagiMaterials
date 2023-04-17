package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.capability.RagiEnergyStorage
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.registry.RagiRegistries
import hiiragi283.ragi_materials.util.RagiUtil
import hiiragi283.ragi_materials.util.SoundManager
import hiiragi283.ragi_materials.util.toBracket
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.items.CapabilityItemHandler

class TileIndustrialLabo : TileLaboBase(), ITickable {

    private val battery = RagiEnergyStorage(64000)
    private var count = 0
    var front = EnumFacing.NORTH

    private var cache: LaboRecipe? = null

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
                    else -> CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory)
                }
            }

            CapabilityEnergy.ENERGY -> CapabilityEnergy.ENERGY.cast(battery)
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
                if (cacheRecipe()) {
                    RagiMaterials.LOGGER.debug("The recipe cached is ${cache!!.registryName}")
                    for (i in 0..4) {
                        val input = cache!!.getInput(i)
                        val output = cache!!.getOutput(i)
                        if (!inputs.getStackInSlot(i).isEmpty && !input.isEmpty) {
                            inputs.extractItem(i, input.count, false) //入力スロットからアイテムを減らす
                            RagiMaterials.LOGGER.debug("The slot$i is decreased!")
                        }
                        RagiUtil.dropItem(world, pos.offset(front), output)
                        RagiMaterials.LOGGER.debug("The output is ${output.toBracket()}")
                    }
                    battery.extractEnergy(1000, false)
                    SoundManager.playSound(this, SoundManager.getSound("minecraft:block.piston.extend"))
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }

    private fun cacheRecipe(): Boolean {
        var result = false
        //cacheが空の場合，新規で検索する
        if (cache == null) {
            for (recipe in RagiRegistries.LABO_RECIPE.valuesCollection) {
                if (recipe.match(inventory)) {
                    cache = recipe
                    result = true
                    break
                }
            }
        }
        //cacheがある場合，それが現在のレシピに適応できないなら空にする
        else if (cache!!.match(inventory)) result = true else cache = null
        return result
    }

    //    TileItemHandlerBase    //

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:industrial_labo"

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.industrial_labo"

}