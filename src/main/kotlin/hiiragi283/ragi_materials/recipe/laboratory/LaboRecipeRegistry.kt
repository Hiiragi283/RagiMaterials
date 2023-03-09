package hiiragi283.ragi_materials.recipe.laboratory

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

object LaboRecipeRegistry {

    val list: MutableList<LaboRecipeBuilder> = mutableListOf()

    fun init() {

        //Hydrogen
        LaboRecipeBuilder(
                RagiUtil.getFilledBottle(MaterialRegistry.HYDROGEN, count = 2),
                RagiUtil.getFilledBottle(material = MaterialRegistry.OXYGEN),
                output = RagiUtil.getFilledBottle(MaterialRegistry.WATER, count = 2)
        )

        //Boron
        LaboRecipeBuilder(
                MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORAX),
                RagiUtil.getFilledBottle(material = MaterialRegistry.SULFURIC_ACID),
                outputs = listOf(
                        MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_SULFATE),
                        MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BORON_OXIDE, 2),
                        RagiUtil.getFilledBottle(MaterialRegistry.HYDROGEN, count = 11)
                )
        )

        //Fluorine
        LaboRecipeBuilder(
                MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.FLUORITE),
                RagiUtil.getFilledBottle(material = MaterialRegistry.SULFURIC_ACID),
                outputs = listOf(
                        MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.GYPSUM),
                        RagiUtil.getFilledBottle(material = MaterialRegistry.HYDROGEN_FLUORIDE)
                )
        )

        //Magnesium
        LaboRecipeBuilder(
                MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CARBONATE),
                RagiUtil.getFilledBottle(MaterialRegistry.HYDROGEN_CHLORIDE, count = 2),
                outputs = listOf(
                        MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.MAGNESIUM_CHLORIDE),
                        RagiUtil.getFilledBottle(material = MaterialRegistry.WATER),
                        RagiUtil.getFilledBottle(material = MaterialRegistry.CARBON_DIOXIDE)
                )
        )

        //Aluminium
        LaboRecipeBuilder(
                MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.BAUXITE),
                MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2),
                RagiUtil.getFilledBottle(MaterialRegistry.WATER ,count = 3),
                outputs = listOf(
                        MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.RUTILE, 3),
                        MaterialUtil.getPart(PartRegistry.DUST_TINY, MaterialRegistry.GALLIUM, 1),
                        RagiUtil.getFilledBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2)
                )
        )

        LaboRecipeBuilder(
                RagiUtil.getFilledBottle(MaterialRegistry.ALUMINA_SOLUTION, count = 2),
                ItemStack(RagiInit.ItemBlazingCube),
                outputs = listOf(
                        MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.ALUMINIUM_OXIDE),
                        MaterialUtil.getPart(PartRegistry.DUST, MaterialRegistry.SODIUM_HYDROXIDE, 2),
                        RagiUtil.getFilledBottle(MaterialRegistry.WATER, count = 3),
                        ItemStack(RagiInit.ItemBlazingCube)
                )
        )

    }
}