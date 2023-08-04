package hiiragi283.api.registry

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidRegistry

object HeatSourceRegistry {

    private val REGISTRY: HashMap<String, Int> = hashMapOf()

    init {
        registerBlock(Blocks.FIRE, 1000 + 273)
        registerBlock(Blocks.LAVA, FluidRegistry.LAVA.temperature)
        registerBlock(Blocks.LIT_FURNACE, 800 + 273)
        registerBlock(Blocks.MAGMA, 800 + 273)
        registerBlock(Blocks.TORCH, 400 + 273)
        registerBlock(Blocks.WATER, FluidRegistry.WATER.temperature)
    }

    @JvmStatic
    fun getHeat(state: IBlockState): Int = getHeat(state.block, state.block.getMetaFromState(state))

    @JvmStatic
    fun getHeat(block: Block, metadata: Int): Int = REGISTRY.getOrDefault(block.registryName?.let { serialize(it, metadata) }, 273)

    @JvmStatic
    fun registerBlock(state: IBlockState, temp: Int) {
        registerBlock(state.block, state.block.getMetaFromState(state), temp)
    }

    @JvmStatic
    fun registerBlock(block: Block, metadata: Int, temp: Int) {
        block.registryName?.let { registerBlock(it, metadata, temp) }
    }

    @JvmStatic
    fun registerBlock(location: ResourceLocation, metadata: Int, temp: Int) {
        REGISTRY[serialize(location, metadata)] = temp
    }

    @JvmStatic
    fun registerBlock(block: Block, temp: Int) {
        (0..15).forEach { i -> registerBlock(block, i, temp) }
    }

    @JvmStatic
    fun registerBlock(location: ResourceLocation, temp: Int) {
        (0..15).forEach { i -> registerBlock(location, i, temp) }
    }

    private fun serialize(location: ResourceLocation, metadata: Int): String = "$location:$metadata"

}