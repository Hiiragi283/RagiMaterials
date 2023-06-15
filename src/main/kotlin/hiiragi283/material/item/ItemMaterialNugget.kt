package hiiragi283.material.item

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.RagiIngredient
import net.minecraft.item.ItemStack

object ItemMaterialNugget : ItemMaterialBase(PartRegistry.NUGGET) {

    override fun isMatch(material: HiiragiMaterial): Boolean = super.isMatch(material) && material.isMetal()

    //    IRMEntry    //

    override fun registerRecipe() {
        MaterialRegistry.getMaterials()
            .filter { ItemMaterialGear.isMatch(it) }
            .forEach {
                CraftingBuilder(ItemStack(this, 9, it.index))
                    .addIngredient(RagiIngredient("ingot${it.getOreDictName()}"))
                    .buildShapeless()
            }
    }

}