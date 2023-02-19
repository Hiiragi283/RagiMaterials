package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRecipe
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategory
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class FFCategory(guiHelper: IGuiHelper) : IRecipeCategory<FFWrapper> {

    private val background: IDrawableStatic

    //JEIタブの背景を設定するメソッド
    init {
        val location = ResourceLocation(Reference.MOD_ID, "textures/gui/jei/forge_furnace.png")
        background = guiHelper.createDrawable(location, 1, 1, 90, 18)
    }

    //JEiタブのIDを取得するメソッド
    override fun getUid(): String {
        return JEICore.ForgeFurnace
    }

    //JEiタブの名前を取得するメソッド
    override fun getTitle(): String {
        return I18n.format("gui.$uid")
    }

    //JEiタブの背景を取得するメソッド
    override fun getBackground(): IDrawable {
        return background
    }

    //なんかエクストラするメソッド
    override fun drawExtras(mc: Minecraft) {}

    //JEiタブのアイコンを取得するメソッド?
    override fun getIcon(): IDrawable? {
        return null
    }

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: FFWrapper, ingredients: IIngredients) {
        //変化前と変化後のアイテムをwrapperから取得
        val input: ItemStack = recipeWrapper.input
        val output: ItemStack = recipeWrapper.output
        //inputのスロットを登録
        recipeLayout.itemStacks.init(0, true, 0, 0)
        recipeLayout.itemStacks[0] = input
        //Forge Furnaceのスロットを登録
        recipeLayout.itemStacks.init(1, false, 36, 0)
        when (recipeWrapper.type) {
            FFRecipe.EnumFire.BURNING -> recipeLayout.itemStacks[1] = ItemStack(RagiInit.BlockForgeFurnace)
            FFRecipe.EnumFire.BOOSTED -> recipeLayout.itemStacks[1] =
                listOf(ItemStack(RagiInit.BlockForgeFurnace), ItemStack(RagiInit.ItemBlockBlazeHeater, 1, 0))

            FFRecipe.EnumFire.HELLRISE -> recipeLayout.itemStacks[1] = ItemStack(RagiInit.ItemBlockBlazeHeater, 1, 1)
        }
        //outputのスロットを登録
        recipeLayout.itemStacks.init(2, false, 72, 0)
        recipeLayout.itemStacks[2] = output
    }

    //JEiタブに紐づいたmod名を取得するメソッド
    override fun getModName(): String {
        return Reference.MOD_NAME
    }
}