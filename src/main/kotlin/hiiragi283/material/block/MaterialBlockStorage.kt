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

object MaterialBlockStorage : MaterialBlock(
    HiiragiShapes.BLOCK,
    model = { block: MaterialBlock ->
        ModelLoader.registerItemVariants(
            block.itemBlock,
            hiiragiLocation("block_gem"),
            hiiragiLocation("block_metal")
        )
        ModelLoader.setCustomMeshDefinition(block.itemBlock) { stack: ItemStack ->
            val material: HiiragiMaterial? = block.getMaterial(stack)
            when {
                material?.isMetal() == true -> hiiragiLocation("block_metal").toModelLocation()
                material?.isGem() == true -> hiiragiLocation("block_gem").toModelLocation()
                else -> block.registryName!!.toModelLocation()
            }
        }
    },
    recipe = { block: MaterialBlock, material: HiiragiMaterial ->
        if (HiiragiShapes.INGOT.isValid(material)) {
            CraftingBuilder(block.itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .build()
        } else if (HiiragiShapes.GEM.isValid(material)) {
            CraftingBuilder(block.itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                .build()
        }
    }
)