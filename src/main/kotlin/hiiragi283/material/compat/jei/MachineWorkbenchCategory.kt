package hiiragi283.material.compat.jei

import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.init.HiiragiBlocks
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.itemStackWild
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack

class MachineWorkbenchCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<MachineWorkbenchCategory.Wrapper>(HiiragiJEIPlugin.MACHINE_WORKBENCH, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_1.png"), 0, 0, 18 * 5, 18)

    override val iconDrawable: IDrawable? =
        guiHelper.createDrawableIngredient(HiiragiBlocks.MACHINE_WORKBENCH.itemStack())

    override fun setRecipe(iRecipeLayout: IRecipeLayout, wrapper: Wrapper, iIngredients: IIngredients) {
        //casing
        iRecipeLayout.itemStacks.init(0, true, 0, 0)
        iRecipeLayout.itemStacks[0] = wrapper.casing
        //recipe module
        iRecipeLayout.itemStacks.init(1, true, 18 * 2, 0)
        iRecipeLayout.itemStacks[1] = wrapper.recipeModule
        //machine
        iRecipeLayout.itemStacks.init(2, false, 18 * 4, 0)
        iRecipeLayout.itemStacks[2] = wrapper.machine
    }

    class Wrapper(recipeType: MachineType) : IRecipeWrapper {

        val casing: ItemStack = HiiragiShapes.CASING.getItem()
            ?.item()?.itemStackWild()
            ?: ItemStack.EMPTY

        val recipeModule: ItemStack = HiiragiRegistries.RECIPE_MODULE.getValue(recipeType)
            ?.itemStack()
            ?: ItemStack.EMPTY

        val machine: ItemStack = HiiragiRegistries.BLOCK_MACHINE.getValue(recipeType)
            ?.itemStackWild()
            ?: ItemStack.EMPTY

        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, listOf(casing, recipeModule))
            iIngredients.setOutput(VanillaTypes.ITEM, machine)
        }

    }

}