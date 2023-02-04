package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.Block
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemBlockMaterialMetal(private val blockFrom: Block): ItemBlockBase(blockFrom, 255) {

    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until Reference.numMaterial) {
                //EnumMaterialsを取得
                val material = MaterialRegistry.getMaterial(i)
                //materialがWILDCARDでない，かつmaterialのtypeのhasIngotがtrueの場合
                if (material != MaterialRegistry.WILDCARD && material.type.hasIngot) {
                    //ItemStackをlistに追加
                    subItems.add(ItemStack(this, 1, i))
                }
            }
        }
    }

    override fun getItemStackDisplayName(stack: ItemStack): String {
        //EnumMaterialの取得
        val material = MaterialRegistry.getMaterial(stack.metadata)
        return I18n.format("item.ragi_${blockFrom.registryName}.name", I18n.format("material.${material.name}"))
    }
}