package hiiragi283.api.item

import hiiragi283.api.HiiragiEntry
import hiiragi283.material.RMReference
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class HiiragiItem(id: String, private var maxMeta: Int) : Item(), HiiragiEntry.ITEM {

    init {
        setRegistryName(RMReference.MOD_ID, id)
        creativeTab = CreativeTabs.MISC
        hasSubtypes = maxMeta > 0
        maxMeta = 0.coerceAtLeast(maxMeta)
        translationKey = id
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
            subItems.add(ItemStack(this, 1, 0))
            if (hasSubtypes) {
                (1..maxMeta).forEach {
                    subItems.add(ItemStack(this, 1, it))
                }
            }
        }
    }

}