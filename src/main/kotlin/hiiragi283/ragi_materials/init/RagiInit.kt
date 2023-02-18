package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiInit {

    //Creative Tabの定義
    val TabBlocks: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Blocks() else CreativeTabs.MISC
    val TabMaterials: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Materials() else CreativeTabs.MISC

    //Blockの定義
    val BlockBellow = BlockBellow()
    val BlockBlazeHeater = BlockBlazeHeater()
    val BlockCropCoal = BlockCropCoal()
    val BlockCropLignite = BlockCropLignite()
    val BlockCropPeat = BlockCropPeat("crop_peat")
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockLitForgeFurnace = BlockLitForgeFurnace()
    val BlockOreDictConv = BlockOreDictConv()
    val BlockSaltPond = BlockSaltPond()

    //Itemの定義
    val ItemBlockBellow = ItemBlockBase(BlockBellow, 0)
    val ItemBlockBlazeHeater = ItemBlockBase(BlockBlazeHeater, 1)
    val ItemBlockForgeFurnace = ItemBlockBase(BlockForgeFurnace, 0)
    val ItemBlockOreDictConv = ItemBlockBase(BlockOreDictConv, 0)
    val ItemBlockSaltPond = ItemBlockBase(BlockSaltPond, 0)

    val ItemBlazingCube: Item = ItemBase(Reference.MOD_ID, "blazing_cube", 0).setCreativeTab(CreativeTabs.MISC)
    val ItemBlockCrystal = ItemMaterial("block_crystal", MaterialType.CRYSTAL)
    val ItemBlockMetal = ItemMaterial("block_metal", MaterialType.METAL)
    val ItemBookDebug: Item = ItemBookDebug().setCreativeTab(CreativeTabs.MISC)
    val ItemCrystal = ItemMaterial("crystal", MaterialType.CRYSTAL)
    val ItemDust = ItemMaterial("dust", MaterialType.DUST)
    val ItemDustTiny = ItemMaterial("dust_tiny", MaterialType.DUST)
    val ItemForgeHammer: Item = ItemForgeHammer().setCreativeTab(CreativeTabs.TOOLS)
    val ItemFullBottle = ItemFullBottle()
    val ItemIngot = ItemMaterial("ingot", MaterialType.INGOT)
    val ItemIngotHot = ItemMaterial("ingot_hot", MaterialType.METAL)
    val ItemNugget = ItemMaterial("nugget", MaterialType.METAL)
    val ItemPlate = ItemMaterial("plate", MaterialType.INGOT)
    val ItemSeedCoal = ItemSeedCoal().setCreativeTab(CreativeTabs.MISC)
    val ItemSeedLignite = ItemSeedLignite().setCreativeTab(CreativeTabs.MISC)
    val ItemSeedPeat: Item = ItemSeedPeat("seed_peat").setCreativeTab(CreativeTabs.MISC)

    fun init() {
        //Blockの登録
        ForgeRegistries.BLOCKS.registerAll(
            BlockBellow,
            BlockBlazeHeater,
            BlockCropCoal,
            BlockCropLignite,
            BlockCropPeat,
            BlockForgeFurnace,
            BlockLitForgeFurnace,
            BlockOreDictConv,
            BlockSaltPond
        )
        //Itemの登録
        ForgeRegistries.ITEMS.registerAll(
            ItemBlockBellow,
            ItemBlockBlazeHeater,
            ItemBlockForgeFurnace,
            ItemBlockOreDictConv,
            ItemBlockSaltPond,

            ItemBlazingCube,
            ItemBlockCrystal,
            ItemBlockMetal,
            ItemBookDebug,
            ItemDust,
            ItemDustTiny,
            ItemForgeHammer,
            ItemFullBottle,
            ItemCrystal,
            ItemIngot,
            ItemIngotHot,
            ItemNugget,
            ItemPlate,
            ItemSeedCoal,
            ItemSeedLignite,
            ItemSeedPeat
        )
        //Fluidの登録
        //listの各materialに対して実行
        for (material in MaterialRegistry.list) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != MaterialType.INTERNAL && material.type.getTypeBase().contains("fluid")) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.color)
                //MaterialTypesがGASの場合
                if (material.type == MaterialType.GAS) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
                //material.typeにliquidが含まれる場合場合
                /*if (material.type.getTypeBase().contains("liquid")) {
                    //液体ブロックを生成・登録・割り当て
                    val fluidBlock = BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.name)
                    ForgeRegistries.BLOCKS.register(fluidBlock)
                    fluid.block = fluidBlock
                }*/
            }
        }
    }

    class RagiCreativeTabs {

        class Materials : CreativeTabs("ragi_materials.materials") {

            override fun getTabIconItem(): ItemStack {
                return ItemStack(ItemIngot, 1, 26)
            }
        }

        class Blocks : CreativeTabs("ragi_materials.blocks") {

            override fun getTabIconItem(): ItemStack {
                return ItemStack(ItemBlockForgeFurnace)
            }
        }
    }
}