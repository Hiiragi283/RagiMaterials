package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.RagiMaterial
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.biome.Biome
import net.minecraftforge.common.BiomeDictionary

class BlockSoilPeat: BlockSoilFuel("soil_peat") {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 3)
    }

    //    General    //

    override fun getDropMain() = MaterialRegistry.PEAT

    override fun getDropAdd() = MaterialRegistry.LIGNITE

    //    BlockState    //

    override fun getProperty() = AGE

    //    Event    //

    override fun getAllowedBiomes(): List<Biome> = BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH).toMutableList().also{
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN))
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.RIVER))
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP))
        it.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WET))
    }

    //    IMaterialBLock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): RagiMaterial = MaterialRegistry.PEAT

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial = MaterialRegistry.PEAT

}