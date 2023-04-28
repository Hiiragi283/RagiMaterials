package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack
import ragi_materials.core.RagiMaterials
import ragi_materials.core.material.part.MaterialPart
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType

class MaterialInfoCategory(guiHelper: IGuiHelper) : JEICategoryBase<MaterialInfoWrapper>() {

    private var background: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/material_info.png"), 0, 0, 144, 108)

    //JEiタブのIDを取得するメソッド
    override fun getUid(): String = JEICore.MaterialInfo

    //JEiタブの背景を取得するメソッド
    override fun getBackground(): IDrawable = background

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(layout: IRecipeLayout, wrapper: MaterialInfoWrapper, ingredients: IIngredients) {
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

    private fun initPart(layout: IRecipeLayout, wrapper: MaterialInfoWrapper, index: Int, x: Int, y: Int, part: MaterialPart) {
        layout.itemStacks.init(index, true, 18 * x - 9, 18 * y - 9)
        layout.itemStacks[index] = wrapper.getInputsList(mutableListOf(), part)
    }

    private fun initFluid(layout: IRecipeLayout, wrapper: MaterialInfoWrapper, index: Int, x: Int, y: Int) {
        layout.fluidStacks.init(index, true, 18 * x - 8, 18 * y - 8)
        wrapper.material.getFluid()?.let { layout.fluidStacks[index] = FluidStack(it, 1000) }
    }

}