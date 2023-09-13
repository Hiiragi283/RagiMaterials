package hiiragi283.material.api.item

import hiiragi283.material.RMReference
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.block.HiiragiBlock
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

open class HiiragiItemBlock(block: HiiragiBlock, maxMeta: Int) : ItemBlock(block), HiiragiEntry.ITEM {

    private val maxMeta: Int

    init {
        registryName = block.registryName!!
        creativeTab = CreativeTabs.MISC
        hasSubtypes = maxMeta > 0
        this.maxMeta = 0.coerceAtLeast(maxMeta)
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

    //    HiiragiEntry    //

    override fun registerModel() {

    }

}