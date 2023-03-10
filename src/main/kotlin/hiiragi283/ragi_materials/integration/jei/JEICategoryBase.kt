package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.Reference
import mezz.jei.api.IGuiHelper
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n

abstract class JEICategoryBase<T : IRecipeWrapper>(guiHelper: IGuiHelper) : IRecipeCategory<T> {

    //JEiタブの名前を取得するメソッド
    override fun getTitle(): String = I18n.format("gui.$uid")

    //JEiタブに紐づいたmod名を取得するメソッド
    override fun getModName(): String = Reference.MOD_NAME

}