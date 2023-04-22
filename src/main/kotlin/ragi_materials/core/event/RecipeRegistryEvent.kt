package ragi_materials.core.event

import net.minecraft.item.ItemStack
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.recipe.FFRecipe
import ragi_materials.core.recipe.LaboRecipe
import ragi_materials.core.recipe.MillRecipe
import ragi_materials.core.util.getBottle
import ragi_materials.core.util.getPart

object RecipeRegistryEvent {

    @SubscribeEvent
    fun registerFFRecipe(event: RegistryEvent.Register<FFRecipe>) {
        val registry = event.registry

        //Material Part -> Hot Material Ingot (this will be removed)
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (material.type.match(TypeRegistry.METAL) && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                FFRecipe.Builder().apply {
                    input = getPart(part, material)
                    output = getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
                }.build().setRegistryName(RagiMaterials.MOD_ID, "${part.name}_${material.name}").also {
                    registry.register(it)
                    RagiMaterials.LOGGER.debug("The FF recipe ${it.registryName} is registered!")
                }
            }
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
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.WATER.name).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
            }

            //Boron
            LaboRecipe.Builder().apply {
                inputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.BORAX)
                inputs[1] = getBottle(material = MaterialRegistry.SULFURIC_ACID)
                outputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.BORON_OXIDE, 2)
                outputs[1] = getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_SULFATE)
                outputs[2] = getBottle(MaterialRegistry.WATER, count = 11)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.BORON_OXIDE.name).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
            }

            //Fluorine
            LaboRecipe.Builder().apply {
                inputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.FLUORITE)
                inputs[1] = getBottle(material = MaterialRegistry.SULFURIC_ACID)
                outputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM)
                outputs[1] = getBottle(material = MaterialRegistry.HYDROGEN_FLUORIDE)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.HYDROGEN_FLUORIDE.name).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
            }

            //Magnesium
            LaboRecipe.Builder().apply {
                inputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.MAGNESITE)
                inputs[1] = getBottle(MaterialRegistry.HYDROGEN_CHLORIDE, count = 2)
                outputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CHLORIDE)
                outputs[1] = getBottle(material = MaterialRegistry.WATER)
                outputs[2] = getBottle(material = MaterialRegistry.CARBON_DIOXIDE)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.MAGNESIUM_CHLORIDE.name).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
            }

            //Aluminium
            LaboRecipe.Builder().apply {
                inputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.BAUXITE)
                inputs[1] = getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
                inputs[2] = getBottle(MaterialRegistry.WATER, count = 3)
                outputs[0] = getPart(PartRegistry.DUST_TINY, MaterialRegistry.RUTILE, 3)
                outputs[1] = getPart(PartRegistry.DUST_TINY, MaterialRegistry.GALLIUM, 1)
                outputs[2] = getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA_SOLUTION.name).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
            }

            LaboRecipe.Builder().apply {
                inputs[0] = getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
                outputs[0] = getPart(PartRegistry.DUST, MaterialRegistry.ALUMINA)
                outputs[1] = getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
                outputs[2] = getBottle(MaterialRegistry.WATER, count = 3)
                catalyst = ItemStack(RagiRegistry.ItemBlazingCube)
            }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA.name).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
            }
        }
    }

    @SubscribeEvent
    fun registerMillRecipe(event: RegistryEvent.Register<MillRecipe>) {
        val registry = event.registry
        //Material Part -> Material Dust
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            if (part.scale >= 1.0f && part.type != EnumMaterialType.DUST) {

                val partOutput = when (part) {
                    PartRegistry.ORE -> PartRegistry.ORE_CRUSHED
                    else -> PartRegistry.DUST
                }

                MillRecipe.Builder().apply {
                    this.input = getPart(part, material)
                    this.output = getPart(partOutput, material, part.scale.toInt())
                }.build().setRegistryName(RagiMaterials.MOD_ID, "${part.name}_${material.name}").also {
                    registry.register(it)
                    RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
                }
            }
        }
    }
}