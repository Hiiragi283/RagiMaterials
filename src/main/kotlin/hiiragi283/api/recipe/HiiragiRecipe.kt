package hiiragi283.api.recipe

import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.registries.IForgeRegistryEntry

@Optional.Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
abstract class HiiragiRecipe<T : IForgeRegistryEntry<T>?> : IForgeRegistryEntry.Impl<T>(), IRecipeWrapper 