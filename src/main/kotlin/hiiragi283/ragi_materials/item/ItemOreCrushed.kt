package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.material.OreProperty
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

class ItemOreCrushed : ItemBase(Reference.MOD_ID, "ore_crushed", OreProperty.mapOre1.size - 1), ICustomModel, IMaterialItem {

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ore_crushed.name", I18n.format("${RagiRegistry.ITEM.ItemBlockOre1.getTranslationKey(stack)}.name"))

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack): Color {
        val list = OreProperty.listOre1
        val index = stack.metadata % list.size
        return list[index].second.getColor()
    }
}