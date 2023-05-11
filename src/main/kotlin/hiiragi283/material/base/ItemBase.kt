package hiiragi283.material.base

import hiiragi283.material.RagiMaterials
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

abstract class ItemBase(ID: String, private var maxMeta: Int) : Item() {

    init {
        setRegistryName(RagiMaterials.MOD_ID, ID)
        creativeTab = CreativeTabs.MISC
        hasSubtypes = maxMeta > 0 //メタデータを使用するかどうか
        maxMeta = 0.coerceAtLeast(maxMeta) //maxMetaを0以上にする
        translationKey = ID //翻訳キーをIDから取得する
    }

    //    General    //

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getTranslationKey(stack: ItemStack): String =
        super.getTranslationKey() + if (maxMeta == 0) "" else ".${stack.metadata}"

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0..maxMeta) {
                subItems.add(ItemStack(this, 1, i))
            }
        }
    }

    //    ItemBase    //

    open fun register(registry: IForgeRegistry<Item>) {
        registry.register(this)
    }

    open fun registerOreDict() {}

    open fun registerRecipe() {}

}