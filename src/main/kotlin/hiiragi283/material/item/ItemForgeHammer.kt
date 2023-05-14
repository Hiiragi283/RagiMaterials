package hiiragi283.material.item

import hiiragi283.material.base.ItemBase
import hiiragi283.material.util.addCraftingShaped
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object ItemForgeHammer : ItemBase("forge_hammer", 0) {

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

    override fun registerRecipe() {
        addCraftingShaped(
            ItemStack(this),
            "AAA",
            "AAA",
            " B ",
            'A', "ingotIron",
            'B', ItemStack(Items.SIGN)
        )
    }
}