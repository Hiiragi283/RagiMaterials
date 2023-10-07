package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.toModelLocation
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialBlockStorage : MaterialBlock(HiiragiShapes.BLOCK) {

    override fun registerRecipe(material: HiiragiMaterial) {
        val builder: CraftingBuilder = CraftingBuilder(this.itemStack(material))
            .setPattern("AAA", "AAA", "AAA")
        if (HiiragiShapes.INGOT.isValid(material)) {
            builder.setIngredient('A', HiiragiShapes.INGOT.getOreDict(material)).build()
        } else if (HiiragiShapes.GEM.isValid(material)) {
            builder.setIngredient('A', HiiragiShapes.GEM.getOreDict(material)).build()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        ModelLoader.registerItemVariants(
            itemBlock,
            hiiragiLocation("block_gem"),
            hiiragiLocation("block_metal")
        )
        ModelLoader.setCustomMeshDefinition(itemBlock) { stack: ItemStack ->
            val material: HiiragiMaterial? = this.getMaterial(stack)
            when {
                material?.isMetal() == true -> hiiragiLocation("block_metal").toModelLocation()
                material?.isGem() == true -> hiiragiLocation("block_gem").toModelLocation()
                else -> this.registryName!!.toModelLocation()
            }
        }
    }

}