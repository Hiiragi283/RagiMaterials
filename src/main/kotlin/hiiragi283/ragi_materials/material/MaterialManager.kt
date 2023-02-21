package hiiragi283.ragi_materials.material

import net.minecraftforge.fluids.Fluid

object MaterialManager {

    fun getFluid(index: Int): Fluid? {
        return getMaterial(index)!!.getFluid()
    }

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): MaterialBuilder? {
        val material = MaterialRegistry.map[index]
        return if (material !== null) material else null
    }

    //代入したnameと一致するmaterialを返すメソッド
    fun getMaterial(name: String): MaterialBuilder? {
        var result: MaterialBuilder? = null
        for (material in MaterialRegistry.map.values) {
            if (material.name == name) result = material
            break
        }
        //resultを返す
        return result
    }
}