package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class MillRecipe private constructor(location: ResourceLocation, val input: ItemStack, val output: ItemStack) {

    fun match(input: ItemStack, fuel: Int) = RagiUtil.isSameStack(this.input, input, false)

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(Reference.MOD_ID, path))

        var input: ItemStack = ItemStack.EMPTY
        var output: ItemStack = ItemStack.EMPTY

        fun build(): MillRecipe {
            return MillRecipe(location, input, output).also {
                Registry.list.add(it)
                Registry.map[location.toString()] = it
            }
        }
    }

    object Registry {

        val list: MutableList<MillRecipe> = mutableListOf()
        val map: LinkedHashMap<String, MillRecipe> = linkedMapOf()

        init {
            materialRecipe()
        }

        private fun materialRecipe() {
            for (pair in RagiMaterial.validPair) {
                val part = pair.first
                val material = pair.second
                if (part.scale >= 1.0f && part.type != EnumMaterialType.DUST) {
                    Builder("${part.name}_${material.name}").apply {
                        input = MaterialUtil.getPart(part, material)
                        output = MaterialUtil.getPart(PartRegistry.DUST, material, part.scale.toInt())
                    }.build()
                }
            }
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("MillRecipe: <${it.key}>") }
        }
    }
}