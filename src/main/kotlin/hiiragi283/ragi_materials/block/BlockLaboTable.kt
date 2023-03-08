package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.base.ITileBase
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class BlockLaboTable: BlockContainer(Material.IRON) {

    private val registryName = "laboratory_table"

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setCreativeTab(RagiInit.TabBlocks)
        setHarvestLevel("pickaxe", 2)
        setRegistryName(Reference.MOD_ID, registryName)
        soundType = SoundType.METAL
        unlocalizedName = registryName
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("EnumBlockRenderType.MODEL", "net.minecraft.util.EnumBlockRenderType"))
    override fun getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL

    /* できなかった。
    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(1.0, 1.0, 1.0, 1.0, 0.875, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        //ソウルサンドと同じ当たり判定にすることで，下からホッパーで回収できるようにする
        return AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0)
    }*/

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileLaboTable) {
            InventoryHelper.dropInventoryItems(world, pos, tile.invLabo)
        }
        super.breakBlock(world, pos, state)
    }

    @Deprecated("Deprecated in Java")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, block: Block, fromPos: BlockPos) {
        if (world.isBlockPowered(pos)) {
            val tile = world.getTileEntity(pos)
            if (tile !== null && tile is TileLaboTable) tile.chemicalReaction(world, pos)
        }
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val tile = world.getTileEntity(pos)
        return if (tile !== null && tile is ITileBase) tile.onTileActivated(world, pos, player, hand, facing) else false
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..2) {
            tooltip.add(I18n.format("tips.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //    Tile Entity    //

    override fun createNewTileEntity(world: World, meta: Int): TileEntity = TileLaboTable()
}