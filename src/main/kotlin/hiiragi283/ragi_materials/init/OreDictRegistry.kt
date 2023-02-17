package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import hiiragi283.ragi_materials.util.RagiUtils

object OreDictRegistry {

    private val listOreDict = listOf(
        OreDictHandler(MaterialType.CRYSTAL, "block", "block_crystal"),
        OreDictHandler(MaterialType.METAL, "block", "block_metal"),
        OreDictHandler(MaterialType.DUST, "dust", "dust"),
        OreDictHandler(MaterialType.DUST, "dustTiny", "dust_tiny"),
        OreDictHandler(MaterialType.CRYSTAL, "gem", "crystal"),
        OreDictHandler(MaterialType.INGOT, "ingot", "ingot"),
        OreDictHandler(MaterialType.METAL, "ingotHot", "ingot_hot"),
        OreDictHandler(MaterialType.METAL, "nugget", "nugget"),
        OreDictHandler(MaterialType.INGOT, "plate", "plate")
    )

    //鉱石辞書を登録するメソッド
    fun init() {
        //list内の各materialに対して実行
        for (material in MaterialRegistry.list) {
            //listOreDict内の各OreDictHandlerに対して実行
            for (oredict in listOreDict) {
                //materialのtypeがoredictのtypeを含む場合
                if (material.type.getTypeBase().contains(oredict.type.name)) {
                    RagiUtils.setOreDict(
                        oredict.prefix + material.getOreDict(),
                        RagiUtils.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, material.index)
                    )
                    if (material == MaterialRegistry.CHROMIUM) {
                        //Chromium
                        RagiUtils.setOreDict(
                            "${oredict.prefix}Chrome",
                            RagiUtils.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, 24)
                        )
                    }
                    if (material == MaterialRegistry.NITER) {
                        //Saltpeter
                        RagiUtils.setOreDict(
                            "${oredict.prefix}Saltpeter",
                            RagiUtils.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, 222)
                        )
                    }
                }
            }
        }

        RagiUtils.setOreDict("dustGunpowder", RagiUtils.getStack("minecraft:gunpowder", 1, 0))
        RagiUtils.setOreDict("gunpowder", RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, 223))
        RagiUtils.setOreDict("dustSugar", RagiUtils.getStack("minecraft:sugar", 1, 0))
        RagiUtils.setOreDict("sugar", RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, 224))
    }

    class OreDictHandler(val type: MaterialType, val prefix: String, val ID: String)
}