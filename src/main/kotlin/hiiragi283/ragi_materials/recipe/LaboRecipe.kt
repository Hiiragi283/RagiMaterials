package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiFluidUtil
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.same
import hiiragi283.ragi_materials.util.sameExact
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Optional.Interface
import net.minecraftforge.items.IItemHandler

@Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
data class LaboRecipe private constructor(private val location: ResourceLocation, private var inputs: MutableList<ItemStack>, private var outputs: MutableList<ItemStack>, private var catalyst: ItemStack) : IRecipeWrapper {

    constructor(recipe: LaboRecipe) : this(recipe.getLocation(), recipe.getInputs(), recipe.getOutputs(), recipe.getCatalyst())

    fun getLocation() = location

    fun getInput(slot: Int): ItemStack = inputs[slot].copy()

    fun getInputs() = inputs.toMutableList()

    fun getOutput(slot: Int): ItemStack = outputs[slot].copy()

    fun getOutputs() = outputs.toMutableList()

    fun getCatalyst(): ItemStack = catalyst.copy()

    fun match(inventory: IItemHandler): Boolean {
        var result = true
        for (i in 0..4) {
            val input = this.inputs[i]
            val stackSlot = inventory.getStackInSlot(i)
            result = result && input.same(stackSlot) && stackSlot.count >= input.count
        }
        return result
    }

    //stackの個数まで一致するか判断するメソッド
    fun matchExact(inventory: IItemHandler): Boolean {
        var result = true
        for (i in 0..4) {
            result = result && this.inputs[i].sameExact(inventory.getStackInSlot(i))
        }
        return result
    }

    fun setInput(slot: Int, stack: ItemStack) = also { inputs[slot] = stack }

    fun setInputs(inputs: MutableList<ItemStack>) = also { this.inputs = inputs }

    fun setOutput(slot: Int, stack: ItemStack) = also { outputs[slot] = stack }

    fun setOutputs(outputs: MutableList<ItemStack>) = also { this.outputs = outputs }

    fun setCatalyst(stack: ItemStack) = also { catalyst = stack }

    //    IRecipeWrapper    //

    override fun getIngredients(ings: IIngredients) {
        val inputs = getInputs().toMutableList().also { it.add(getCatalyst()) }
        ings.setInputLists(VanillaTypes.ITEM, mutableListOf(inputs))
        ings.setOutputLists(VanillaTypes.ITEM, mutableListOf(getOutputs()))
    }

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(RagiMaterials.MOD_ID, path))

        var inputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        var outputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        var catalyst: ItemStack = ItemStack.EMPTY

        fun build() = LaboRecipe(location, inputs, outputs, catalyst).also {
            Registry.map[location.toString()] = it
        }
    }

    object Registry {

        val map: LinkedHashMap<String, LaboRecipe> = linkedMapOf()
        val list = map.values

        fun load() {

            //Hydrogen
            Builder(MaterialRegistry.WATER.name).apply {
                inputs[0] = RagiFluidUtil.getBottle(MaterialRegistry.HYDROGEN, count = 2)
                inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.OXYGEN)
                outputs[0] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 2)
            }.build()

            //Boron
            Builder(MaterialRegistry.BORON_OXIDE.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORAX)
                inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.SULFURIC_ACID)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORON_OXIDE, 2)
                outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_SULFATE)
                outputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 11)
            }.build()

            //Fluorine
            Builder(MaterialRegistry.HYDROGEN_FLUORIDE.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.FLUORITE)
                inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.SULFURIC_ACID)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM)
                outputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.HYDROGEN_FLUORIDE)
            }.build()

            //Magnesium
            Builder(MaterialRegistry.HYDROGEN_FLUORIDE.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESITE)
                inputs[1] = RagiFluidUtil.getBottle(MaterialRegistry.HYDROGEN_CHLORIDE, count = 2)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CHLORIDE)
                outputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.WATER)
                outputs[2] = RagiFluidUtil.getBottle(material = MaterialRegistry.CARBON_DIOXIDE)
            }.build()

            //Aluminium
            Builder(MaterialRegistry.ALUMINA_SOLUTION.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BAUXITE)
                inputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
                inputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 3)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.RUTILE, 3)
                outputs[1] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.GALLIUM, 1)
                outputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
            }.build()

            Builder(MaterialRegistry.ALUMINA.name).apply {
                inputs[0] = RagiFluidUtil.getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.ALUMINA)
                outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
                outputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 3)
                catalyst = ItemStack(RagiRegistry.ItemBlazingCube)
            }.build()
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("LaboRecipe: <${it.key}>") }
        }
    }
}