package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiUtils

object OreDictRegistry {

    private val listOreDict = listOf(
        OreDictHandler(EnumMaterialType.BLOCK_CRYSTAL, "block", "block_crystal"),
        OreDictHandler(EnumMaterialType.CRYSTAL, "gem", "crystal"),
        OreDictHandler(EnumMaterialType.DUST, "dust", "dust"),
        OreDictHandler(EnumMaterialType.DUST, "dustTiny", "dust_tiny"),
        OreDictHandler(EnumMaterialType.DUST, "ore", "ore"),
        OreDictHandler(EnumMaterialType.DUST, "ore", "ore_nether"),
        OreDictHandler(EnumMaterialType.DUST, "ore", "ore_end"),
        OreDictHandler(EnumMaterialType.INGOT, "ingot", "ingot"),
        OreDictHandler(EnumMaterialType.PLATE, "plate", "plate"),
        OreDictHandler(EnumMaterialType.STICK, "stick", "stick"),
        OreDictHandler(EnumMaterialType.BLOCK_METAL, "block", "block_metal"),
        OreDictHandler(EnumMaterialType.GEAR, "gear", "gear"),
        OreDictHandler(EnumMaterialType.INGOT_HOT, "ingotHot", "ingot_hot"),
        OreDictHandler(EnumMaterialType.NUGGET, "nugget", "nugget")
    )

    //鉱石辞書を登録するメソッド
    fun init() {
        //list内の各materialに対して実行
        for (material in MaterialRegistry.mapIndex.values) {
            //listOreDict内の各OreDictHandlerに対して実行
            for (oredict in listOreDict) {
                //materialのtypeがoredictのtypeを含む場合
                if (material.type.parts.contains(oredict.type)) {
                    RagiUtils.setOreDict(
                        oredict.prefix + material.getOreDict(),
                        RagiUtils.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, material.index)
                    )
                    if (material.oredictAlias !== null) {
                        RagiUtils.setOreDict(
                            oredict.prefix + material.oredictAlias,
                            RagiUtils.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, material.index)
                        )
                    }
                }
            }
        }

        RagiUtils.setOreDict(
            "gearStone",
            RagiUtils.getStack("${Reference.MOD_ID}:gear", 1, MaterialRegistry.STONE.index)
        )
        RagiUtils.setOreDict("gearWood", RagiUtils.getStack("${Reference.MOD_ID}:gear", 1, MaterialRegistry.WOOD.index))
        RagiUtils.setOreDict(
            "stickStone",
            RagiUtils.getStack("${Reference.MOD_ID}:stick", 1, MaterialRegistry.STONE.index)
        )

        RagiUtils.setOreDict("dustGunpowder", RagiUtils.getStack("minecraft:gunpowder", 1, 0))
        RagiUtils.setOreDict(
            "gunpowder",
            RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, MaterialRegistry.GUNPOWDER.index)
        )
        RagiUtils.setOreDict("dustSugar", RagiUtils.getStack("minecraft:sugar", 1, 0))
        RagiUtils.setOreDict("sugar", RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, MaterialRegistry.SUGAR.index))
        RagiUtils.setOreDict("gemCharcoal", RagiUtils.getStack("minecraft:coal", 1, 1))
        RagiUtils.setOreDict(
            "charcoal",
            RagiUtils.getStack("${Reference.MOD_ID}:crystal", 1, MaterialRegistry.CHARCOAL.index)
        )
        RagiUtils.setOreDict(
            "fuelCoke",
            RagiUtils.getStack("${Reference.MOD_ID}:crystal", 1, MaterialRegistry.COKE.index)
        )
    }

    class OreDictHandler(val type: EnumMaterialType, val prefix: String, val ID: String)
}