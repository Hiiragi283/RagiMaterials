package ragi_materials.core.integration.jei

import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n
import ragi_materials.core.RagiMaterials

interface JEICategoryBase<T : IRecipeWrapper> : IRecipeCategory<T> {

    //    IRecipeCategory    //

    val backGround: IDrawableStatic

    override fun getBackground() = backGround

    override fun getModName(): String = RagiMaterials.MOD_NAME

    override fun getTitle(): String = I18n.format("gui.$uid")

}