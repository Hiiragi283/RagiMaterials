package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtils

object RagiInitOreDict {

    private val mapType = mapOf(
        "block" to "metal",
        "dust" to "dust",
        "dustTiny" to "dust",
        "gem" to "gem",
        "ingot" to "metal",
        "ingotHot" to "metal",
        "nugget" to "metal",
        "plate" to "metal"
    )

    private val mapPrefix = mapOf(
        "block" to "block_metal",
        "dust" to "dust",
        "dustTiny" to "dust_tiny",
        "gem" to "gem",
        "ingot" to "ingot",
        "ingotHot" to "ingot_hot",
        "nugget" to "nugget",
        "plate" to "plate"
    )

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {
        //list内の各materialに対して実行
        for (material in MaterialRegistry.list) {
            for (prefix in mapType.keys) {
                if (material.type.getTypeBase().contains(mapType[prefix])) {
                    //鉱石辞書名 -> prefix + registryNameをUpperCamelCaseに変換した文字列
                    //ItemStack -> pathをprefix, metadataをmaterialのindexとしてRagiUtils.getStackで取得
                    RagiUtils.setOreDict(
                        prefix + material.getOreDict(),
                        RagiUtils.getStack("${Reference.MOD_ID}:${mapPrefix[prefix]}", 1, material.index)
                    )
                    //Chromium
                    RagiUtils.setOreDict(
                        "${prefix}Chromium",
                        RagiUtils.getStack("${Reference.MOD_ID}:${mapPrefix[prefix]}", 1, 24)
                    )
                    //SUS
                    RagiUtils.setOreDict(
                        "${prefix}SUS",
                        RagiUtils.getStack("${Reference.MOD_ID}:${mapPrefix[prefix]}", 1, 206)
                    )
                }
            }
        }
    }
}