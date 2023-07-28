package hiiragi283.material.common.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object TagUtil {

    @JvmStatic
    fun getBlockTag(identifier: Identifier): TagKey<Block> = TagKey.of(Registry.BLOCK_KEY, identifier)

    @JvmStatic
    fun getBlockTagCommon(name: String): TagKey<Block> = TagKey.of(Registry.BLOCK_KEY, commonId(name))


    @JvmStatic
    fun getItemTag(identifier: Identifier): TagKey<Item> = TagKey.of(Registry.ITEM_KEY, identifier)

    @JvmStatic
    fun getItemTagCommon(name: String): TagKey<Item> = TagKey.of(Registry.ITEM_KEY, commonId(name))

}