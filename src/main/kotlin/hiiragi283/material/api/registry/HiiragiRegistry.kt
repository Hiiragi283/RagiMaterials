package hiiragi283.material.api.registry

open class HiiragiRegistry<K, V>(val name: String, private val mutable: Boolean = false) {

    //    Lock    //

    protected var isLocked: Boolean = false

    fun lock() {
        isLocked = true
    }

    //    Registration    //

    protected val registry: LinkedHashMap<K, V> = linkedMapOf()

    fun getEntries(): List<Pair<K, V>> = registry.toList()

    fun getValues(): Collection<V> = registry.values

    operator fun get(key: K): V? = registry[key]

    protected val reversed: LinkedHashMap<V, K> = linkedMapOf()

    fun getReversedEntries(): List<Pair<V, K>> = reversed.toList()

    @Synchronized
    internal operator fun <T : V> set(key: K, value: T): T = when {
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

    //    Operation    //

    operator fun contains(key: K): Boolean = registry.containsKey(key)

}