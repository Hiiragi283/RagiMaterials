package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModRegistry
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.EnumSubMaterial
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry

object BloomeryJEI {

    fun getListOre(): Set<RagiMaterial> {
        val set: MutableSet<RagiMaterial> = mutableSetOf()
        for (material in MaterialRegistry.getMaterials()) {
            material.mapSubMaterials[EnumSubMaterial.NATIVE]?.let { set.add(material) }
        }
        return set
    }

    class Wrapper(val material: RagiMaterial) : IRecipeWrapper {

        fun getInput() = material.getPart(PartRegistry.ORE)

        fun getOutput() = material.mapSubMaterials[EnumSubMaterial.NATIVE]!!.getPart(PartRegistry.INGOT)

        override fun getIngredients(ingredients: IIngredients) {
            ingredients.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
            ingredients.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutput()))
        }
    }

    class Category(guiHelper: IGuiHelper) : JEICategoryBase<Wrapper> {

        companion object {
            fun registerCategory(registry: IRecipeCategoryRegistration) {
                if (RagiConfig.module.enableMetallurgy) registry.addRecipeCategories(Category(registry.jeiHelpers.guiHelper))
            }

            fun register(registry: IModRegistry) {
                if (RagiConfig.module.enableMetallurgy) {
                    registry.handleRecipes(RagiMaterial::class.java, { Wrapper(it) }, JEICore.Bloomery)
                    registry.addRecipes(getListOre(), JEICore.Bloomery)
                    registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockBloomery), JEICore.Bloomery)
                }
            }
        }

        override val backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/forge_furnace.png"), 1, 1, 54, 18)

        override fun getUid() = JEICore.Bloomery

        override fun setRecipe(layout: IRecipeLayout, wrapper: Wrapper, ingredients: IIngredients) {
            //inputのスロットを登録
            layout.itemStacks.init(0, true, 0, 0)
            layout.itemStacks[0] = wrapper.getInput()
            //outputのスロットを登録
            layout.itemStacks.init(1, false, 36, 0)
            layout.itemStacks[1] = wrapper.getOutput()
        }
    }

}