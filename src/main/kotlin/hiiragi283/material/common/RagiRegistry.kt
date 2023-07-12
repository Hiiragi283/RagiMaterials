package hiiragi283.material.common

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.MaterialPartBlockItem
import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.item.RespawnBookItem

object RagiRegistry {

    private fun register(entry: HiiragiEntry) {
        entry.register()
        entry.registerModel()
        entry.registerRecipe()
        entry.registerTag()
    }

    private fun register(sets: Set<HiiragiEntry>) {
        sets.forEach(::register)
    }

    fun loadBlocks() {
        //Initialize Material Blocks
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.type == HiiragiShape.Type.BLOCK }
                .filter { it.isValid(material) }
                .map { MaterialPartBlock(material, it) }
                .forEach {
                    register(it)
                    register(MaterialPartBlockItem(it))
                }
        }
    }

    fun loadFluids() {
        //Initialize Fluids and Buckets
        MaterialRegistry.getMaterials()
            .map(::MaterialFluid)
            .forEach(::register)
    }

    fun loadItems() {

        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            ShapeRegistry.getShapes()
                .filter { it.type == HiiragiShape.Type.ITEM }
                .filter { it.isValid(material) }
                .map { MaterialPartItem(material, it) }
                .forEach(::register)
        }
    }

}