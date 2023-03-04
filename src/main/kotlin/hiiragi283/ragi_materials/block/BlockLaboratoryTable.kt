package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.tile.TileLaboratoryTable
import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
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

class BlockLaboratoryTable: BlockContainer(Material.IRON) {

    companion object {
        val SLOT1: PropertyBool = PropertyBool.create("slot1")
        val SLOT2: PropertyBool = PropertyBool.create("slot2")
        val SLOT3: PropertyBool = PropertyBool.create("slot3")
        val SLOT4: PropertyBool = PropertyBool.create("slot4")
        val SLOT5: PropertyBool = PropertyBool.create("slot5")
    }

    private val registryName = "laboratory_table"

    init {
        defaultState = blockState.baseState
                .withProperty(SLOT1, false)
                .withProperty(SLOT2, false)
                .withProperty(SLOT3, false)
                .withProperty(SLOT4, false)
                .withProperty(SLOT5, false)
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

    /* できなかった。
    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(1.0, 1.0, 1.0, 1.0, 0.875, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        //ソウルサンドと同じ当たり判定にすることで，下からホッパーで回収できるようにする
        return AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0)
    }*/

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, SLOT1, SLOT2, SLOT3, SLOT4, SLOT5)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return if (state.getValue(SLOT5)) {
            5
        } else if (state.getValue(SLOT4)) {
            4
        } else if (state.getValue(SLOT3)) {
            3
        } else if (state.getValue(SLOT2)) {
            2
        } else if (state.getValue(SLOT1)) {
            1
        } else 0
    }

    @Deprecated("Deprecated in Java", ReplaceWith("defaultState.withProperty(ING, meta)", "hiiragi283.ragi_materials.block.BlockLaboratoryTable.Companion.ING"))
    override fun getStateFromMeta(meta: Int): IBlockState {
        return when (meta) {
            1 -> defaultState.withProperty(SLOT1, true)
            2 -> defaultState.withProperty(SLOT2, true)
            3 -> defaultState.withProperty(SLOT3, true)
            4 -> defaultState.withProperty(SLOT4, true)
            5 -> defaultState.withProperty(SLOT5, true)
            else -> defaultState
        }
    }

    //    Event    //

    override fun breakBlock(world: World, pos: BlockPos, state: IBlockState) {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileLaboratoryTable) {
            InventoryHelper.dropInventoryItems(world, pos, tile.invLaboratory)
        }
        super.breakBlock(world, pos, state)
    }

    @Deprecated("Deprecated in Java")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if (!world.isRemote && world.isBlockPowered(pos)) {
            val tile = world.getTileEntity(pos)
            if (tile !== null && tile is TileLaboratoryTable) tile.chemicalReaction(world, pos)
        }
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val tile = world.getTileEntity(pos)
        if (tile !== null && tile is TileLaboratoryTable) tile.onTileActivated(world, pos, player, hand)
        return true
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

    //    Tile Entity    //

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return TileLaboratoryTable()
    }
}