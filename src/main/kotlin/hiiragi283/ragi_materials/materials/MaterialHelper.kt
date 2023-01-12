package hiiragi283.ragi_materials.materials

import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

object MaterialHelper {

    //道具に使える素材と耐久値のMap
    val mapToolMaterial = mapOf(
        EnumMaterials.DEBUG to 1109,
        EnumMaterials.TITANIUM to 512,
        EnumMaterials.IRON to 64,
        EnumMaterials.COPPER to 32,
        EnumMaterials.TUNGSTEN to 512
    )

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
        //デフォルト値はWILDCARD
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

    //代入したregistryNameと一致するEnumMaterialsを返すメソッド
    fun getMaterial(registryName: String): EnumMaterials {
        //デフォルト値はWILDCARD
        var materialMatches: EnumMaterials = EnumMaterials.WILDCARD
        for (material in EnumMaterials.values()) {
            //indexが一致する場合
            if (material.registryName == registryName) {
                //materialMatchesにmaterialを代入
                materialMatches = material
            }
        }
        //materialMatchesを返す
        return materialMatches
    }

}