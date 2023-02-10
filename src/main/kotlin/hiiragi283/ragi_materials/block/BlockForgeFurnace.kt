package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class BlockForgeFurnace : BlockHorizontal(Material.ROCK) {

    //private変数の宣言
    companion object {
        val FUEL: PropertyInteger = PropertyInteger.create("fuel", 0, 3)
    }

    private val registryName = "forge_furnace"

    //コンストラクタの初期化
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

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING, FUEL)
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //Blockstateからブロックを取得し、更にそこからアイテムを取得して返す
        return Item.getItemFromBlock(state.block)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        val stateMeta = when (state.getValue(FACING)) {
            EnumFacing.NORTH -> when (state.getValue(FUEL)) {
                1 -> 1
                2 -> 2
                3 -> 3
                else -> 0
            }

            EnumFacing.SOUTH -> when (state.getValue(FUEL)) {
                1 -> 5
                2 -> 6
                3 -> 7
                else -> 4
            }

            EnumFacing.WEST -> when (state.getValue(FUEL)) {
                1 -> 9
                2 -> 10
                3 -> 11
                else -> 8
            }

            EnumFacing.EAST -> when (state.getValue(FUEL)) {
                1 -> 13
                2 -> 14
                3 -> 15
                else -> 12
            }

            else -> 0
        }
        return stateMeta
    }

    //ブロックが設置されたときに呼び出されるメソッド
    override fun getStateForPlacement(
        world: World,
        pos: BlockPos,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        meta: Int,
        placer: EntityLivingBase,
        hand: EnumHand
    ): IBlockState {
        return this.defaultState.withProperty(BlockHorizontal.FACING, placer.horizontalFacing.opposite)
    }

    //メタデータからBlockstateを得るメソッド
    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        val facing = EnumFacing.getFront((meta / 4) + 2)
        val fuel = meta % 4
        return blockState.baseState.withProperty(FACING, facing).withProperty(FUEL, fuel)
    }

    //ブロックを右クリックした時に呼ばれるメソッド
    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        //プレイヤーが利き手に持っているアイテムを取得
        val stack = player.getHeldItem(hand)
        when (stack.item) {
            Items.COAL -> ForgeFurnaceHelper.setFuel(world, pos, state, stack) //燃料を投入
            RagiInit.ItemToolBellow -> ForgeFurnaceHelper.setBoosted(world, pos, state, stack) //火力UP
            else -> {
                ForgeFurnaceHelper.getResult(world, pos, state, stack, ForgeFurnaceHelper.mapForgeBurning) //レシピ実行
            }
        }
        return true
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }
}