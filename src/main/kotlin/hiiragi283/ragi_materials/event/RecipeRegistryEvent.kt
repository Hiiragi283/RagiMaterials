package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.init.RagiBlocks
import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import hiiragi283.ragi_materials.api.recipe.FFRecipe
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import hiiragi283.ragi_materials.material.OreProperties
import hiiragi283.ragi_materials.util.getBottle
import hiiragi283.ragi_materials.util.getPart
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

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
            catalyst = ItemStack(RagiItems.ItemBlazingCube)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA.name).also {
            registry.register(it)
            RagiMaterials.LOGGER.debug("The Labo recipe ${it.registryName} is registered!")
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
                MillRecipe.Builder().apply {
                    input = getPart(part, material)
                    output = getPart(PartRegistry.DUST, material, part.scale.toInt())
                }.build().setRegistryName(RagiMaterials.MOD_ID, "${part.name}_${material.name}").also {
                    registry.register(it)
                    RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
                }
            }
        }

        //Ore -> Crushed Ore -> Material Bust
        val blockOre = RagiBlocks.BlockOre1
        val itemCrushed = RagiItems.ItemOreCrushed
        for (i in OreProperties.listOre1.indices) {
            //Ore -> Crushed Ore
            MillRecipe.Builder().apply {
                input = ItemStack(blockOre, 1, i)
                output = ItemStack(itemCrushed, 2, i)
            }.build().setRegistryName("${blockOre.registryName}_$i").also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
            }
            //Crushed Ore -> Dust
            MillRecipe.Builder().apply {
                input = ItemStack(itemCrushed, 1, i)
                output = getPart(PartRegistry.DUST, OreProperties.listOre1[i].second.first)
            }.build().setRegistryName("${itemCrushed.registryName}_$i").also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
            }
        }

        //Vanilla Ores
        val itemCrushedVanilla = RagiItems.ItemOreCrushedVanilla
        val list = listOf(
                Blocks.GOLD_ORE,
                Blocks.IRON_ORE,
                Blocks.COAL_ORE,
                Blocks.LAPIS_ORE,
                Blocks.DIAMOND_ORE,
                Blocks.REDSTONE_ORE,
                Blocks.EMERALD_ORE,
                Blocks.QUARTZ_ORE
        )
        for (i in list.indices) {
            //Ore -> Crushed Ore
            MillRecipe.Builder().apply {
                input = ItemStack(list[i])
                output = ItemStack(itemCrushedVanilla, 2, i)
            }.build().setRegistryName(list[i].registryName!!).also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
            }
            //Crushed Ore -> Dust
            MillRecipe.Builder().apply {
                input = ItemStack(itemCrushedVanilla, 1, i)
                //ラピスとレッドストーン用の処理
                val amount = if (i == 3 || i == 5) 8 else 1
                output = getPart(PartRegistry.DUST, OreProperties.listVanilla[i].second.first, amount)
            }.build().setRegistryName("${itemCrushedVanilla.registryName}_$i").also {
                registry.register(it)
                RagiMaterials.LOGGER.debug("The Mill recipe ${it.registryName} is registered!")
            }
        }
    }
}