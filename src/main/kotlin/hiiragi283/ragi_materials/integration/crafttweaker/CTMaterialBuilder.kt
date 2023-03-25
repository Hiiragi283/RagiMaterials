package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.annotations.ZenRegister
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenConstructor
import stanhebben.zenscript.annotations.ZenMethod
import java.awt.Color

@ZenClass("mods.ragi_materials.MaterialBuilder")
@ZenRegister
class CTMaterialBuilder @ZenConstructor constructor(index: Int, name: String, type: String) {

    var builder: RagiMaterial.Builder

    init {
        builder = RagiMaterial.Builder(index, name, TypeRegistry.getType(type))
    }

    //燃焼時間を設定するメソッド
    @ZenMethod
    fun setBurnTime(time: Int) = also { builder.burnTime = time }

    //色を設定するメソッド
    @ZenMethod
    fun setColor(color: Int) = also { builder.color = Color(color) }

    //結晶の構造を設定するメソッド
    @ZenMethod
    fun setCrystalType(type: String) = also { builder.crystalType = EnumCrystalType.getType(type) }

    //崩壊後の素材を設定するメソッド
    @ZenMethod
    fun setDecayed(material: RagiMaterial) = also { builder.decayed = material }

    //化学式を設定するメソッド
    @ZenMethod
    fun setFormula(formula: String?) = also { builder.formula = formula }

    //モル質量を設定するメソッド
    @ZenMethod
    fun setMolarMass(molar: Float?) = also { builder.molar = molar }

    //モル質量を設定するメソッド
    @ZenMethod
    fun setOreDictAlt(oredict: String?) = also { builder.oredictAlt = oredict }

    //レア度を設定するメソッド
    @ZenMethod
    fun setRarity(rarity: String) = also { builder.rarity = RagiUtil.getEnum(rarity) }

    //融点を設定するメソッド
    @ZenMethod
    fun setTempMelt(melt: Int?) = also { builder.tempMelt = melt }

    //沸点を設定するメソッド
    @ZenMethod
    fun setTempBoil(boil: Int?) = also { builder.tempBoil = boil }

    //素材の組成を設定し，そこから自動的に物性を生成するメソッド
    @ZenMethod
    fun setComponents(components: Map<RagiMaterial, Int>) = also {
        builder.setComponents(components.toList())
    }

    //素材を混合物に設定するメソッド
    @ZenMethod
    fun setMixture() = also { builder.setMixture() }

    //素材を単体に設定するメソッド
    @ZenMethod
    fun setSimple(material: RagiMaterial, weight: Int) = also {
        builder.setSimple(material to weight)
    }

    @ZenMethod
    fun build() = builder.build()

}