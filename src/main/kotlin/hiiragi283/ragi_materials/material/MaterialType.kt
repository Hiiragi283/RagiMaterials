package hiiragi283.ragi_materials.material

object MaterialType {

    val map: HashMap<String, TypeHandler> = hashMapOf()

    val DUST = TypeHandler("dust").register() //個体全般
    val FLUID = TypeHandler("fluid").register() //流体全般 (流体ブロックなし)
    val INTERNAL = TypeHandler("internal").register() //内部データ

    val CRYSTAL = TypeHandler("crystal").addTypeBase(DUST).register() //宝石類
    val INGOT = TypeHandler("ingot").addTypeBase(DUST).register() //インゴット全般
    val LIQUID = TypeHandler("liquid").addTypeBase(FLUID).register() //液体 (液体ブロックあり)

    val GAS = TypeHandler("gas").addTypeBase(FLUID, LIQUID).register() //気体全般 (液体ブロックあり)
    val METAL = TypeHandler("metal").addTypeBase(DUST, FLUID, INGOT).register() //金属全般
    val METALLOID = TypeHandler("metalloid").addTypeBase(DUST, INGOT).register() //半金属

    val WILDCARD = TypeHandler("wildcard").addTypeBase(DUST, FLUID, LIQUID, METAL).register() //デバッグ用

    class TypeHandler(val name: String) {

        private val typeBase: MutableList<String> = mutableListOf()

        fun addTypeBase(vararg types: TypeHandler): TypeHandler {
            for (type in types) {
                typeBase.add(type.name)
            }
            return this
        }

        fun getTypeBase(): List<String> {
            typeBase.add(this.name) //自分自身のtypeも含む
            return typeBase
        }

        fun register(): TypeHandler {
            map[this.name] = this
            return this
        }
    }
}