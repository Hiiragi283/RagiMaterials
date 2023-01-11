package hiiragi283.ragi_materials.render

import hiiragi283.ragi_materials.init.RagiInitItem
import hiiragi283.ragi_materials.materials.EnumMaterials
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader

object ModelMaterial {

    //素材のモデルを一括で登録するメソッド
    fun setModelMaterial() {
        //EnumMaterials内の各materialに対して実行
        for (material in EnumMaterials.values()) {
            //list内の各itemに対して実行
            for (item in listOf(RagiInitItem.ItemBlockMetal, RagiInitItem.ItemDust, RagiInitItem.ItemIngot, RagiInitItem.ItemNugget, RagiInitItem.ItemPlate)) {
                //白金族のindexとindexが一致する場合
                if (listOf(44, 45, 46, 76, 77, 78).contains(material.index)) {
                    //専用のモデルを割り当てる
                    ModelLoader.setCustomModelResourceLocation(item, material.index, ModelResourceLocation(item.registryName.toString()+ "_precious", "inventory"))
                }
                //一般庶民の場合
                else {
                    ModelLoader.setCustomModelResourceLocation(item, material.index, ModelResourceLocation(item.registryName.toString(), "inventory"))
                }
            }
        }
    }
}