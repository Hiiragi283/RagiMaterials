package hiiragi283.material.material_old

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.IMaterialBase
import hiiragi283.material.material_old.type.EnumCrystalType
import hiiragi283.material.material_old.type.MaterialType
import hiiragi283.material.material_old.type.TypeRegistry
import hiiragi283.material.util.ColorUtil
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import rechellatek.snakeToUpperCamelCase
import java.awt.Color

/**
 * Register your custom materials until Pre-Initialization
 */

data class RagiMaterialOld private constructor(
    @JvmField val index: Int,
    @JvmField val name: String,
    val type: MaterialType,
    val burnTime: Int,
    @JvmField val color: Color,
    val crystalType: EnumCrystalType,
    @JvmField val formula: String,
    @JvmField val molar: Float
) : IMaterialBase<RagiMaterialOld> {

    val listValidParts: MutableList<ItemMaterial> = mutableListOf()

    companion object {
        @JvmStatic
        val EMPTY: RagiMaterialOld = Builder().build()

        //NBTタグから素材を取得するメソッド
        @JvmStatic
        fun readFromNBT(tag: NBTTagCompound): RagiMaterialOld =
            MaterialRegistryOld.getMaterial(tag.getString("material"))
    }

    //nameから液体を取得するメソッド
    fun getFluid(): Fluid? = FluidRegistry.getFluid(this.name)

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String = this.name.snakeToUpperCamelCase()

    //翻訳後の名前を取得するメソッド
    fun getTranslatedName(): String = I18n.format("material.$name")

    //materialのツールチップを生成するメソッド
    fun getTooltip(tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", getTranslatedName())) //名称
        if (formula.isNotEmpty()) tooltip.add(I18n.format("tips.ragi_materials.property.formula", formula)) //化学式
        if (molar > 0.0f) tooltip.add(I18n.format("tips.ragi_materials.property.mol", molar)) //モル質量
    }

    //素材が空か判定するメソッド
    override fun isEmpty(): Boolean = this == EMPTY

    //部品と素材の組み合わせが有効か判定するメソッド
    fun isValidPart(item: ItemMaterial): Boolean = item in type.getParts()

    //()つきの化学式を返すメソッド
    fun setBracket(): RagiMaterialOld = copy(formula = "(${formula})")

    //NBTタグに素材を書き込むメソッド
    fun writeToNBT(tag: NBTTagCompound?): NBTTagCompound {
        val tag1 = tag ?: NBTTagCompound()
        return tag1.also { it.setString("material", name) }
    }

    //    IMaterialBaseOld    //

    override fun getColor(): Color = color

    override fun getFormula(): String = formula

    override fun getName(): String = name

    override fun getIndex(): Int = index

    override fun getMolar(): Double = molar.toDouble()

    //素材を登録するメソッド
    fun register() = also {
        val material = MaterialRegistryOld.getMaterial(name)
        //同じ名前の素材がない場合
        if (material.isEmpty()) {
            MaterialRegistryOld.setMaterial(this)
            RagiMaterials.LOGGER.info("The material $name is registered!")
        } else {
            RagiMaterials.LOGGER.warn("The material $name is duplicated with ${material.name}!")
        }
    }

    class Builder(
        private val index: Int = -1,
        private val name: String = "empty",
        private val type: MaterialType = TypeRegistry.INTERNAL
    ) {

        var burnTime = -1
        var color = Color(0xFFFFFF)
        private var components: List<Pair<IMaterialBase<*>, Int>> = listOf()
        var crystalType = EnumCrystalType.NONE
        var formula: String = ""
        var molar: Float = 0.0f

        //燃焼時間を設定するメソッド
        fun setBurnTime(time: Int): Builder = also { it.burnTime = time }

        //色を設定するメソッド
        fun setColor(color: Color): Builder = also { it.color = color }

        //結晶の構造を設定するメソッド
        fun setCrystalType(type: EnumCrystalType): Builder = also { it.crystalType = type }

        //化学式を設定するメソッド
        fun setFormula(formula: String): Builder = also { it.formula = formula }

        //モル質量を設定するメソッド
        fun setMolarMass(molar: Float): Builder = also { it.molar = molar }

        //素材の組成を設定し，そこから自動的に物性を生成するメソッド
        fun setComponents(components: List<Pair<IMaterialBase<*>, Int>>): Builder = also { builder ->
            builder.components = components
            //自動で生成
            builder.color = initColor()
            builder.formula = initFormula()
            builder.molar = initMolar()
        }

        //色を自動で生成するメソッド
        private fun initColor(): Color {
            val mapColor: MutableMap<Color, Int> = mutableMapOf()
            for (pair in components) {
                mapColor[pair.first.getColor()] = pair.second
            }
            return ColorUtil.mixColor(mapColor)
        }

        //化学式を自動で生成するメソッド
        private fun initFormula(): String {
            //変数の宣言・初期化
            var formula = ""
            var subscript: String
            var subscript1 = '\u2080'
            var subscript10 = '\u2080'
            for (pair in components) {
                //文字を代入する
                formula += pair.first.getFormula()
                val weight = pair.second
                //化学式の下付き数字の桁数調整
                if (weight in 2..9) subscript1 = '\u2080' + weight
                else if (weight in 10..99) {
                    subscript1 = '\u2080' + (weight % 10)
                    subscript10 = '\u2080' + (weight / 10)
                }
                //2桁目が0でない場合，下付き数字を2桁にする
                subscript = if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
                //下付き数字を代入する
                if (weight > 1) formula += subscript
            }
            //化学式を返す
            return formula
        }

        //モル質量を自動で生成するメソッド
        private fun initMolar(): Float {
            var molar = 0.0f
            for (pair in components) {
                molar = pair.first.getMolar().toFloat() * pair.second
            }
            return molar
        }

        //素材を混合物に設定するメソッド
        fun setMixture(): Builder = also {
            it.formula = initFormulaMixture()
            it.molar = 0.0f
        }

        //混合物用の化学式を自動で生成するメソッド
        private fun initFormulaMixture(): String {
            var formula = ""
            for (pair in components) {
                formula += ",${pair.first.getFormula()}"
            }
            return if (formula.length > 1) "(${formula.substring(1)})" else ""
        }

        //素材を単体に設定するメソッド
        fun setSimple(element: IMaterialBase<*>, amount: Int): Builder =
            also { setComponents(listOf(element to amount)) }

        fun build(): RagiMaterialOld = RagiMaterialOld(
            index,
            name,
            type,
            burnTime,
            color,
            crystalType,
            formula,
            molar,
        )

        fun buildAndRegister(): RagiMaterialOld {
            return build().register()
        }
    }
}