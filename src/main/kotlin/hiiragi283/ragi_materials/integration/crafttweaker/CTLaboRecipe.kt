package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.IAction
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import hiiragi283.ragi_materials.recipe.LaboRecipe
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

@ZenClass("mods.ragi_materials.LaboTable")
@ZenRegister
class CTLaboRecipe {

    companion object {
        @ZenMethod
        @JvmStatic
        fun addRecipe(name: String, outputs: Array<IItemStack>, inputs: Array<IItemStack>) {
            val location = ResourceLocation("crafttweaker", name)
            CraftTweakerCore.listAdd.add(AddLabo(location, HiiragiUtil.getStacks(outputs), HiiragiUtil.getStacks(inputs)))
        }
    }

    class AddLabo(val location: ResourceLocation, private val outputs: List<ItemStack>, private val inputs: List<ItemStack>) : IAction {

        //    IAction    //

        override fun apply() {
            LaboRecipe.Builder(location).also {
                for (i in inputs.indices) {
                    it.inputs[i] = inputs[i]
                }
                for (i in outputs.indices) {
                    it.outputs[i] = outputs[i]
                }
            }.build()
        }

        override fun describe() = "LaboRecipe: <${location}> was registered!"

    }

}