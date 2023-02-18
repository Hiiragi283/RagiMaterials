package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.Block
import net.minecraft.block.IGrowable
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.common.IPlantable
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

open class BlockCropPeat(ID: String): Block(Material.PLANTS), IGrowable, IPlantable {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 3)
    }

    //コンストラクタの初期化
    init {
        defaultState = blockState.baseState.withProperty(AGE, 0)
        setHardness(0.0f)
        setRegistryName(Reference.MOD_ID, ID)
        soundType = SoundType.PLANT
        tickRandomly = true
        unlocalizedName = ID
    }

    //作物が成長できるか判定するメソッド
    override fun canGrow(worldIn: World, pos: BlockPos, state: IBlockState, isClient: Boolean): Boolean {
        return state.getValue(AGE) != 4 //最大まで成長していない場合はtrue
    }

    //骨粉が使えるか判定するメソッド
    override fun canUseBonemeal(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState): Boolean {
        return true
    }

    //Blockstateの登録をするメソッド
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, AGE)
    }

    //面の種類を取得するメソッド
    @Deprecated("Deprecated in Java",
        ReplaceWith("BlockFaceShape.UNDEFINED", "net.minecraft.block.state.BlockFaceShape")
    )
    override fun getBlockFaceShape(
        worldIn: IBlockAccess,
        state: IBlockState,
        pos: BlockPos,
        face: EnumFacing
    ): BlockFaceShape {
        return BlockFaceShape.UNDEFINED
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    //ブロックの当たり判定を取得するメソッド
    @Deprecated("Deprecated in Java")
    override fun getBoundingBox(
        state: IBlockState, world: IBlockAccess, pos: BlockPos
    ): AxisAlignedBB {
        return AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)
    }

    //ドロップするアイテムを得るメソッド
    override fun getDrops(
        drops: NonNullList<ItemStack>,
        world: IBlockAccess,
        pos: BlockPos,
        state: IBlockState,
        fortune: Int
    ) {
        //サーバー側の場合
        if (world is World && !world.isRemote) {
            val age = state.getValue(AGE)
            val random = world.rand
            //種は常にドロップ
            drops.add(ItemStack(getDropSeed(), random.nextInt(3), 0))
            //最大まで成長している場合，泥炭をドロップ
            if (age == 3) {
                drops.add(ItemStack(RagiInit.ItemCrystal, fortune + 1, getDropMain()))
            }
            //fortune/8の確率で褐炭がドロップ
            if (random.nextInt(7) < fortune) {
                drops.add(ItemStack(RagiInit.ItemCrystal,  1, getDropSub()))
            }
        }
    }

    //主産物のメタデータを取得するメソッド
    open fun getDropMain(): Int {
        return MaterialRegistry.PEAT.index
    }

    //種を取得するメソッド
    open fun getDropSeed(): Item {
        return RagiInit.ItemSeedPeat
    }

    //副産物のメタデータを取得するメソッド
    open fun getDropSub(): Int {
        return MaterialRegistry.LIGNITE.index
    }

    //Blockstateからメタデータを得るメソッド
    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(AGE)
    }

    //植物を取得するメソッド
    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState {
        val state = world.getBlockState(pos)
        return if (state.block !== this) defaultState else state
    }

    //植物の種類を取得するメソッド
    override fun getPlantType(world: IBlockAccess?, pos: BlockPos?): EnumPlantType {
        return EnumPlantType.Crop //作物
    }

    //メタデータからBlockstateを得るメソッド
    @Deprecated("Deprecated in Java", ReplaceWith(
        "blockState.baseState.withProperty(AGE, meta)",
        "hiiragi283.ragi_materials.block.BlockCropPeat.Companion.AGE"
    ))
    override fun getStateFromMeta(meta: Int): IBlockState {
        return blockState.baseState.withProperty(AGE, meta % 4)
    }

    //成長時に割り込むメソッド
    override fun grow(world: World, rand: Random, pos: BlockPos, state: IBlockState) {
        if (!world.isRemote) {
            val age = state.getValue(AGE)
            if (age < 3) world.setBlockState(pos, state.withProperty(AGE, age + 1), 2) //成長

        }
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

    //Random Tickで呼び出されるメソッド
    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        grow(world, rand, pos, state)
    }
}