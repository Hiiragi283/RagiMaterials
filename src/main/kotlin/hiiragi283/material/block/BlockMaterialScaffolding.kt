package hiiragi283.material.block

import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.api.block.BlockMaterial
import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.setModelSame
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object BlockMaterialScaffolding : BlockMaterial(HiiragiShapes.SCAFFOLDING) {

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    MaterialBlock    //

    override fun getRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial) {
        CraftingBuilder(getItemStack(material, 6))
            .setPattern("AAA", " B ", "B B")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
            .setIngredient('B', HiiragiShapes.STICK.getOreDict(material))
            .build()
    }

    override fun getModel(entry: HiiragiEntry<*>) {
        entry.asItem().setModelSame()
    }

}