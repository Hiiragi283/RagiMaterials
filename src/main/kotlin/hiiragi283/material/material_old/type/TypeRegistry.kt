package hiiragi283.material.material_old.type

object TypeRegistry {

    private val mapType: HashMap<String, MaterialType> = hashMapOf()

    fun getType(name: String): MaterialType = mapType.getOrDefault(name, INTERNAL)

    fun setType(type: MaterialType): MaterialType? = mapType.putIfAbsent(type.name, type)

    val CRYSTAL = MaterialType("crystal")

    val DUST = MaterialType("dust")

    val FUEL = MaterialType("fuel")

    val GAS = MaterialType("gas")

    val INGOT = MaterialType("ingot")

    val INTERNAL = MaterialType("internal")

    val LIQUID = MaterialType("liquid")

    val METAL = MaterialType("metal")

    val STONE = MaterialType("stone")

    val WILDCARD = MaterialType("wildcard")

    val WOOD = MaterialType("wood")

}