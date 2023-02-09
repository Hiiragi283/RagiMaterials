package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialBuilder.MaterialType
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.MaterialUtils
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemMaterial(private val ID: String, private vararg val type: MaterialType) : ItemBase(Reference.MOD_ID, ID, Reference.numMaterial) {

    init {
        creativeTab = RagiInit.TabMaterials
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //MaterialRegistry.list内の各materialに対して実行
            for (material in MaterialRegistry.list) {
                //materialがWILDCARDでない，かつmaterialのtypeが一致する場合
                if (material != MaterialRegistry.WILDCARD && type.contains(material.type)) {
                    //ItemStackをlistに追加
                    val stack = ItemStack(this, 1, material.index)
                    subItems.add(stack)
                    //RagiLogger.infoDebug("The stack ${stack.toBracket()} has been added creative tab !")
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
        MaterialUtils.materialInfo(material, tooltip)
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}