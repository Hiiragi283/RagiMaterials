package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiUtil

object OreDictRegistry {

    private val listOreDict = listOf(
            OreDictHandler(EnumMaterialType.BLOCK_MATERIAL, "block", "block_material"),
            OreDictHandler(EnumMaterialType.CRYSTAL, "gem", "crystal"),
            OreDictHandler(EnumMaterialType.DUST, "dust", "dust"),
            OreDictHandler(EnumMaterialType.DUST, "dustTiny", "dust_tiny"),
            OreDictHandler(EnumMaterialType.DUMMY, "ore", "ore"),
            OreDictHandler(EnumMaterialType.DUMMY, "ore", "ore_nether"),
            OreDictHandler(EnumMaterialType.DUMMY, "ore", "ore_end"),
            OreDictHandler(EnumMaterialType.INGOT, "ingot", "ingot"),
            OreDictHandler(EnumMaterialType.PLATE, "plate", "plate"),
            OreDictHandler(EnumMaterialType.STICK, "stick", "stick"),
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
                if (oredict.type in material.type.parts) {
                    RagiUtil.setOreDict(
                            oredict.prefix + material.getOreDict(),
                            RagiUtil.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, material.index)
                    )
                    if (material.oredictAlt !== null) {
                        RagiUtil.setOreDict(
                                oredict.prefix + material.oredictAlt,
                                RagiUtil.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, material.index)
                        )
                    }
                }
                if (material.hasOre) {
                    RagiUtil.setOreDict(
                            oredict.prefix + material.getOreDict(),
                            RagiUtil.getStack("${Reference.MOD_ID}:${oredict.ID}", 1, material.index)
                    )
                }
            }
        }

        RagiUtil.setOreDict("gearStone", RagiUtil.getStack("${Reference.MOD_ID}:gear", 1, MaterialRegistry.STONE.index))
        //RagiUtil.setOreDict("gearWood", RagiUtil.getStack("${Reference.MOD_ID}:gear", 1, MaterialRegistry.WOOD.index))
        RagiUtil.setOreDict("stickStone", RagiUtil.getStack("${Reference.MOD_ID}:stick", 1, MaterialRegistry.STONE.index))

        RagiUtil.setOreDict("dustGunpowder", RagiUtil.getStack("minecraft:gunpowder", 1, 0))
        //RagiUtil.setOreDict("gunpowder", RagiUtil.getStack("${Reference.MOD_ID}:dust", 1, MaterialRegistry.GUNPOWDER.index))
        RagiUtil.setOreDict("dustSugar", RagiUtil.getStack("minecraft:sugar", 1, 0))
        //RagiUtil.setOreDict("sugar", RagiUtil.getStack("${Reference.MOD_ID}:dust", 1, MaterialRegistry.SUGAR.index))
        RagiUtil.setOreDict("gemCharcoal", RagiUtil.getStack("minecraft:coal", 1, 1))
        RagiUtil.setOreDict("charcoal", RagiUtil.getStack("${Reference.MOD_ID}:crystal", 1, MaterialRegistry.CHARCOAL.index))
        RagiUtil.setOreDict("fuelCoke", RagiUtil.getStack("${Reference.MOD_ID}:crystal", 1, MaterialRegistry.COKE.index))
    }

    class OreDictHandler(val type: EnumMaterialType, val prefix: String, val ID: String)
}