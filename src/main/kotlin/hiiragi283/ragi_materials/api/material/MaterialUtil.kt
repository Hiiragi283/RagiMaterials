package hiiragi283.ragi_materials.api.material

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.material.part.MaterialPart
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

object MaterialUtil {

    //部品と素材の組み合わせが有効か判定するメソッド
    fun isValidPart(part: MaterialPart, material: RagiMaterial): Boolean = part.type in material.type.list

    //部品を取得するメソッド
    fun getPart(part: MaterialPart, material: RagiMaterial, amount: Int = 1): ItemStack {
        return if (isValidPart(part, material)) ItemStack(RagiInit.mapMaterialParts[part]!!, amount, material.index) else ItemStack.EMPTY
    }

    //代入されたMapから化学式を生成するメソッド
    fun getFormula(components: List<Pair<RagiMaterial, Int>>): String {
        //変数の宣言・初期化
        var formula = ""
        var subscript: String
        var subscript1 = '\u2080'
        var subscript10 = '\u2080'
        components.forEach {
            //文字を代入する
            formula += it.first.formula
            val weight = it.second
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

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): RagiMaterial = RagiMaterial.mapIndex[index] ?: RagiMaterial.EMPTY

    //代入したnameと一致するmaterialを返すメソッド
    fun getMaterial(name: String): RagiMaterial = RagiMaterial.mapName[name] ?: RagiMaterial.EMPTY

    //代入したnameと一致するmaterialを返すメソッド (元素用)
    fun getElement(name: String): RagiMaterial = RagiMaterial.mapElement[name] ?: RagiMaterial.EMPTY

    //materialのツールチップを生成するメソッド
    fun materialInfo(material: RagiMaterial, tooltip: MutableList<String>) {
        tooltip.add("§e=== Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.property.name", I18n.format("material.${material.name}"))) //名称
        material.formula?.let { tooltip.add(I18n.format("tips.ragi_materials.property.formula", it)) } //化学式
        material.molar?.let { tooltip.add(I18n.format("tips.ragi_materials.property.mol", it)) } //モル質量
        if (material.tempMelt !== null && material.tempBoil !== null) {
            if (material.tempMelt == material.tempBoil) tooltip.add(I18n.format("tips.ragi_materials.property.subl", material.tempMelt!!)) //昇華点
        } else {
            material.tempMelt?.let { tooltip.add(I18n.format("tips.ragi_materials.property.melt", it)) } //融点
            material.tempBoil?.let { tooltip.add(I18n.format("tips.ragi_materials.property.boil", it)) } //沸点
        }
    }

    fun printMap() {
        RagiMaterial.mapElement.values.forEach { RagiMaterials.LOGGER.debug("<element:${it.name}>") }
        RagiMaterial.list.forEach { RagiMaterials.LOGGER.debug("Index: ${it.index}, <material:${it.name}>") }
    }
}