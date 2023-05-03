package ragi_materials.main.tile

import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.recipe.LaboRecipe
import ragi_materials.core.util.*

class TileLaboTable : TileLaboBase() {

    //    Recipe    //

    fun chemicalReaction() {
        var isSucceeded = false
        var cache: LaboRecipe? = null
        if (!world.isRemote) {
            //インベントリが空でない場合
            if (!input.isEmpty()) {
                //レシピチェック
                for (recipe in RagiRegistry.LABO_RECIPE.valuesCollection) {
                    if (recipe.matchExact(inventory)) {
                        isSucceeded = true
                        cache = recipe
                        break
                    }
                }
                RagiMaterials.LOGGER.debug("$isSucceeded")
                input.clear()
                //成功時の処理
                if (isSucceeded) {
                    for (i in cache!!.getOutputs().indices) {
                        input.setStackInSlot(i, cache.getOutput(i))
                        RagiMaterials.LOGGER.debug("The output is ${cache.getOutput(i).toBracket()}")
                    }
                    playSoundHypixel(this)
                    succeeded(this)
                }
                //失敗時の処理
                else {
                    input.setStackInSlot(0, ItemStack(RagiRegistry.ItemWaste, 1, 0))
                    playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE)
                    failed(this)
                }
            }
            syncData() //クライアント側にパケットを送る
        }
    }

    //    ITileContainer    //

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:laboratory_table"

}