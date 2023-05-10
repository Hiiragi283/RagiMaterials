package ragi_materials.core.event

import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.recipe.*
import ragi_materials.core.util.getBottle
import ragi_materials.core.util.getFluidStack

object RecipeRegistryEvent {

    private fun <T : IForgeRegistryEntry.Impl<T>> T.registerRecipe(registry: IForgeRegistry<T>) {
        registry.register(this)
        RagiMaterials.LOGGER.debug("The recipe ${this.registryName} is registered!")
    }

    @SubscribeEvent
    fun registerBasinRecipe(event: RegistryEvent.Register<BasinRecipe>) {
        val registry = event.registry
        if (RagiConfig.module.enableMetallurgy) {
            for (material in MaterialRegistry.getMaterials()) {
                val input = material.getPart(PartRegistry.CRUSHED)
                if (!input.isEmpty) {
                    BasinRecipe.Builder().apply {
                        this.input = input
                        this.fluid = getFluidStack("water", 100)
                        this.outputs = arrayOf(material.getPart(PartRegistry.DUST), MaterialRegistry.STONE.getPart(PartRegistry.DUST), ItemStack.EMPTY)
                    }.build().setRegistryName(RagiMaterials.MOD_ID, material.name).registerRecipe(registry)
                }
            }
        }
    }

    @SubscribeEvent
    fun registerBFRecipe(event: RegistryEvent.Register<BlastFurnaceRecipe>) {
        val registry = event.registry
        if (RagiConfig.module.enableMetallurgy) {
            BlastFurnaceRecipe.Builder().apply {
                ore = MaterialRegistry.HEMATITE.getPart(PartRegistry.CRUSHED)
                fuel = MaterialRegistry.CHARCOAL.getPart(PartRegistry.CRYSTAL, 2)
                flux = MaterialRegistry.LIME.getPart(PartRegistry.DUST)
                fluid = getFluidStack(MaterialRegistry.IRON.name, 144 * 3)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.IRON.name).registerRecipe(registry)
        }
    }

    @SubscribeEvent
    fun registerHPRecipe(event: RegistryEvent.Register<HopperPressRecipe>) {
        val registry = event.registry
        if (RagiConfig.module.enableMain) {
            HopperPressRecipe.Builder().apply {
                input = ItemStack(Items.WHEAT_SEEDS)
                output = FluidStack(RagiRegistry.FluidSeedOil, 50)
            }.build().setRegistryName(RagiMaterials.MOD_ID, "").registerRecipe(registry)
        }
    }

    @SubscribeEvent
    fun registerLaboRecipe(event: RegistryEvent.Register<LaboRecipe>) {
        val registry = event.registry
        if (RagiConfig.module.enableMain) {
            //Hydrogen
            LaboRecipe.Builder().apply {
                inputs[0] = getBottle(MaterialRegistry.HYDROGEN, count = 2)
                inputs[1] = getBottle(material = MaterialRegistry.OXYGEN)
                outputs[0] = getBottle(MaterialRegistry.WATER, count = 2)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.WATER.name).registerRecipe(registry)

            //Fluorine
            LaboRecipe.Builder().apply {
                inputs[0] = MaterialRegistry.FLUORITE.getPart(PartRegistry.DUST)
                inputs[1] = getBottle(material = MaterialRegistry.SULFURIC_ACID)
                outputs[0] = MaterialRegistry.GYPSUM.getPart(PartRegistry.DUST)
                outputs[1] = getBottle(material = MaterialRegistry.HYDROGEN_FLUORIDE)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.HYDROGEN_FLUORIDE.name).registerRecipe(registry)

            //Magnesium
            LaboRecipe.Builder().apply {
                inputs[0] = MaterialRegistry.MAGNESITE.getPart(PartRegistry.DUST)
                inputs[1] = getBottle(MaterialRegistry.HYDROGEN_CHLORIDE, count = 2)
                outputs[0] = MaterialRegistry.MAGNESIUM_CHLORIDE.getPart(PartRegistry.DUST)
                outputs[1] = getBottle(material = MaterialRegistry.WATER)
                outputs[2] = getBottle(material = MaterialRegistry.CARBON_DIOXIDE)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.MAGNESIUM_CHLORIDE.name).registerRecipe(registry)

            //Aluminium
            LaboRecipe.Builder().apply {
                inputs[0] = MaterialRegistry.BAUXITE.getPart(PartRegistry.DUST)
                inputs[1] = MaterialRegistry.SODIUM_HYDROXIDE.getPart(PartRegistry.DUST, 2)
                inputs[2] = getBottle(MaterialRegistry.WATER, count = 3)
                outputs[0] = MaterialRegistry.RUTILE.getPart(PartRegistry.DUST_TINY, 3)
                outputs[1] = MaterialRegistry.GALLIUM.getPart(PartRegistry.DUST_TINY, 1)
                outputs[2] = getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA_SOLUTION.name).registerRecipe(registry)

            LaboRecipe.Builder().apply {
                inputs[0] = getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
                outputs[0] = MaterialRegistry.ALUMINA.getPart(PartRegistry.DUST)
                outputs[1] = MaterialRegistry.SODIUM_HYDROXIDE.getPart(PartRegistry.DUST, 2)
                outputs[2] = getBottle(MaterialRegistry.WATER, count = 3)
                catalyst = ItemStack(RagiRegistry.ItemBlazingCube)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA.name).registerRecipe(registry)
        }
    }

    @SubscribeEvent
    fun registerMillRecipe(event: RegistryEvent.Register<MillRecipe>) {
        val registry = event.registry
        //Material Part -> Material Dust
        for (material in MaterialRegistry.getMaterials()) {
            for (part in material.listValidParts) {
                if (part.scale >= 1.0f && part.type != EnumMaterialType.DUST) {
                    val partOutput = when (part) {
                        PartRegistry.ORE -> PartRegistry.CRUSHED
                        else -> PartRegistry.DUST
                    }
                    MillRecipe.Builder().apply {
                        this.input = material.getPart(part)
                        this.output = material.getPart(partOutput, part.scale.toInt())
                    }.build().setRegistryName(RagiMaterials.MOD_ID, "${part.name}_${material.name}").registerRecipe(registry)
                }
            }
        }
    }
}