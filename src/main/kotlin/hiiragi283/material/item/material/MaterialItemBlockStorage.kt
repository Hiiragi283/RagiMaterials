package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.toModelLocation
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialItemBlockStorage : MaterialItem(HiiragiShapes.BLOCK) {

    override fun addRecipes(material: HiiragiMaterial) {
        // 9x Ingot -> 1x Block
        if (HiiragiShapes.INGOT.isValid(material)) {
            CraftingBuilder(itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .build()
        }
        // 9x Gem -> 1x Block
        else if (HiiragiShapes.GEM.isValid(material)) {
            CraftingBuilder(itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                .build()
        }
        //Grinder Recipe
        addGrinderRecipe(material)
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        ModelLoader.registerItemVariants(
            this,
            hiiragiLocation("block_gem"),
            hiiragiLocation("block_metal")
        )
        ModelLoader.setCustomMeshDefinition(this) { stack: ItemStack ->
            val material: HiiragiMaterial? = getMaterial(stack)
            when {
                material?.isMetal() == true -> hiiragiLocation("block_metal").toModelLocation()
                material?.isGem() == true -> hiiragiLocation("block_gem").toModelLocation()
                else -> registryName!!.toModelLocation()
            }
        }
    }

}