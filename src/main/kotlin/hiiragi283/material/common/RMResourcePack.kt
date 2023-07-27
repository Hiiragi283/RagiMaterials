package hiiragi283.material.common

import hiiragi283.material.common.util.appendBefore
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.loot.JLootTable
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.tags.JTag
import net.minecraft.util.Identifier

object RMResourcePack {

    private val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(hiiragiId("runtime"))

    //    BlockState    //

    fun addBlockState(identifier: Identifier, state: JState) {
        RESOURCE_PACK.addBlockState(state, identifier)
    }

    //    LootTable    //

    private fun addLootTable(identifier: Identifier, lootTable: JLootTable) {
        RESOURCE_PACK.addLootTable(identifier, lootTable)
    }

    fun addBlockLootTable(identifier: Identifier, lootTable: JLootTable) {
        addLootTable(identifier.appendBefore("block/"), lootTable)
    }

    //    Model    //

    private fun addModel(identifier: Identifier, model: JModel) {
        RESOURCE_PACK.addModel(model, identifier)
    }

    fun addBlockModel(identifier: Identifier, model: JModel) {
        addModel(identifier.appendBefore("block/"), model)
    }


    fun addItemModel(identifier: Identifier, model: JModel) {
        addModel(identifier.appendBefore("item/"), model)
    }

    //    Recipe    //

    fun addRecipe(identifier: Identifier, recipe: JRecipe) {
        RESOURCE_PACK.addRecipe(identifier, recipe)
    }

    //    Tag    //

    private val REGISTRY: HashMap<Identifier, MutableList<Identifier>> = hashMapOf()

    private fun addTag(identifier: Identifier, tag: Identifier, isTag: Boolean = false) {
        val list = REGISTRY[identifier] ?: mutableListOf()
        if (isTag) list.add(tag) else list.add(tag)
        REGISTRY[identifier] = list
    }

    fun addBlockTag(identifier: Identifier, tag: Identifier, isTag: Boolean = false) {
        addTag(identifier.appendBefore("blocks/"), tag, isTag)
    }

    fun addBlockAndItemTag(identifier: Identifier, tag: Identifier, isTag: Boolean = false) {
        addBlockTag(identifier, tag, isTag)
        addItemTag(identifier, tag, isTag)
    }

    fun addItemTag(identifier: Identifier, tag: Identifier, isTag: Boolean = false) {
        addTag(identifier.appendBefore("items/"), tag, isTag)
    }

    /*private fun addTag(identifier: Identifier, tag: JTag) {
        RESOURCE_PACK.addTag(identifier, tag)
    }

    fun addBlockTag(identifier: Identifier, tag: JTag) {
        addTag(identifier.appendBefore("blocks/"), tag)
    }

    fun addBlockAndItemTag(identifier: Identifier, tag: JTag) {
        addBlockTag(identifier, tag)
        addItemTag(identifier, tag)
    }

    fun addItemTag(identifier: Identifier, tag: JTag) {
        addTag(identifier.appendBefore("items/"), tag)
    }*/

    //    Register    //

    fun register() {
        for ((id, list) in REGISTRY) {
            val jTag = JTag.tag()
            list.forEach { jTag.add(it) }
            RESOURCE_PACK.addTag(id, jTag)
        }
        RRPCallback.AFTER_VANILLA.register { it.add(RESOURCE_PACK) }
        RagiMaterials.LOGGER.info("The resource pack registered!")
    }

}