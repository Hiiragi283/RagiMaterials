package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.FluidStack

class MaterialInfoCategory(guiHelper: IGuiHelper) : JEICategoryBase<MaterialInfoWrapper>() {

    private var background: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/material_info.png"), 0, 0, 162, 108)

    //JEiタブのIDを取得するメソッド
    override fun getUid(): String = JEICore.MaterialInfo

    //JEiタブの背景を取得するメソッド
    override fun getBackground(): IDrawable = background

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(layout: IRecipeLayout, wrapper: MaterialInfoWrapper, ingredients: IIngredients) {
        val material = wrapper.material
        //Dust
        layout.itemStacks.init(0, true, 18 * 1, 18 * 3)
        layout.itemStacks[0] = wrapper.getInputsList(mutableListOf(), PartRegistry.DUST)
        //Tiny Dust
        layout.itemStacks.init(1, true, 18 * 1, 18 * 5)
        layout.itemStacks[1] = wrapper.getInputsList(mutableListOf(), PartRegistry.DUST_TINY)
        //Block
        layout.itemStacks.init(2, true, 18 * 3, 18 * 1)
        layout.itemStacks[2] = wrapper.getInputsList(mutableListOf(), PartRegistry.BLOCK)
        //Ingot or Crystal
        layout.itemStacks.init(3, true, 18 * 3, 18 * 3)
        val listMain = mutableListOf<ItemStack>()
        if (EnumMaterialType.INGOT in material.type.list) listMain.addAll(wrapper.getInputsList(mutableListOf(), PartRegistry.INGOT))
        if (EnumMaterialType.CRYSTAL in material.type.list) listMain.addAll(wrapper.getInputsList(mutableListOf(), PartRegistry.CRYSTAL))
        layout.itemStacks[3] = listMain
        //Nugget
        layout.itemStacks.init(4, true, 18 * 3, 18 * 5)
        layout.itemStacks[4] = wrapper.getInputsList(mutableListOf(), PartRegistry.NUGGET)
        //Fluid
        layout.fluidStacks.init(0, true, 18 * 5 + 1, 18 * 2 + 1)
        material.getFluid()?.let { layout.fluidStacks[0] = FluidStack(it, 1000) }
        //Hot Ingot
        layout.itemStacks.init(5, true, 18 * 5, 18 * 4)
        layout.itemStacks[5] = wrapper.getInputsList(mutableListOf(), PartRegistry.INGOT_HOT)
        //Stick
        layout.itemStacks.init(6, true, 18 * 7, 18 * 1)
        layout.itemStacks[6] = wrapper.getInputsList(mutableListOf(), PartRegistry.STICK)
        //Plate
        layout.itemStacks.init(7, true, 18 * 7, 18 * 3)
        layout.itemStacks[7] = wrapper.getInputsList(mutableListOf(), PartRegistry.PLATE)
        //Gear
        layout.itemStacks.init(8, true, 18 * 7, 18 * 5)
        layout.itemStacks[8] = wrapper.getInputsList(mutableListOf(), PartRegistry.GEAR)
    }


}