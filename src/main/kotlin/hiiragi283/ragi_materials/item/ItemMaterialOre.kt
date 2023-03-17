package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.part.MaterialPart
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterialOre(part: MaterialPart) : ItemMaterial(part) {

    //    General    //

    override fun getItemBurnTime(stack: ItemStack): Int = -1

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            //list内の各materialに対して実行
            for (material in MaterialRegistry.list) {
                //hasOre==trueの場合
                if (material.hasOre) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                }
            }
        }
    }
}