package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

object LaboRecipeRegistry {

    val map: LinkedHashMap<String, LaboRecipe> = linkedMapOf()

    fun init() {

        //Hydrogen
        LaboRecipe.Builder(MaterialRegistry.WATER.name).apply {
            inputs[0] = RagiUtil.getFilledBottle(MaterialRegistry.HYDROGEN, count = 2)
            inputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistry.OXYGEN)
            outputs[0] = RagiUtil.getFilledBottle(MaterialRegistry.WATER, count = 2)
        }.build()

        //Boron
        LaboRecipe.Builder(MaterialRegistry.BORON_OXIDE.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORAX)
            inputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistry.SULFURIC_ACID)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORON_OXIDE, 2)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_SULFATE)
            outputs[2] = RagiUtil.getFilledBottle(MaterialRegistry.HYDROGEN, count = 11)
        }.build()

        //Fluorine
        LaboRecipe.Builder(MaterialRegistry.HYDROGEN_FLUORIDE.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.FLUORITE)
            inputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistry.SULFURIC_ACID)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM)
            outputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistry.HYDROGEN_FLUORIDE)
        }.build()

        //Magnesium
        LaboRecipe.Builder(MaterialRegistry.HYDROGEN_FLUORIDE.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CARBONATE)
            inputs[0] = RagiUtil.getFilledBottle(MaterialRegistry.HYDROGEN_CHLORIDE, count = 2)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CHLORIDE)
            outputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistry.WATER)
            outputs[2] = RagiUtil.getFilledBottle(material = MaterialRegistry.CARBON_DIOXIDE)
        }.build()

        //Aluminium
        LaboRecipe.Builder(MaterialRegistry.ALUMINA_SOLUTION.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BAUXITE)
            inputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
            inputs[2] = RagiUtil.getFilledBottle(MaterialRegistry.WATER ,count = 3)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.RUTILE, 3)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.GALLIUM, 1)
            outputs[2] = RagiUtil.getFilledBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
        }.build()

        LaboRecipe.Builder(MaterialRegistry.ALUMINIUM_OXIDE.name).apply{
            inputs[1] = RagiUtil.getFilledBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.ALUMINIUM_OXIDE)
            outputs[2] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
            outputs[3] = RagiUtil.getFilledBottle(MaterialRegistry.WATER, count = 3)
        }.setCatalyst(0, ItemStack(RagiInit.ItemBlazingCube)).build()
    }
}