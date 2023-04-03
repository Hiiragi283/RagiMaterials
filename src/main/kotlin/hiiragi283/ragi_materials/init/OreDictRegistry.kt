package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

object OreDictRegistry {

    //鉱石辞書を登録するメソッド
    fun load() {

        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            val stack = MaterialUtil.getPart(part, material)
            RagiUtil.setOreDict(part.prefixOre + material.getOreDict(), stack)
            material.oredictAlt?.let { RagiUtil.setOreDict(part.prefixOre + it, stack) }
        }

        //Ore
        for (i in OreProperty.listOre1.indices) {
            RagiUtil.setOreDict("ore${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.BLOCK.BlockOre1, 1, i))
            RagiUtil.setOreDict("crushed${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.ITEM.ItemOreCrushed, 1, i))
        }
        RagiUtil.setOreDict("oreSaltpeter", ItemStack(RagiRegistry.BLOCK.BlockOre1, 1, 6))
        RagiUtil.setOreDict("oreSaltpeterCrushed", ItemStack(RagiRegistry.ITEM.ItemOreCrushed, 1, 6))

        for (i in OreProperty.listVanilla.indices) {
            RagiUtil.setOreDict("crushed${OreProperty.listVanilla[i].first}", ItemStack(RagiRegistry.ITEM.ItemOreCrushedVanilla, 1, i))
        }

        //Others
        RagiUtil.setOreDict("charcoal", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.CHARCOAL))
        RagiUtil.setOreDict("dustGunpowder", RagiUtil.getStack("minecraft:gunpowder", 1, 0))
        RagiUtil.setOreDict("dustSugar", RagiUtil.getStack("minecraft:sugar", 1, 0))
        RagiUtil.setOreDict("fuelCoke", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.COKE))
        RagiUtil.setOreDict("gearStone", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.STONE))
        RagiUtil.setOreDict("gearWood", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.WOOD))
        RagiUtil.setOreDict("gemCharcoal", RagiUtil.getStack("minecraft:coal", 1, 1))
        RagiUtil.setOreDict("stickStone", MaterialUtil.getPart(PartRegistry.STICK, MaterialRegistry.STONE))
    }
}