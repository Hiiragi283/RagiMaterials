package hiiragi283.material.compat.jei

import hiiragi283.material.HiiragiBlocks
import hiiragi283.material.HiiragiItems
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.compat.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.compat.jei.ingredients.MaterialStackHelper
import hiiragi283.material.compat.jei.ingredients.MaterialStackRenderer
import hiiragi283.material.container.ContainerModuleMachine
import hiiragi283.material.gui.GuiModuleMachine
import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack

@JEIPlugin
class HiiragiJEIPlugin : IModPlugin {

    init {
        RagiMaterials.LOGGER.info("Enabled Integration: JEI / HEI")
    }

    companion object {

        const val MATERIAL = "${RMReference.MOD_ID}.material"
        const val MACHINE_WORKBENCH = "${RMReference.MOD_ID}.machine_workbench"

        fun getRecipeTypeID(type: IMachineRecipe.Type): String = "${RMReference.MOD_ID}.${type.lowercase()}"

    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {

        val guiHelper: IGuiHelper = registry.jeiHelpers.guiHelper
        val list: MutableList<IRecipeCategory<*>> = mutableListOf()

        list.add(HiiragiMaterialCategory(guiHelper))
        list.add(MachineWorkbenchCategory(guiHelper))

        IMachineRecipe.Type.values()
            .map { MachineRecipeCategory(it, guiHelper) }
            .forEach(list::add)

        registry.addRecipeCategories(*list.toTypedArray())

    }

    override fun register(registry: IModRegistry) {
        //HiiragiMaterial
        registry.handleRecipes(HiiragiMaterial::class.java, HiiragiMaterialCategory::Wrapper, MATERIAL)
        registry.addRecipes(HiiragiRegistries.MATERIAL.getValues(), MATERIAL)
        registry.addRecipeCatalyst(ItemStack(HiiragiItems.MATERIAL_BOTTLE, 1, 32767), MATERIAL)
        //Machine Workbench
        registry.handleRecipes(IMachineRecipe.Type::class.java, MachineWorkbenchCategory::Wrapper, MACHINE_WORKBENCH)
        registry.addRecipes(IMachineRecipe.Type.values().toList(), MACHINE_WORKBENCH)
        registry.addRecipeCatalyst(ItemStack(HiiragiBlocks.MACHINE_WORKBENCH), MACHINE_WORKBENCH)
        //Machine Recipe
        IMachineRecipe.Type.values().forEach { type ->
            registry.handleRecipes(
                IMachineRecipe::class.java,
                MachineRecipeCategory::Wrapper,
                getRecipeTypeID(type)
            )
            registry.addRecipes(
                HiiragiRegistries.MACHINE_RECIPE.getValue(type)!!.getValues(),
                getRecipeTypeID(type)
            )
            registry.addRecipeCatalyst(
                HiiragiRegistries.MODULE_MACHINE.getValue(type)
                    ?.getItemStackWild()
                    ?: HiiragiBlocks.MACHINE_TEST.getItemStack(),
                getRecipeTypeID(type)
            )
            HiiragiRegistries.RECIPE_MODULE.getValue(type)?.getItemStack()
                ?.let { stack: ItemStack -> registry.addRecipeCatalyst(stack, getRecipeTypeID(type)) }
            registry.recipeTransferRegistry.addRecipeTransferHandler(
                ContainerModuleMachine::class.java as Class<out Container>,
                getRecipeTypeID(type),
                0,
                6,
                12,
                36
            )
        }
        registry.addRecipeClickArea(
            GuiModuleMachine::class.java as Class<out GuiContainer>,
            8 + 18 * 4,
            18 * 2,
            18,
            18,
            *IMachineRecipe.Type.values().map(::getRecipeTypeID).toTypedArray()
        )
    }

    override fun registerIngredients(registry: IModIngredientRegistration) {
        registry.register(
            HiiragiIngredientTypes.MATERIAL,
            HiiragiRegistries.MATERIAL.getValues().map { it.toMaterialStack() },
            MaterialStackHelper,
            MaterialStackRenderer,
        )
    }

}