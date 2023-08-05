package hiiragi283.api.registry

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.core.util.hiiragiLocation
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.RegistryBuilder

object HiiragiRegistry {

    fun init() {}

    @JvmStatic
    val CRUCIBLE: IForgeRegistry<CrucibleRecipe> = RegistryBuilder<CrucibleRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("crucible"))
        .setType(CrucibleRecipe::class.java)
        .create()

    private val HEAT_SOURCE: HashMap<String, Int> = hashMapOf()

    init {
        registerHeatSource(Blocks.FIRE, 1000 + 273)
        registerHeatSource(Blocks.LAVA, FluidRegistry.LAVA.temperature)
        registerHeatSource(Blocks.LIT_FURNACE, 800 + 273)
        registerHeatSource(Blocks.MAGMA, 800 + 273)
        registerHeatSource(Blocks.TORCH, 400 + 273)
        registerHeatSource(Blocks.WATER, FluidRegistry.WATER.temperature)
    }

    @JvmStatic
    fun getHeat(state: IBlockState): Int = getHeat(state.block, state.block.getMetaFromState(state))

    @JvmStatic
    fun getHeat(block: Block, metadata: Int): Int =
        HEAT_SOURCE.getOrDefault(block.registryName?.let { serialize(it, metadata) }, 273)

    @JvmStatic
    fun registerHeatSource(state: IBlockState, temp: Int) {
        registerHeatSource(state.block, state.block.getMetaFromState(state), temp)
    }

    @JvmStatic
    fun registerHeatSource(block: Block, metadata: Int, temp: Int) {
        block.registryName?.let { registerHeatSource(it, metadata, temp) }
    }

    @JvmStatic
    fun registerHeatSource(location: ResourceLocation, metadata: Int, temp: Int) {
        HEAT_SOURCE[serialize(location, metadata)] = temp
    }

    @JvmStatic
    fun registerHeatSource(block: Block, temp: Int) {
        (0..15).forEach { i -> registerHeatSource(block, i, temp) }
    }

    @JvmStatic
    fun registerHeatSource(location: ResourceLocation, temp: Int) {
        (0..15).forEach { i -> registerHeatSource(location, i, temp) }
    }

    private fun serialize(location: ResourceLocation, metadata: Int): String = "$location:$metadata"

}