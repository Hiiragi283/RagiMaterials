package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.IMaterialBlock
import hiiragi283.ragi_materials.material.MaterialBuilder
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

open class BlockCropPeat(ID: String) : BlockBase(ID, Material.PLANTS, -1), IGrowable, IPlantable, IMaterialBlock {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 3)
    }

    init {
        defaultState = blockState.baseState.withProperty(AGE, 0)
        blockHardness = 0.0f
        soundType = SoundType.PLANT
        tickRandomly = true
    }

    //    General    //

    @Deprecated("Deprecated in Java", ReplaceWith("BlockFaceShape.UNDEFINED", "net.minecraft.block.state.BlockFaceShape"))
    override fun getBlockFaceShape(world: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape = BlockFaceShape.UNDEFINED

    @Deprecated("Deprecated in Java", ReplaceWith("AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)", "net.minecraft.util.math.AxisAlignedBB"))
    override fun getBoundingBox(state: IBlockState, world: IBlockAccess, pos: BlockPos): AxisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0)

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
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
                drops.add(ItemStack(RagiInit.ItemCrystal, 1, getDropSub()))
            }
        }
    }

    open fun getDropMain(): Int = MaterialRegistry.PEAT.index

    open fun getDropSeed(): Item = RagiInit.ItemSeedPeat

    open fun getDropSub(): Int = MaterialRegistry.LIGNITE.index

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isFullCube(state: IBlockState): Boolean = false

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isOpaqueCube(state: IBlockState): Boolean = false

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, AGE)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(AGE)

    @Deprecated("Deprecated in Java", ReplaceWith("blockState.baseState.withProperty(AGE, meta)", "hiiragi283.ragi_materials.block.BlockCropPeat.Companion.AGE"))
    override fun getStateFromMeta(meta: Int): IBlockState = blockState.baseState.withProperty(AGE, meta % 4)

    //    Event    //

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        grow(world, rand, pos, state)
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer(): BlockRenderLayer = BlockRenderLayer.CUTOUT

    //    IPlantable    //

    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState {
        val state = world.getBlockState(pos)
        return if (state.block !== this) defaultState else state
    }

    override fun getPlantType(world: IBlockAccess?, pos: BlockPos?): EnumPlantType = EnumPlantType.Crop //作物

    //    IGrowable    //

    override fun canGrow(world: World, pos: BlockPos, state: IBlockState, isClient: Boolean): Boolean = state.getValue(AGE) != 4 //最大まで成長していない場合はtrue

    override fun canUseBonemeal(world: World, rand: Random, pos: BlockPos, state: IBlockState): Boolean = true

    override fun grow(world: World, rand: Random, pos: BlockPos, state: IBlockState) {
        if (!world.isRemote) {
            val age = state.getValue(AGE)
            if (age < 3) world.setBlockState(pos, state.withProperty(AGE, age + 1), 2) //成長
        }
    }

    //    IMaterialBlock    //

    override fun getMaterialBlock(world: World, pos: BlockPos, state: IBlockState): MaterialBuilder = MaterialRegistry.PEAT
}