package hiiragi283.ragi_materials.items

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import net.minecraft.item.ItemStack

class ItemBookConversion(): ItemBase(Reference.MOD_ID, "book_conversion", 0) {

    //クラフト時に返却されるstackを取得するメソッド
    override fun getContainerItem(stack: ItemStack): ItemStack {
        return stack.copy() //自分自身を返す
    }

    //クラフト時にstackを返却するかどうかを確認するメソッド
    override fun hasContainerItem(stack: ItemStack): Boolean {
        return true
    }
}