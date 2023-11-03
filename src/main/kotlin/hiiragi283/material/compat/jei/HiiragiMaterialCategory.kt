package hiiragi283.material.compat.jei

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.compat.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class HiiragiMaterialCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<HiiragiMaterialCategory.Wrapper>(HiiragiJEIPlugin.MATERIAL, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/material_info.png"), 0, 0, 170, 116)

    override fun setRecipe(iRecipeLayout: IRecipeLayout, wrapper: Wrapper, iIngredients: IIngredients) {
        //MaterialStack
        getMaterialStacks(iRecipeLayout).init(0, false, 4 + 1, 4 + 1)
        getMaterialStacks(iRecipeLayout)[0] = wrapper.material
        //FluidStack
        iRecipeLayout.fluidStacks.init(0, true, 18 * 8 + 5, 5)
        wrapper.fluidStack?.let { iRecipeLayout.fluidStacks[0] = it }
        //ItemStack
        for (i in wrapper.stacks.indices) {
            iRecipeLayout.itemStacks.init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4)
            iRecipeLayout.itemStacks[i] = wrapper.stacks[i]
        }
    }

    class Wrapper(
        val material: HiiragiMaterial,
        val stacks: List<ItemStack>,
        val fluidStack: FluidStack?
    ) : IRecipeWrapper {

        //    IRecipeWrapper    //
        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, stacks)
            fluidStack?.let { iIngredients.setInput(VanillaTypes.FLUID, it) }
            iIngredients.setOutput(HiiragiIngredientTypes.MATERIAL, material)
        }

        override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
            minecraft.fontRenderer.drawString(
                material.getTranslatedName(),
                24,
                10,
                HiiragiColor.WHITE.rgb
            )
        }

    }

}