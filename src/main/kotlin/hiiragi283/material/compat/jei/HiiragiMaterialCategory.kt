package hiiragi283.material.compat.jei

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
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
        getMaterialStacks(iRecipeLayout)[0] = wrapper.getMaterialStack()
        //FluidStack
        iRecipeLayout.fluidStacks.init(0, true, 18 * 8 + 5, 5)
        iRecipeLayout.fluidStacks[0] = wrapper.getFluids()
        //ItemStack
        for (i in wrapper.getStacks().indices) {
            iRecipeLayout.itemStacks.init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4)
            iRecipeLayout.itemStacks[i] = wrapper.getStacks()[i]
        }
    }

    class Wrapper(
        material: HiiragiMaterial,
        private val stacks: Collection<ItemStack>
    ) : IRecipeWrapper {

        private val materialStack: MaterialStack = material.toMaterialStack()

        private val fluids: Collection<FluidStack> = material.getFluidStacks()

        fun getMaterialStack(): MaterialStack = materialStack.copy()

        fun getFluids(): List<FluidStack> = fluids.toList()

        fun getStacks(): List<ItemStack> = stacks.toList()

        //    IRecipeWrapper    //
        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, getStacks())
            iIngredients.setInputs(VanillaTypes.FLUID, getFluids())
            iIngredients.setOutput(HiiragiIngredientTypes.MATERIAL, getMaterialStack())
        }

        override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
            minecraft.fontRenderer.drawString(
                getMaterialStack().material.getTranslatedName(),
                24,
                10,
                HiiragiColor.WHITE.rgb
            )
        }

    }

}