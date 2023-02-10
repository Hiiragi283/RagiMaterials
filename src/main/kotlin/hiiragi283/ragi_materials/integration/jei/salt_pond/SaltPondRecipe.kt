package hiiragi283.ragi_materials.integration.jei.salt_pond

import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.item.ItemStack

//JEiに登録するためのレシピを生成するクラス
class SaltPondRecipe(val stringIn: String) {

    //private変数の宣言
    val stackOut: ItemStack = when (stringIn) {
        "water" -> ItemStack(RagiInit.ItemDust, 1, 11)
        "saltwater" -> ItemStack(RagiInit.ItemDust, 1, 12)
        "brine" -> ItemStack(RagiInit.ItemDust, 1, 3)
        else -> ItemStack.EMPTY
    }

}