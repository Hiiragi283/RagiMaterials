package hiiragi283.ragi_materials.main.materials

import com.google.common.collect.Lists
import hiiragi283.ragi_lib.main.base.ItemBase
import hiiragi283.ragi_materials.main.Reference
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialBase(ID: String) : ItemBase(Reference.MOD_ID, ID, 32767) {

    override fun getItemStackDisplayName(stack: ItemStack): String {
        //EnumMaterialの取得
        val material = MaterialHelper.getMaterial(stack.metadata)
        return I18n.format("item.ragi_dust.name", I18n.format("material.${material.registryName}"))
    }

    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //listの定義
            val list: MutableList<ItemStack> = Lists.newArrayList()
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until 32767) {
                //EnumMaterialsを取得
                val material = MaterialHelper.getMaterial(i)
                //materialがWILDCARDでない場合
                if (material != EnumMaterials.WILDCARD) {
                    //materialのtypeがDUSTかMETALの場合
                    if (material.type == MaterialTypes.DUST || material.type == MaterialTypes.METAL) {
                        //ItemStackをlistに追加
                        list.add(ItemStack(this, 1, i))
                    }
                }
            }
            //list内のすべてのアイテムをクリエイティブタブに登録
            subItems.addAll(list)
        }
    }
}