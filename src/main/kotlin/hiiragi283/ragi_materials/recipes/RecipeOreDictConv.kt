package hiiragi283.ragi_materials.recipes

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils.toBracket
import net.minecraft.client.resources.I18n
import net.minecraft.init.Items
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.world.World
import net.minecraftforge.oredict.OreDictionary

class RecipeOreDictConv : net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe>(), IRecipe {

    init {
        setRegistryName("${Reference.MOD_ID}:recipe_ore_dict_conv") //タイプミス防止のため予めレシピ名を書いておく
    }

    //レシピが一致するかどうかを判定するメソッド
    override fun matches(inv: InventoryCrafting, worldIn: World): Boolean {
        //変数の宣言
        var hasIngredient = false
        var hasConversionBook = false
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0 .. 2) {
            for (j in 0 .. 2) {
                //そのスロットが空でない場合
                if(inv.getStackInRowAndColumn(i ,j).item != Items.AIR && !hasIngredient) {
                    hasIngredient = true
                }
                //そのスロットに変換本が入っている場合
                else if (inv.getStackInRowAndColumn(i ,j).item == RagiInit.ItemBookDebug) {
                    hasConversionBook = true
                }
            }
        }
        //2つの変数の論理積を返す
        return hasIngredient && hasConversionBook
    }

    //クラフト結果を返すメソッド
    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        RagiLogger.infoDebug(I18n.format("text.ragi_materials.decoration_line.name"))
        //変数の宣言
        var ingredient = ItemStack.EMPTY
        var hasConversionBook = false
        var result = ItemStack.EMPTY
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0 .. 2) {
            for (j in 0 .. 2) {
                //そのスロットに変換本が入っている場合
                if (inv.getStackInRowAndColumn(i ,j).item == RagiInit.ItemBookDebug) {
                    hasConversionBook = true
                }
                //そのスロットに何かしらのものが入っている場合
                else if(inv.getStackInRowAndColumn(i ,j).item != Items.AIR) {
                    ingredient = inv.getStackInRowAndColumn(i ,j)
                }
            }
        }
        RagiLogger.infoDebug("The ingredient is ${ingredient.toBracket()} !")
        if (ingredient.item != Items.AIR && hasConversionBook) {
            val arrayIDs = OreDictionary.getOreIDs(ingredient)
            for (id in arrayIDs) {
                val oreDict = OreDictionary.getOreName(id)
                RagiLogger.infoDebug(oreDict)
                val listStacks = OreDictionary.getOres(oreDict)
                for (stack in listStacks) {
                    RagiLogger.infoDebug("The stack is ${stack.toBracket()} !")
                    if (stack.item.registryName.toString().split(":")[0] == Reference.MOD_ID) {
                        result = stack
                        break
                    }
                }
            }
        }
        RagiLogger.infoDebug("The result is ${result.toBracket()} !")
        return result
    }

    //レシピのサイズが一致するかどうかを返すメソッド
    //よくわかってない
    override fun canFit(width: Int, height: Int): Boolean {
        return true
    }

    //デフォルトのクラフト結果を返すメソッド
    override fun getRecipeOutput(): ItemStack {
        return ItemStack.EMPTY //何もない
    }

    //不定形かどうかを判定するメソッド（多分）
    override fun isDynamic(): Boolean {
        return true
    }

}