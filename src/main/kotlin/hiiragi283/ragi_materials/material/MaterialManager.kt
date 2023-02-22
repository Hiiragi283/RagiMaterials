package hiiragi283.ragi_materials.material

import net.minecraftforge.fluids.Fluid

object MaterialManager {

    fun getFluid(index: Int): Fluid? {
        return getMaterial(index)!!.getFluid()
    }

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): MaterialBuilder? {
        return if (MaterialRegistry.mapIndex[index] !== null) MaterialRegistry.mapIndex[index] else null
    }

    //代入したnameと一致するmaterialを返すメソッド
    fun getMaterial(name: String): MaterialBuilder? {
        return if (MaterialRegistry.mapName[name] !== null) MaterialRegistry.mapName[name] else null
    }
}