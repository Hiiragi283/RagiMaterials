package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.init.RagiInit
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
        val material = MaterialRegistry.getMaterial(stack.metadata)
        //tooltipの追加
        MaterialUtils.materialInfo(material, tooltip)
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //MaterialRegistry.list内の各materialに対して実行
            for (material in MaterialRegistry.list) {
                //materialがWILDCARDでない，かつmaterialのtypeが一致する場合
                if (material != MaterialRegistry.WILDCARD && material.type.getTypeBase().contains(type.name)) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                    //RagiLogger.infoDebug("The stack ${stack.toBracket()} has been added creative tab !")
                }
            }
        }
    }

    //stackの表示名を上書きするメソッド
    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val material = MaterialRegistry.getMaterial(stack.metadata)
        return I18n.format("item.ragi_$ID.name", I18n.format("material.${material.name}"))
    }

    //エンチャント効果を乗せるかどうかを決めるメソッド
    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean {
        val material = MaterialRegistry.getMaterial(stack.metadata)
        //materialのtypeがRADIOACTIVEを含むならtrue
        return material.type.getTypeBase().contains(MaterialType.RADIOACTIVE.name)
    }
}