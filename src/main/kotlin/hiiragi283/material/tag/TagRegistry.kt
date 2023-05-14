package hiiragi283.material.tag

import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

object TagRegistry {

    fun <T : Registry<T>> getOrCreateCommonTag(registry: RegistryKey<T>, id: Identifier): TagKey<T> =
        TagKey.of(registry, id)

}