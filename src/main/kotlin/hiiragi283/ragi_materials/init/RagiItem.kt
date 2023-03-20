package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.part.PartRegistry
import net.minecraft.creativetab.CreativeTabs

object RagiItem {

    val ItemBlockBlazingForge = ItemBlockBase(RagiBlock.BlockBlazingForge)
    val ItemBlockForgeFurnace = ItemBlockBase(RagiBlock.BlockForgeFurnace)
    val ItemBlockFullBottleStation = ItemBlockBase(RagiBlock.BlockFullBottleStation)
    val ItemBlockIndustrialLabo = ItemBlockBase(RagiBlock.BlockIndustrialLabo)
    val ItemBlockLaboratoryTable = ItemBlockBase(RagiBlock.BlockLaboratoryTable)
    val ItemBlockOre1 = ItemBlockBase(RagiBlock.BlockOre1, RagiBlock.BlockOre1.list.size - 1)
    val ItemBlockOreDictConv = ItemBlockBase(RagiBlock.BlockOreDictConv)
    val ItemBlockOreRainbow = ItemBlockBase(RagiBlock.BlockOreRainbow)
    //val ItemBlockSaltPond = ItemBlockBase(RagiBlock.BlockSaltPond)
    val ItemBlockSoilCoal = ItemBlockBase(RagiBlock.BlockSoilCoal)
    val ItemBlockSoilLignite = ItemBlockBase(RagiBlock.BlockSoilLignite)
    val ItemBlockSoilPeat = ItemBlockBase(RagiBlock.BlockSoilPeat)
    val ItemBlockStoneMill = ItemBlockBase(RagiBlock.BlockStoneMill)

    val ItemBlazingCube = ItemBase(Reference.MOD_ID, "blazing_cube", 0).apply { creativeTab = CreativeTabs.MISC }
    val ItemBookDebug = ItemBookDebug()
    val ItemFullBottle = ItemFullBottle()
    val ItemOreCrushed = ItemOreCrushed()
    val ItemWaste = ItemWaste()

    val ItemBlockMaterial = ItemMaterial(PartRegistry.BLOCK)
    val ItemCrystal = ItemMaterial(PartRegistry.CRYSTAL)
    val ItemDust = ItemMaterial(PartRegistry.DUST)
    val ItemDustTiny = ItemMaterial(PartRegistry.DUST_TINY)
    val ItemGear = ItemMaterial(PartRegistry.GEAR)
    val ItemIngot = ItemMaterial(PartRegistry.INGOT)
    val ItemIngotHot = ItemMaterial(PartRegistry.INGOT_HOT)
    val ItemNugget = ItemMaterial(PartRegistry.NUGGET)
    val ItemPlate = ItemMaterial(PartRegistry.PLATE)
    val ItemStick = ItemMaterial(PartRegistry.STICK)

}