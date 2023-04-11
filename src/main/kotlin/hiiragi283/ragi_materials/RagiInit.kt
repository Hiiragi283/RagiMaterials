package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.color.ColorManager
import hiiragi283.ragi_materials.client.color.RagiColor
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.crafting.CraftingRegistry
import hiiragi283.ragi_materials.crafting.SmeltingRegistry
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.proxy.IProxy
import hiiragi283.ragi_materials.recipe.FFRecipe
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.tile.TileTransferEnergy
import hiiragi283.ragi_materials.tile.TileTransferFluid
import hiiragi283.ragi_materials.tile.TileTransferHeat
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

object RagiInit : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //lateinit変数の初期化
        initFields()
        //collectionへの自動登録
        val fields = this::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val obj = field.get(this)
                if (obj is Block) RagiRegistry.setBlocks.add(obj)
                else if (obj is Item) RagiRegistry.setItems.add(obj)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        RagiRegistry.setBlocks.forEach {
            if (it is IMaterialBlock) RagiRegistry.setIMaterialBlock.add(it)
        }
        RagiRegistry.setItems.forEach {
            if (it is ItemBlock) {
                val block = it.block
                if (block is IMaterialBlock) RagiRegistry.setIMaterialItemBlock.add(it)
            } else if (it is IMaterialItem) RagiRegistry.setIMaterialItem.add(it)
        }
    }

    private fun initFields() {

        //    Block    //

        RagiRegistry.BlockSoilAir = BlockSoilAir()

        RagiRegistry.BlockBlazingForge = BlockBlazingForge()
        RagiRegistry.BlockForgeFurnace = BlockForgeFurnace()
        RagiRegistry.BlockFullBottleStation = BlockFullBottleStation()
        RagiRegistry.BlockIndustrialLabo = BlockIndustrialLabo()
        RagiRegistry.BlockLaboratoryTable = BlockLaboTable()
        RagiRegistry.BlockOre1 = BlockOreMaterial("ore_block")
        RagiRegistry.BlockOreDictConv = BlockOreDictConv()
        RagiRegistry.BlockOreRainbow = BlockOreRainbow("ore_rainbow")
        RagiRegistry.BlockSoilCoal = BlockSoilCoal()
        RagiRegistry.BlockSoilLignite = BlockSoilLignite()
        RagiRegistry.BlockSoilPeat = BlockSoilPeat()
        RagiRegistry.BlockStoneMill = BlockStoneMill()
        RagiRegistry.BlockTransferEnergy = BlockTransferBase("energy", TileTransferEnergy::class.java, RagiColor.YELLOW)
        RagiRegistry.BlockTransferFluid = BlockTransferBase("fluid", TileTransferFluid::class.java, RagiColor.AQUA)
        RagiRegistry.BlockTransferHeat = BlockTransferBase("heat", TileTransferHeat::class.java, ColorManager.mixColor(RagiColor.RED, RagiColor.GOLD))

        //    Item    //

        RagiRegistry.ItemBlockBlazingForge = ItemBlockBase(RagiRegistry.BlockBlazingForge)
        RagiRegistry.ItemBlockForgeFurnace = ItemBlockBase(RagiRegistry.BlockForgeFurnace)
        RagiRegistry.ItemBlockFullBottleStation = ItemBlockBase(RagiRegistry.BlockFullBottleStation)
        RagiRegistry.ItemBlockIndustrialLabo = ItemBlockBase(RagiRegistry.BlockIndustrialLabo)
        RagiRegistry.ItemBlockLaboratoryTable = ItemBlockBase(RagiRegistry.BlockLaboratoryTable)
        RagiRegistry.ItemBlockOre1 = ItemBlockBase(RagiRegistry.BlockOre1, OreProperty.mapOre1.size - 1)
        RagiRegistry.ItemBlockOreDictConv = ItemBlockBase(RagiRegistry.BlockOreDictConv)
        RagiRegistry.ItemBlockOreRainbow = ItemBlockBase(RagiRegistry.BlockOreRainbow)
        RagiRegistry.ItemBlockSoilCoal = ItemBlockBase(RagiRegistry.BlockSoilCoal)
        RagiRegistry.ItemBlockSoilLignite = ItemBlockBase(RagiRegistry.BlockSoilLignite)
        RagiRegistry.ItemBlockSoilPeat = ItemBlockBase(RagiRegistry.BlockSoilPeat)
        RagiRegistry.ItemBlockStoneMill = ItemBlockBase(RagiRegistry.BlockStoneMill)
        RagiRegistry.ItemBlockTransferEnergy = ItemBlockBase(RagiRegistry.BlockTransferEnergy)
        RagiRegistry.ItemBlockTransferFluid = ItemBlockBase(RagiRegistry.BlockTransferFluid)
        RagiRegistry.ItemBlockTransferHeat = ItemBlockBase(RagiRegistry.BlockTransferHeat)

        RagiRegistry.ItemBlazingCube = ItemBase(RagiMaterials.MOD_ID, "blazing_cube", 0)
        RagiRegistry.ItemBookDebug = ItemBookDebug()
        RagiRegistry.ItemEnderTable = ItemEnderTable()
        RagiRegistry.ItemForgeHammer = ItemForgeHammer()
        RagiRegistry.ItemFullBottle = ItemFullBottle()
        RagiRegistry.ItemOreCrushed = ItemOreCrushed()
        RagiRegistry.ItemOreCrushedVanilla = ItemOreCrushedVanilla()
        RagiRegistry.ItemWaste = ItemWaste()

        RagiRegistry.ItemBlockMaterial = ItemMaterial(PartRegistry.BLOCK)
        RagiRegistry.ItemCrystal = ItemMaterial(PartRegistry.CRYSTAL)
        RagiRegistry.ItemDust = ItemMaterial(PartRegistry.DUST)
        RagiRegistry.ItemDustTiny = ItemMaterial(PartRegistry.DUST_TINY)
        RagiRegistry.ItemGear = ItemMaterial(PartRegistry.GEAR)
        RagiRegistry.ItemIngot = ItemMaterial(PartRegistry.INGOT)
        RagiRegistry.ItemIngotHot = ItemMaterial(PartRegistry.INGOT_HOT)
        RagiRegistry.ItemNugget = ItemMaterial(PartRegistry.NUGGET)
        RagiRegistry.ItemPlate = ItemMaterial(PartRegistry.PLATE)
        RagiRegistry.ItemStick = ItemMaterial(PartRegistry.STICK)

    }

    override fun onInit(event: FMLInitializationEvent) {
        registerFluid()
        registerOreDict()
        CraftingRegistry.load()
        SmeltingRegistry.load()
    }

    private fun registerFluid() {
        //Fluidの登録
        for (material in RagiMaterial.list) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.list) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.color)
                //MaterialTypesがGASの場合
                if (material.type.match(TypeRegistry.GAS)) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
            }
        }
    }

    private fun registerOreDict() {
        //鉱石辞書の登録
        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            val stack = MaterialUtil.getPart(part, material)
            RagiUtil.setOreDict(part.prefixOre + material.getOreDict(), stack)
            material.oredictAlt?.let { RagiUtil.setOreDict(part.prefixOre + it, stack) }
        }

        //Ore
        for (i in OreProperty.listOre1.indices) {
            RagiUtil.setOreDict("ore${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.BlockOre1, 1, i))
            RagiUtil.setOreDict("crushed${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.ItemOreCrushed, 1, i))
        }
        RagiUtil.setOreDict("oreSaltpeter", ItemStack(RagiRegistry.BlockOre1, 1, 6))
        RagiUtil.setOreDict("oreSaltpeterCrushed", ItemStack(RagiRegistry.ItemOreCrushed, 1, 6))

        for (i in OreProperty.listVanilla.indices) {
            RagiUtil.setOreDict("crushed${OreProperty.listVanilla[i].first}", ItemStack(RagiRegistry.ItemOreCrushedVanilla, 1, i))
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

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerRecipes()
        printDebug()
    }

    private fun registerRecipes() {
        //レシピの登録
        FFRecipe.Registry.load()
        LaboRecipe.Registry.load()
        MillRecipe.Registry.load()
    }

    private fun printDebug() {
        //デバッグ用
        if (RagiConfig.debugMode.isDebug) {
            FFRecipe.Registry.printMap()
            LaboRecipe.Registry.printMap()
            MaterialUtil.printMap()
            MillRecipe.Registry.printMap()
            //RagiRegistry.printTiles()
        }
    }

}