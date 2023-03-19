package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.material.MaterialRegistryNew
import hiiragi283.ragi_materials.material.RagiMaterial
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.biome.Biome
import net.minecraftforge.common.BiomeDictionary

class BlockSoilLignite: BlockSoilFuel("soil_lignite") {

    companion object {
        val AGE: PropertyInteger = PropertyInteger.create("age", 0, 5)
    }

    //    General    //

    override fun getDropMain() = MaterialRegistryNew.LIGNITE

    override fun getDropAdd() = MaterialRegistryNew.COAL

    //    BlockState    //

    override fun getProperty() = AGE

    //    Event    //

    override fun getAllowedBiomes(): List<Biome> = BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN).toMutableList()

    //    IMaterialBLock    //

    override fun getMaterialBlock(world: IBlockAccess, pos: BlockPos, state: IBlockState): RagiMaterial = MaterialRegistryNew.LIGNITE

    //    IMaterialItem    //

    override fun getMaterial(stack: ItemStack): RagiMaterial = MaterialRegistryNew.LIGNITE

}