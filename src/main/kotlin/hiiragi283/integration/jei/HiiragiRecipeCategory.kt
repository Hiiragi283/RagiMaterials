package hiiragi283.integration.jei

import hiiragi283.material.RMReference
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n

interface HiiragiRecipeCategory<T : IRecipeWrapper> : IRecipeCategory<T> {

    val backGround: IDrawableStatic

    override fun getBackground() = backGround

    override fun getModName(): String = RMReference.MOD_NAME

    override fun getTitle(): String = I18n.format("gui.$uid")

}