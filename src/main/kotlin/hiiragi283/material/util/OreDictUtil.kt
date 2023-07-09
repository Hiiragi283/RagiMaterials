package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object OreDictUtil {

    fun register(oredict: String, item: Item?, meta: Int = 0, share: String? = null) {
        item?.let { OreDictionary.registerOre(oredict, ItemStack(it, 1, meta)) }
        share?.let { shareOredict(oredict, it) }
    }

    fun register(oredict: String, block: Block?, meta: Int = 0, share: String? = null) {
        block?.let { OreDictionary.registerOre(oredict, ItemStack(it, 1, meta)) }
        share?.let { shareOredict(oredict, it) }
    }

    fun shareOredict(oredict1: String, oredict2: String) {
        OreDictionary.getOres(oredict1).forEach { OreDictionary.registerOre(oredict2, it) }
        OreDictionary.getOres(oredict2).forEach { OreDictionary.registerOre(oredict1, it) }
    }

}