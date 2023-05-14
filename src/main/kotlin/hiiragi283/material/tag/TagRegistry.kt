package hiiragi283.material.tag

import hiiragi283.material.RagiRegistry
import hiiragi283.material.item.ItemMaterial
import hiiragi283.material.util.getItem
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey

object TagRegistry {

    val TAG_IRON_INGOT = TagKey.create(Registry.ITEM_REGISTRY, ResourceLocation("c", "iron_ingots"))

    //Tag -> ItemMaterial
    fun <T : Any> getItemMaterial(tag: TagKey<T>): ItemMaterial {
        val item = getItem(tag.location)
        return if (item is ItemMaterial) item else RagiRegistry.INGOT_IRON
    }

}