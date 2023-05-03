package ragi_materials.core.recipe

import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.registries.IForgeRegistryEntry

@Optional.Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
abstract class RecipeBase<T : IForgeRegistryEntry<T>> : IForgeRegistryEntry.Impl<T>(), IRecipeWrapper {

    abstract fun match(inventory: IItemHandler): Boolean

}