package hiiragi283.ragi_materials.api.init

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.material.IMaterialBlock
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.color.RagiColor
import hiiragi283.ragi_materials.tile.TileTransferEnergy
import hiiragi283.ragi_materials.tile.TileTransferFluid
import net.minecraft.block.Block

object RagiBlocks {

    val BlockSoilAir = BlockSoilAir()

    val BlockBlazingForge = BlockBlazingForge()
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockFullBottleStation = BlockFullBottleStation()
    val BlockIndustrialLabo = BlockIndustrialLabo()
    val BlockLaboratoryTable = BlockLaboTable()
    val BlockOre1 = BlockOreMaterial("ore_block")
    val BlockOreDictConv = BlockOreDictConv()
    val BlockOreRainbow = BlockOreRainbow("ore_rainbow")
    val BlockSoilCoal = BlockSoilCoal()
    val BlockSoilLignite = BlockSoilLignite()
    val BlockSoilPeat = BlockSoilPeat()
    val BlockStoneMill = BlockStoneMill()
    val BlockTransferEnergy = BlockTransferBase("energy", TileTransferEnergy::class.java, RagiColor.YELLOW)
    val BlockTransferFluid = BlockTransferBase("fluid", TileTransferFluid::class.java, RagiColor.AQUA)
    //val BlockTransferHeat = BlockTransferBase("heat", TileTransferHeat::class.java, ColorManager.mixColor(RagiColor.RED, RagiColor.GOLD))

    lateinit var BlockTransferGas: Block

    fun load() {
        this::class.java.declaredFields.forEach {
            it.isAccessible = true
            RagiMaterials.LOGGER.debug(it.name)
            try {
                val obj = it.get(this)
                if (obj is BlockBase) {
                    RagiInit.setBlocks.add(obj)
                    RagiMaterials.LOGGER.debug("The block ${obj.registryName} is added to list!")
                    if (obj.itemBlock !== null) {
                        RagiInit.setItemBlocks.add(obj.itemBlock!!)
                        RagiMaterials.LOGGER.debug("The item block ${obj.registryName} is added to list!")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        RagiInit.setBlocks.forEach {
            if (it is IMaterialBlock) RagiInit.setIMaterialBlocks.add(it)
        }
        RagiInit.setItemBlocks.forEach {
            val block = it.block
            if (block is IMaterialBlock) RagiInit.setIMaterialItemBlocks.add(it)
        }
    }
}