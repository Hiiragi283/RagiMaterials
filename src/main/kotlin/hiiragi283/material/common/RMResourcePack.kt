package hiiragi283.material.common

import hiiragi283.material.common.util.TagUtil
import hiiragi283.material.common.util.appendBefore
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JRecipe
import net.minecraft.util.Identifier

object RMResourcePack {

    private val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(hiiragiId("runtime"))

    //    BlockState    //

    fun addBlockState(identifier: Identifier, state: JState) {
        RESOURCE_PACK.addBlockState(state, identifier)
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

    private val TAG_REGISTRY: HashMap<Identifier, MutableList<Identifier>> = hashMapOf()

    private fun addTag(identifier: Identifier, tag: Identifier) {
        val tags = TAG_REGISTRY[identifier] ?: mutableListOf()
        tags.add(tag)
        TAG_REGISTRY[identifier] = tags
    }

    fun addBlockTag(identifier: Identifier, tag: Identifier) {
        addTag(identifier.appendBefore("blocks/"), tag)
    }

    fun addItemTag(identifier: Identifier, tag: Identifier) {
        addTag(identifier.appendBefore("items/"), tag)
    }

    //    Register    //

    fun register() {

        TAG_REGISTRY.forEach { RESOURCE_PACK.addTag(it.key, TagUtil.addIds(it.value)) }

        RRPCallback.BEFORE_VANILLA.register { it.add(RESOURCE_PACK) }
        RagiMaterials.LOGGER.info("The resource pack registered!")
    }

}