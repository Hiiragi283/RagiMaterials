package hiiragi283.ragi_materials.unused

import hiiragi283.ragi_materials.base.ItemToolBase
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraftforge.common.util.EnumHelper

object RagiInit {

    //ToolMaterialの宣言
    val ToolTitanium = EnumHelper.addToolMaterial("RM_TITANIUM", 3, 511, 8.0f, 3.0f, 10)!!

    val ItemsAxe = arrayOf(
            ItemToolBase.AXE(MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsHammer = arrayOf(
            ItemToolBase("hammer", MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsPickaxe = arrayOf(
            ItemToolBase.PICKAXE(MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsSpade = arrayOf(
            ItemToolBase.SPADE(MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsSword = arrayOf(
            ItemToolBase.SWORD(MaterialRegistry.TITANIUM, ToolTitanium)
    )

    /*init {
        //Fluidの登録
        for (material in MaterialRegistry.mapIndex.values) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.list) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.color)
                //MaterialTypesがGASの場合
                if (material.type == TypeRegistry.GAS) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
            }
        }
    }*/
}