package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.setModelSame
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialBlockFrame : MaterialBlock(HiiragiShapes.FRAME) {

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    @SideOnly(Side.CLIENT)
    @Deprecated("Deprecated in Java", ReplaceWith("blockState != blockAccess.getBlockState(pos.offset(side))"))
    override fun shouldSideBeRendered(
        blockState: IBlockState,
        blockAccess: IBlockAccess,
        pos: BlockPos,
        side: EnumFacing
    ): Boolean {
        return blockState != blockAccess.getBlockState(pos.offset(side))
    }

    //    MaterialBlock    //

    override fun getRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial) {
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("AAA", "A A", "AAA")
            .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
            .build()
    }

    override fun getModel(entry: HiiragiEntry<*>) {
        this.setModelSame()
    }

}