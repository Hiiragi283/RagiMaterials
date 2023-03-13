package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.base.BlockBase
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.material.part.PartRegistry
import net.minecraft.block.IGrowable
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.BiomeDictionary
import java.util.*

class BlockSoilPeat: BlockBase("soil_peat", Material.GROUND, -1), IGrowable, IMaterialBlock, IMaterialItem {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 3)
    }

    init {
        blockHardness = 0.5f
        blockResistance = 0.5f
        defaultState = blockState.baseState.withProperty(AGE, 0)
        soundType = SoundType.GROUND
        tickRandomly = true
    }

    //    General    //

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        if (world is World && !world.isRemote) {
            val random = world.rand
            if (isMaxAge(state)) {
                drops.add(MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.PEAT, 4))
            } else {
                drops.add(ItemStack(this))
            }
            if (random.nextInt(7) == 0) {
                drops.add(MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.LIGNITE))
            }
        }
    }

    //    BlockState    //

    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, AGE)

    override fun getMetaFromState(state: IBlockState): Int = state.getValue(AGE)

    @Deprecated("Deprecated in Java", ReplaceWith("this.defaultState.withProperty(AGE, meta)", "hiiragi283.ragi_materials.block.BlockSoilPeat.Companion.AGE"))
    override fun getStateFromMeta(meta: Int): IBlockState = this.defaultState.withProperty(AGE, meta)

    private fun getAge(state: IBlockState) = state.getValue(AGE)

    private fun isMaxAge(state: IBlockState) = getAge(state) == AGE.allowedValues.last()

    //    Event    //

    override fun updateTick(world: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (canGrow(world, pos, state, false)) grow(world, rand, pos, state)
        val biome = world.getBiome(pos)
        //バイオームが水系の場合
        if (biome in listBiomes) {
            for (facing in EnumFacing.values()) {
                val posTo = pos.offset(facing)
                val stateTo = world.getBlockState(posTo)
                //隣接するブロックの種類がGROUNDまたはGRASSの場合，それを侵食する
                if (stateTo.material in listOf(Material.GROUND, Material.GRASS)) {
                    world.setBlockState(posTo, this.defaultState, 2)
                }
            }
        }
    }

    private val listBiomes = BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH).toMutableList().also{
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN))
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.RIVER))
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP))
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WET))
    }

    //    IGrowable    //

    override fun canGrow(world: World, pos: BlockPos, state: IBlockState, isClient: Boolean): Boolean {
        val list: MutableList<Boolean> = mutableListOf()
        for (facing in EnumFacing.values()) {
            list.add(world.isAirBlock(pos.offset(facing)))
        }
        return !list.contains(true) //各面が空気に触れていない場合，成長可能
    }

    override fun canUseBonemeal(world: World, rand: Random, pos: BlockPos, state: IBlockState): Boolean = false

    override fun grow(world: World, rand: Random, pos: BlockPos, state: IBlockState) {
        if (canGrow(world, pos, state, false) && !isMaxAge(state)) world.setBlockState(pos, state.withProperty(AGE, getAge(state) + 1), 2)
    }

    //    IMaterialBLock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): MaterialBuilder = MaterialRegistry.PEAT

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): MaterialBuilder = MaterialRegistry.PEAT

    override fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack = stack

}