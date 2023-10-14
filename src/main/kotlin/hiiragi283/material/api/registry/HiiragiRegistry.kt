package hiiragi283.material.api.registry

open class HiiragiRegistry<K, V>(val name: String, private val removable: Boolean = false) {

    //    Lock    //

    private var isLocked: Boolean = false

    fun lock() {
        isLocked = true
    }

    //    Registration    //

    private val registry: LinkedHashMap<K, V> = linkedMapOf()

    fun getValues(): Collection<V> = registry.values

    fun getValue(key: K): V? = registry[key]

    fun <T : V> register(key: K, value: T): T = when {
        isLocked -> throw IllegalStateException("[$name] This registry is locked!")
        registry.containsKey(key) -> throw IllegalStateException("[$name] The key: $key is already registered!")
        else -> {
            registry[key] = value
            value
        }
    }

    fun remove(key: K): V? = when {
        isLocked -> throw IllegalStateException("[$name] This registry is locked!")
        !removable -> throw IllegalStateException("[$name] This registry cannot remove entry!")
        !registry.containsKey(key) -> throw IllegalStateException("[$name] The key: $key is not registered!")
        else -> registry.remove(key)
    }

    fun <T : Comparable<T>> sort(sorter: (Pair<K, V>) -> T) {
        val mapSorted: Map<K, V> = registry.toList().sortedBy(sorter).toMap()
        registry.clear()
        registry.putAll(mapSorted)
    }

}