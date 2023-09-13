package hiiragi283.material.api.recipe

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialStack
import hiiragi283.compat.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.util.HiiragiColor
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

class HiiragiMaterialRecipe(
    private val material: MaterialStack,
    private val stacks: List<ItemStack>
) : HiiragiRecipe<HiiragiMaterialRecipe>() {

    constructor(material: HiiragiMaterial) : this(material.toMaterialStack(), material.getAllItemStack())

    fun getMaterial(): MaterialStack = material.copy()

    fun getStacks(): List<ItemStack> = stacks.toList()

    //    IRecipeWrapper    //
    override fun getIngredients(iIngredients: IIngredients) {
        iIngredients.setInputs(VanillaTypes.ITEM, stacks)
        iIngredients.setOutput(HiiragiIngredientTypes.MATERIAL, material)
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
        minecraft.fontRenderer.drawString(material.material.getTranslatedName(), 24, 10, HiiragiColor.WHITE.rgb)
    }
}