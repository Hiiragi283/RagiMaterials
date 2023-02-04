package hiiragi283.ragi_materials.base

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemBase(MOD: String, ID: String?, maxMeta: Int) : Item() {

    //private変数の宣言
    private val maxMeta: Int

    //コンストラクタの初期化
    init {
        this.maxMeta = maxMeta //メタデータの最大値の初期化
        creativeTab = CreativeTabs.MISC //表示するクリエイティブタブの設定
        unlocalizedName = ID.toString() //翻訳キーをIDから取得する
        if (maxMeta == 0) setHasSubtypes(false) else setHasSubtypes(true) //メタデータを使用する

        ForgeRegistries.ITEMS.register(this.setRegistryName(MOD, ID))
    }

    //メタデータを得るメソッド
    override fun getMetadata(damage: Int): Int {
        //代入した値が0以上maxMeta以下の場合，そのまま返す
        //そうでない場合はmaxMetaを返す
        return if (damage in 0..maxMeta) damage else maxMeta
    }

    //翻訳キーを得るメソッド
    override fun getUnlocalizedName(stack: ItemStack): String {
        //メタデータが0のみの場合、なにもしない
        return if (maxMeta == 0) super.getUnlocalizedName() else super.getUnlocalizedName() + "." + stack.metadata
    }

    //メタデータ付きアイテムをクリエイティブタブに登録するメソッド
    @SideOnly(Side.CLIENT) //Client側のみ
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0 until maxMeta + 1) {
                subItems.add(ItemStack(this, 1, i))
            }
        }
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }
}