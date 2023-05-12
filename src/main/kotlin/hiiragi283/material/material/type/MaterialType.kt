package hiiragi283.material.material.type

import hiiragi283.material.item.ItemMaterial

data class MaterialType(val name: String, val parts: Set<ItemMaterial>) {

    /**
     * Collectionは比較不可能なため，わざと除外する
     */
    override fun equals(other: Any?): Boolean {
        return if (other !== null && other is MaterialType) name == other.name else false
    }

    override fun hashCode() = name.hashCode()

    class Builder(val name: String) {

        private val parts: MutableSet<ItemMaterial> = mutableSetOf()

        fun addParts(items: Collection<ItemMaterial>) = also { parts.addAll(items) }

        fun addParts(vararg items: ItemMaterial) = also { parts.addAll(items) }

        fun build() = MaterialType(name, parts).also {
            TypeRegistry.setType(it)
        }
    }
}