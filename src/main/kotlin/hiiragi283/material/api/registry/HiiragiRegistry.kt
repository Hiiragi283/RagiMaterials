package hiiragi283.material.api.registry

open class HiiragiRegistry<K, V>(val name: String, private val mutable: Boolean = false) {

    //    Lock    //

    private var isLocked: Boolean = false

    fun lock() {
        isLocked = true
    }

    //    Registration    //

    private val registry: LinkedHashMap<K, V> = linkedMapOf()

    fun getEntries(): List<Pair<K, V>> = registry.toList()

    fun getValues(): Collection<V> = registry.values

    fun getValue(key: K): V? = registry[key]

    private val reversed: LinkedHashMap<V, K> = linkedMapOf()

    fun getReversedEntries(): List<Pair<V, K>> = reversed.toList()

    fun getKeys(): Collection<K> = registry.keys

    fun getKey(value: V): K? = reversed[value]

    @Synchronized
    fun <T : V> register(key: K, value: T): T = when {
        isLocked -> throw IllegalStateException("[$name] This registry is locked!")
        registry.containsKey(key) && !mutable -> throw IllegalStateException("[$name] The key: $key is already registered!")
        else -> {
            registry[key] = value
            value
        }
    }

    @Synchronized
    fun remove(key: K): V? = when {
        isLocked -> throw IllegalStateException("[$name] This registry is locked!")
        !mutable -> throw IllegalStateException("[$name] This registry cannot remove entry!")
        !registry.containsKey(key) -> throw IllegalStateException("[$name] The key: $key is not registered!")
        else -> registry.remove(key)
    }

    @Synchronized
    fun <T : Comparable<T>> sort(sorter: (Pair<K, V>) -> T) {
        val mapSorted: Map<K, V> = registry.toList().sortedBy(sorter).toMap()
        registry.clear()
        registry.putAll(mapSorted)
    }

}