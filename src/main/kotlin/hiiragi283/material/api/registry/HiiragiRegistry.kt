package hiiragi283.material.api.registry

class HiiragiRegistry<K, V>(val name: String) {

    private val registry: LinkedHashMap<K, V> = linkedMapOf()

    private var isLocked: Boolean = false

    fun getValues(): Collection<V> = registry.values

    fun getValue(key: K): V? = registry[key]

    fun getValueOrElse(key: K, defaultValue: V) = registry.getOrElse(key) { defaultValue }

    fun getValueOrElse(key: K, defaultValue: () -> V) = registry.getOrElse(key, defaultValue)

    fun getRegistry(): Map<K, V> = registry.toMap()

    fun register(key: K, value: V) {
        if (isLocked) throw IllegalStateException("")
        else if (registry.containsKey(key)) throw IllegalStateException("")
        else registry[key] = value
    }

    fun lock() {
        isLocked = true
    }

}