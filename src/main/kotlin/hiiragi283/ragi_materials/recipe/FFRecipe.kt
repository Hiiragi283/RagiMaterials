package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.same
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Optional.Interface
import kotlin.math.pow

@Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
data class FFRecipe private constructor(private val location: ResourceLocation, private val input: ItemStack, private val output: ItemStack, private val fuel: Int) : IRecipeWrapper {

    constructor(recipe: FFRecipe) : this(recipe.getLocation(), recipe.getInput(), recipe.getOutput(), recipe.getFuel())

    fun getLocation() = location

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun getFuel() = fuel

    fun match(input: ItemStack, fuel: Int) = this.input.same(input) && fuel >= this.fuel

    //    IRecipeWrapper    //

    override fun getIngredients(ing: IIngredients) {
        //各listをIIngredientsに設定
        ing.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ing.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutput()))
    }

    override fun drawInfo(mc: Minecraft, wid: Int, hei: Int, mouseX: Int, mouseY: Int) {
        //テクスチャをGUI上に乗せる
        //ResourceLocation res = new ResourceLocation(domain, path);
        //mc.getTextureManager().bindTexture(res);
        //文字列をGUI上に描画する
        mc.fontRenderer.drawString("§7Fuel: ${getFuel()}", 60.0f, 4.5f, 0x000000, false)
    }

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(RagiMaterials.MOD_ID, path))

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
                Registry.map[location.toString()] = it
            }
        }

    }

    object Registry {

        val map: LinkedHashMap<String, FFRecipe> = linkedMapOf()
        val list = map.values

        fun load() {
            //materialRecipe()
        }

        private fun materialRecipe() {
            for (pair in RagiMaterial.validPair) {
                val part = pair.first
                val material = pair.second
                val type = part.type
                val scale = part.scale
                if (material.type.match(TypeRegistry.METAL) && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
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