package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack

//JEiに登録するためのレシピを生成するクラス
class ForgeFurnaceRecipe(stringIn: String, map: MutableMap<String, String>, type: EnumFire) {

    //private変数の宣言
    val stackIn: ItemStack = RagiUtils.getStack(stringIn)
    private val stringOut: String
    val stackOut: ItemStack
    val recipeType = type

    init {
        stringOut = map.getValue(stringIn)
        stackOut = RagiUtils.getStack(stringOut)
    }

    enum class EnumFire(val display: String) {
        BURNING("§6§lBurning"), BOOSTED("§c§lBoosted"), HELLRISE("§4§lHellrise")
    }
}