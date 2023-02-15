package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.material.CompoundBuilder
import hiiragi283.ragi_materials.material.MaterialBuilder
import net.minecraft.client.resources.I18n

object MaterialUtils {

    //materialのツールチップを生成するメソッド
    fun materialInfo(material: MaterialBuilder, tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        //化学式
        if (material.formula !== null) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.formula", material.formula
            )
        )
        //モル質量
        if (material.molar != 0.0f) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.mol", material.molar
            )
        )
        //融点
        if (material.melt != 0) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.melt", material.melt
            )
        )
        //沸点
        if (material.boil != 0) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.boil", material.boil
            )
        )
        //昇華点
        if (material.subl != 0) tooltip.add(
            I18n.format(
                "text.ragi_materials.property.subl", material.subl
            )
        )
    }

    //代入されたMapから化学式を生成するメソッド
    fun calcFormula(mapComponents: Map<Any, Int>): String {
        //変数の宣言・初期化
        var formula = ""
        var subscript: String
        var subscript1 = '\u2080'
        var subscript10 = '\u2080'
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //化学式の下付き数字の桁数調整
            if (mapComponents.getValue(key) in 2..9) subscript1 = '\u2080' + mapComponents.getValue(key)
            else if (mapComponents.getValue(key) in 10 .. 99) {
                subscript1 = '\u2080' + (mapComponents.getValue(key) / 10)
                subscript10 = '\u2080' + (mapComponents.getValue(key) % 10)
            }
            //2桁目が0でない場合，下付き数字を2桁にする
            subscript = if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
            //keyがMaterialBuilder型の場合
            if (key is MaterialBuilder) {
                var formulaRaw = key.formula
                //keyがCompoundBuilder型の場合，化学式の前後を()で囲む
                if (key is CompoundBuilder) formulaRaw = "(${formulaRaw})"
                formula += formulaRaw
                if (mapComponents.getValue(key) > 1) formula += subscript
            }
            //keyがString型の場合
            else if (key is String) {
                formula += key
                if (mapComponents.getValue(key) > 1) formula += subscript
            }
        }
        //化学式を返す
        return formula
    }
}