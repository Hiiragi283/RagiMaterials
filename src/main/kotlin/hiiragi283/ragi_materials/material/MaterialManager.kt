package hiiragi283.ragi_materials.material

import net.minecraftforge.fluids.Fluid

object MaterialManager {

    fun getFluid(index: Int): Fluid {
        return getMaterial(index).getFluid()
    }

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): MaterialBuilder {
        //デフォルト値はWILDCARD
        var materialMatches = MaterialRegistry.WILDCARD
        for (material in MaterialRegistry.list) {
            if (material.index == index) materialMatches = material
        }
        //materialMatchesを返す
        return materialMatches
    }

    //代入したnameと一致するmaterialを返すメソッド
    fun getMaterial(name: String): MaterialBuilder {
        //デフォルト値はWILDCARD
        var materialMatches = MaterialRegistry.WILDCARD
        for (material in MaterialRegistry.list) {
            if (material.name == name) materialMatches = material
        }
        //materialMatchesを返す
        return materialMatches
    }
}