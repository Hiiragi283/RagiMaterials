package hiiragi283.material.common

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.block.registerMaterialOreBlocks
import hiiragi283.material.api.block.registerMaterialStorageBlocks
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.*
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.api.shape.ShapeType
import hiiragi283.material.common.item.RespawnBookItem
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.util.registry.Registry

object RMRegistry {

    fun loadBlocks() {

        //Initialize Material Storage Blocks
        MaterialRegistry.getMaterials()
            .filter { "block_metal" in it.validShapes || "block_gem" in it.validShapes }
            .forEach { registerMaterialStorageBlocks(it) }

        //Initialize Material Ore Blocks
        MaterialRegistry.getMaterials()
            .filter { it.hasOre }
            .forEach { registerMaterialOreBlocks(it) }

    }

    fun loadBlockItems() {
        //Initialize BlockItems
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.getType() == ShapeType.BLOCK }
                .map { Registry.BLOCK.get(HiiragiPart(it, material).getId()) }
                .filterIsInstance<MaterialBlock>()
                .map { MaterialBlockItem(it) }
                .forEach { it.register() }
        }
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
                .map { HiiragiPart(it, material) }
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

    fun loadTags() {

        //Initialize Material Block Tags
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.getType() == ShapeType.BLOCK }
                .map { HiiragiPart(it, material) }
        }

        //Initialize Material Item Tags
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.isValid(material) }
                .map { HiiragiPart(it, material) }
                .filterNot { it.isEmpty() }
                .map { it.getCommonId() }
                .forEach {
                    RMResourcePack.addItemTag(hiiragiId(material.name), it, true)
                }
        }
    }

}