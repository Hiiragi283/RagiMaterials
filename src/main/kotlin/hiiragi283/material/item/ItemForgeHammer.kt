package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object ItemForgeHammer : HiiragiItem("forge_hammer", 0) {

    init {
        maxDamage = 63
        setMaxStackSize(1)
    }

    //クラフト時に返却されるstackを取得するメソッド
    override fun getContainerItem(stack: ItemStack): ItemStack = stack.copy().also {
        it.itemDamage += 1
    }

    //クラフト時にstackを返却するかどうかを確認するメソッド
    override fun hasContainerItem(stack: ItemStack): Boolean = true

    override fun registerRecipe() {
        CraftingBuilder(ItemStack(this))
            .setPattern("AAA", "AAA", " B ")
            .setIngredient('A', "ingotIron")
            .setIngredient('B', ItemStack(Items.SIGN))
            .buildShaped()
    }
}