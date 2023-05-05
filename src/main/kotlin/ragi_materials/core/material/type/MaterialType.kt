package ragi_materials.core.material.type

data class MaterialType constructor(val name: String) {

    val types: MutableList<EnumMaterialType> = mutableListOf()

    fun enableOre() = Builder(name).addType(types).addType(EnumMaterialType.ORE).build()

    fun enableRadio() = Builder(name).addType(types).addType(EnumMaterialType.RADIOACTIVE).build()

    fun match(type: MaterialType) = name == "wildcard" || this == type

    class Builder(val name: String) {

        private var list: MutableList<EnumMaterialType> = mutableListOf()

        //fun addType(type: EnumMaterialType): Builder = also { list.add(type) }

        fun addType(types: Collection<EnumMaterialType>): Builder = also { list.addAll(types) }

        fun addType(vararg types: EnumMaterialType): Builder = also { list.addAll(types) }

        fun build(): MaterialType {
            val type = MaterialType(name).also { it.types.addAll(list) }
            TypeRegistry.map[type.name] = type
            return type
        }
    }
}