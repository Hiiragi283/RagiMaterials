package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.registry.RagiRegistry
import hiiragi283.ragi_materials.util.RagiResult
import hiiragi283.ragi_materials.util.RagiUtil
import hiiragi283.ragi_materials.util.SoundManager
import hiiragi283.ragi_materials.util.toBracket
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler

class TileLaboTable : TileLaboBase(100), ITileSyncable {

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) inventory as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?) = capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY

    //    Recipe    //

    fun chemicalReaction(world: World, pos: BlockPos) {
        var isFailed = true
        //サーバー側，かつインベントリが空でない場合
        if (!world.isRemote && !inputs.isEmpty()) {
            //レシピチェック
            for (recipe in RagiRegistry.LABO_RECIPE.valuesCollection) {
                if (recipe.matchExact(inventory)) {
                    isFailed = false
                    for (i in recipe.getOutputs().indices) {
                        RagiUtil.dropItem(world, pos.add(0, 1, 0), recipe.getOutput(i), 0.0, 0.25, 0.0)
                        RagiMaterials.LOGGER.debug("The output is ${recipe.getOutput(i).toBracket()}")
                    }
                    SoundManager.playSoundHypixel(this)
                    RagiResult.succeeded(this)
                    break
                }
            }
            RagiMaterials.LOGGER.debug("$isFailed")
            //失敗時の処理
            if (isFailed) {
                RagiUtil.dropItem(world, pos.add(0, 1, 0), ItemStack(RagiItems.ItemWaste, 1, 0), 0.0, 0.25, 0.0)
                SoundManager.playSound(this, SoundManager.getSound("minecraft:entity.generic.explode"))
                RagiResult.failed(this)
            }
        }
        sync() //反応結果によらずインベントリを空にする
        syncData()
    }

    //    TileItemHandlerBase    //

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:laboratory_table"

    override fun getName() = "gui.${RagiMaterials.MOD_ID}.labo_table"

    //    ITileSyncable    //

    override fun sync() {
        inputs.clear()
    }
}