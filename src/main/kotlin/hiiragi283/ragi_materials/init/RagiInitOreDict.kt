package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.materials.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtils
import hiiragi283.ragi_materials.util.RegexStatics.snakeToLowerCamelCase

object RagiInitOreDict {

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {
        //EnumMaterialsの各enumに対して実行
        for (material in MaterialRegistry.list) {
            //list内の各prefixに対して実行
            //MaterialType.DUST用
            for (prefix in listOf("dust")) {
                //materialのtypeのhasIngotがtrueの場合
                if (material.type.hasDust) {
                    //鉱石辞書名 -> prefix + registryNameをUpperCamelCaseに変換した文字列
                    //ItemStack -> pathをprefix, metadataをmaterialのindexとしてRagiUtils.getStackで取得
                    RagiUtils.setOreDict(
                        prefix + material.getOreDict(),
                        RagiUtils.getStack(Reference.MOD_ID + ":" + prefix, 1, material.index)
                    )
                }
            }
            //list内の各prefixに対して実行
            //MaterialType.METAL用
            for (prefix in listOf("ingot", "ingot_hot", "nugget", "plate")) {
                //materialのtypeのhasIngotがtrueの場合
                if (material.type.hasIngot) {
                    //鉱石辞書名 -> prefix + registryNameをUpperCamelCaseに変換した文字列
                    //ItemStack -> pathをprefix, metadataをmaterialのindexとしてRagiUtils.getStackで取得
                    RagiUtils.setOreDict(
                        prefix.snakeToLowerCamelCase() + material.getOreDict(),
                        RagiUtils.getStack(Reference.MOD_ID + ":" + prefix, 1, material.index)
                    )
                    //金属ブロックの鉱石辞書
                    RagiUtils.setOreDict(
                        "block" + material.getOreDict(),
                        RagiUtils.getStack("${Reference.MOD_ID}:block_metal", 1, material.index)
                    )
                }
            }
        }
    }
}