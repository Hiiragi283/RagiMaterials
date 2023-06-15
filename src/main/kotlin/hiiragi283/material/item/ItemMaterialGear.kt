package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.item.ItemStack

object ItemMaterialGear : ItemMaterialBase(PartRegistry.GEAR) {

    override fun isMatch(material: HiiragiMaterial): Boolean = super.isMatch(material) && material.isMetal()

    //    IRMEntry    //

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { isMatch(it) }
            .forEach {
                CraftingBuilder(ItemStack(this, 1, it.index))
                    .setPattern(" A ", "A A", " A ")
                    .setIngredient('A', "ingot${it.getOreDictName()}")
                    .buildShaped()
            }
    }

}