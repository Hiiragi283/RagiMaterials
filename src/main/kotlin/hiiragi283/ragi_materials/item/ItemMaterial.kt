package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialManager
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterial(private val ID: String, private val type: MaterialType) :
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
                if (material.type != MaterialType.INTERNAL && material.type.getTypeBase().contains(type.name)) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                    //RagiLogger.infoDebug("The stack ${stack.toBracket()} has been added creative tab !")
                }
            }
        }
    }

    //stackの燃焼時間を返すメソッド
    override fun getItemBurnTime(stack: ItemStack): Int {
        var time: Int = when (stack.metadata) {
            MaterialRegistry.COAL.index -> 200 * 8
            MaterialRegistry.CHARCOAL.index -> 200 * 8
            MaterialRegistry.COKE.index -> 200 * 16
            MaterialRegistry.ANTHRACITE.index -> 200 * 24
            MaterialRegistry.LIGNITE.index -> 200 * 4
            MaterialRegistry.PEAT.index -> 200 * 2
            else -> -1 //それ以外
        }
        //dust_tinyの場合は1/9
        if (stack.item.registryName!!.resourcePath == "dust_tiny") time /= 9
        return time
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