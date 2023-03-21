package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import kotlin.math.pow

class FFRecipe private constructor(private val location: ResourceLocation, private val input: ItemStack, private val output: ItemStack, private val fuel: Int) {

    fun getLocation() = location

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun getFuel() = fuel

    fun match(input: ItemStack, fuel: Int) = RagiUtil.isSameStack(this.input, input, false) && fuel >= this.fuel

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(Reference.MOD_ID, path))

        private fun getFuelConsumption(stack: ItemStack): Int? {
            val item = stack.item
            return if (item is IMaterialItem && item is ItemMaterial) {
                val material = item.getMaterial(stack)
                val tempMelt = material.tempMelt
                val scale = item.part.scale
                return tempMelt?.run { (2.0.pow(this / 1000) * scale).toInt() * 200 }
            } else null
        }

        var input: ItemStack = ItemStack.EMPTY
        var output: ItemStack = ItemStack.EMPTY
        var fuel = 0

        fun build(): FFRecipe {
            //inputから燃料を算出できる場合，そちらが優先される
            getFuelConsumption(input)?.let { fuel = it }
            return FFRecipe(location, input, output, fuel).also {
                Registry.list.add(it)
                Registry.map[location.toString()] = it
            }
        }

    }

    object Registry {

        val list: LinkedHashSet<FFRecipe> = linkedSetOf()
        val map: LinkedHashMap<String, FFRecipe> = linkedMapOf()

        init {
            materialRecipe()
        }

        private fun materialRecipe() {
            for (pair in RagiMaterial.validPair) {
                val part = pair.first
                val material = pair.second
                val type = part.type
                val scale = part.scale
                if (material.type == TypeRegistry.METAL && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                    Builder("${part.name}_${material.name}").apply {
                        input = MaterialUtil.getPart(part, material)
                        output = MaterialUtil.getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
                    }.build()
                }
            }
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("FFRecipe: <${it.key}>") }
        }
    }
}