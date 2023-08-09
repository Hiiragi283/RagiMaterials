package hiiragi283.api.item

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.block.HiiragiBlock
import hiiragi283.material.RMReference
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class HiiragiItemBlock(block: HiiragiBlock, private var maxMeta: Int) : ItemBlock(block), HiiragiEntry.ITEM {

    init {
        registryName = block.registryName!!
        creativeTab = CreativeTabs.MISC
        hasSubtypes = maxMeta > 0
        maxMeta = 0.coerceAtLeast(maxMeta)
    }

    //    General    //

    override fun getCreatorModId(stack: ItemStack): String = RMReference.MOD_ID

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getTranslationKey(stack: ItemStack): String = StringBuilder().also {
        it.append(super.getTranslationKey())
        if (hasSubtypes) {
            it.append("_")
            it.append(stack.metadata)
        }
    }.toString()

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            (0..maxMeta).forEach {
                subItems.add(ItemStack(this, 1, it))
            }
        }
    }

    //    HiiragiEntry    //

    override fun registerModel() {

    }

}