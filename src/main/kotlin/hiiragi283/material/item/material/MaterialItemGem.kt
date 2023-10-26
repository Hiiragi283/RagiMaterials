package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemGem : MaterialItem(HiiragiShapes.GEM) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Block -> 9x Gem
        if (HiiragiShapes.BLOCK.canCreateMaterialItem(material)) {
            CraftingBuilder(itemStack(material, 9))
                .addIngredient(HiiragiShapes.BLOCK.getOreDict(material))
                .build()
        }
        //Grinder Recipe
        addGrinderRecipe(material)
    }

    /*@SideOnly(Side.CLIENT)
    override fun registerModel() {

        ModelLoader.registerItemVariants(
            this, *CrystalType.values().map { it.getModelLocation(this) }.toTypedArray()
        )

        ModelLoader.setCustomMeshDefinition(this) { stack: ItemStack ->
            (getMaterial(stack)?.crystalType ?: CrystalType.NONE).getModelLocation(this).toModelLocation()
        }

    }*/

}