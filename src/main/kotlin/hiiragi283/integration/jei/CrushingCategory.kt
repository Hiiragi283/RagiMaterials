package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.core.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.item.ItemStack

class CrushingCategory(guiHelper: IGuiHelper) : HiiragiRecipeCategory<CrushingRecipe>(CRUSHING, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_7_jei.png"), 0, 0, 18 * 9, 18)

    override fun setRecipe(layout: IRecipeLayout, wrapper: CrushingRecipe, p2: IIngredients) {
        //inputのスロットを登録
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = wrapper.input.getAllItemStack()
        //outputのスロットを登録
        val outputs: List<ItemStack> = wrapper.getAllOutputs()

        fun setOutput(index: Int) {
            layout.itemStacks.init(index + 1, false, 18 * (index + 2), 0)
            layout.itemStacks[index + 1] = outputs.getOrElse(index) { ItemStack.EMPTY }
        }

        (0..6).forEach { setOutput(it) }
    }

}