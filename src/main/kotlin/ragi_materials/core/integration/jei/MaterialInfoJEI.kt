package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModRegistry
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType

object MaterialInfoJEI {

    class Wrapper(val material: RagiMaterial) : IRecipeWrapper {

        private val listParts: MutableSet<ItemStack> = mutableSetOf()

        init {
            initParts()
        }

        private fun initParts() {
            for (part in material.listValidParts) {
                getInputsList(listParts, part)
            }
        }

        fun getInputsList(set: MutableSet<ItemStack>, part: MaterialPart): List<ItemStack> {
            //部品を追加
            set.add(material.getPart(part))
            //鉱石辞書にも対応させる
            set.addAll(OreDictionary.getOres(part.prefixOre + material.getOreDict()))
            return set.toList()
        }

        override fun getIngredients(ingredients: IIngredients) {
            ingredients.setInputLists(VanillaTypes.ITEM, listOf(listParts.toList()))
            material.getFluid()?.let { ingredients.setInputs(VanillaTypes.FLUID, listOf(FluidStack(it, 1000))) }
        }

        override fun drawInfo(mc: Minecraft, wid: Int, hei: Int, mouseX: Int, mouseY: Int) {
            //テクスチャをGUI上に乗せる
            //ResourceLocation res = new ResourceLocation(domain, path);
            //mc.getTextureManager().bindTexture(res);
            //文字列をGUI上に描画する
            //mc.fontRenderer.drawString("", 60.0f, 4.5f, 0x000000, false)
        }
    }

    class Category(guiHelper: IGuiHelper) : JEICategoryBase<Wrapper> {

        companion object {
            fun registerCategory(registry: IRecipeCategoryRegistration) {
                registry.addRecipeCategories(Category(registry.jeiHelpers.guiHelper))
            }

            fun register(registry: IModRegistry) {
                registry.handleRecipes(RagiMaterial::class.java, { Wrapper(it) }, JEICore.MaterialInfo)
                registry.addRecipes(MaterialRegistry.getMaterials(), JEICore.MaterialInfo)
                registry.addRecipeCatalyst(ItemStack(Items.IRON_INGOT), JEICore.MaterialInfo)
            }
        }

        override var backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/material_info.png"), 0, 0, 144, 108)

        override fun getUid(): String = JEICore.MaterialInfo

        override fun setRecipe(layout: IRecipeLayout, wrapper: Wrapper, ingredients: IIngredients) {
            val material = wrapper.material

            initPart(layout, wrapper, 0, 1, 1, PartRegistry.ORE) //Ore
            initPart(layout, wrapper, 1, 1, 3, PartRegistry.CRUSHED) //Crushed Ore
            initPart(layout, wrapper, 2, 1, 5, PartRegistry.PURIFIED) //Purified Crushed Ore
            initFluid(layout, wrapper, 0, 3, 1) //Fluid
            initPart(layout, wrapper, 4, 3, 3, PartRegistry.DUST) //Dust
            initPart(layout, wrapper, 5, 3, 5, PartRegistry.DUST_TINY) //Tiny Dust
            initPart(layout, wrapper, 6, 5, 1, PartRegistry.BLOCK) //Block

            //Ingot or Crystal
            layout.itemStacks.init(7, true, 18 * 5 - 9, 18 * 3 - 9)
            val listMain = mutableListOf<ItemStack>()
            if (EnumMaterialType.INGOT in material.type.types) listMain.addAll(wrapper.getInputsList(mutableSetOf(), PartRegistry.INGOT))
            if (EnumMaterialType.CRYSTAL in material.type.types) listMain.addAll(wrapper.getInputsList(mutableSetOf(), PartRegistry.CRYSTAL))
            layout.itemStacks[7] = listMain

            initPart(layout, wrapper, 8, 5, 5, PartRegistry.NUGGET) //Nugget
            initPart(layout, wrapper, 9, 7, 1, PartRegistry.GEAR) //Gear
            initPart(layout, wrapper, 10, 7, 3, PartRegistry.PLATE) //Plate
            initPart(layout, wrapper, 11, 7, 5, PartRegistry.STICK) //Stick
        }

        private fun initPart(layout: IRecipeLayout, wrapper: Wrapper, index: Int, x: Int, y: Int, part: MaterialPart) {
            layout.itemStacks.init(index, true, 18 * x - 9, 18 * y - 9)
            layout.itemStacks[index] = wrapper.getInputsList(mutableSetOf(), part)
        }

        private fun initFluid(layout: IRecipeLayout, wrapper: Wrapper, index: Int, x: Int, y: Int) {
            layout.fluidStacks.init(index, true, 18 * x - 8, 18 * y - 8, 16, 16, 1000, false, null)
            wrapper.material.getFluid()?.let { layout.fluidStacks[index] = FluidStack(it, 1000) }
        }

    }

}