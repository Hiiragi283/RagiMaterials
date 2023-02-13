package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.*

class BlockLitForgeFurnace : BlockHorizontal(Material.ROCK) {

    //private変数の宣言
    private val registryName = "lit_forge_furnace"

    //コンストラクタの初期化
    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
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
        return BlockStateContainer(this, FACING)
    }

    //コンパレーター出力を上書きするメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("4"))
    override fun getComparatorInputOverride(state: IBlockState, world: World, pos: BlockPos): Int {
        return 4 //常に4を返す
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //ForgeFurnaceを返す
        return Item.getItemFromBlock(RagiInit.BlockForgeFurnace)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index - 2
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
        return this.defaultState.withProperty(FACING, placer.horizontalFacing.opposite)
    }

    //メタデータからBlockstateを得るメソッド
    @Deprecated("Deprecated in Java")
    override fun getStateFromMeta(meta: Int): IBlockState {
        val facing = EnumFacing.getFront((meta / 4) + 2)
        return blockState.baseState.withProperty(FACING, facing)
    }

    //コンパレーター出力を上書きするか判別するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("true"))
    override fun hasComparatorInputOverride(state: IBlockState): Boolean {
        return true
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
        if(!world.isRemote) {
            //プレイヤーが利き手に持っているアイテムを取得
            val stack = player.getHeldItem(hand)
            ForgeFurnaceHelper.getResult(world, pos, state, stack, ForgeFurnaceHelper.mapForgeBoosted) //レシピ実行
        }
        return true
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }

    //Random Tickで呼び出されるメソッド
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!world.isRemote) world.setBlockState(
            pos,
            RagiInit.BlockForgeFurnace.defaultState
                .withProperty(FACING, state.getValue(FACING))
                .withProperty(BlockForgeFurnace.FUEL, 3),
            2) //火力DOWN
    }
}