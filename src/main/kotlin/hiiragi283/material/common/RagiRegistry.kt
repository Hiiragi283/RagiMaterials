package hiiragi283.material.common

import hiiragi283.material.api.IHiiragiEntry
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
        PartRegistry.getParts()
            .filter { it.type == PartType.BLOCK }
            .forEach { part ->
                MaterialRegistry.getMaterials()
                    .filter { part.predicate(it) }
                    .map { MaterialPartBlock(it, part) }
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
        PartRegistry.getParts()
            .filter { it.type == PartType.ITEM }
            .forEach { part ->
                MaterialRegistry.getMaterials()
                    .filter { part.predicate(it) }
                    .map { MaterialPartItem(it, part) }
                    .forEach { materialItemSet.add(it) }
            }

        materialItemSet.forEach {
            it.register()
            it.registerModel()
            it.registerRecipe()
            it.registerTag()
        }

    }

    fun initTranslation() {

        MaterialRegistry.getMaterials().forEach(IHiiragiEntry::registerTranslation)
        RagiMaterials.LOGGER.info("Material translation registered!")

    }

}