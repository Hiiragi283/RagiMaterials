package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.color.RagiColor
import hiiragi283.ragi_materials.config.RagiConfig
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
import hiiragi283.ragi_materials.recipe.furnace.SmeltingRegistry
import hiiragi283.ragi_materials.recipe.workbench.CraftingRegistry
import hiiragi283.ragi_materials.tile.TileTransferEnergy
import hiiragi283.ragi_materials.tile.TileTransferFluid
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.oredict.OreDictionary

object RagiInit : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //lateinit変数の初期化
        initFields()
        //collectionへの自動登録
        val fields = RagiRegistry::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val obj = field.get(RagiRegistry)
                //Block
                if (obj is Block) {
                    RagiRegistry.setBlocks.add(obj)
                    RagiLogger.infoDebug("The block ${obj.registryName} is added to list!")
                    //ItemBlock
                    if (obj is IItemBlock && obj.getItemBlock() !== null) {
                        RagiRegistry.setItemBlocks.add(obj.getItemBlock()!!)
                        RagiLogger.infoDebug("The item block ${obj.registryName} is added to list!")
                    }
                }
                //Item
                if (obj is Item) {
                    RagiRegistry.setItems.add(obj)
                    RagiLogger.infoDebug("The item ${obj.registryName} is added to list!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        RagiRegistry.setBlocks.forEach {
            if (it is IMaterialBlock) RagiRegistry.setIMaterialBlocks.add(it)
        }
        RagiRegistry.setItemBlocks.forEach {
            val block = it.block
            if (block is IMaterialBlock) RagiRegistry.setIMaterialItemBlocks.add(it)
        }
        RagiRegistry.setItems.forEach {
            if (it is IMaterialItem) RagiRegistry.setIMaterialItems.add(it)
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
        //RagiRegistry.BlockTransferHeat = BlockTransferBase("heat", TileTransferHeat::class.java, ColorManager.mixColor(RagiColor.RED, RagiColor.GOLD))

        //    Item    //

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
            OreDictionary.registerOre(part.prefixOre + material.getOreDict(), stack)
            material.oredictAlt?.let { OreDictionary.registerOre(part.prefixOre + it, stack) }
        }

        //Ore
        for (i in OreProperty.listOre1.indices) {
            OreDictionary.registerOre("ore${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.BlockOre1, 1, i))
            OreDictionary.registerOre("crushed${OreProperty.listOre1[i].first}", ItemStack(RagiRegistry.ItemOreCrushed, 1, i))
        }
        OreDictionary.registerOre("oreSaltpeter", ItemStack(RagiRegistry.BlockOre1, 1, 6))
        OreDictionary.registerOre("oreSaltpeterCrushed", ItemStack(RagiRegistry.ItemOreCrushed, 1, 6))

        for (i in OreProperty.listVanilla.indices) {
            OreDictionary.registerOre("crushed${OreProperty.listVanilla[i].first}", ItemStack(RagiRegistry.ItemOreCrushedVanilla, 1, i))
        }

        //Others
        OreDictionary.registerOre("charcoal", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.CHARCOAL))
        OreDictionary.registerOre("dustGunpowder", RagiUtil.getStack("minecraft:gunpowder", 1, 0))
        OreDictionary.registerOre("dustSugar", RagiUtil.getStack("minecraft:sugar", 1, 0))
        OreDictionary.registerOre("fuelCoke", MaterialUtil.getPart(PartRegistry.CRYSTAL, MaterialRegistry.COKE))
        OreDictionary.registerOre("gearStone", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.STONE))
        OreDictionary.registerOre("gearWood", MaterialUtil.getPart(PartRegistry.GEAR, MaterialRegistry.WOOD))
        OreDictionary.registerOre("gemCharcoal", RagiUtil.getStack("minecraft:coal", 1, 1))
        OreDictionary.registerOre("stickStone", MaterialUtil.getPart(PartRegistry.STICK, MaterialRegistry.STONE))
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
            //FFRecipe.Registry.printMap()
            //LaboRecipe.Registry.printMap()
            MaterialUtil.printMap()
            //MillRecipe.Registry.printMap()
            //RagiRegistry.printTiles()
        }
    }

}