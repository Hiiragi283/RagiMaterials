package hiiragi283.ragi_materials.material

open class MaterialType(val name: String) {

    companion object {
        val DUST = MaterialType("dust") //個体全般
        val FLUID = MaterialType("fluid") //流体全般 (流体ブロックなし)
        val INTERNAL = MaterialType("interal") //内部データ
        val RADIOACTIVE = MaterialType("radioactive") //放射性物質

        val CRYSTAL = MaterialType("crystal")
            .addTypeBase(DUST) //宝石類
        val INGOT = MaterialType("ingot")
            .addTypeBase(DUST) //インゴット全般
        val LIQUID = MaterialType("liquid")
            .addTypeBase(FLUID) //液体 (液体ブロックあり)

        val GAS = MaterialType("gas")
            .addTypeBase(FLUID, LIQUID) //気体全般 (液体ブロックあり)
        val METAL = MaterialType("metal")
            .addTypeBase(DUST, FLUID, INGOT) //金属全般
        val SEMIMETAL = MaterialType("semimetal")
            .addTypeBase(DUST, INGOT) //半金属

        val METAL_RADIO = MaterialType("metal_radio")
            .addTypeBase(DUST, FLUID, METAL, RADIOACTIVE) //放射性金属

        val WILDCARD = MaterialType("wildcard")
            .addTypeBase(DUST, FLUID, LIQUID, METAL) //デバッグ用

        val list =listOf(
            CRYSTAL,
            DUST,
            FLUID,
            GAS,
            INGOT,
            INTERNAL,
            LIQUID,
            METAL,
            METAL_RADIO,
            RADIOACTIVE,
            SEMIMETAL,
            WILDCARD
        )
    }

    private val typeBase: MutableList<String> = mutableListOf()

    fun addTypeBase(vararg types: MaterialType): MaterialType {
        for (type in types) {
            typeBase.add(type.name)
        }
        return this
    }

    fun getTypeBase(): List<String> {
        typeBase.add(this.name) //自分自身のtypeも含む
        return typeBase
    }
}