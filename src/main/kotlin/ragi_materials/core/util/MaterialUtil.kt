@file:JvmName("MaterialUtil")

package ragi_materials.core.util

import net.minecraft.item.ItemStack
import ragi_materials.core.RagiRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.MaterialPart

//部品を取得するメソッド
fun getPart(part: MaterialPart, material: RagiMaterial, amount: Int = 1): ItemStack {
    if (material.isValidPart(part)) {
        val item = RagiRegistry.mapMaterialParts[part]
        if (item !== null) return ItemStack(item, amount, material.index)
    }
    return ItemStack.EMPTY
}