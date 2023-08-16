package hiiragi283.api.registry

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.MetaResourceLocation
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.toMetaLocation
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.RegistryBuilder

object HiiragiRegistry {

    fun init() {}

    @JvmStatic
    val CRUCIBLE: IForgeRegistry<CrucibleRecipe> = RegistryBuilder<CrucibleRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("crucible_new"))
        .setType(CrucibleRecipe::class.java)
        .create()

    val CRUSHING: IForgeRegistry<CrushingRecipe> = RegistryBuilder<CrushingRecipe>()
        .allowModification()
        .disableSaving()
        .setName(hiiragiLocation("crush"))
        .setType(CrushingRecipe::class.java)
        .create()

    val HEAT_SOURCE: Map<MetaResourceLocation, Int>
        get() = heatSourceInternal
    private val heatSourceInternal: HashMap<MetaResourceLocation, Int> = hashMapOf()

    init {
        registerHeatSource(Blocks.FIRE, 1200 + 273)
        registerHeatSource(Blocks.LAVA, FluidRegistry.LAVA.temperature)
        registerHeatSource(Blocks.LIT_FURNACE, 800 + 273)
        registerHeatSource(Blocks.MAGMA, 800 + 273)
        registerHeatSource(Blocks.TORCH, 400 + 273)
    }

    @JvmStatic
    fun getHeat(metaLocation: MetaResourceLocation): Int = heatSourceInternal.getOrDefault(metaLocation, 273)

    @JvmStatic
    fun getHeat(state: IBlockState): Int = getHeat(state.toMetaLocation())

    @JvmStatic
    fun getHeat(block: Block): Int = getHeat(block.defaultState)

    @JvmStatic
    fun registerHeatSource(metaLocation: MetaResourceLocation, temp: Int) {
        if (heatSourceInternal.containsKey(metaLocation)) {
            RagiMaterials.LOGGER.error("The heat source: $metaLocation was already registered!")
            return
        }
        heatSourceInternal[metaLocation] = temp
    }

    @JvmStatic
    fun registerHeatSource(state: IBlockState, temp: Int) = registerHeatSource(state.toMetaLocation(), temp)

    @JvmStatic
    fun registerHeatSource(block: Block, temp: Int) = registerHeatSource(block.defaultState, temp)

}