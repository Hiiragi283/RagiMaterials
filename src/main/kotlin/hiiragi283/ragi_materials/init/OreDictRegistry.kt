package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.RagiUtil

object OreDictRegistry {

    private val listOreDict = listOf(
            OreDictHandler(EnumMaterialType.BLOCK_MATERIAL, "block", PartRegistry.BLOCK),
            OreDictHandler(EnumMaterialType.CRYSTAL, "gem", PartRegistry.CRYSTAL),
            OreDictHandler(EnumMaterialType.DUST, "dust", PartRegistry.DUST),
            OreDictHandler(EnumMaterialType.DUST, "dustTiny", PartRegistry.DUST_TINY),
            OreDictHandler(EnumMaterialType.DUMMY, "ore", PartRegistry.ORE),
            OreDictHandler(EnumMaterialType.DUMMY, "ore", PartRegistry.ORE_NETHER),
            OreDictHandler(EnumMaterialType.DUMMY, "ore", PartRegistry.ORE_END),
            OreDictHandler(EnumMaterialType.INGOT, "ingot", PartRegistry.INGOT),
            OreDictHandler(EnumMaterialType.PLATE, "plate", PartRegistry.PLATE),
            OreDictHandler(EnumMaterialType.STICK, "stick", PartRegistry.STICK),
            OreDictHandler(EnumMaterialType.GEAR, "gear", PartRegistry.GEAR),
            OreDictHandler(EnumMaterialType.INGOT_HOT, "ingotHot", PartRegistry.INGOT_HOT),
            OreDictHandler(EnumMaterialType.NUGGET, "nugget", PartRegistry.NUGGET)
    )

    //鉱石辞書を登録するメソッド
    init {
        //list内の各materialに対して実行
        for (material in MaterialRegistry.list) {
            //listOreDict内の各OreDictHandlerに対して実行
            for (oredict in listOreDict) {
                val stack = MaterialUtil.getPart(oredict.part, material)
                //materialのtypeがoredictのtypeを含む場合
                if (oredict.type in material.type.list) {
                    RagiUtil.setOreDict(oredict.prefix + material.getOreDict(), stack)
                    //別の鉱石辞書名を持っている場合
                    if (material.oredictAlt !== null) {
                        RagiUtil.setOreDict(oredict.prefix + material.oredictAlt, stack)
                    }
                }
                //鉱石専用の処理
                if (oredict.type == EnumMaterialType.DUMMY && material.hasOre) {
                    RagiUtil.setOreDict(oredict.prefix + material.getOreDict(), stack)
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

    class OreDictHandler(val type: EnumMaterialType, val prefix: String, val part: MaterialPart)
}