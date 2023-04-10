package hiiragi283.ragi_materials.base

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemBase(MOD: String, ID: String, val maxMeta: Int) : Item() {

    init {
        setRegistryName(MOD, ID)
        hasSubtypes = maxMeta > 0 //メタデータを使用するかどうか
        translationKey = ID //翻訳キーをIDから取得する
    }

    //    General    //

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getTranslationKey(stack: ItemStack): String = super.getTranslationKey() + if (maxMeta == 0) "" else ".${stack.metadata}"

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0..maxMeta) {
                subItems.add(ItemStack(this, 1, i))
            }
        }
    }
}