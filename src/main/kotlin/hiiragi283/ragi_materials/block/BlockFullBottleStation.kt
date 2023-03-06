package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.base.ITileBase
import hiiragi283.ragi_materials.tile.TileFullBottleStation
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.BlockContainer
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockFullBottleStation: BlockContainer(Material.IRON) {

    private val registryName = "fullbottle_station"

    init {
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(5.0F)
        setHarvestLevel("pickaxe", 2)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(5.0F)
        soundType = SoundType.METAL
        unlocalizedName = registryName
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("EnumBlockRenderType.MODEL", "net.minecraft.util.EnumBlockRenderType"))
    override fun getRenderType(state: IBlockState): EnumBlockRenderType {
        return EnumBlockRenderType.MODEL
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..2) {
            tooltip.add(I18n.format("text.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileFullBottleStation) {
            RagiUtil.dropInventoryItems(world, pos, tile.inventory)
        }
        super.breakBlock(world, pos, state)
    }


    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is ITileBase) tile.onTileActivated(world, pos, player, hand, facing)
        return true
    }

    //    Tile Entity    //

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return TileFullBottleStation()
    }

}