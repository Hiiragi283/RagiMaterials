package hiiragi283.material.api.registry

open class HiiragiRegistry<K, V>(val name: String) {

    private val registry: LinkedHashMap<K, V> = linkedMapOf()

    private var isLocked: Boolean = false

    fun getEntries(): List<Pair<K, V>> = registry.toList()

    fun getValues(): Collection<V> = registry.values

    fun getValue(key: K): V? = registry[key]

    fun getValueOrElse(key: K, defaultValue: V) = registry.getOrElse(key) { defaultValue }

    fun getValueOrElse(key: K, defaultValue: () -> V) = registry.getOrElse(key, defaultValue)

    fun getRegistry(): Map<K, V> = registry.toMap()

    fun <T : V> register(key: K, value: T): T {
        if (isLocked) throw IllegalStateException("[$name] This registry is locked!")
        else if (registry.containsKey(key)) throw IllegalStateException("[$name] The key: $key is already registered!")
        else {
            registry[key] = value
            return value
        }
    }

    fun <T : Comparable<T>> sort(sorter: (Pair<K, V>) -> T) {
        val mapSorted: Map<K, V> = registry.toList().sortedBy(sorter).toMap()
        registry.clear()
        registry.putAll(mapSorted)
    }

    fun lock() {
        isLocked = true
    }

}