package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.util.getPart

object MaterialInfoJEI {

    class Wrapper(val material: RagiMaterial) : IRecipeWrapper {

        private val listParts: MutableList<ItemStack> = mutableListOf()

        init {
            initParts()
        }

        private fun initParts() {
            for (part in PartRegistry.list) {
                getInputsList(listParts, part)
            }
        }

        fun getInputsList(list: MutableList<ItemStack>, part: MaterialPart): MutableList<ItemStack> {
            val stackPart = getPart(part, material)
            if (!stackPart.isEmpty) {
                list.add(stackPart) //部品を追加
                //鉱石辞書にも対応させる
                list.addAll(OreDictionary.getOres(part.prefixOre + material.getOreDict()))
            }
            return list
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

    class Category(guiHelper: IGuiHelper) : JEICategoryBase<Wrapper>() {

        override var backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/material_info.png"), 0, 0, 144, 108)

        override fun getUid(): String = JEICore.MaterialInfo

        override fun setRecipe(layout: IRecipeLayout, wrapper: Wrapper, ingredients: IIngredients) {
            val material = wrapper.material

            initPart(layout, wrapper, 0, 1, 1, PartRegistry.ORE) //Ore
            initPart(layout, wrapper, 1, 1, 3, PartRegistry.ORE_CRUSHED) //Crushed Ore
            //initPart(layout, wrapper, 1, 1, 5, PartRegistry.)
            initFluid(layout, wrapper, 0, 3, 1) //Fluid
            initPart(layout, wrapper, 4, 3, 3, PartRegistry.DUST) //Dust
            initPart(layout, wrapper, 5, 3, 5, PartRegistry.DUST_TINY) //Tiny Dust
            initPart(layout, wrapper, 6, 5, 1, PartRegistry.BLOCK) //Block

            //Ingot or Crystal
            layout.itemStacks.init(7, true, 18 * 5 - 9, 18 * 3 - 9)
            val listMain = mutableListOf<ItemStack>()
            if (EnumMaterialType.INGOT in material.type.list) listMain.addAll(wrapper.getInputsList(mutableListOf(), PartRegistry.INGOT))
            if (EnumMaterialType.CRYSTAL in material.type.list) listMain.addAll(wrapper.getInputsList(mutableListOf(), PartRegistry.CRYSTAL))
            layout.itemStacks[7] = listMain

            initPart(layout, wrapper, 8, 5, 5, PartRegistry.NUGGET) //Nugget
            initPart(layout, wrapper, 9, 7, 1, PartRegistry.GEAR) //Gear
            initPart(layout, wrapper, 10, 7, 3, PartRegistry.PLATE) //Plate
            initPart(layout, wrapper, 11, 7, 5, PartRegistry.STICK) //Stick
        }

        private fun initPart(layout: IRecipeLayout, wrapper: Wrapper, index: Int, x: Int, y: Int, part: MaterialPart) {
            layout.itemStacks.init(index, true, 18 * x - 9, 18 * y - 9)
            layout.itemStacks[index] = wrapper.getInputsList(mutableListOf(), part)
        }

        private fun initFluid(layout: IRecipeLayout, wrapper: Wrapper, index: Int, x: Int, y: Int) {
            layout.fluidStacks.init(index, true, 18 * x - 8, 18 * y - 8)
            wrapper.material.getFluid()?.let { layout.fluidStacks[index] = FluidStack(it, 1000) }
        }

    }

}