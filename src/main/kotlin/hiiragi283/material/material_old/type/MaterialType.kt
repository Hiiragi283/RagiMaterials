package hiiragi283.material.material_old.type

import hiiragi283.material.material_old.ItemMaterial

data class MaterialType(val name: String) {

    init {
        TypeRegistry.setType(this)
    }

    private val parts: MutableSet<ItemMaterial> = mutableSetOf()

    fun addParts(items: Collection<ItemMaterial>): MaterialType = also { parts.addAll(items) }

    fun addParts(vararg items: ItemMaterial): MaterialType = also { parts.addAll(items) }

    fun getParts(): Set<ItemMaterial> = parts.toSet()

}