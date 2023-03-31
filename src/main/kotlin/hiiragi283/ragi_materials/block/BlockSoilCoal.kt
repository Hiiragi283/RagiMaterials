package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.BlockSoulSand
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.BiomeDictionary

class BlockSoilCoal : BlockSoilFuel("soil_coal") {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 7)
    }

    //    General    //

    override fun getDropMain() = MaterialRegistry.COAL

    override fun getDropAdd() = MaterialRegistry.ANTHRACITE

    //    BlockState    //

    override fun getProperty() = AGE

    //    Event    //

    override fun getAllowedBiomes() = BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toMutableList()

    override fun isAllowedBlocks(state: IBlockState) = state.block is BlockSoulSand

    //    IGrowable    //

    override fun canGrow(world: World, pos: BlockPos, state: IBlockState, isClient: Boolean) = true

    //    IMaterialBLock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState) = MaterialRegistry.COAL

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack) = MaterialRegistry.COAL

}