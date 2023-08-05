package hiiragi283.integration.jei

import hiiragi283.material.RMReference
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n

abstract class HiiragiRecipeCategory<T : IRecipeWrapper>(private val id: String, val guiHelper: IGuiHelper) : IRecipeCategory<T> {

    abstract val backGround: IDrawableStatic

    override fun getBackground() = backGround

    override fun getModName(): String = RMReference.MOD_NAME

    override fun getTitle(): String = I18n.format("gui.$id")

    override fun getUid(): String = id

}