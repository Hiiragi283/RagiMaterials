package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid

object MaterialUtil {

    fun getFluid(index: Int): Fluid? {
        return getMaterial(index).getFluid()
    }

    //代入されたMapから化学式を生成するメソッド
    fun getFormula(mapComponents: Map<MaterialBuilder, Int>): String {
        //変数の宣言・初期化
        var formula = ""
        var subscript: String
        var subscript1 = '\u2080'
        var subscript10 = '\u2080'
        //mapComponents内の各keyに対して実行
        for (key in mapComponents.keys) {
            //化学式の下付き数字の桁数調整
            if (mapComponents.getValue(key) in 2..9) subscript1 = '\u2080' + mapComponents.getValue(key)
            else if (mapComponents.getValue(key) in 10..99) {
                subscript1 = '\u2080' + (mapComponents.getValue(key) % 10)
                subscript10 = '\u2080' + (mapComponents.getValue(key) / 10)
            }
            //2桁目が0でない場合，下付き数字を2桁にする
            subscript = if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
            var formulaRaw = key.formula
            //hasBracketがtrueの場合，化学式の前後を()で囲む
            if (key.hasBracket) formulaRaw = "(${formulaRaw})"
            formula += formulaRaw
            if (mapComponents.getValue(key) > 1) formula += subscript
        }
        //化学式を返す
        return formula
    }

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): MaterialBuilder {
        return if (MaterialRegistry.mapIndex[index] !== null) MaterialRegistry.mapIndex[index]!! else MaterialBuilder.EMPTY
    }

    //代入したnameと一致するmaterialを返すメソッド
    fun getMaterial(name: String): MaterialBuilder {
        return if (MaterialRegistry.mapName[name] !== null) MaterialRegistry.mapName[name]!! else MaterialBuilder.EMPTY
    }

    fun getPart(part: MaterialPart, material: MaterialBuilder, amount: Int = 1): ItemStack {
        return if (part.type in material.type.list) {
            RagiUtil.getStack("${Reference.MOD_ID}:${part.name}", amount, material.index)
        } else ItemStack.EMPTY
    }

    //materialのツールチップを生成するメソッド
    fun materialInfo(material: MaterialBuilder, tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", I18n.format("material.${material.name}"))) //名称
        if (material.formula !== null) tooltip.add(I18n.format("tips.ragi_materials.property.formula", material.formula!!)) //化学式
        if (material.molar !== null) tooltip.add(I18n.format("tips.ragi_materials.property.mol", material.molar!!)) //モル質量
        if (material.tempMelt !== null) tooltip.add(I18n.format("tips.ragi_materials.property.melt", material.tempMelt!!)) //融点
        if (material.tempBoil !== null) tooltip.add(I18n.format("tips.ragi_materials.property.boil", material.tempBoil!!)) //沸点
        if (material.tempSubl !== null) tooltip.add(I18n.format("tips.ragi_materials.property.subl", material.tempSubl!!)) //昇華点
    }

    fun printMap() {
        for (material in MaterialRegistry.mapIndex.values) {
            RagiLogger.infoDebug("Index: ${material.index}, <material:${material.name}>")
        }
    }
}