package hiiragi283.ragi_materials.items

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.materials.EnumMaterials
import hiiragi283.ragi_materials.materials.MaterialHelper
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialDust : ItemBase(Reference.MOD_ID, "dust", 255) {

    override fun getItemStackDisplayName(stack: ItemStack): String {
        //EnumMaterialの取得
        val material = MaterialHelper.getMaterial(stack.metadata)
        return I18n.format("item.ragi_dust.name", I18n.format("material.${material.registryName}"))
    }

    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until 255) {
                //EnumMaterialsを取得
                val material = MaterialHelper.getMaterial(i)
                //materialがWILDCARDでない場合
                if (material != EnumMaterials.WILDCARD) {
                    //materialのtypeのhasDustがtrueの場合
                    if (material.type.hasDust) {
                        //ItemStackをlistに追加
                        subItems.add(ItemStack(this, 1, i))
                    }
                }
            }
        }
    }
}