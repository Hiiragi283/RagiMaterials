package ragi_materials.core.integration.jei

import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.resources.I18n
import ragi_materials.core.RagiMaterials

abstract class JEICategoryBase<T : IRecipeWrapper> : IRecipeCategory<T> {

    //JEiタブの名前を取得するメソッド
    override fun getTitle(): String = I18n.format("gui.$uid")

    //JEiタブに紐づいたmod名を取得するメソッド
    override fun getModName(): String = RagiMaterials.MOD_NAME

}