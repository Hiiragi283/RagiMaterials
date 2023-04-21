package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.MaterialPart
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.util.getPart
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

class MaterialInfoWrapper(val material: RagiMaterial) : IRecipeWrapper {

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