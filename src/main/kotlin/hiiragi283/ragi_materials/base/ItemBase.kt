package hiiragi283.ragi_materials.base

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemBase(MOD: String, ID: String, val maxMeta: Int) : Item() {

    init {
        setRegistryName(MOD, ID)
        hasSubtypes = setHasSubtypes(maxMeta) //メタデータを使用するかどうか
        unlocalizedName = ID //翻訳キーをIDから取得する
    }

    //    General    //

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getUnlocalizedName(stack: ItemStack): String = if (maxMeta == 0) super.getUnlocalizedName() else super.getUnlocalizedName() + "." + stack.metadata

    private fun setHasSubtypes(maxMeta: Int): Boolean = maxMeta > 0

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (this.isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0..maxMeta) {
                subItems.add(ItemStack(this, 1, i))
            }
        }
    }
}