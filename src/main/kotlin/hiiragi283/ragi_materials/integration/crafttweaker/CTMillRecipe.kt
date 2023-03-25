package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.IAction
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import hiiragi283.ragi_materials.recipe.MillRecipe
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

@ZenClass("mods.ragi_materials.StoneMill")
@ZenRegister
class CTMillRecipe {

    companion object {
        @ZenMethod
        @JvmStatic
        fun addRecipe(name: String, output: IItemStack, input: IItemStack) {
            val location = ResourceLocation("crafttweaker", name)
            CraftTweakerCore.listAdd.add(AddMill(location, HiiragiUtil.getStack(output), HiiragiUtil.getStack(input)))
        }
    }

    class AddMill(val location: ResourceLocation, val output: ItemStack, val input: ItemStack) : IAction {

        //    IAction    //

        override fun apply() {
            if (!output.isEmpty && !input.isEmpty) {
                MillRecipe.Builder(location).also {
                    it.input = input
                    it.output = output
                }.build()
            }
        }

        override fun describe() = "MillRecipe: <${location}> was registered!"

    }

}