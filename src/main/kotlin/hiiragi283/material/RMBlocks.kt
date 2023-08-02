package hiiragi283.material

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.BlockMaterial
import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.config.RMConfig
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

object RMBlocks : HiiragiEntry.BLOCK {

    override val itemBlock: HiiragiItemBlock? = null

    @JvmField
    val MATERIAL_BLOCK = BlockMaterial(HiiragiShapes.BLOCK)

    override fun register(registry: IForgeRegistry<Block>) {
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            MATERIAL_BLOCK.register(registry)
        }
    }

    override fun registerOreDict() {
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            MATERIAL_BLOCK.registerOreDict()
        } else {
            RMItems.MATERIAL_BLOCK.registerOreDict()
        }
    }

    override fun registerRecipe() {
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            MATERIAL_BLOCK.registerRecipe()
        } else {
            RMItems.MATERIAL_BLOCK.registerRecipe()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorBlock(blockColors: BlockColors) {
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            MATERIAL_BLOCK.registerColorBlock(blockColors)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            MATERIAL_BLOCK.registerColorItem(itemColors)
        } else {
            RMItems.MATERIAL_BLOCK.registerColorItem(itemColors)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerModel() {
        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            MATERIAL_BLOCK.registerModel()
        } else {
            RMItems.MATERIAL_BLOCK.registerModel()
        }
    }

}