package hiiragi283.ragi_materials.items

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.materials.EnumMaterials
import hiiragi283.ragi_materials.materials.MaterialHelper
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialDust : ItemBase(Reference.MOD_ID, "dust", Reference.numMaterial) {

    //stackの表示名を上書きするメソッド
    override fun getItemStackDisplayName(stack: ItemStack): String {
        //EnumMaterialの取得
        val material = MaterialHelper.getMaterial(stack.metadata)
        return I18n.format("item.ragi_dust.name", I18n.format("material.${material.registryName}"))
    }

    //エンチャント効果を乗せるかどうかを決めるメソッド
    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean {
        //白金族のindexとメタデータが一致するならtrue
        return listOf(44, 45, 46, 76, 77, 78).contains(stack.metadata)
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
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

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        //EnumMaterialsの取得
        val material = MaterialHelper.getMaterial(stack.metadata)
        //tooltipの追加
        tooltip.add("§e===Property===")
        tooltip.add(I18n.format("text.ragi_materials.property.mol", material.mol))
        //融点がnullでない場合
        if(material.melting !== null) {
            //融点・沸点をtooltipに追加 (ケルビン温度)
            tooltip.add(I18n.format("text.ragi_materials.property.melt", material.melting + 273))
            tooltip.add(I18n.format("text.ragi_materials.property.boil", material.boiling + 273))
        }
        //融点がnullの場合
        else {
            //沸点を昇華点としてtooltipに追加 (ケルビン温度)
            tooltip.add(I18n.format("text.ragi_materials.property.subl", material.boiling + 273))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}