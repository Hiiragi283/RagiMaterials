package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.base.ItemMaterialToolBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiInit {

    //Creative Tabの定義
    val TabBlocks: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Blocks() else CreativeTabs.MISC
    val TabFullBottle: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.FullBottles() else CreativeTabs.MISC
    val TabMaterials: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Materials() else CreativeTabs.MISC

    //Blockの定義
    val BlockBellow = BlockBellow()
    val BlockBlazeHeater = BlockBlazeHeater()
    val BlockCropCoal = BlockCropCoal()
    val BlockCropLignite = BlockCropLignite()
    val BlockCropPeat = BlockCropPeat("crop_peat")
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockForgeFurnaceNew = BlockForgeFurnaceNew()
    val BlockFullBottleStation = BlockFullBottleStation()
    val BlockLaboratoryTable = BlockLaboTable()
    val BlockLitForgeFurnace = BlockLitForgeFurnace()
    val BlockOreDictConv = BlockOreDictConv()
    val BlockSaltPond = BlockSaltPond()

    //Itemの定義
    val ItemBlockBellow = ItemBlockBase(BlockBellow, 0)
    val ItemBlockBlazeHeater = ItemBlockBase(BlockBlazeHeater, 1)
    val ItemBlockForgeFurnace = ItemBlockBase(BlockForgeFurnace, 0)
    val ItemBlockForgeFurnaceNew = ItemBlockBase(BlockForgeFurnaceNew, 0)
    val ItemBlockFullBottleStation = ItemBlockBase(BlockFullBottleStation, 0)
    val ItemBlockLaboratoryTable = ItemBlockBase(BlockLaboratoryTable, 0)
    val ItemBlockOreDictConv = ItemBlockBase(BlockOreDictConv, 0)
    val ItemBlockSaltPond = ItemBlockBase(BlockSaltPond, 0)

    val ItemBlazingCube: Item = ItemBase(Reference.MOD_ID, "blazing_cube", 0).setCreativeTab(CreativeTabs.MISC)
    val ItemBookDebug: Item = ItemBookDebug().setCreativeTab(CreativeTabs.MISC)
    val ItemForgeHammer = ItemMaterialToolBase("forge_hammer")
    val ItemFullBottle: Item = ItemFullBottle().setCreativeTab(TabFullBottle)
    val ItemSeedCoal: Item = ItemSeedCoal().setCreativeTab(CreativeTabs.MISC)
    val ItemSeedLignite: Item = ItemSeedLignite().setCreativeTab(CreativeTabs.MISC)
    val ItemSeedPeat: Item = ItemSeedPeat("seed_peat").setCreativeTab(CreativeTabs.MISC)
    val ItemWaste = ItemWaste()

    val ItemBlockMaterial = ItemMaterial(PartRegistry.BLOCK, EnumMaterialType.BLOCK_MATERIAL)
    val ItemCrystal = ItemMaterial(PartRegistry.CRYSTAL, EnumMaterialType.CRYSTAL)
    val ItemDust = ItemMaterial(PartRegistry.DUST, EnumMaterialType.DUST)
    val ItemDustTiny = ItemMaterial(PartRegistry.DUST_TINY, EnumMaterialType.DUST)
    val ItemGear = ItemMaterial(PartRegistry.GEAR, EnumMaterialType.GEAR)
    val ItemIngot = ItemMaterial(PartRegistry.INGOT, EnumMaterialType.INGOT)
    val ItemIngotHot = ItemMaterial(PartRegistry.INGOT_HOT, EnumMaterialType.INGOT_HOT)
    val ItemNugget = ItemMaterial(PartRegistry.NUGGET, EnumMaterialType.NUGGET)
    val ItemOre = ItemMaterialOre(PartRegistry.ORE)
    val ItemOreNether = ItemMaterialOre(PartRegistry.ORE_NETHER)
    val ItemOreEnd = ItemMaterialOre(PartRegistry.ORE_END)
    val ItemPlate = ItemMaterial(PartRegistry.PLATE, EnumMaterialType.PLATE)
    val ItemStick = ItemMaterial(PartRegistry.STICK, EnumMaterialType.STICK)

    fun init() {
        //Blockの登録
        ForgeRegistries.BLOCKS.registerAll(
                BlockBellow,
                BlockBlazeHeater,
                BlockCropCoal,
                BlockCropLignite,
                BlockCropPeat,
                BlockForgeFurnace,
                BlockForgeFurnaceNew,
                BlockFullBottleStation,
                BlockLaboratoryTable,
                BlockLitForgeFurnace,
                BlockOreDictConv,
                BlockSaltPond
        )
        //Itemの登録
        ForgeRegistries.ITEMS.registerAll(
                ItemBlockBellow,
                ItemBlockBlazeHeater,
                ItemBlockForgeFurnace,
                ItemBlockForgeFurnaceNew,
                ItemBlockFullBottleStation,
                ItemBlockLaboratoryTable,
                ItemBlockOreDictConv,
                ItemBlockSaltPond,

                ItemBlazingCube,
                ItemBookDebug,
                ItemForgeHammer,
                ItemFullBottle,
                ItemSeedCoal,
                ItemSeedLignite,
                ItemSeedPeat,
                ItemWaste,

                //ItemBlockCrystal,
                //ItemBlockMetal,
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
                ItemStick
        )
        //Fluidの登録
        //listの各materialに対して実行
        for (material in MaterialRegistry.mapIndex.values) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.parts) {
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