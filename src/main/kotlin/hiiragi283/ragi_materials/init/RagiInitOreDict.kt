package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialBuilder.MaterialType
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtils

object RagiInitOreDict {

    private val mapType = mapOf(
        "block" to listOf(MaterialType.CARBON, MaterialType.METAL),
        "dust" to listOf(MaterialType.CARBON, MaterialType.DUST, MaterialType.METAL),
        "ingot" to listOf(MaterialType.CARBON, MaterialType.METAL),
        "ingot_hot" to listOf(MaterialType.METAL),
        "nugget" to listOf(MaterialType.CARBON, MaterialType.METAL),
        "plate" to listOf(MaterialType.CARBON, MaterialType.METAL)
    )

    private val mapPrefix = mapOf(
        "block" to "block_metal",
        "dust" to "dust",
        "ingot" to "ingot",
        "ingot_hot" to "ingotHot",
        "nugget" to "nugget",
        "plate" to "plate"
    )

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {
        //EnumMaterialsの各enumに対して実行
        for (material in MaterialRegistry.list) {
            for (ID in mapType.keys) {
                if (mapType[ID]?.contains(material.type) == true) {
                    //鉱石辞書名 -> prefix + registryNameをUpperCamelCaseに変換した文字列
                    //ItemStack -> pathをprefix, metadataをmaterialのindexとしてRagiUtils.getStackで取得
                    RagiUtils.setOreDict(
                        mapPrefix[ID] + material.getOreDict(),
                        RagiUtils.getStack("${Reference.MOD_ID}:$ID", 1, material.index)
                    )
                    //Chromium
                    RagiUtils.setOreDict(
                        "${mapPrefix[ID]}Chromium",
                        RagiUtils.getStack("${Reference.MOD_ID}:$ID", 1, 24)
                    )
                    //SUS
                    RagiUtils.setOreDict(
                        "${mapPrefix[ID]}SUS",
                        RagiUtils.getStack("${Reference.MOD_ID}:$ID", 1, 206)
                    )
                }
            }
        }
    }
}