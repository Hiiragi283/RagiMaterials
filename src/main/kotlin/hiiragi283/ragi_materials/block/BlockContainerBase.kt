package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.item.ItemBlockBase
import hiiragi283.ragi_materials.tile.TileBase
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class BlockContainerBase<T : TileEntity>(val id: String, material: Material, val tile: Class<T>, private val maxTips: Int) : BlockContainer(material), IItemBlock {

    init {
        GameRegistry.registerTileEntity(tile, ResourceLocation(RagiMaterials.MOD_ID, "te_$id"))
        setRegistryName(RagiMaterials.MOD_ID, id)
        translationKey = id
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("EnumBlockRenderType.MODEL", "net.minecraft.util.EnumBlockRenderType"))
    override fun getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        var result = false
        if (hand == EnumHand.MAIN_HAND) {
            val tile = world.getTileEntity(pos)
            result = if (tile !== null && tile is TileBase) tile.onTileActivated(world, pos, player, hand, facing) else false
        }
        return result
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.path
        if (maxTips != -1) {
            tooltip.add("§e=== Info ===")
            for (i in 0..maxTips) {
                tooltip.add(I18n.format("tips.ragi_materials.${path}.$i"))
            }
        }
    }

    //    BlockContainer    //

    override fun createNewTileEntity(worldIn: World, meta: Int): T = tile.newInstance()

    //    IItemBlock    //

    override fun getItemBlock() = ItemBlockBase(this)

}