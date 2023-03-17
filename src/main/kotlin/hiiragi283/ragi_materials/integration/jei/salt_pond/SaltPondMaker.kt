package hiiragi283.ragi_materials.integration.jei.salt_pond

import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialRegistry
import mezz.jei.api.IModRegistry
import net.minecraft.item.ItemStack

object SaltPondMaker {

    fun register(registry: IModRegistry) {
        //リストをレシピ一覧に登録
        registry.addRecipes(listOf(Recipe("water"), Recipe("saltwater"), Recipe("brine")), JEICore.SaltPond)
    }

    class Recipe(val stringIn: String) {

        //private変数の宣言
        val stackOut: ItemStack = when (stringIn) {
            "water" -> ItemStack(RagiItem.ItemDust, 1, MaterialRegistry.SALT.index)
            "saltwater" -> ItemStack(RagiItem.ItemDust, 1, MaterialRegistry.MAGNESIUM_CHLORIDE.index)
            "brine" -> ItemStack(RagiItem.ItemDust, 1, MaterialRegistry.LITHIUM_CHLORIDE.index)
            else -> ItemStack.EMPTY
        }
    }
}