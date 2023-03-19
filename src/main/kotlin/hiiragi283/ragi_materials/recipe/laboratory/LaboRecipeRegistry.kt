package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.util.RagiLogger

object LaboRecipeRegistry {

    val list: MutableList<LaboRecipe> = mutableListOf()
    val map: LinkedHashMap<String, LaboRecipe> = linkedMapOf()

    init {

        //Hydrogen
        /*LaboRecipe.Builder(MaterialRegistryNew.WATER.name).apply {
            inputs[0] = RagiFluidUtil.getBottle(MaterialRegistryNew.HYDROGEN, count = 2)
            inputs[1] = RagiFluidUtil.getBottle(material = MaterialRegistryNew.OXYGEN)
            outputs[0] = RagiFluidUtil.getBottle(MaterialRegistryNew.WATER, count = 2)
        }.build()

        //Boron
        LaboRecipe.Builder(MaterialRegistryNew.BORON_OXIDE.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.BORAX)
            inputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistryNew.SULFURIC_ACID)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.BORON_OXIDE, 2)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.SODIUM_SULFATE)
            outputs[2] = RagiUtil.getFilledBottle(MaterialRegistryNew.WATER, count = 11)
        }.build()

        //Fluorine
        LaboRecipe.Builder(MaterialRegistryNew.HYDROGEN_FLUORIDE.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.FLUORITE)
            inputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistryNew.SULFURIC_ACID)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.GYPSUM)
            outputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistryNew.HYDROGEN_FLUORIDE)
        }.build()

        //Magnesium
        LaboRecipe.Builder(MaterialRegistryNew.HYDROGEN_FLUORIDE.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.MAGNESIUM_CARBONATE)
            inputs[1] = RagiUtil.getFilledBottle(MaterialRegistryNew.HYDROGEN_CHLORIDE, count = 2)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.MAGNESIUM_CHLORIDE)
            outputs[1] = RagiUtil.getFilledBottle(material = MaterialRegistryNew.WATER)
            outputs[2] = RagiUtil.getFilledBottle(material = MaterialRegistryNew.CARBON_DIOXIDE)
        }.build()

        //Aluminium
        LaboRecipe.Builder(MaterialRegistryNew.ALUMINA_SOLUTION.name).apply {
            inputs[0] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.BAUXITE)
            inputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.SODIUM_HYDROXIDE, 2)
            inputs[2] = RagiUtil.getFilledBottle(MaterialRegistryNew.WATER, count = 3)
            outputs[0] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistryNew.RUTILE, 3)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistryNew.GALLIUM, 1)
            outputs[2] = RagiUtil.getFilledBottle(MaterialRegistryNew.ALUMINA_SOLUTION, count = 2)
        }.build()

        LaboRecipe.Builder(MaterialRegistryNew.ALUMINIUM_OXIDE.name).apply {
            inputs[1] = RagiUtil.getFilledBottle(MaterialRegistryNew.ALUMINA_SOLUTION, count = 2)
            outputs[1] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.ALUMINIUM_OXIDE)
            outputs[2] = MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistryNew.SODIUM_HYDROXIDE, 2)
            outputs[3] = RagiUtil.getFilledBottle(MaterialRegistryNew.WATER, count = 3)
        }.setCatalyst(0, ItemStack(RagiItem.ItemBlazingCube)).build()*/
    }

    fun printMap() {
        map.forEach { RagiLogger.infoDebug("CR recipe: ${it.key}>") }
    }
}