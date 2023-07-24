package hiiragi283.material.common

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.*
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.with
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.api.shape.ShapeType
import hiiragi283.material.common.item.RespawnBookItem
import hiiragi283.material.common.util.getRegistryEntries
import net.minecraft.util.registry.Registry

object RMRegistry {

    fun loadBlocks() {

        //Initialize Material Blocks
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.getType() == ShapeType.BLOCK && it.isValid(material) }
                .map { it with material }
                .map { MaterialBlock(it) }
                .forEach { it.register() }
        }

    }

    fun loadBlockItems() {
        //Initialize BlockItems
        getRegistryEntries(Registry.BLOCK)
            .filterIsInstance<HiiragiEntry.BLOCK>()
            .map { it.getObject() }
            .filterIsInstance<MaterialBlock>()
            .map { MaterialBlockItem(it) }
            .forEach { it.register() }
    }

    fun loadFluids() {
        //Initialize Fluids and Buckets
        MaterialRegistry.getMaterials()
            .map { MaterialFluid(it) }
            .forEach { it.register() }
    }

    fun loadItems() {

        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.getType() == ShapeType.ITEM && it.isValid(material) }
                .map { it with material }
                .map { MaterialItem(it) }
                .forEach { it.register() }
        }
    }

    fun loadToolItems() {
        //Initialize Material Tool Items
        MaterialRegistry.getMaterials()
            .filter { it.hasToolProperty() }
            .flatMap {
                listOf(
                    MaterialAxeItem(it),
                    MaterialFileItem(it),
                    MaterialHammerItem(it),
                    MaterialHoeItem(it),
                    MaterialPickaxeItem(it),
                    MaterialShovelItem(it)
                )
            }
            .forEach { it.register() }
    }

    /*fun loadTags() {
        //Initialize Material Item Tags
        MaterialRegistry.getMaterials().forEach { material ->
            RMResourcePack.addItemTag(
                hiiragiId(material.name),
                ShapeRegistry.getShapes()
                    .filter { it.isValid(material) }
                    .map { it with material }
                    .filterNot { it.isEmpty() }
                    .map { it.getTadId() }
                    .let { TagUtil.addTags(it) }
            )
        }
    }*/

}