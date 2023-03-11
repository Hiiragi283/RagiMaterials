package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.base.ItemToolBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiInit {

    //Creative Tabの定義
    val TabBlocks: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Blocks() else CreativeTabs.MISC
    val TabFullBottle: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.FullBottles() else CreativeTabs.MISC
    val TabMaterials: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Materials() else CreativeTabs.MISC

    //Blockの定義
    val BlockCropCoal = BlockCropCoal()
    val BlockCropLignite = BlockCropLignite()
    val BlockCropPeat = BlockCropPeat("crop_peat")
    val BlockForgeFurnace = BlockForgeFurnaceNew()
    val BlockFullBottleStation = BlockFullBottleStation()
    val BlockLaboratoryTable = BlockLaboTable()
    val BlockOreDictConv = BlockOreDictConv()
    val BlockOreRainbow = BlockOreRainbow("ore_rainbow")
    val BlockSaltPond = BlockSaltPond()

    //ToolMaterialの宣言
    val ToolTitanium = EnumHelper.addToolMaterial("RM_TITANIUM", 3, 511, 8.0f, 3.0f, 10)!!

    //Itemの定義
    val ItemBlockForgeFurnace = ItemBlockBase(BlockForgeFurnace, 0)
    val ItemBlockFullBottleStation = ItemBlockBase(BlockFullBottleStation, 0)
    val ItemBlockLaboratoryTable = ItemBlockBase(BlockLaboratoryTable, 0)
    val ItemBlockOreDictConv = ItemBlockBase(BlockOreDictConv, 0)
    val ItemBlockOreRainbow = ItemBlockBase(BlockOreRainbow, 0)
    val ItemBlockSaltPond = ItemBlockBase(BlockSaltPond, 0)

    val ItemBlazingCube: Item = ItemBase(Reference.MOD_ID, "blazing_cube", 0).setCreativeTab(CreativeTabs.MISC)
    val ItemBookDebug: Item = ItemBookDebug().setCreativeTab(CreativeTabs.MISC)
    val ItemFullBottle: Item = ItemFullBottle().setCreativeTab(TabFullBottle)
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

    val ItemsAxe = arrayOf(
            ItemToolBase.AXE(MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsHammer = arrayOf(
            ItemToolBase("hammer", MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsPickaxe = arrayOf(
            ItemToolBase.PICKAXE(MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsSpade = arrayOf(
            ItemToolBase.SPADE(MaterialRegistry.TITANIUM, ToolTitanium)
    )
    val ItemsSword = arrayOf(
            ItemToolBase.SWORD(MaterialRegistry.TITANIUM, ToolTitanium)
    )

    init {
        //Blockの登録
        ForgeRegistries.BLOCKS.registerAll(
                BlockCropCoal,
                BlockCropLignite,
                BlockCropPeat,
                BlockForgeFurnace,
                BlockFullBottleStation,
                BlockLaboratoryTable,
                BlockOreDictConv,
                BlockOreRainbow,
                BlockSaltPond
        )

        //Itemの登録
        ForgeRegistries.ITEMS.registerAll(
                ItemBlockForgeFurnace,
                ItemBlockFullBottleStation,
                ItemBlockLaboratoryTable,
                ItemBlockOreDictConv,
                ItemBlockOreRainbow,
                ItemBlockSaltPond,

                ItemBlazingCube,
                ItemBookDebug,
                ItemFullBottle,
                ItemSeedCoal,
                ItemSeedLignite,
                ItemSeedPeat,
                ItemWaste,

                ItemBlockMaterial,
                ItemDust,
                ItemDustTiny,
                ItemGear,
                ItemCrystal,
                ItemIngot,
                ItemIngotHot,
                ItemNugget,
                ItemOre,
                ItemOreNether,
                ItemOreEnd,
                ItemPlate,
                ItemStick,

                *ItemsAxe,
                *ItemsHammer,
                *ItemsPickaxe,
                *ItemsSpade,
                *ItemsSword
        )

        //Fluidの登録
        //listの各materialに対して実行
        for (material in MaterialRegistry.mapIndex.values) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.list) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.color)
                //MaterialTypesがGASの場合
                if (material.type == TypeRegistry.GAS) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
            }
        }
    }

    class RagiCreativeTabs {

        class Materials : CreativeTabs("ragi_materials.materials") {
            override fun getTabIconItem(): ItemStack = ItemStack(ItemIngot, 1, 26)
        }

        class Blocks : CreativeTabs("ragi_materials.blocks") {
            override fun getTabIconItem(): ItemStack = ItemStack(ItemBlockForgeFurnace)
        }

        class FullBottles : CreativeTabs("ragi_materials.fullbottles") {
            override fun getTabIconItem(): ItemStack = ItemStack(ItemFullBottle)
        }
    }
}