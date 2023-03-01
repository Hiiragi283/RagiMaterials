package hiiragi283.ragi_materials.base

import net.minecraft.block.Block
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemToolBase(MOD: String, ID: String?, maxDamage: Int, material: ToolMaterial, set: Set<Block>) :
        ItemTool(material, set) {

    //コンストラクタの初期化
    init {
        this.maxDamage = maxDamage
        creativeTab = CreativeTabs.TOOLS //表示するクリエイティブタブの設定
        setRegistryName(MOD, ID)
        unlocalizedName = ID.toString() //翻訳キーをIDから取得する
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}