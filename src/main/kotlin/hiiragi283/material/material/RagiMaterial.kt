package hiiragi283.material.material

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.part.MaterialPart
import hiiragi283.material.material.part.PartRegistry
import hiiragi283.material.material.type.EnumCrystalType
import hiiragi283.material.material.type.MaterialType
import hiiragi283.material.material.type.TypeRegistry
import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor
import net.minecraft.client.resources.I18n
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import rechellatek.snakeToUpperCamelCase
import java.awt.Color

/**
 * Register your custom materials until Pre-Initialization
 */

data class RagiMaterial private constructor(
    override val name: String = "empty",
    override val type: MaterialType = TypeRegistry.INTERNAL,
    val burnTime: Int = -1,
    override val color: Color = RagiColor.WHITE,
    val crystalType: EnumCrystalType = EnumCrystalType.NONE,
    override val formula: String = "",
    override val molar: Float? = null
) : IMaterialBase<RagiMaterial> {

    val listValidParts: MutableList<MaterialPart> = mutableListOf()

    companion object {
        @JvmStatic
        val EMPTY = RagiMaterial()

        //NBTタグから素材を取得するメソッド
        @JvmStatic
        fun readFromNBT(tag: NBTTagCompound) = MaterialRegistry.getMaterial(tag.getString("material"))
    }

    //nameから液体を取得するメソッド
    fun getFluid(): Fluid? = FluidRegistry.getFluid(this.name)

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String = this.name.snakeToUpperCamelCase()

    //materialのツールチップを生成するメソッド
    fun getTooltip(tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", I18n.format("material.$name"))) //名称
        tooltip.add(I18n.format("tips.ragi_materials.property.formula", formula)) //化学式
        molar?.let { tooltip.add(I18n.format("tips.ragi_materials.property.mol", it)) } //モル質量
    }

    //素材が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY

    //部品と素材の組み合わせが有効か判定するメソッド
    fun isValidPart(part: MaterialPart): Boolean = part.type in type.types

    //()つきの化学式を返すメソッド
    fun setBracket() = copy(formula = "(${formula})")

    //素材を登録するメソッド
    override fun register(): RagiMaterial {
        val material = MaterialRegistry.getMaterial(name)
        //同じ名前の素材がない場合
        if (!material.isEmpty()) {
            MaterialRegistry.setMaterial(this)
            RagiMaterials.LOGGER.info("The material $name is registered!")
        } else {
            RagiMaterials.LOGGER.warn("The material $name is duplicated with ${material.name}!")
        }
        for (part in PartRegistry.getList()) {
            if (isValidPart(part)) {
                listValidParts.add(part)
            }
        }
        return this
    }

    //NBTタグに素材を書き込むメソッド
    fun writeToNBT(tag: NBTTagCompound?): NBTTagCompound {
        val tag1 = tag ?: NBTTagCompound()
        return tag1.also { it.setString("material", name) }
    }

    class Builder(
        private val name: String = "empty",
        private val type: MaterialType = TypeRegistry.INTERNAL
    ) {

        private var burnTime = -1
        private var color = Color(0xFFFFFF)
        private var components: List<Pair<IMaterialBase<*>, Int>> = listOf()
        private var crystalType = EnumCrystalType.NONE
        private var formula: String = ""
        private var molar: Float? = null

        //燃焼時間を設定するメソッド
        fun setBurnTime(time: Int) = also { it.burnTime = time }

        //色を設定するメソッド
        fun setColor(color: Color) = also { it.color = color }

        //結晶の構造を設定するメソッド
        fun setCrystalType(type: EnumCrystalType) = also { it.crystalType = type }

        //化学式を設定するメソッド
        fun setFormula(formula: String) = also { it.formula = formula }

        //モル質量を設定するメソッド
        fun setMolarMass(molar: Float?) = also { it.molar = molar }

        //素材の組成を設定し，そこから自動的に物性を生成するメソッド
        fun setComponents(components: List<Pair<IMaterialBase<*>, Int>>) = also { builder ->
            builder.components = components
            val materials = builder.components.toMap().keys
            //自動で生成
            builder.color = initColor()
            builder.formula = initFormula()
            builder.molar = initMolar()
        }

        //色を自動で生成するメソッド
        private fun initColor(): Color {
            val mapColor: MutableMap<Color, Int> = mutableMapOf()
            for (pair in components) {
                mapColor[pair.first.color] = pair.second
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
                formula += pair.first.formula
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
        private fun initMolar(): Float? {
            var molar = 0.0f
            for (pair in components) {
                pair.first.molar?.let { molar = it * pair.second }
            }
            return if (molar == 0.0f) null else molar
        }

        //素材を混合物に設定するメソッド
        fun setMixture() = also {
            it.formula = initFormulaMixture()
            it.molar = null
        }

        //混合物用の化学式を自動で生成するメソッド
        private fun initFormulaMixture(): String {
            var formula = ""
            for (pair in components) {
                formula += ",${pair.first.formula}"
            }
            return "(${formula.substring(1)})"
        }

        //素材を単体に設定するメソッド
        fun setSimple(element: IMaterialBase<*>, amount: Int) = also { setComponents(listOf(element to amount)) }

        fun build(): RagiMaterial = RagiMaterial(
            name,
            type,
            burnTime,
            color,
            crystalType,
            formula,
            molar,
        )

        fun buildAndRegister(): RagiMaterial {
            return build().register()
        }
    }
}