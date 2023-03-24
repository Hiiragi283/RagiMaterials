package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiFluidUtil
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Optional.Interface
import net.minecraftforge.items.IItemHandler

data class LaboRecipe private constructor(private val location: ResourceLocation, private val inputs: MutableList<ItemStack>, private val outputs: MutableList<ItemStack>) {

    fun getLocation() = location

    fun getInput(slot: Int): ItemStack = inputs[slot].copy()

    fun getInputs() = inputs.toList()

    fun getOutput(slot: Int): ItemStack = outputs[slot].copy()

    fun getOutputs() = outputs.toList()

    fun match(inventory: IItemHandler, useCount: Boolean): Boolean {
        var result = false
        if (inventory.slots >= 5) {
            val matchSlot0 = RagiUtil.isSameStack(this.inputs[0], inventory.getStackInSlot(0), useCount)
            val matchSlot1 = RagiUtil.isSameStack(this.inputs[1], inventory.getStackInSlot(1), useCount)
            val matchSlot2 = RagiUtil.isSameStack(this.inputs[2], inventory.getStackInSlot(2), useCount)
            val matchSlot3 = RagiUtil.isSameStack(this.inputs[3], inventory.getStackInSlot(3), useCount)
            val matchSlot4 = RagiUtil.isSameStack(this.inputs[4], inventory.getStackInSlot(4), useCount)
            result = matchSlot0 && matchSlot1 && matchSlot2 && matchSlot3 && matchSlot4
            if (!useCount) {
                val amountSlot0 = matchSlot0 && (inventory.getStackInSlot(0).count >= this.inputs[0].count)
                val amountSlot1 = matchSlot1 && (inventory.getStackInSlot(1).count >= this.inputs[1].count)
                val amountSlot2 = matchSlot2 && (inventory.getStackInSlot(2).count >= this.inputs[2].count)
                val amountSlot3 = matchSlot3 && (inventory.getStackInSlot(3).count >= this.inputs[3].count)
                val amountSlot4 = matchSlot4 && (inventory.getStackInSlot(4).count >= this.inputs[4].count)
                result = amountSlot0 && amountSlot1 && amountSlot2 && amountSlot3 && amountSlot4
            }
        }
        return result
    }

    class Builder(private val location: ResourceLocation) {

        constructor(domain: String, path: String) : this(ResourceLocation(domain, path))

        constructor(path: String) : this(ResourceLocation(Reference.MOD_ID, path))

        var inputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)
        var outputs: MutableList<ItemStack> = mutableListOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)

        fun setCatalyst(slot: Int, stack: ItemStack): Builder = also {
            it.inputs[slot] = stack
            it.outputs[slot] = stack
        }

        fun build() = LaboRecipe(location, inputs, outputs).also {
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
                inputs[1] = RagiFluidUtil.getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
                outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.ALUMINA)
                outputs[2] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
                outputs[3] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 3)
            }.setCatalyst(0, ItemStack(RagiItem.ItemBlazingCube)).build()
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("LaboRecipe: <${it.key}>") }
        }
    }

    @Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
    class Wrapper(info: LaboRecipe) : IRecipeWrapper {

        val inputs = info.getInputs()
        val output = info.getOutputs()

        override fun getIngredients(ingredients: IIngredients) {
            ingredients.setInputLists(VanillaTypes.ITEM, mutableListOf(inputs))
            ingredients.setOutputLists(VanillaTypes.ITEM, mutableListOf(output))
        }
    }

}