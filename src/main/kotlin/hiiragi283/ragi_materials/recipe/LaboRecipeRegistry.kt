package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.util.RagiFluidUtil
import net.minecraft.item.ItemStack

object LaboRecipeRegistry {

    val set: MutableSet<LaboRecipe> = mutableSetOf()

    fun load() {

        //Hydrogen
        LaboRecipe.Builder().apply {
            inputs[0] = RagiFluidUtil.getBottle(MaterialRegistry.HYDROGEN, count = 2)
            inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.OXYGEN)
            outputs[0] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 2)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.WATER.name).also { set.add(it) }

        //Boron
        LaboRecipe.Builder().apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORAX)
            inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.SULFURIC_ACID)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORON_OXIDE, 2)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_SULFATE)
            outputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 11)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.BORON_OXIDE.name).also { set.add(it) }

        //Fluorine
        LaboRecipe.Builder().apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.FLUORITE)
            inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.SULFURIC_ACID)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM)
            outputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.HYDROGEN_FLUORIDE)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.HYDROGEN_FLUORIDE.name).also { set.add(it) }

        //Magnesium
        LaboRecipe.Builder().apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESITE)
            inputs[1] = RagiFluidUtil.getBottle(MaterialRegistry.HYDROGEN_CHLORIDE, count = 2)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CHLORIDE)
            outputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistry.WATER)
            outputs[2] = RagiFluidUtil.getBottle(material = MaterialRegistry.CARBON_DIOXIDE)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.MAGNESIUM_CHLORIDE.name).also { set.add(it) }

        //Aluminium
        LaboRecipe.Builder().apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BAUXITE)
            inputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
            inputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 3)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.RUTILE, 3)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.GALLIUM, 1)
            outputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA_SOLUTION.name).also { set.add(it) }

        LaboRecipe.Builder().apply {
            inputs[0] = RagiFluidUtil.getBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.ALUMINA)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2)
            outputs[2] = RagiFluidUtil.getBottle(MaterialRegistry.WATER, count = 3)
            catalyst = ItemStack(RagiItems.ItemBlazingCube)
        }.build().setRegistryName(RagiMaterials.MOD_ID, MaterialRegistry.ALUMINA.name).also { set.add(it) }
    }
}