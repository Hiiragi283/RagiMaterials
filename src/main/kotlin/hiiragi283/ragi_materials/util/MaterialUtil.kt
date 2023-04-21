@file:JvmName("MaterialUtil")

package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.MaterialPart
import net.minecraft.item.ItemStack

//代入したindexと一致するMaterialBuilderを返すメソッド
fun getMaterialFromIndex(index: Int): RagiMaterial = MaterialRegistry.mapIndex[index] ?: RagiMaterial.EMPTY

//代入したnameと一致するmaterialを返すメソッド
fun getMaterialFromName(name: String): RagiMaterial = MaterialRegistry.mapName[name] ?: RagiMaterial.EMPTY

//代入したnameと一致するmaterialを返すメソッド (元素用)
fun getElement(name: String): RagiMaterial = MaterialRegistry.mapElement[name] ?: RagiMaterial.EMPTY

//部品を取得するメソッド
fun getPart(part: MaterialPart, material: RagiMaterial, amount: Int = 1): ItemStack = if (material.isValidPart(part)) ItemStack(RagiInit.mapMaterialParts[part]!!, amount, material.index) else ItemStack.EMPTY