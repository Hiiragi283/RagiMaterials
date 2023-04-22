package ragi_materials.core.material.type

data class MaterialType constructor(val name: String, val list: MutableList<EnumMaterialType>) {

    fun enableOre() = Builder(name).addTypes(list).addType(EnumMaterialType.ORE).build()

    fun enableRadio() = Builder(name).addTypes(list).addType(EnumMaterialType.RADIOACTIVE).build()

    fun match(type: MaterialType) = this == TypeRegistry.WILDCARD || this == type

    class Builder(val name: String) {

        private var list: MutableList<EnumMaterialType> = mutableListOf()

        fun addType(type: EnumMaterialType): Builder = also { list.add(type) }

        fun addTypes(types: List<EnumMaterialType>): Builder = also { list.addAll(types) }

        fun addTypes(vararg types: EnumMaterialType): Builder = also { list.addAll(types) }

        fun build(): MaterialType {
            val type = MaterialType(name, list)
            TypeRegistry.map[type.name] = type
            return type
        }
    }
}