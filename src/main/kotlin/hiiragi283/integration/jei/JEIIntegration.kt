package hiiragi283.integration.jei

import hiiragi283.api.item.ICastItem
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.integration.jei.ingredients.HiiragiMaterialHelperJEI
import hiiragi283.integration.jei.ingredients.HiiragiMaterialRendererJEI
import hiiragi283.material.RMBlocks
import hiiragi283.material.RMItems
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.registry.ForgeRegistries

const val CRUCIBLE_CAST = "${RMReference.MOD_ID}.crucible_cast"
const val CRUCIBLE_HEAT = "${RMReference.MOD_ID}.crucible_heat"
const val CRUCIBLE_MELT = "${RMReference.MOD_ID}.crucible_melt"
const val CRUSHING = "${RMReference.MOD_ID}.crushing"

@JEIPlugin
class JEIIntegration : IModPlugin {

    init {
        RagiMaterials.LOGGER.info("Enabled Integration: JEI / HEI")
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(
            CrucibleCastCategory(guiHelper),
            CrucibleMeltCategory(guiHelper),
            CrushingCategory(guiHelper),
            HeatSourceCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {
        //Crucible - Cast
        registry.handleRecipes(CrucibleCastRecipe::class.java, { it }, CRUCIBLE_CAST)
        registry.addRecipes(getCrucibleCastRecipes(), CRUCIBLE_CAST)
        registry.addRecipeCatalyst(ItemStack(RMBlocks.CRUCIBLE), CRUCIBLE_CAST, CRUCIBLE_MELT)
        //Crucible - Heat Source
        registry.handleRecipes(HeatSourceRecipe::class.java, { it }, CRUCIBLE_HEAT)
        registry.addRecipes(HiiragiRegistry.HEAT_SOURCE.map { HeatSourceRecipe(it.key, it.value) }, CRUCIBLE_HEAT)
        registry.addRecipeCatalyst(ItemStack(Items.LAVA_BUCKET), CRUCIBLE_HEAT)
        //Crucible - Melt
        registry.handleRecipes(CrucibleRecipe::class.java, { it }, CRUCIBLE_MELT)
        registry.addRecipes(HiiragiRegistry.CRUCIBLE.valuesCollection, CRUCIBLE_MELT)
        //Crushing
        registry.handleRecipes(CrushingRecipe::class.java, { it }, CRUSHING)
        registry.addRecipes(HiiragiRegistry.CRUSHING.valuesCollection, CRUSHING)
        registry.addRecipeCatalyst(ItemStack(RMItems.CRUSHING_HAMMER), CRUSHING)
    }

    override fun registerIngredients(registry: IModIngredientRegistration) {
        registry.register(
            HiiragiIngredientTypes.MATERIAL,
            MaterialRegistry.getMaterials(),
            HiiragiMaterialHelperJEI,
            HiiragiMaterialRendererJEI,
        )

    }

    private fun getCrucibleCastRecipes(): Collection<CrucibleCastRecipe> {
        return ForgeRegistries.ITEMS.valuesCollection
            .filterIsInstance<ICastItem>() //ICastItemを継承しているItemのみ選択
            .map { ItemStack(it.getCastItem()) }
            .flatMap { stack ->
                FluidRegistry.getRegisteredFluids().values //Fluidの一覧を取得
                    .filter { (stack.item as ICastItem).getFluidAmount(stack) > 0 } //要求する液体量が0より多いもののみ選択
                    .map { FluidStack(it, (stack.item as ICastItem).getFluidAmount(stack)) } //FluidStackに変換
                    .map { CrucibleCastRecipe.of(stack, it) } //レシピに変換
                    .filterNot { it.getOutput().isEmpty } //OutputがItemStack.EMPTYでないもののみ選択
            }
    }

}