package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.setModelSame
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialBlockFrame : MaterialBlock(HiiragiShapes.FRAME) {

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("BlockFaceShape.UNDEFINED", "net.minecraft.block.state.BlockFaceShape")
    )
    override fun getBlockFaceShape(
        worldIn: IBlockAccess,
        state: IBlockState,
        pos: BlockPos,
        face: EnumFacing
    ): BlockFaceShape = BlockFaceShape.UNDEFINED

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    MaterialBlock    //

    override fun getRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial) {
        CraftingBuilder(entry.getItemStack(material, 2))
            .setPattern("AAA", "A A", "AAA")
            .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
            .build()
    }

    override fun getModel(entry: HiiragiEntry<*>) {
        this.setModelSame()
    }

}