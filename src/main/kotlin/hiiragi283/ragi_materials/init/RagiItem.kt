package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.part.PartRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object RagiItem {

    val ItemBlockBlazingForge = ItemBlockBase(RagiBlock.BlockBlazingForge)
    val ItemBlockForgeFurnace = ItemBlockBase(RagiBlock.BlockForgeFurnace)
    val ItemBlockFullBottleStation = ItemBlockBase(RagiBlock.BlockFullBottleStation)
    val ItemBlockIndustrialLabo = ItemBlockBase(RagiBlock.BlockIndustrialLabo)
    val ItemBlockLaboratoryTable = ItemBlockBase(RagiBlock.BlockLaboratoryTable)
    val ItemBlockOreDictConv = ItemBlockBase(RagiBlock.BlockOreDictConv)
    val ItemBlockOreRainbow = ItemBlockBase(RagiBlock.BlockOreRainbow)
    val ItemBlockSaltPond = ItemBlockBase(RagiBlock.BlockSaltPond)

    val ItemBlazingCube: Item = ItemBase(Reference.MOD_ID, "blazing_cube", 0).setCreativeTab(CreativeTabs.MISC)
    val ItemBookDebug: Item = ItemBookDebug().setCreativeTab(CreativeTabs.MISC)
    val ItemFullBottle: Item = ItemFullBottle().setCreativeTab(RagiRegistry.TabFullBottle)
    val ItemSeedCoal: Item = ItemSeedCoal().setCreativeTab(CreativeTabs.MISC)
    val ItemSeedLignite: Item = ItemSeedLignite().setCreativeTab(CreativeTabs.MISC)
    val ItemSeedPeat: Item = ItemSeedPeat("seed_peat").setCreativeTab(CreativeTabs.MISC)
    val ItemWaste = ItemWaste()

    val ItemBlockMaterial = ItemMaterial(PartRegistry.BLOCK)
    val ItemCrystal = ItemMaterial(PartRegistry.CRYSTAL)
    val ItemDust = ItemMaterial(PartRegistry.DUST)
    val ItemDustTiny = ItemMaterial(PartRegistry.DUST_TINY)
    val ItemGear = ItemMaterial(PartRegistry.GEAR)
    val ItemIngot = ItemMaterial(PartRegistry.INGOT)
    val ItemIngotHot = ItemMaterial(PartRegistry.INGOT_HOT)
    val ItemNugget = ItemMaterial(PartRegistry.NUGGET)
    val ItemOre = ItemMaterialOre(PartRegistry.ORE)
    val ItemOreNether = ItemMaterialOre(PartRegistry.ORE_NETHER)
    val ItemOreEnd = ItemMaterialOre(PartRegistry.ORE_END)
    val ItemPlate = ItemMaterial(PartRegistry.PLATE)
    val ItemStick = ItemMaterial(PartRegistry.STICK)

}