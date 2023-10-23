package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.CrystalType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.toModelLocation
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialItemGem : MaterialItem(HiiragiShapes.GEM) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Block -> 9x Gem
        if (HiiragiShapes.BLOCK.isValid(material)) {
            CraftingBuilder(itemStack(material, 9))
                .addIngredient(HiiragiShapes.BLOCK.getOreDict(material))
                .build()
        }
        //Grinder Recipe
        addGrinderRecipe(material)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {

        ModelLoader.registerItemVariants(
            this, *CrystalType.values().map { it.getModelLocation(this) }.toTypedArray()
        )

        ModelLoader.setCustomMeshDefinition(this) { stack: ItemStack ->
            (getMaterial(stack)?.crystalType ?: CrystalType.NONE).getModelLocation(this).toModelLocation()
        }

    }

}