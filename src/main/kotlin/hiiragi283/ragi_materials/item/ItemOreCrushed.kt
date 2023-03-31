package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.client.render.model.ICustomModel
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.OreProperty
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemOreCrushed: ItemBase(Reference.MOD_ID, "ore_crushed", OreProperty.mapOre1.size - 1), ICustomModel {

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String = I18n.format("item.ore_crushed.name", I18n.format("${RagiItem.ItemBlockOre1.getTranslationKey(stack)}.name"))

}