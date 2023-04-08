package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.material.OreProperty
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

class ItemOreCrushedVanilla : ItemBase(Reference.MOD_ID, "ore_crushed_vanilla", 7), ICustomModel, IMaterialItem {

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String {
        val keyLang = when (stack.metadata) {
            0 -> "tile.oreGold.name"
            1 -> "tile.oreIron.name"
            2 -> "tile.oreCoal.name"
            3 -> "tile.oreLapis.name"
            4 -> "tile.oreDiamond.name"
            5 -> "tile.oreRedstone.name"
            6 -> "tile.oreEmerald.name"
            7 -> "tile.netherquartz.name"
            else -> ""
        }
        return I18n.format("item.ore_crushed.name", I18n.format(keyLang))
    }

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Color {
        val list = OreProperty.listVanilla
        val index = stack.metadata % list.size
        return list[index].second.getColor()
    }

}