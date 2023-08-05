package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.chemistry.RCBlocks
import hiiragi283.core.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModRegistry
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

class CrucibleMeltCategory(guiHelper: IGuiHelper) : HiiragiRecipeCategory<CrucibleRecipe> {

    companion object {
        fun registerCategory(registry: IRecipeCategoryRegistration) {
            registry.addRecipeCategories(CrucibleMeltCategory(registry.jeiHelpers.guiHelper))
        }

        fun register(registry: IModRegistry) {
            registry.handleRecipes(CrucibleRecipe::class.java, { CrucibleRecipe(it) }, JeiIntegration.CRUCIBLE_MELT)
            registry.addRecipes(HiiragiRegistry.CRUCIBLE.valuesCollection, JeiIntegration.CRUCIBLE_MELT)
            registry.addRecipeCatalyst(ItemStack(RCBlocks.CRUCIBLE), JeiIntegration.CRUCIBLE_MELT)
        }
    }

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_jei.png"), 1, 1, 54, 18)

    override fun getUid(): String = JeiIntegration.CRUCIBLE_MELT

    override fun setRecipe(layout: IRecipeLayout, wrapper: CrucibleRecipe, p2: IIngredients) {
        //inputのスロットを登録
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = OreDictionary.getOres(wrapper.input.getOreDict())
        //outputのスロットを登録
        layout.fluidStacks.init(0, true, 36 + 1, 1, 16, 16, 144 * 9, true, null)
        layout.fluidStacks[0] = wrapper.output
    }

}