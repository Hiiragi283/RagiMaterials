package hiiragi283.material.compat.jei

import hiiragi283.material.RMReference
import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.client.gui.GuiMachineWorkbench
import hiiragi283.material.client.gui.GuiModuleMachine
import hiiragi283.material.compat.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.compat.jei.ingredients.MaterialStackHelper
import hiiragi283.material.compat.jei.ingredients.MaterialStackRenderer
import hiiragi283.material.container.ContainerModuleMachine
import hiiragi283.material.init.HiiragiBlocks
import hiiragi283.material.util.HiiragiLogger
import hiiragi283.material.util.itemStack
import hiiragi283.material.util.itemStackWild
import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IIngredientBlacklist
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategory
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.init.Items
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack
import kotlin.math.min

@JEIPlugin
class HiiragiJEIPlugin : IModPlugin {

    init {
        HiiragiLogger.info("Enabled Integration: JEI / HEI")
    }

    companion object {

        const val MATERIAL = "${RMReference.MOD_ID}.material"
        const val MACHINE_WORKBENCH = "${RMReference.MOD_ID}.machine_workbench"

        fun getRecipeTypeID(type: MachineType): String = "${RMReference.MOD_ID}.${type.lowercase()}"

    }

    override fun registerIngredients(registry: IModIngredientRegistration) {
        registry.register(
            HiiragiIngredientTypes.MATERIAL,
            HiiragiMaterial.REGISTRY.getValues(),
            MaterialStackHelper,
            MaterialStackRenderer,
        )
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {

        val guiHelper: IGuiHelper = registry.jeiHelpers.guiHelper
        val list: MutableList<IRecipeCategory<*>> = mutableListOf()

        list.add(HiiragiMaterialCategory(guiHelper))
        list.add(MachineWorkbenchCategory(guiHelper))

        MachineType.values()
            .filter { it != MachineType.NONE }
            .map { MachineRecipeCategory(it, guiHelper) }
            .forEach(list::add)

        registry.addRecipeCategories(*list.toTypedArray())

    }

    override fun register(registry: IModRegistry) {
        //HiiragiMaterial
        registry.handleRecipes(HiiragiMaterialCategory.Wrapper::class.java, { it }, MATERIAL)
        registry.addRecipes(createMaterialWrapper(), MATERIAL)
        registry.addRecipeCatalyst(Items.IRON_INGOT.itemStack(), MATERIAL)
        //Machine Workbench
        registry.handleRecipes(MachineType::class.java, MachineWorkbenchCategory::Wrapper, MACHINE_WORKBENCH)
        registry.addRecipes(MachineType.values().toList(), MACHINE_WORKBENCH)
        registry.addRecipeCatalyst(HiiragiBlocks.MACHINE_WORKBENCH.itemStack(), MACHINE_WORKBENCH)
        registry.addRecipeClickArea(
            GuiMachineWorkbench::class.java as Class<out GuiContainer>,
            8 + 18 * 5,
            18 * 2,
            18,
            18,
            MACHINE_WORKBENCH
        )
        //Machine Recipe
        MachineType.values()
            .filter { it != MachineType.NONE }
            .forEach { type ->
            registry.handleRecipes(
                IMachineRecipe::class.java,
                MachineRecipeCategory::Wrapper,
                getRecipeTypeID(type)
            )
            registry.addRecipes(
                IMachineRecipe.REGISTRY[type]!!.getValues(),
                getRecipeTypeID(type)
            )
                RecipeModuleItem.REGISTRY[type]?.itemStack()?.let { stack: ItemStack ->
                registry.addRecipeCatalyst(stack, getRecipeTypeID(type))
            }
                ModuleMachineBlock.REGISTRY[type]?.itemStackWild()?.let { stack: ItemStack ->
                registry.addRecipeCatalyst(stack, getRecipeTypeID(type))
            }
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
            *MachineType.values().map(::getRecipeTypeID).toTypedArray()
        )
        //Blacklist
        val blacklist: IIngredientBlacklist = registry.jeiHelpers.ingredientBlacklist
        HiiragiMaterial.REGISTRY.getValues().forEach(blacklist::addIngredientToBlacklist)
    }

    private fun createMaterialWrapper(): Collection<HiiragiMaterialCategory.Wrapper> =
        HiiragiMaterial.REGISTRY.getValues().flatMap { material: HiiragiMaterial ->
            val stacks: List<ItemStack> = material.getItemStacks()
            (0..stacks.size / 45)
                .map { index: Int -> stacks.subList(0 + 45 * index, min(stacks.size, 45 + 45 * index)) }
                .map { stackList: List<ItemStack> -> HiiragiMaterialCategory.Wrapper(material, stackList) }
        }

}