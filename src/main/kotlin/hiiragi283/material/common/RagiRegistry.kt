package hiiragi283.material.common

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.createMaterialBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.createMaterialBlockItem
import hiiragi283.material.api.item.createMaterialItem
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.item.ForgeFileItem
import hiiragi283.material.common.item.ForgeHammerItem
import hiiragi283.material.common.item.RespawnBookItem

object RagiRegistry {

    fun loadBlocks() {
        //Initialize Material Blocks
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.type == HiiragiShape.Type.BLOCK }
                .filter { it.isValid(material) }
                .forEach {
                    createMaterialBlock(material, it).also { block ->
                        block.register()
                        createMaterialBlockItem(material, it, block).register()
                    }
                }
        }
    }

    fun loadFluids() {
        //Initialize Fluids and Buckets
        MaterialRegistry.getMaterials()
            .map(::MaterialFluid)
            .forEach(HiiragiEntry<*>::register)
    }

    fun loadItems() {

        ForgeFileItem.register()
        ForgeHammerItem.register()
        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.type == HiiragiShape.Type.ITEM }
                .filter { it.isValid(material) }
                .map { createMaterialItem(material, it) }
                .forEach(HiiragiEntry<*>::register)
        }
    }

}