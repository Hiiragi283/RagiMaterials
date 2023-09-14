package hiiragi283.material.compat.jei

import hiiragi283.material.RMReference
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.compat.jei.ingredients.HiiragiIngredientTypes
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IGuiIngredientGroup
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n

abstract class HiiragiRecipeCategory<T : IRecipeWrapper>(private val id: String, val guiHelper: IGuiHelper) : IRecipeCategory<T> {

    fun getMaterialStacks(layout: IRecipeLayout): IGuiIngredientGroup<MaterialStack> =
        layout.getIngredientsGroup(HiiragiIngredientTypes.MATERIAL)

    //    HiiragiRecipeCategory    //

    abstract val backGround: IDrawableStatic

    override fun getBackground() = backGround

    override fun getModName(): String = RMReference.MOD_NAME

    override fun getTitle(): String = I18n.format("gui.$id")

    override fun getUid(): String = id

}