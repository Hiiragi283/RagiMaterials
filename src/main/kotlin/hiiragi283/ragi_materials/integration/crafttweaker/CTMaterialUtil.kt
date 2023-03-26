package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.annotations.ZenRegister
import hiiragi283.ragi_materials.material.RagiMaterial
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

@ZenClass("mods.ragi_materials.MaterialUtil")
@ZenRegister
class CTMaterialUtil {

    companion object {

        @ZenMethod
        @JvmStatic
        fun getIndex(material: RagiMaterial) = material.index

        @ZenMethod
        @JvmStatic
        fun getName(material: RagiMaterial) = material.name

        @ZenMethod
        @JvmStatic
        fun getType(material: RagiMaterial) = material.type.name

        @ZenMethod
        @JvmStatic
        fun getBurnTime(material: RagiMaterial) = material.burnTime

        @ZenMethod
        @JvmStatic
        fun getColor(material: RagiMaterial) = material.color.rgb

        @ZenMethod
        @JvmStatic
        fun getCrystalType(material: RagiMaterial) = material.crystalType.name

        @ZenMethod
        @JvmStatic
        fun getDecayed(material: RagiMaterial) = material.decayed

        @ZenMethod
        @JvmStatic
        fun getFormula(material: RagiMaterial) = material.formula

        @ZenMethod
        @JvmStatic
        fun getMolar(material: RagiMaterial) = material.molar

        @ZenMethod
        @JvmStatic
        fun getRarity(material: RagiMaterial): String = material.rarity.name

        @ZenMethod
        @JvmStatic
        fun getTempMelt(material: RagiMaterial) = material.tempMelt

        @ZenMethod
        @JvmStatic
        fun getTempBoil(material: RagiMaterial) = material.tempBoil

        @ZenMethod
        @JvmStatic
        fun stringFormula(name: String) = RagiMaterial.Formula(name).build()

        @ZenMethod
        @JvmStatic
        fun setBracket(material: RagiMaterial) = stringFormula("${material.formula}")

    }

}