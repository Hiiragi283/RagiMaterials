package hiiragi283.material.creativetab

import hiiragi283.material.RagiMaterials
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object CreativeTabMaterial : CreativeTabs("${RagiMaterials.MODID}.material") {

    override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)

}