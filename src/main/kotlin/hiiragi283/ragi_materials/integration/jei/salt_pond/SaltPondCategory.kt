package hiiragi283.ragi_materials.integration.jei.salt_pond

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.integration.jei.JEICategoryBase
import hiiragi283.ragi_materials.integration.jei.JEICore
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class SaltPondCategory(guiHelper: IGuiHelper) : JEICategoryBase<SaltPondWrapper>(guiHelper) {

    private val background: IDrawableStatic

    //JEIタブの背景を設定するメソッド
    init {
        val location = ResourceLocation(Reference.MOD_ID, "textures/gui/jei/salt_pond.png")
        background = guiHelper.createDrawable(location, 1, 1, 90, 18)
    }

    //JEiタブのIDを取得するメソッド
    override fun getUid(): String = JEICore.SaltPond


    //JEiタブの背景を取得するメソッド
    override fun getBackground(): IDrawable = background

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(layout: IRecipeLayout, wrapper: SaltPondWrapper, ingredients: IIngredients) {
        //変化前と変化後のアイテムをwrapperから取得
        val input = wrapper.input
        val output = wrapper.output
        //inputのスロットを登録
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = input
        //Salt Pondのスロットを登録
        layout.itemStacks.init(1, false, 36, 0)
        layout.itemStacks[1] = ItemStack(RagiBlock.BlockSaltPond)
        //outputのスロットを登録
        layout.itemStacks.init(2, false, 72, 0)
        layout.itemStacks[2] = output
    }
}