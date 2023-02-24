package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import hiiragi283.ragi_materials.util.RagiUtils

object OreDictRegistry {

    private val listOreDict = listOf(
        OreDictHandler(MaterialType.EnumMaterialType.BLOCK_CRYSTAL, "block", "block_crystal"),
        OreDictHandler(MaterialType.EnumMaterialType.CRYSTAL, "gem", "crystal"),
        OreDictHandler(MaterialType.EnumMaterialType.DUST, "dust", "dust"),
        OreDictHandler(MaterialType.EnumMaterialType.DUST, "dustTiny", "dust_tiny"),
        OreDictHandler(MaterialType.EnumMaterialType.INGOT, "ingot", "ingot"),
        OreDictHandler(MaterialType.EnumMaterialType.PLATE, "plate", "plate"),
        OreDictHandler(MaterialType.EnumMaterialType.STICK, "stick", "stick"),
        OreDictHandler(MaterialType.EnumMaterialType.BLOCK_METAL, "block", "block_metal"),
        OreDictHandler(MaterialType.EnumMaterialType.GEAR, "gear", "gear"),
        OreDictHandler(MaterialType.EnumMaterialType.INGOT_HOT, "ingotHot", "ingot_hot"),
        OreDictHandler(MaterialType.EnumMaterialType.NUGGET, "nugget", "nugget")
    )

    //鉱石辞書を登録するメソッド
    fun init() {
        //list内の各materialに対して実行
        for (material in MaterialRegistry.mapIndex.values) {
            //listOreDict内の各OreDictHandlerに対して実行
            for (oredict in listOreDict) {
                //materialのtypeがoredictのtypeを含む場合
                if (material.type.contains(oredict.type)) {
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

        RagiUtils.setOreDict("gearStone", RagiUtils.getStack("${Reference.MOD_ID}:gear", 1, MaterialRegistry.STONE.index))
        RagiUtils.setOreDict("gearWood", RagiUtils.getStack("${Reference.MOD_ID}:gear", 1, MaterialRegistry.WOOD.index))
        RagiUtils.setOreDict("stickStone", RagiUtils.getStack("${Reference.MOD_ID}:stick", 1, MaterialRegistry.STONE.index))

        RagiUtils.setOreDict("dustGunpowder", RagiUtils.getStack("minecraft:gunpowder", 1, 0))
        RagiUtils.setOreDict("gunpowder", RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, MaterialRegistry.GUNPOWDER.index))
        RagiUtils.setOreDict("dustSugar", RagiUtils.getStack("minecraft:sugar", 1, 0))
        RagiUtils.setOreDict("sugar", RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, MaterialRegistry.SUGAR.index))
        RagiUtils.setOreDict("gemCharcoal", RagiUtils.getStack("minecraft:coal", 1, 1))
        RagiUtils.setOreDict("charcoal", RagiUtils.getStack("${Reference.MOD_ID}:crystal", 1, MaterialRegistry.CHARCOAL.index))
    }

    class OreDictHandler(val type: MaterialType.EnumMaterialType, val prefix: String, val ID: String)
}