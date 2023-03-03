package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockForgeFurnace : BlockHorizontal(Material.ROCK) {

    companion object {
        val FUEL: PropertyInteger = PropertyInteger.create("fuel", 0, 3)
    }

    private val registryName = "forge_furnace"

    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(FUEL, 0)
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(3.5F)
        setHarvestLevel("pickaxe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(3.5F)
        soundType = SoundType.STONE
        unlocalizedName = registryName
    }

    //    General    //

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING, FUEL)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return (state.getValue(FACING).index - 2) * 4 + state.getValue(FUEL)
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        return this.defaultState.withProperty(FACING, placer.horizontalFacing.opposite)
    }

    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        val facing = EnumFacing.getFront((meta / 4) + 2)
        val fuel = meta % 4
        return blockState.baseState.withProperty(FACING, facing).withProperty(FUEL, fuel)
    }

    //    Event    //

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        //プレイヤーが利き手に持っているアイテムを取得
        val stack = player.getHeldItem(hand)
        //サーバー側の場合
        if (!world.isRemote) {
            if (stack.item == Items.COAL) {
                FFHelper.setFuel(world, pos, state, stack) //燃料を投入
            } else {
                FFHelper.getResult(world, pos, state, player, stack) //レシピ実行
            }
        }
        return true
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..3) {
            tooltip.add(I18n.format("text.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //    Redstone    //

    @Deprecated("Deprecated in Java", ReplaceWith("state.getValue(FUEL)", "hiiragi283.ragi_materials.block.BlockForgeFurnace.Companion.FUEL"))
    override fun getComparatorInputOverride(state: IBlockState, world: World, pos: BlockPos): Int {
        return state.getValue(FUEL) //燃料の量を返す
    }

    @Deprecated("Deprecated in Java", ReplaceWith("true"))
    override fun hasComparatorInputOverride(state: IBlockState): Boolean {
        return true
    }

}