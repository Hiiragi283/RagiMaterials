package ragi_materials.main.block

import ragi_materials.core.material.MaterialRegistry
import net.minecraft.block.BlockGravel
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.BiomeDictionary
import ragi_materials.core.block.RagiProperty

class BlockSoilLignite : BlockSoilFuel("soil_lignite") {

    //    General    //

    override fun getDropMain() = MaterialRegistry.LIGNITE

    override fun getDropAdd() = MaterialRegistry.COAL

    //    BlockState    //

    override fun getProperty() = RagiProperty.AGE6

    //    Event    //

    override fun getAllowedBiomes() = BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN).toMutableList()

    override fun isAllowedBlocks(state: IBlockState) = state.block is BlockGravel

    //    IGrowable    //

    override fun canGrow(world: World, pos: BlockPos, state: IBlockState, isClient: Boolean) = true

    //    IMaterialBLock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState) = MaterialRegistry.LIGNITE

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack) = MaterialRegistry.LIGNITE

}