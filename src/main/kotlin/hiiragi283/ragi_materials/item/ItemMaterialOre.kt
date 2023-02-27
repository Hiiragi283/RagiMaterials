package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialOre(ID: String) : ItemMaterial(ID, EnumMaterialType.DUMMY) {

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //list内の各materialに対して実行
            for (material in MaterialRegistry.mapIndex.values) {
                //hasOre==trueの場合
                if (material.hasOre) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                }
            }
        }
    }

    //stackの燃焼時間を返すメソッド
    override fun getItemBurnTime(stack: ItemStack): Int {
        return -1
    }
}