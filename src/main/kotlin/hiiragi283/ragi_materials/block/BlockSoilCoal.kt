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

class BlockSoilCoal: BlockSoilFuel("soil_coal") {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 7)
    }

    //    General    //

    override fun getDropMain() = MaterialRegistry.COAL

    override fun getDropAdd() = MaterialRegistry.ANTHRACITE

    //    BlockState    //

    override fun getProperty() = AGE

    //    Event    //

    override fun getAllowedBiomes(): List<Biome> = BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toMutableList()

    //    IMaterialBLock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): RagiMaterial = MaterialRegistry.COAL

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial = MaterialRegistry.COAL

}