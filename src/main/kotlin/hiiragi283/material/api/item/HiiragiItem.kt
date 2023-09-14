package hiiragi283.material.api.item

import hiiragi283.material.HiiragiCreativeTabs
import hiiragi283.material.RMReference
import hiiragi283.material.api.registry.HiiragiEntry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class HiiragiItem(id: String, maxMeta: Int) : Item(), HiiragiEntry.ITEM {

    val maxMeta: Int

    init {
        setRegistryName(RMReference.MOD_ID, id)
        creativeTab = HiiragiCreativeTabs.COMMON
        hasSubtypes = maxMeta > 0
        this.maxMeta = 0.coerceAtLeast(maxMeta)
        translationKey = id
    }

    //    General    //

    override fun getCreatorModId(stack: ItemStack): String = RMReference.MOD_ID

    override fun getMetadata(damage: Int): Int = if (damage in 0..maxMeta) damage else maxMeta

    override fun getTranslationKey(stack: ItemStack): String = StringBuilder().also { builder: StringBuilder ->
        builder.append(super.getTranslationKey())
        if (hasSubtypes) {
            builder.append("_")
            builder.append(stack.metadata)
        }
    }.toString()

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (isInCreativeTab(tab)) {
            subItems.add(ItemStack(this, 1, 0))
            if (hasSubtypes) {
                (1..maxMeta).forEach { meta: Int ->
                    subItems.add(ItemStack(this, 1, meta))
                }
            }
        }
    }

}