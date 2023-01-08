package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.materials.EnumMaterials
import hiiragi283.ragi_materials.materials.MaterialHelper
import hiiragi283.ragi_materials.util.RagiUtils

object RagiInitOreDict {

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {
        //EnumMaterialsの各enumに対して実行
        for (material in EnumMaterials.values()) {
            //list内の各prefixに対して実行
            for (prefix in MaterialHelper.listPrefix) {
                //鉱石辞書名 -> prefix + registryNameをUpperCamelCaseに変換した文字列
                //ItemStack -> pathをprefix, metadataをmaterialのindexとしてRagiUtils.getStackで取得
                RagiUtils.setOreDict(
                    prefix + material.getOreDict(), RagiUtils.getStack(
                        Reference.MOD_ID, prefix, 1, material.index
                    )
                )
            }
        }
    }
}