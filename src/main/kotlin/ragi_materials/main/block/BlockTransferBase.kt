package ragi_materials.main.block

import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.block.BlockContainerBase
import ragi_materials.core.block.RagiProperty
import ragi_materials.core.material.IMaterialBlock
import ragi_materials.core.util.RagiColor
import ragi_materials.core.util.RagiFacing
import ragi_materials.main.client.model.ICustomModel
import ragi_materials.main.client.model.ModelManager
import ragi_materials.main.tile.TileTransferBase
import java.awt.Color
import java.util.*

class BlockTransferBase<T : TileTransferBase<*>>(val type: String, tile: Class<T>, val color: Color) : BlockContainerBase<T>("${type}_transfer", Material.CIRCUITS, tile, 2), ICustomModel, IMaterialBlock {

    init {
        defaultState = blockState.baseState.withProperty(RagiFacing.HORIZONTAL, EnumFacing.NORTH).withProperty(RagiProperty.MODE2, EnumTransferMode.NEAREST)
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos) = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0)

    @Deprecated("Deprecated in Java", ReplaceWith("NULL_AABB", "net.minecraft.block.Block.NULL_AABB"))
    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos) = NULL_AABB

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullBlock(state: IBlockState) = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState() = BlockStateContainer(this, RagiFacing.HORIZONTAL, RagiProperty.MODE2)

    override fun getMetaFromState(state: IBlockState): Int = RagiFacing.getMeta(state) + state.getValue(RagiProperty.MODE2).meta * 4

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState = this.defaultState.withProperty(RagiFacing.HORIZONTAL, placer.horizontalFacing.opposite)

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(RagiFacing.HORIZONTAL, RagiFacing.getValue(meta)).withProperty(RagiProperty.MODE2, EnumTransferMode.getValue(meta))", "hiiragi283.ragi_materials.util.RagiFacing", "hiiragi283.ragi_materials.util.RagiFacing"))
    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(RagiFacing.HORIZONTAL, RagiFacing.getValue(meta)).withProperty(RagiProperty.MODE2, EnumTransferMode.getValue(meta))

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        tooltip.add("§e=== Info ===")
        tooltip.add(I18n.format("tips.ragi_materials.transfer.0", "§b${type}§r§7"))
        tooltip.add(I18n.format("tips.ragi_materials.transfer.1"))
        tooltip.add(I18n.format("tips.ragi_materials.transfer.2"))
    }

    @SideOnly(Side.CLIENT)
    override fun getRenderLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    @SideOnly(Side.CLIENT)
    override fun registerCustomModel() {
        ModelManager.setModelAlt(this, ModelResourceLocation("${RagiMaterials.MOD_ID}:transfer", "inventory"))
    }

    //    IMaterialItem    //

    override fun getColor(world: IBlockAccess, pos: BlockPos, state: IBlockState, tintIndex: Int) = if (tintIndex == 0) color else RagiColor.WHITE

    override fun getColor(stack: ItemStack, tintIndex: Int) = if (tintIndex == 0) color else RagiColor.WHITE

}