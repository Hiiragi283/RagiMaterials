package hiiragi283.material.compat.jei

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack

class MachineWorkbenchCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<MachineWorkbenchCategory.Wrapper>(HiiragiJEIPlugin.MACHINE_WORKBENCH, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1.png"), 0, 0, 18 * 3, 18)

    override fun setRecipe(iRecipeLayout: IRecipeLayout, wrapper: Wrapper, iIngredients: IIngredients) {
        //input
        iRecipeLayout.itemStacks.init(0, true, 0, 0)
        iRecipeLayout.itemStacks[0] = wrapper.input
        //output
        iRecipeLayout.itemStacks.init(1, false, 18 * 2, 0)
        iRecipeLayout.itemStacks[1] = wrapper.output
    }

    class Wrapper(val recipeType: IMachineRecipe.Type) : IRecipeWrapper {

        val input: ItemStack = HiiragiRegistries.RECIPE_MODULE.getValue(recipeType)
            ?.getItemStack(HiiragiMaterial.HIIRAGI)
            ?: ItemStack.EMPTY

        val output: ItemStack = HiiragiRegistries.MODULE_MACHINE.getValue(recipeType)
            ?.getItemStack(HiiragiMaterial.HIIRAGI)
            ?: ItemStack.EMPTY

        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInput(VanillaTypes.ITEM, input)
            iIngredients.setOutput(VanillaTypes.ITEM, output)
        }

    }

}