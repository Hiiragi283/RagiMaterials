package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtils

object RagiInitOreDict {

    private val mapType = mapOf(
        "block" to listOf(MaterialBuilder.MaterialType.METAL),
        "dust" to listOf(MaterialBuilder.MaterialType.DUST, MaterialBuilder.MaterialType.METAL),
        "ingot" to listOf(MaterialBuilder.MaterialType.METAL),
        "ingot_hot" to listOf(MaterialBuilder.MaterialType.METAL),
        "nugget" to listOf(MaterialBuilder.MaterialType.METAL),
        "plate" to listOf(MaterialBuilder.MaterialType.METAL)
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
                }
            }
        }
    }

}