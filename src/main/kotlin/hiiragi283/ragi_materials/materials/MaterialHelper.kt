package hiiragi283.ragi_materials.materials

import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

object MaterialHelper {

    val listPrefix = listOf("dust", "ingot", "nugget", "plate")

    //EnumMaterialもしくはindexから液体を取得するメソッド
    fun getFluid(material: EnumMaterials): Fluid {
        val fluid = FluidRegistry.getFluid(material.registryName)
        //fluidが存在しない場合は水を返す
        return if (fluid !== null) fluid else FluidRegistry.getFluid("water")
    }

    fun getFluid(index: Int): Fluid {
        return getFluid(getMaterial(index))
    }

    //代入したindexと一致するEnumMaterialsを返すメソッド
    fun getMaterial(index: Int): EnumMaterials {
        //デフォルト値はDEBUG
        var materialMatches: EnumMaterials = EnumMaterials.WILDCARD
        for (material in EnumMaterials.values()) {
            //indexが一致する場合
            if (material.index == index) {
                //materialMatchesにmaterialを代入
                materialMatches = material
            }
        }
        //materialMatchesを返す
        return materialMatches
    }

}