package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialManager
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterial(private val ID: String, private val type: EnumMaterialType) :
    ItemBase(Reference.MOD_ID, ID, RagiConfig.material.maxMaterials) {

    init {
        creativeTab = RagiInit.TabMaterials
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //list内の各materialに対して実行
            for (material in MaterialRegistry.mapIndex.values) {
                //typeがINTERNALでない，かつmaterialのtypeが一致する場合
                if (material.type != TypeRegistry.INTERNAL && material.type.parts.contains(type)) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                }
            }
        }
    }

    //stackの燃焼時間を返すメソッド
    override fun getItemBurnTime(stack: ItemStack): Int {
        return if (MaterialManager.getMaterial(stack.metadata) !== null) {
            //素材に紐づいた燃焼時間を取得
            var time = MaterialManager.getMaterial(stack.metadata)!!.burnTime
                //dust_tinyの場合は1/9
                if (stack.item.registryName!!.resourcePath == "dust_tiny") time /= 9
            time
        } else -1
    }

    //stackの表示名を上書きするメソッド
    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val material = MaterialManager.getMaterial(stack.metadata)
        return if (material !== null) I18n.format(
            "item.ragi_$ID.name",
            I18n.format("material.${material.name}")
        ) else super.getItemStackDisplayName(stack)
    }
}