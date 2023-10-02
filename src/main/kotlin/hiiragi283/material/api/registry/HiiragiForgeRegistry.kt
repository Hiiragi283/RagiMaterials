package hiiragi283.material.api.registry

import net.minecraft.client.renderer.color.BlockColors
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

class HiiragiForgeRegistry<T: HiiragiEntry<U>, U: IForgeRegistryEntry<U>>(name: String) : HiiragiRegistry<ResourceLocation, T>(name) {

    private fun getForgeValues(): List<U> = getValues().map(HiiragiEntry<U>::getObject)

    fun registerAll(entries: Collection<T>) {
        entries.forEach(this::register)
    }

    fun <t: T> register(entry: t): t {
        entry.onRegister()
        return register(entry.getObject().registryName!!, entry)
    }

    fun <t: T> registerOptional(entry: t, predicate: () -> Boolean): t {
        if (predicate()) register(entry)
        return entry
    }

    fun register(registry: IForgeRegistry<U>) {
        getForgeValues().map(registry::register)
        lock()
    }

    fun registerOreDict() {
        getValues().forEach(HiiragiEntry<U>::registerOreDict)
    }

    fun registerRecipe() {
        getValues().forEach(HiiragiEntry<U>::registerRecipe)
    }

    fun registerBlockColor(blockColors: BlockColors) {
        getValues().forEach { it.registerBlockColor(blockColors) }
    }

    fun registerItemColor(itemColors: ItemColors) {
        getValues().forEach { it.registerItemColor(itemColors) }
    }

    fun registerModel() {
        getValues().forEach(HiiragiEntry<U>::registerModel)
    }

}