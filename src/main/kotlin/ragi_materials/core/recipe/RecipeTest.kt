package ragi_materials.core.recipe

import net.minecraft.init.Items
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.world.World
import ragi_materials.core.RagiMaterials

class RecipeTest : net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe>(), IRecipe {

    init {
        setRegistryName("${RagiMaterials.MOD_ID}:recipe_test") //タイプミス防止のため予めレシピ名を書いておく
    }

    //レシピが一致するかどうかを判定するメソッド
    override fun matches(inv: InventoryCrafting, world: World): Boolean {
        //変数の宣言
        var hasWritableBook = false
        var hasEnchantedBook = false
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0..inv.sizeInventory) {
            //そのスロットに本と羽ペンが入っている場合
            if (inv.getStackInSlot(i).item == Items.WRITABLE_BOOK && !hasWritableBook) {
                hasWritableBook = true
            }
            //そのスロットにエンチャント本が入っている場合
            else if (inv.getStackInSlot(i).item == Items.ENCHANTED_BOOK) {
                hasEnchantedBook = true
            }
        }
        //2つの変数の論理積を返す
        return hasWritableBook && hasEnchantedBook
    }

    //クラフト結果を返すメソッド
    override fun getCraftingResult(inv: InventoryCrafting): ItemStack {
        //変数の宣言
        var amountWritableBook = 1
        var bookEnchanted = ItemStack.EMPTY
        //クラフトグリッド内の各スロットに対して実行
        for (i in 0..inv.sizeInventory) {
            //そのスロットに本と羽ペンが入っている場合
            if (inv.getStackInSlot(i).item == Items.WRITABLE_BOOK) {
                amountWritableBook++
            }
            //そのスロットにエンチャント本が入っている場合
            else if (inv.getStackInSlot(i).item == Items.ENCHANTED_BOOK) {
                bookEnchanted = inv.getStackInSlot(i)
            }
        }
        val result = ItemStack(Items.ENCHANTED_BOOK, amountWritableBook, 0)
        //bookEnchantedがNBTタグを持っている場合，NBTタグをresultに移す
        if (bookEnchanted.tagCompound !== null) result.tagCompound = bookEnchanted.tagCompound
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