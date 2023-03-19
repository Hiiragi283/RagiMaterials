package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

object MaterialUtil {

    //部品と素材の組み合わせが有効か判定するメソッド
    fun isValidPart(part: MaterialPart, material: MaterialBuilder): Boolean = part.type in material.type.list

    fun isValidPart(part: MaterialPart, material: RagiMaterial): Boolean = part.type in material.type.list

    //部品を取得するメソッド
    fun getPart(part: MaterialPart, material: MaterialBuilder, amount: Int = 1): ItemStack {
        var result = ItemStack.EMPTY
        if (part.type == EnumMaterialType.DUMMY) {
            if (material.hasOre) result = RagiUtil.getStack("${Reference.MOD_ID}:${part.name}", amount, material.index)
        } else if (isValidPart(part, material)) {
            result = RagiUtil.getStack("${Reference.MOD_ID}:${part.name}", amount, material.index)
        }
        return result
    }

    fun getPartNew(part: MaterialPart, material: RagiMaterial, amount: Int = 1): ItemStack {
        return if (isValidPart(part, material)) RagiUtil.getStack("${Reference.MOD_ID}:${part.name}", amount, material.index)else ItemStack.EMPTY
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

    fun getFormula(components: List<Pair<RagiMaterial, Int>>): String {
        //変数の宣言・初期化
        var formula = ""
        var subscript: String
        var subscript1 = '\u2080'
        var subscript10 = '\u2080'
        components.forEach {
            val weight = it.second
            //化学式の下付き数字の桁数調整
            if (weight in 2..9) subscript1 = '\u2080' + weight
            else if (weight in 10..99) {
                subscript1 = '\u2080' + (weight % 10)
                subscript10 = '\u2080' + (weight / 10)
            }
            //2桁目が0でない場合，下付き数字を2桁にする
            subscript = if (subscript10 == '\u2080') subscript1.toString() else subscript10.toString() + subscript1
            if (weight > 1) formula += subscript
        }
        //化学式を返す
        return formula
    }

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): MaterialBuilder {
        return if (MaterialRegistry.mapIndex[index] !== null) MaterialRegistry.mapIndex[index]!! else MaterialBuilder.EMPTY
    }

    fun getMaterialNew(index: Int): RagiMaterial = RagiMaterial.mapIndex[index] ?: RagiMaterial.EMPTY

    //代入したnameと一致するmaterialを返すメソッド
    fun getMaterial(name: String): MaterialBuilder {
        return if (MaterialRegistry.mapName[name] !== null) MaterialRegistry.mapName[name]!! else MaterialBuilder.EMPTY
    }

    fun getMaterialNew(name: String): RagiMaterial = RagiMaterial.mapName[name] ?: RagiMaterial.EMPTY

    //materialのツールチップを生成するメソッド
    fun materialInfo(material: RagiMaterial, tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", I18n.format("material.${material.name}"))) //名称
        material.formula?.let { tooltip.add(I18n.format("tips.ragi_materials.property.formula", it)) } //化学式
        material.molar?.let { tooltip.add(I18n.format("tips.ragi_materials.property.mol", it)) } //モル質量
        if (material.tempMelt?.equals(material.tempBoil) == true) tooltip.add(I18n.format("tips.ragi_materials.property.subl", material.tempMelt!!)) //昇華点
        else {
            material.tempMelt?.let { tooltip.add(I18n.format("tips.ragi_materials.property.melt", it)) } //融点
            material.tempBoil?.let { tooltip.add(I18n.format("tips.ragi_materials.property.boil", it)) } //沸点
        }
    }

    fun printMap() {
        MaterialRegistry.list.forEach { RagiLogger.infoDebug("Index: ${it.index}, <material:${it.name}>") }
        RagiMaterial.list.forEach { RagiLogger.infoDebug("New Index: ${it.index}, <material:${it.name}>") }
    }
}