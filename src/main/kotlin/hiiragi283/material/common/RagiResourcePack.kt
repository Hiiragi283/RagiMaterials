package hiiragi283.material.common

import hiiragi283.material.common.util.appendBefore
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.tags.JTag
import net.minecraft.util.Identifier

object RagiResourcePack {

    private val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(hiiragiId("runtime"))

    fun addBlockState(identifier: Identifier, state: JState) {
        RESOURCE_PACK.addBlockState(state, identifier)
    }

    private fun addModel(identifier: Identifier, model: JModel) {
        RESOURCE_PACK.addModel(model, identifier)
    }

    fun addBlockModel(identifier: Identifier, model: JModel) {
        addModel(identifier.appendBefore("block/"), model)
    }


    fun addItemModel(identifier: Identifier, model: JModel) {
        addModel(identifier.appendBefore("item/"), model)
    }

    fun addRecipe(identifier: Identifier, recipe: JRecipe) {
        RESOURCE_PACK.addRecipe(identifier, recipe)
    }

    private fun addTag(identifier: Identifier, tag: JTag) {
        RESOURCE_PACK.addTag(identifier, tag)
    }

    fun addBlockTag(identifier: Identifier, tag: JTag) {
        addTag(identifier.appendBefore("blocks/"), tag)
    }

    fun addItemTag(identifier: Identifier, tag: JTag) {
        addTag(identifier.appendBefore("items/"), tag)
    }

    fun register() {
        RRPCallback.BEFORE_VANILLA.register { it.add(RESOURCE_PACK) }
        RagiMaterials.LOGGER.info("The resource pack registered!")
    }

}