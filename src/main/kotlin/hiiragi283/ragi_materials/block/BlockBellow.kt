package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import hiiragi283.ragi_materials.util.RagiUtils.toInt
import net.minecraft.block.Block
import net.minecraft.block.BlockHorizontal
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

class BlockBellow : BlockHorizontal(Material.CLOTH) {

    //private変数の宣言
    companion object {
        val ACTIVE: PropertyBool = PropertyBool.create("active")
    }

    private val registryName = "block_bellow"

    //コンストラクタの初期化
    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false)
        setCreativeTab(RagiInit.TabBlocks)
        setHardness(3.5F)
        setHarvestLevel("axe", 0)
        setRegistryName(Reference.MOD_ID, registryName)
        setResistance(3.5F)
        soundType = SoundType.CLOTH
        unlocalizedName = registryName
    }

    //Itemにtooltipを付与するメソッド
    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        val path = stack.item.registryName!!.resourcePath
        tooltip.add("§e=== Info ===")
        for (i in 0..2) {
            tooltip.add(I18n.format("text.ragi_materials.${path}.$i"))
        }
        super.addInformation(stack, world, tooltip, ITooltipFlag.TooltipFlags.NORMAL)
    }

    //ふいごを使うメソッド
    private fun blowBellow(world: World, pos: BlockPos, state: IBlockState) {
        //サーバー側，かつACTIVEでない場合
        if (!world.isRemote && !state.getValue(ACTIVE)) {
            //ふいごが向いている方向，その先の座標とstateを取得
            val facingBellow = state.getValue(FACING)
            val posFurnace = pos.offset(facingBellow)
            val stateTo = world.getBlockState(posFurnace)
            //stateTo.blockがBlockForgeFurnace，かつ燃料が満タンの場合
            if (stateTo.block is BlockForgeFurnace && stateTo.getValue(BlockForgeFurnace.FUEL) == 3) {
                val facingFurnace = stateTo.getValue(FACING) //Forge Furnaceの方向
                val litForgeFurnace = RagiInit.BlockLitForgeFurnace.defaultState
                world.setBlockState(posFurnace, litForgeFurnace.withProperty(FACING, facingFurnace), 2) //火力UP
                world.scheduleUpdate(posFurnace, RagiInit.BlockLitForgeFurnace, 100) //Forge Furnaceのtick更新を100 tick後に設定
                world.setBlockState(pos, state.withProperty(ACTIVE, true), 2) //アニメーション
                world.scheduleUpdate(pos, this, 40) //Bellowのtick更新を20 tick後に設定
                world.playSound(
                    null,
                    posFurnace,
                    RagiUtils.getSound("minecraft:entity.blaze.shoot"),
                    SoundCategory.BLOCKS,
                    1.0f,
                    0.5f
                ) //SEを再生
                RagiLogger.infoDebug("Forge Furnace was boosted!")
            }
        }
    }

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, ACTIVE, FACING)
    }

    //面の種類を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBlockFaceShape(
        worldIn: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing
    ): BlockFaceShape {
        return when (face) {
            EnumFacing.UP -> when (state.getValue(ACTIVE)) {
                true -> BlockFaceShape.UNDEFINED
                false -> BlockFaceShape.SOLID
            }

            EnumFacing.DOWN -> BlockFaceShape.SOLID
            else -> BlockFaceShape.UNDEFINED
        }
    }

    //ブロックの当たり判定を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(
        state: IBlockState, world: IBlockAccess, pos: BlockPos
    ): AxisAlignedBB {
        return when (state.getValue(ACTIVE)) {
            true -> AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0)
            else -> AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)
        }
    }

    //ドロップするアイテムを得るメソッド
    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item {
        //Blockstateからブロックを取得し、更にそこからアイテムを取得して返す
        return Item.getItemFromBlock(state.block)
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        return ((!state.getValue(ACTIVE)).toInt()) * 4 + (state.getValue(FACING).index - 2)
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
        val facing = EnumFacing.getFront((meta % 4) + 2)
        return blockState.baseState.withProperty(ACTIVE, meta / 4 == 0).withProperty(FACING, facing)
    }

    //ブロックがフルブロックかどうかを判定するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    //ブロックが光を透過するかを判定するメソッド
    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    //隣接したブロックに変更が起きた時に呼ばれるメソッド
    @Deprecated("Deprecated in Java")
    override fun neighborChanged(state: IBlockState, world: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        //サーバー側，かつふいごが信号を受けている場合
        if (!world.isRemote && world.isBlockPowered(pos)) blowBellow(world, pos, state)
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
        blowBellow(world, pos, state)
        return true
    }

    //ドロップする確率を得るメソッド
    override fun quantityDropped(random: Random): Int {
        //常にドロップさせるので1を返す
        return 1
    }

    //Random Tickで呼び出されるメソッド
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!world.isRemote) {
            world.setBlockState(
                pos, state.withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false), 2
            ) //アニメーション
        }
    }
}