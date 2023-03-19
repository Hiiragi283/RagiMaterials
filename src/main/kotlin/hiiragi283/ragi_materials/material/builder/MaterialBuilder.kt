package hiiragi283.ragi_materials.material.builder

import hiiragi283.ragi_materials.material.type.MaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraft.item.EnumRarity
import net.minecraftforge.common.IRarity
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import java.awt.Color

open class MaterialBuilder(open val index: Int, open val name: String, open val type: MaterialType) {

    //private変数の宣言
    open var burnTime = -1
    open var color: Color = Color(0xFFFFFF)
    open var decayed: MaterialBuilder? = null
    open var formula: String? = null
    open var hasBracket = false
    open var hasOre = false
    open var molar: Float? = null
    open var oredictAlt: String? = null
    open var rarity: IRarity = EnumRarity.COMMON
    open var tempBoil: Int? = null
    open var tempMelt: Int? = null
    open var tempSubl: Int? = null

    companion object {
        val EMPTY = MaterialBuilder(0, "empty", TypeRegistry.INTERNAL).apply {}
    }

    //化学式に()をつけるメソッド
    fun addBracket(): MaterialBuilder = copy().also { it.hasBracket = true }

    //nameから液体を取得するメソッド
    fun getFluid(): Fluid? = FluidRegistry.getFluid(this.name)

    //コピーを返すメソッド
    open fun copy(): MaterialBuilder {
        return MaterialBuilder(this.index, this.name, this.type).also {
            it.burnTime = this.burnTime
            it.color = this.color
            it.decayed = this.decayed
            it.formula = this.formula
            it.hasBracket = this.hasBracket
            it.hasOre = this.hasOre
            it.molar = this.molar
            it.oredictAlt = this.oredictAlt
            it.rarity = this.rarity
            it.tempBoil = this.tempBoil
            it.tempMelt = this.tempMelt
            it.tempSubl = this.tempSubl
        }
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String = this.name.snakeToUpperCamelCase()

    //素材が空か判定するメソッド
    fun isEmpty(): Boolean = this == EMPTY

    fun isNotEmpty(): Boolean = !isEmpty()

    //燃焼時間を設定するメソッド
    fun setBurnTime(time: Int): MaterialBuilder = also { it.burnTime = time }

    //色を設定するメソッド
    fun setColor(color: Color): MaterialBuilder = also { it.color = color }

    //崩壊後の素材を設定するメソッド
    fun setDecayed(material: MaterialBuilder): MaterialBuilder = also { it.decayed = material }

    //化学式を設定するメソッド
    fun setFormula(formula: String?): MaterialBuilder = also { it.formula = formula }

    //モル質量を設定するメソッド
    fun setMolarMass(molar: Float?): MaterialBuilder = also { it.molar = molar }

    //鉱石を追加するメソッド
    fun setOre(): MaterialBuilder = also { it.hasOre = true }

    //モル質量を設定するメソッド
    fun setOreDictAlt(oredict: String?): MaterialBuilder = also { it.oredictAlt = oredict }

    //レア度を設定するメソッド
    fun setRarity(rarity: IRarity): MaterialBuilder = also { it.rarity = rarity }

    //融点を設定するメソッド
    fun setTempMelt(melt: Int?): MaterialBuilder = also { it.tempMelt = melt }

    //沸点を設定するメソッド
    fun setTempBoil(boil: Int?): MaterialBuilder = also { it.tempBoil = boil }

    //昇華点を設定するメソッド
    fun setTempSubl(subl: Int?): MaterialBuilder = also { it.tempSubl = subl }

    //素材を登録するメソッド
    fun register(): MaterialBuilder = also {
        /*if (it.index >= 0) {
            MaterialRegistry.list.add(it)
            MaterialRegistry.mapIndex[it.index] = it
            MaterialRegistry.mapName[it.name] = it
            //有効な部品の組み合わせを登録
            for (part in PartRegistry.list) {
                if(MaterialUtil.isValidPart(part, it)) MaterialRegistry.validPair.add(part to it)
            }
        }*/
    }
}