package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import java.awt.Color

object RagiConfigHelper {

    //String型のlist/arrayをmapに変換するメソッド
    fun convertListToMap(list: Array<String>, map: MutableMap<String, String>): MutableMap<String, String> {
        for (key in list) {
            map[key.split(";")[0]] = key.split(";")[1]
            RagiLogger.infoDebug("${key.split(";")[0]} to ${map[key.split(";")[0]]}")
        }
        return map
    }

    //configから素材を登録するメソッド
    fun registerMaterial(value: String) {
        //valueをばらしてプロパティを得る
        val listProperty = value.split(":")
        val index = listProperty[0].toInt()
        val name = listProperty[1]
        var type = MaterialBuilder.MaterialType.WILDCARD
        val color = Color(listProperty[3].toIntOrNull(16)!!)
        val formula = listProperty[4]
        val molar = listProperty[5].toFloat()
        val melt = listProperty[6].toInt()
        val boil = listProperty[7].toInt()
        //MaterialTypeの確認
        for (i in MaterialBuilder.MaterialType.values()) {
            if (i.name == listProperty[2]) {
                type = i
                break
            }
        }
        //indexが1025以上2048以下，かつtypeがWILDCARDでない場合，素材を登録する
        if (index in 1025..Reference.numMaterial && type != MaterialBuilder.MaterialType.WILDCARD) {
            val material = MaterialBuilder(index, name, type)
            material.setColor(color)
            material.setFormula(formula)
            material.setMolarMass(molar)
            material.setTempMelt(melt)
            material.setTempBoil(boil)
            MaterialRegistry.list.add(material)
        }
    }

    //EnumTempを取得するメソッド
    enum class EnumTemp {
        CELSIUS, FAHRENHEIT, KELVIN;
    }

    fun getEnumTemp(name: String): EnumTemp {
        return when (name) {
            "CELSIUS" -> EnumTemp.CELSIUS
            "FAHRENHEIT" -> EnumTemp.FAHRENHEIT
            "KELVIN" -> EnumTemp.KELVIN
            else -> EnumTemp.CELSIUS
        }
    }
}