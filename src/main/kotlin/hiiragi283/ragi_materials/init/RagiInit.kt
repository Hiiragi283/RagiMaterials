package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialType
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries
import java.awt.Color

object RagiInit {

    //Creative Tabの定義
    val TabBlocks: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Blocks() else CreativeTabs.MISC
    val TabMaterials: CreativeTabs = if (!RagiMaterials.isLoadedGT) RagiCreativeTabs.Materials() else CreativeTabs.MISC

    //Blockの定義
    val BlockBellow = BlockBellow()
    val BlockBlazeHeater = BlockBlazeHeater()
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockLitForgeFurnace = BlockLitForgeFurnace()
    val BlockOreDictConv = BlockOreDictConv()
    val BlockSaltPond = BlockSaltPond()

    //Itemの定義
    val ItemBlockBellow = ItemBlockBase(BlockBellow, 0, 2)
    val ItemBlockBlazeHeater = ItemBlockBase(BlockBlazeHeater, 1, 2)
    val ItemBlockForgeFurnace = ItemBlockBase(BlockForgeFurnace, 0, 3)
    val ItemBlockOreDictConv = ItemBlockBase(BlockOreDictConv, 0, 1)
    val ItemBlockSaltPond = ItemBlockBase(BlockSaltPond, 0, 2)

    val ItemBlazingCube: Item = ItemBase(Reference.MOD_ID, "blazing_cube", 0).setCreativeTab(CreativeTabs.MISC)
    val ItemBlockMetal = ItemMaterial("block_metal", MaterialType.METAL)

    val ItemBookDebug: Item = ItemBookDebug().setCreativeTab(CreativeTabs.MISC)
    val ItemDust = ItemMaterial("dust", MaterialType.DUST)
    val ItemDustTiny = ItemMaterial("dust_tiny", MaterialType.DUST)
    val ItemForgeHammer: Item = ItemForgeHammer().setCreativeTab(CreativeTabs.TOOLS)
    val ItemIngot = ItemMaterial("ingot", MaterialType.METAL)
    val ItemIngotHot = ItemMaterial("ingot_hot", MaterialType.METAL)
    val ItemNugget = ItemMaterial("nugget", MaterialType.METAL)
    val ItemPlate = ItemMaterial("plate", MaterialType.METAL)

    val ItemCellTest = ItemCellTest()

    fun register() {
        //configからmaterialを追加
        registerMaterial()
        //Blockの登録
        ForgeRegistries.BLOCKS.registerAll(
            BlockBellow,
            BlockBlazeHeater,
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
            ItemBlockMetal,
            ItemBookDebug,
            ItemDust,
            ItemDustTiny,
            ItemForgeHammer,
            ItemIngot,
            ItemIngotHot,
            ItemNugget,
            ItemPlate,

            ItemCellTest
        )
        //Fluidの登録
        //listの各materialに対して実行
        for (material in MaterialRegistry.list) {
            //materialがWILDCARDでない，かつmaterialのtypeがfluidの場合
            if (material != MaterialRegistry.WILDCARD && material.type.getTypeBase().contains("fluid")) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.getColor())
                //MaterialTypesがGASの場合
                if (material.type == MaterialType.GAS) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
                //materialのtypeのhasFluidBlockがtrueの場合
                if (material.type.getTypeBase().contains("liquid")) {
                    //液体ブロックを生成・登録・割り当て
                    val fluidBlock = BlockFluidClassic(fluid, Material.WATER).setRegistryName(fluid.name)
                    ForgeRegistries.BLOCKS.register(fluidBlock)
                    fluid.block = fluidBlock
                }
            }
        }
    }

    //configからmaterialを登録するメソッド
    private fun registerMaterial() {
        for (value in RagiConfig.material.listMaterials) {
            //valueをばらしてプロパティを得る
            val listProperty = value.split(":")
            val index = listProperty[0].toInt()
            val name = listProperty[1]
            var type = MaterialType.WILDCARD
            val color = Color(listProperty[3].toIntOrNull(16)!!)
            val formula = listProperty[4]
            val molar = listProperty[5].toFloat()
            val melt = listProperty[6].toInt()
            val boil = listProperty[7].toInt()
            //MaterialTypeの確認
            for (i in MaterialType.list) {
                if (i.name == listProperty[2]) {
                    type = i
                    break
                }
            }
            //indexが1023以上maxMaterials以下，かつtypeがWILDCARDでない場合，materialを登録する
            if (index in 1023..RagiConfig.material.maxMaterials && type != MaterialType.WILDCARD) {
                val material = MaterialBuilder(index, name, type)
                material.setColor(color)
                material.setFormula(formula)
                material.setMolarMass(molar)
                material.setTempMelt(melt)
                material.setTempBoil(boil)
                MaterialRegistry.list.add(material)
            }
        }
    }
}