package hiiragi283.material.common

import hiiragi283.material.common.util.TagUtil
import net.minecraft.item.Item
import net.minecraft.tag.TagKey

object RMTags {

    @JvmField
    val FILES: TagKey<Item> = TagUtil.getItemTagCommon("files")

    @JvmField
    val HAMMERS: TagKey<Item> = TagUtil.getItemTagCommon("hammers")

}