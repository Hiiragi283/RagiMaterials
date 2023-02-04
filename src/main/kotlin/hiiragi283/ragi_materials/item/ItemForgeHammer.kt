package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import net.minecraft.item.ItemStack

class ItemForgeHammer : ItemBase(Reference.MOD_ID, "forge_hammer", 0) {

    init {
        maxDamage = 63
        setMaxStackSize(1)
    }

    //クラフト時に返却されるstackを取得するメソッド
    override fun getContainerItem(stack: ItemStack): ItemStack {
        val stack1 = stack.copy()
        stack1.itemDamage += 1
        return stack1
    }

    //クラフト時にstackを返却するかどうかを確認するメソッド
    override fun hasContainerItem(stack: ItemStack): Boolean {
        return true
    }
}