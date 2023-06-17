package hiiragi283.material.item

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialElements
import hiiragi283.material.part.PartRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack

object ItemMaterialBottle : ItemMaterialBase(PartRegistry.BOTTLE) {

    init {
        creativeTab = object : CreativeTabs("${RagiMaterials.MODID}.bottle") {
            override fun createIcon(): ItemStack =
                ItemStack(this@ItemMaterialBottle, 1, MaterialElements.HYDROGEN.index)
        }
    }

    override fun isMatch(material: HiiragiMaterial): Boolean = true

    override fun materialRecipe(material: HiiragiMaterial) {}

}