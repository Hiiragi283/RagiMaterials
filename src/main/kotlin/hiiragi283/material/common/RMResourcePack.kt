package hiiragi283.material.common

import hiiragi283.material.common.util.appendBefore
import hiiragi283.material.common.util.hiiragiId
import net.minecraft.block.Block
import net.minecraft.data.client.BlockStateSupplier
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.item.Item
import net.minecraft.loot.LootTable
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import pers.solid.brrp.v1.api.RuntimeResourcePack
import pers.solid.brrp.v1.fabric.api.RRPCallback
import pers.solid.brrp.v1.model.ModelJsonBuilder
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder

object RMResourcePack {

    private val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(hiiragiId("runtime"))

    private val TAG_REGISTRY: HashMap<TagKey<*>, IdentifiedTagBuilder<*>> = hashMapOf()

    fun register() {
        TAG_REGISTRY.forEach { RESOURCE_PACK.addTag(it.key, it.value) }
        RRPCallback.AFTER_VANILLA.register { it.add(RESOURCE_PACK) }
    }

    //    BlockState    //

    fun addBlockState(identifier: Identifier, state: BlockStateSupplier) {
        RESOURCE_PACK.addBlockState(identifier, state)
    }

    //    LootTable    //

    private fun addLootTable(identifier: Identifier, lootTable: LootTable) {
        RESOURCE_PACK.addLootTable(identifier, lootTable)
    }

    fun addBlockLootTable(identifier: Identifier, lootTable: LootTable) {
        addLootTable(identifier.appendBefore("blocks/"), lootTable)
    }

    //    Model    //

    private fun addModel(identifier: Identifier, model: ModelJsonBuilder) {
        RESOURCE_PACK.addModel(identifier, model)
    }

    fun addBlockModel(identifier: Identifier, model: ModelJsonBuilder) {
        addModel(identifier.appendBefore("block/"), model)
    }


    fun addItemModel(identifier: Identifier, model: ModelJsonBuilder) {
        addModel(identifier.appendBefore("item/"), model)
    }

    //    Recipe    //

    fun addRecipe(identifier: Identifier, recipe: RecipeJsonProvider) {
        RESOURCE_PACK.addRecipe(identifier, recipe)
    }

    fun addRecipe(identifier: Identifier, recipe: CraftingRecipeJsonBuilder) {
        RESOURCE_PACK.addRecipeAndAdvancement(identifier, recipe)
    }

    //    Tag    //

    fun addBlockTag(tagKey: TagKey<Block>, init: IdentifiedTagBuilder<*>.() -> Unit) {
        val builder = TAG_REGISTRY[tagKey] ?: IdentifiedTagBuilder.createBlock(tagKey)
        builder.init()
        TAG_REGISTRY[tagKey] = builder
    }

    fun addItemTag(tagKey: TagKey<Item>, init: IdentifiedTagBuilder<*>.() -> Unit) {
        val builder = TAG_REGISTRY[tagKey] ?: IdentifiedTagBuilder.createItem(tagKey)
        builder.init()
        TAG_REGISTRY[tagKey] = builder
    }

}