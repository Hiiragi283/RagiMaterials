package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class MillRecipe private constructor(private val location: ResourceLocation, private val input: ItemStack, private val output: ItemStack) {

    fun getLocation() = location

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun match(input: ItemStack) = RagiUtil.isSameStack(this.input, input, false)

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
            recipeMaterial()
            recipeOre()
            recipeOreVanilla()
        }

        private fun recipeMaterial() {
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

        private fun recipeOre() {
            val blockOre = RagiBlock.BlockOre1
            val itemCrushed = RagiItem.ItemOreCrushed
            for (i in OreProperty.listOre1.indices) {
                //Ore -> Crushed Ore
                Builder("${blockOre.registryName}_$i").apply {
                    input = ItemStack(blockOre, 1, i)
                    output = ItemStack(itemCrushed, 2, i)
                }.build()
                //Crushed Ore -> Dust
                Builder("${itemCrushed.registryName}_$i").apply {
                    input = ItemStack(itemCrushed, 1, i)
                    output = MaterialUtil.getPart(PartRegistry.DUST, OreProperty.listOre1[i].second.first)
                }.build()
            }
        }

        private fun recipeOreVanilla() {
            val itemCrushed = RagiItem.ItemOreCrushedVanilla
            val list = listOf(
                    Blocks.GOLD_ORE,
                    Blocks.IRON_ORE,
                    Blocks.COAL_ORE,
                    Blocks.LAPIS_ORE,
                    Blocks.DIAMOND_ORE,
                    Blocks.REDSTONE_ORE,
                    Blocks.EMERALD_ORE,
                    Blocks.QUARTZ_ORE
            )
            for (i in list.indices) {
                Builder(list[i].registryName!!).apply {
                    input = ItemStack(list[i])
                    output = ItemStack(itemCrushed, 2, i)
                }.build()
                Builder("${itemCrushed.registryName}_$i").apply {
                    input = ItemStack(itemCrushed, 1, i)
                    output = MaterialUtil.getPart(PartRegistry.DUST, OreProperty.listVanilla[i].second.first)
                }.build()
            }
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("MillRecipe: <${it.key}>") }
        }
    }
}