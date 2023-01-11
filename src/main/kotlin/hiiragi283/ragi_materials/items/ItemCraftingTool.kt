package hiiragi283.ragi_materials.items

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class ItemCraftingTool(ID: String) : ItemBase(Reference.MOD_ID, ID, 0) {

    //クラフト時に返却されるstackを取得するメソッド
    override fun getContainerItem(stack: ItemStack): ItemStack {
        //NBTタグが存在しない場合
        if (stack.tagCompound == null) {
            //NBTタグを生成し，stackに代入する
            val tag = NBTTagCompound()
            tag.setString("material", "hiiragi_tsubasa")
            tag.setInteger("durability", 1109)
            stack.tagCompound = tag
        }
        //NBTタグを取得
        val tag = stack.tagCompound!!
        //NBTタグから耐久地を取得
        var durability = tag.getInteger("durability")
        //耐久地が0の場合
        return if (durability == 0) {
            //壊れる
            ItemStack.EMPTY
        }
        //耐久地が0以外の場合
        else {
            //durabilityを1つ小さくする
            durability -= 1
            tag.setInteger("durability", durability)
            stack.tagCompound = tag
            //stackを複製する
            val stackCopied = stack.copy()
            //複製したものを返す
            stackCopied
        }
    }

    //クラフト時にstackを返却するかどうかを確認するメソッド
    override fun hasContainerItem(stack: ItemStack): Boolean {
        return true
    }
}