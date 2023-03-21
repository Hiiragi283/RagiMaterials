package hiiragi283.ragi_materials.crafting

import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraftforge.fml.common.registry.GameRegistry

object SmeltingManager {

    //かまどレシピを追加するメソッド
    fun addFurnace(output: ItemStack, input: ItemStack, xp: Float = 0f) {
        GameRegistry.addSmelting(input, output, xp)
        //RagiLogger.infoDebug("The smelting recipe $input.toBracket() -> $output.toBracket() was added successfully!")
    }

    //かまどレシピを削除するメソッド
    fun removeFurnace(input: ItemStack) {
        //かまどレシピのマップを取得する
        val mapFurnace = FurnaceRecipes.instance().smeltingList
        //インプットのイテレータを取得する
        val iteratorFurnace = mapFurnace.values.iterator()
        //イテレータの各要素について実行する
        while (iteratorFurnace.hasNext()) {
            //インプットが一致する場合
            if (RagiUtil.isSameStack(mapFurnace[iteratorFurnace.next()]!!, input, true)) {
                //レシピを削除する
                iteratorFurnace.remove()
                //RagiLogger.infoDebug("The smelting input " + input.toBracket() + " was removed successfully!")
            }
        }
    }
}