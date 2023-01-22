package hiiragi283.ragi_materials.items

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.materials.MaterialBuilder.MaterialType
import hiiragi283.ragi_materials.materials.MaterialRegistry
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterial(private val ID: String, private vararg val type: MaterialType) : ItemBase(Reference.MOD_ID, ID, Reference.numMaterial) {

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //MaterialRegistry.list内の各materialに対して実行
            for (material in MaterialRegistry.list) {
                //materialがWILDCARDでない，かつmaterialのtypeが一致する場合
                if (material != MaterialRegistry.WILDCARD && type.contains(material.type)) {
                    //ItemStackをlistに追加
                    subItems.add(ItemStack(this, 1, material.index))
                }
            }
        }
    }

    //エンチャント効果を乗せるかどうかを決めるメソッド
    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean {
        //放射性物質のindexとメタデータが一致するならtrue
        return listOf(90, 92, 94, 97, 98).contains(stack.metadata)
    }

    //stackの表示名を上書きするメソッド
    override fun getItemStackDisplayName(stack: ItemStack): String {
        //EnumMaterialの取得
        val material = MaterialRegistry.getMaterial(stack.metadata)
        return I18n.format("item.ragi_$ID.name", I18n.format("material.${material.name}"))
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        //EnumMaterialsの取得
        val material = MaterialRegistry.getMaterial(stack.metadata)
        //tooltipの追加
        tooltip.add("§e===Property===")
        tooltip.add(I18n.format("text.ragi_materials.property.mol", material.getMolarMass()))
        //融点がnullでない場合
        if(material.getTempMelt(true) !== null) {
            //融点・沸点をtooltipに追加 (ケルビン温度)
            tooltip.add(I18n.format("text.ragi_materials.property.melt", material.getTempMelt() + 273))
            tooltip.add(I18n.format("text.ragi_materials.property.boil", material.getTempBoil() + 273))
        }
        //融点がnullの場合
        else {
            //沸点を昇華点としてtooltipに追加 (ケルビン温度)
            tooltip.add(I18n.format("text.ragi_materials.property.subl", material.getTempSubl() + 273))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}