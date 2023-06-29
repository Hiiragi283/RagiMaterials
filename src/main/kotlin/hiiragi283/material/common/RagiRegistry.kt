package hiiragi283.material.common

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.api.item.MaterialPartBlockItem
import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.part.PartType
import hiiragi283.material.common.item.RespawnBookItem

object RagiRegistry {

    val materialBlockSet: MutableSet<MaterialPartBlock> = mutableSetOf()
    val materialItemSet: MutableSet<MaterialPartItem> = mutableSetOf()

    fun loadBlocks() {
        //Initialize Material Blocks
        MaterialRegistry.getMaterials().forEach { material ->
            PartRegistry.getParts()
                .filter { it.type == PartType.BLOCK }
                .filter { it.predicate(material) }
                .map { MaterialPartBlock(material, it) }
                .forEach { materialBlockSet.add(it) }
        }

        materialBlockSet.forEach {
            it.register()
            it.registerModel()
            it.registerRecipe()
            it.registerTag()
        }

    }

    fun loadBlockItems() {
        //Initialize Material BlockItems
        materialBlockSet
            .map(::MaterialPartBlockItem)
            .forEach {
                it.register()
                it.registerModel()
            }
    }

    fun loadItems() {

        RespawnBookItem.register()

        //Initialize Material Items
        MaterialRegistry.getMaterials().forEach { material ->
            PartRegistry.getParts()
                .filter { it.type == PartType.ITEM }
                .filter { it.predicate(material) }
                .map { MaterialPartItem(material, it) }
                .forEach { materialItemSet.add(it) }
        }

        materialItemSet.forEach {
            it.register()
            it.registerModel()
            it.registerRecipe()
            it.registerTag()
        }

    }

}