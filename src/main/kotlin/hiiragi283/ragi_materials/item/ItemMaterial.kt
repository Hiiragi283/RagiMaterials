package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialManager
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import hiiragi283.ragi_materials.util.MaterialUtils
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterial(private val ID: String, private val type: MaterialType) :
    ItemBase(Reference.MOD_ID, ID, RagiConfig.material.maxMaterials) {

    init {
        creativeTab = RagiInit.TabMaterials
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        //materialの取得
        val material = MaterialManager.getMaterial(stack.metadata)
        //tooltipの追加
        MaterialUtils.materialInfo(material, tooltip)
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //list内の各materialに対して実行
            for (material in MaterialRegistry.list) {
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
        var time: Int = when(stack.metadata) {
            225 -> 200 * 8 //Coal
            226 -> 200 * 8 //Charcoal
            227 -> 200 * 16 //Coke
            228 -> 200 * 24 //Anthracite
            229 -> 200 * 4 //Lignite
            230 -> 200 * 2 //Peat
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
        return I18n.format("item.ragi_$ID.name", I18n.format("material.${material.name}"))
    }
}