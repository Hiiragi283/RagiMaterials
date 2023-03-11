package hiiragi283.ragi_materials.base

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class ItemBlockBase(block: Block, private val maxMeta: Int = 0) : ItemBlock(block) {

    init {
        hasSubtypes = setHasSubtypes(maxMeta) //メタデータを使用するかどうか
        registryName = block.registryName!!
    }

    //    General    //

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getUnlocalizedName(stack: ItemStack): String = if (maxMeta == 0) super.getUnlocalizedName() else super.getUnlocalizedName() + "." + stack.metadata

    private fun setHasSubtypes(maxMeta: Int): Boolean = maxMeta > 0

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            //メタデータの最大値まで処理を繰り返す
            for (i in 0..maxMeta) {
                subItems.add(ItemStack(this, 1, i))
            }
        }
    }
}