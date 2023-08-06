package hiiragi283.api.registry

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.core.util.hiiragiLocation
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.BlockFluidBase
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.RegistryBuilder
import java.util.function.Function
import java.util.function.Predicate

object HiiragiRegistry {

    fun init() {}

    @JvmStatic
    val CRUCIBLE: IForgeRegistry<CrucibleRecipe> = RegistryBuilder<CrucibleRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("crucible"))
        .setType(CrucibleRecipe::class.java)
        .create()

    val CRUSHING: IForgeRegistry<CrushingRecipe> = RegistryBuilder<CrushingRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("crush"))
        .setType(CrushingRecipe::class.java)
        .create()

    private val HEAT_SOURCE: MutableList<Function<IBlockState, Int>> = mutableListOf()

    init {
        registerHeatSource(1200 + 273) { it.block == Blocks.FIRE }
        registerHeatSource(FluidRegistry.LAVA.temperature) { it.block == Blocks.LAVA }
        registerHeatSource(800 + 273) { it.block == Blocks.LIT_FURNACE }
        registerHeatSource(800 + 273) { it.block == Blocks.MAGMA }
        registerHeatSource(400 + 273) { it.block == Blocks.TORCH }
        registerHeatSource {
            val block = it.block
            if (block is BlockFluidBase) block.fluid.temperature else 273
        }
    }

    @JvmStatic
    fun getHeat(state: IBlockState): Int = HEAT_SOURCE.firstOrNull { it.apply(state) != 273 }?.apply(state) ?: 273

    @JvmStatic
    fun getHeat(block: Block): Int = getHeat(block.defaultState)

    @JvmStatic
    fun registerHeatSource(temp: Int, predicate: Predicate<IBlockState>) {
        HEAT_SOURCE.add(Function { if (predicate.test(it)) temp else 273 })
    }

    @JvmStatic
    fun registerHeatSource(function: Function<IBlockState, Int>) {
        HEAT_SOURCE.add(function)
    }

    private fun serialize(location: ResourceLocation, metadata: Int): String = "$location:$metadata"

}