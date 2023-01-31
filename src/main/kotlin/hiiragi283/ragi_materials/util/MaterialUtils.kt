package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.materials.MaterialBuilder
import net.minecraft.client.resources.I18n

object MaterialUtils {

    //素材のツールチップを生成するメソッド
    fun materialInfo(material: MaterialBuilder, tooltip: MutableList<String>) {
        tooltip.add("§e===Property===")
        if (material.getFormula(true) !== null) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.formula", material.getFormula()
            )
        ) //化学式
        if (material.getMolarMass(true) !== null) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.mol", material.getMolarMass()
            )
        ) //モル質量
        if (material.getTempMelt(true) !== null) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.melt", material.getTempMelt() + 273
            )
        ) //融点
        if (material.getTempBoil(true) !== null) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.boil", material.getTempBoil() + 273
            )
        ) //沸点
        if (material.getTempSubl(true) !== null) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.subl", material.getTempSubl() + 273
            )
        ) //昇華点
    }

    //代入されたMapから化学式を生成するメソッド
    fun calcFormula(mapComponents: Map<Any, Int>): String {
        //変数の宣言・初期化
        var formula = ""
        var subscript: String
        var subscript1 = '\u2080'
        var subscript10 = '\u2080'
        //mapComponents内の各keyに対して実行
        for (i in mapComponents.keys) {
            //化学式の下付き数字の桁数調整
            if (mapComponents.getValue(i) in 2..9) subscript1 = '\u2080' + mapComponents.getValue(i)
            else if (mapComponents.getValue(i) in 10 .. 99) {
                subscript1 = '\u2080' + (mapComponents.getValue(i) / 10)
                subscript10 = '\u2080' + (mapComponents.getValue(i) % 10)
            }
            //2桁目が0でない場合，下付き数字を2桁にする
            subscript = if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
            //keyがMaterialBuilder型の場合
            if (i is MaterialBuilder) {
                formula += i.getFormula()
                if (mapComponents.getValue(i) > 1) formula += subscript
            }
            //keyがString型の場合
            else if (i is String) {
                formula += i
                if (mapComponents.getValue(i) > 1) formula += subscript
            }
        }
        //化学式を返す
        return formula
    }
}