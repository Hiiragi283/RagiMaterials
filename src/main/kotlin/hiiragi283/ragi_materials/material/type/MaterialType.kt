package hiiragi283.ragi_materials.material.type

class MaterialType private constructor(val name: String, val list: List<EnumMaterialType>) {

    class Builder(val name: String) {

        private var list: MutableList<EnumMaterialType> = mutableListOf()

        fun addType(type: EnumMaterialType): Builder = also { list.add(type) }

        fun addTypes(types: List<EnumMaterialType>): Builder = also { list.addAll(types) }

        fun addTypes(vararg types: EnumMaterialType): Builder = also {
            for (type in types) {
                list.add(type)
            }
        }

        fun build(): MaterialType {
            val type = MaterialType(name, list)
            TypeRegistry.map[type.name] = type
            return type
        }
    }
}