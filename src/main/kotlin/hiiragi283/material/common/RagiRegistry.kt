package hiiragi283.material.common

import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.common.item.MaterialIngotItem
import hiiragi283.material.common.item.MaterialNuggetItem
import hiiragi283.material.common.item.RespawnBookItem

object RagiRegistry {

    val MaterialItemSet: MutableSet<MaterialPartItem> = mutableSetOf()

    fun loadItems() {

        RespawnBookItem.register("respawn_book")

        //Initialize Material Items
        MaterialRegistry.getMaterials()
            .filter { it.isMetal() }
            .flatMap {
                listOf(
                    MaterialIngotItem(it),
                    MaterialNuggetItem(it)
                )
            }
            .forEach { MaterialItemSet.add(it) }

        MaterialItemSet.forEach {
            it.register()
            it.registerModel()
            it.registerRecipe()
            it.registerTag()
        }

    }

}