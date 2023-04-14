package hiiragi283.ragi_materials.api.recipe

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.same
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Optional.Interface

@Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
data class MillRecipe private constructor(private val location: ResourceLocation, private var input: ItemStack, private var output: ItemStack) : IRecipeWrapper {

    constructor(recipe: MillRecipe) : this(recipe.getLocation(), recipe.getInput(), recipe.getOutput())

    fun getLocation() = location

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun match(input: ItemStack) = this.input.same(input)

    fun setInput(stack: ItemStack) = also { this.input = stack }

    fun setOutput(stack: ItemStack) = also { this.output = stack }

    //    IRecipeWrapper    //

    override fun getIngredients(ings: IIngredients) {
        ings.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ings.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutput()))
    }

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(RagiMaterials.MOD_ID, path))

        var input: ItemStack = ItemStack.EMPTY
        var output: ItemStack = ItemStack.EMPTY

        fun build(): MillRecipe {
            return MillRecipe(location, input, output).also {
                Registry.map[location.toString()] = it
            }
        }
    }

    object Registry {

        val map: LinkedHashMap<String, MillRecipe> = linkedMapOf()
        val list = map.values

        fun load() {
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
            val blockOre = RagiRegistry.BlockOre1
            val itemCrushed = RagiRegistry.ItemOreCrushed
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
            val itemCrushed = RagiRegistry.ItemOreCrushedVanilla
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
                    //ラピスとレッドストーン用の処理
                    var amount = if (i == 3 || i == 5) 8 else 1
                    output = MaterialUtil.getPart(PartRegistry.DUST, OreProperty.listVanilla[i].second.first, amount)
                }.build()
            }
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("MillRecipe: <${it.key}>") }
        }
    }
}