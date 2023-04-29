@file:JvmName("MaterialUtil")

package ragi_materials.core.util

import net.minecraft.item.ItemStack
import ragi_materials.core.RagiRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.MaterialPart

//代入したindexと一致するMaterialBuilderを返すメソッド
fun getMaterialFromIndex(index: Int): RagiMaterial = MaterialRegistry.mapIndex[index] ?: RagiMaterial.EMPTY

//代入したnameと一致するmaterialを返すメソッド
fun getMaterialFromName(name: String): RagiMaterial = MaterialRegistry.mapName[name] ?: RagiMaterial.EMPTY

//代入したnameと一致するmaterialを返すメソッド (元素用)
fun getElement(name: String): RagiMaterial = MaterialRegistry.mapElement[name] ?: RagiMaterial.EMPTY

//部品を取得するメソッド
fun getPart(part: MaterialPart, material: RagiMaterial, amount: Int = 1): ItemStack {
    if (material.isValidPart(part)) {
        val item = RagiRegistry.mapMaterialParts[part]
        if (item !== null) return ItemStack(item, amount, material.index)
    }
    return ItemStack.EMPTY
}