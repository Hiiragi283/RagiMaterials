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

class HiiragiMaterialCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<HiiragiMaterialCategory.Wrapper>(MATERIAL, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/material_info.png"), 0, 0, 170, 116)

    override fun setRecipe(layout: IRecipeLayout, recipe: Wrapper, iIngredients: IIngredients) {
        //MaterialStack
        getMaterialStacks(layout).init(0, false, 4 + 1, 4 + 1)
        getMaterialStacks(layout)[0] = recipe.getMaterial()
        //ItemStack
        for (i in recipe.getStacks().indices) {
            layout.itemStacks.init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4)
            layout.itemStacks[i] = recipe.getStacks()[i]
        }
    }

    class Wrapper(private val material: MaterialStack, private val stacks: List<ItemStack>) : IRecipeWrapper {

        constructor(material: HiiragiMaterial) : this(material.toMaterialStack(), material.getAllItemStack())

        fun getMaterial(): MaterialStack = material.copy()

        fun getStacks(): List<ItemStack> = stacks.toList()

        //    IRecipeWrapper    //
        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, stacks)
            iIngredients.setOutput(HiiragiIngredientTypes.MATERIAL, material)
        }

        override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
            minecraft.fontRenderer.drawString(material.material.getTranslatedName(), 24, 10, HiiragiColor.WHITE.rgb)
        }

    }

}