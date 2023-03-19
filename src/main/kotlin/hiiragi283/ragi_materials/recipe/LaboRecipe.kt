package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.MaterialRegistryNew
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiFluidUtil
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.items.IItemHandler

class LaboRecipe private constructor(location: ResourceLocation, val inputs: MutableList<ItemStack>, val outputs: MutableList<ItemStack>) {

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
            Registry.list.add(it)
            Registry.map[location.toString()] = it
        }
    }

    object Registry {

        val list: MutableList<LaboRecipe> = mutableListOf()
        val map: LinkedHashMap<String, LaboRecipe> = linkedMapOf()

        init {

            //Hydrogen
            Builder(MaterialRegistryNew.WATER.name).apply {
                inputs[0] = RagiFluidUtil.getBottle(MaterialRegistryNew.HYDROGEN, count = 2)
                inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.OXYGEN)
                outputs[0] = RagiFluidUtil.getBottle(MaterialRegistryNew.WATER, count = 2)
            }.build()

            //Boron
            Builder(MaterialRegistryNew.BORON_OXIDE.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.BORAX)
                inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.SULFURIC_ACID)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.BORON_OXIDE, 2)
                outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.SODIUM_SULFATE)
                outputs[2] = RagiFluidUtil.getBottle(MaterialRegistryNew.WATER, count = 11)
            }.build()

            //Fluorine
            Builder(MaterialRegistryNew.HYDROGEN_FLUORIDE.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.FLUORITE)
                inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.SULFURIC_ACID)
                //outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.GYPSUM)
                outputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.HYDROGEN_FLUORIDE)
            }.build()

            //Magnesium
            Builder(MaterialRegistryNew.HYDROGEN_FLUORIDE.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.MAGNESITE)
                inputs[1] = RagiFluidUtil.getBottle(MaterialRegistryNew.HYDROGEN_CHLORIDE, count = 2)
                outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.MAGNESIUM_CHLORIDE)
                outputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.WATER)
                outputs[2] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.CARBON_DIOXIDE)
            }.build()

            //Aluminium
            Builder(MaterialRegistryNew.ALUMINA_SOLUTION.name).apply {
                inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.BAUXITE)
                inputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.SODIUM_HYDROXIDE, 2)
                inputs[2] = RagiFluidUtil.getBottle(MaterialRegistryNew.WATER, count = 3)
                //outputs[0] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistryNew.RUTILE, 3)
                //outputs[1] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistryNew.GALLIUM, 1)
                outputs[2] = RagiFluidUtil.getBottle(MaterialRegistryNew.ALUMINA_SOLUTION, count = 2)
            }.build()

            Builder(MaterialRegistryNew.ALUMINA.name).apply {
                inputs[1] = RagiFluidUtil.getBottle(MaterialRegistryNew.ALUMINA_SOLUTION, count = 2)
                outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.ALUMINA)
                outputs[2] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.SODIUM_HYDROXIDE, 2)
                outputs[3] = RagiFluidUtil.getBottle(MaterialRegistryNew.WATER, count = 3)
            }.setCatalyst(0, ItemStack(RagiItem.ItemBlazingCube)).build()
        }

        fun printMap() {
            map.forEach { RagiLogger.infoDebug("LaboRecipe: <${it.key}>") }
        }
    }
}