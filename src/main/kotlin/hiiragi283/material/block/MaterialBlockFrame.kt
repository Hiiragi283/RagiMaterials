package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialBlockFrame : MaterialBlock(
    HiiragiShapes.FRAME,
    recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("AAA", "A A", "AAA")
            .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
            .build()
    }
) {

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
    ): Boolean = blockState != blockAccess.getBlockState(pos.offset(side))

}