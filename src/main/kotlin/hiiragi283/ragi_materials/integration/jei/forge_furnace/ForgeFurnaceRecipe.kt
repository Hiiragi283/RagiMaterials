package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack

//JEiに登録するためのレシピを生成するクラス
class ForgeFurnaceRecipe(input: String, map: MutableMap<String, String>) {

    //private変数の宣言
    private val stringIn = input
    val stackIn: ItemStack = RagiUtils.getStack(stringIn)
    private val stringOut: String
    val stackOut: ItemStack

    init {
        stringOut = map.getValue(stringIn)
        stackOut = RagiUtils.getStack(stringOut)
    }
}