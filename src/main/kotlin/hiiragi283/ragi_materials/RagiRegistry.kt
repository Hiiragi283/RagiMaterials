package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.render.color.RagiColor
import hiiragi283.ragi_materials.client.render.model.ICustomModel
import hiiragi283.ragi_materials.client.render.model.ModelManager
import hiiragi283.ragi_materials.client.render.model.ModelRegistry
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RagiRegistry {

    fun registerFluid() {
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

    object BLOCK {

        val BlockBlazingForge = BlockBlazingForge()
        val BlockForgeFurnace = BlockForgeFurnace()
        val BlockFullBottleStation = BlockFullBottleStation()
        val BlockIndustrialLabo = BlockIndustrialLabo()
        val BlockLaboratoryTable = BlockLaboTable()
        val BlockOre1 = BlockOreMaterial("ore_block")
        val BlockOreDictConv = BlockOreDictConv()
        val BlockOreRainbow = BlockOreRainbow("ore_rainbow")
        val BlockQuartzAntenna = BlockQuartzAntenna()
        val BlockSoilCoal = BlockSoilCoal()
        val BlockSoilLignite = BlockSoilLignite()
        val BlockSoilPeat = BlockSoilPeat()
        val BlockStoneMill = BlockStoneMill()

        val BlockSoilAir = BlockSoilAir()

        val listAll: MutableList<Block> = mutableListOf()
        var arrayIMaterialBlock: Array<Block> = arrayOf()

        init {
            val fields = this::class.java.declaredFields
            for (field in fields) {
                field.isAccessible = true
                RagiLogger.infoDebug(field.name)
                try {
                    val block = field.get(this)
                    if (block is Block) listAll.add(block)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            val listBlock: MutableList<Block> = mutableListOf()
            listAll.forEach { if (it is IMaterialBlock) listBlock.add(it) }
            arrayIMaterialBlock = listBlock.toTypedArray()
        }

    }

    object CREATIVE {

        val BLOCK = object : CreativeTabs("${Reference.MOD_ID}.blocks") {
            override fun createIcon() = ItemStack(ITEM.ItemBlockForgeFurnace)
        }
        val FULLBOTTLE = object : CreativeTabs("${Reference.MOD_ID}.fullbottles") {
            override fun createIcon() = ItemStack(ITEM.ItemFullBottle)
        }
        val MATERIAL = object : CreativeTabs("${Reference.MOD_ID}.materials") {
            override fun createIcon() = ItemStack(ITEM.ItemIngot, 1, 26)
        }

    }

    object ITEM {

        val ItemBlockBlazingForge = ItemBlockBase(BLOCK.BlockBlazingForge)
        val ItemBlockForgeFurnace = ItemBlockBase(BLOCK.BlockForgeFurnace)
        val ItemBlockFullBottleStation = ItemBlockBase(BLOCK.BlockFullBottleStation)
        val ItemBlockIndustrialLabo = ItemBlockBase(BLOCK.BlockIndustrialLabo)
        val ItemBlockLaboratoryTable = ItemBlockBase(BLOCK.BlockLaboratoryTable)
        val ItemBlockOre1 = ItemBlockBase(BLOCK.BlockOre1, OreProperty.mapOre1.size - 1)
        val ItemBlockOreDictConv = ItemBlockBase(BLOCK.BlockOreDictConv)
        val ItemBlockOreRainbow = ItemBlockBase(BLOCK.BlockOreRainbow)
        val ItemBlockQuartzAntenna = ItemBlockBase(BLOCK.BlockQuartzAntenna)
        val ItemBlockSoilCoal = ItemBlockBase(BLOCK.BlockSoilCoal)
        val ItemBlockSoilLignite = ItemBlockBase(BLOCK.BlockSoilLignite)
        val ItemBlockSoilPeat = ItemBlockBase(BLOCK.BlockSoilPeat)
        val ItemBlockStoneMill = ItemBlockBase(BLOCK.BlockStoneMill)

        val ItemBlazingCube = ItemBase(Reference.MOD_ID, "blazing_cube", 0)
        val ItemBookDebug = ItemBookDebug()
        val ItemForgeHammer = ItemForgeHammer()
        val ItemFullBottle = ItemFullBottle()
        val ItemOreCrushed = ItemOreCrushed()
        val ItemOreCrushedVanilla = ItemOreCrushedVanilla()
        val ItemResonatingFork = ItemResonatingFork()
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

        val listAll: MutableList<Item> = mutableListOf()
        var arrayIMaterialItem: Array<Item> = arrayOf()
        var arrayIMaterialItemBlock: Array<ItemBlockBase> = arrayOf()

        init {

            val fields = this::class.java.declaredFields
            for (field in fields) {
                field.isAccessible = true
                RagiLogger.infoDebug(field.name)
                try {
                    val item = field.get(this)
                    if (item is Item) listAll.add(item)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            val listItem: MutableList<Item> = mutableListOf()
            val listItemBlock: MutableList<ItemBlockBase> = mutableListOf()
            for (item in listAll) {
                if (item is IMaterialItem) listItem.add(item)
                else if (item is ItemBlockBase) {
                    val block = item.block
                    if (block is IMaterialBlock) listItemBlock.add(item)
                }
            }
            arrayIMaterialItem = listItem.toTypedArray()
            arrayIMaterialItemBlock = listItemBlock.toTypedArray()
        }
    }

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        //Blockの自動登録
        BLOCK.listAll.forEach {
            it.creativeTab = CREATIVE.BLOCK
            event.registry.register(it)
            RagiLogger.infoDebug("The block ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        //Itemの自動登録
        ITEM.listAll.forEach {
            if (it is ItemFullBottle) it.setCreativeTab(CREATIVE.FULLBOTTLE)
            if (it is ItemMaterial) it.setCreativeTab(CREATIVE.MATERIAL)
            event.registry.register(it)
            RagiLogger.infoDebug("The item ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun registerModel(event: ModelRegistryEvent) {
        //モデルの自動登録
        ITEM.listAll.forEach {
            if (it is ItemBlockBase) {
                if (it.block !is ICustomModel) {
                    ModelManager.setModel(it)
                    RagiLogger.infoDebug("The model for item block ${it.registryName} is registered!")
                }
            } else {
                if (it !is ICustomModel) {
                    ModelManager.setModel(it)
                    RagiLogger.infoDebug("The model for item ${it.registryName} is registered!")
                }
            }
        }
        //それ以外のモデル登録
        ModelRegistry.load()
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun registerColor(event: ColorHandlerEvent.Item) {

        val blockColors = event.blockColors
        val itemColors = event.itemColors

        //Block
        blockColors.registerBlockColorHandler({ state, world, pos, _ ->
            val block = state.block
            if (world !== null && pos !== null && block is IMaterialBlock) block.getColor(world, pos, state).rgb else 0xFFFFFF
        }, *BLOCK.arrayIMaterialBlock)

        //Item Block
        itemColors.registerItemColorHandler({ stack, _ ->
            var color = 0xFFFFFF
            val item = stack.item
            if (item is ItemBlockBase) {
                val block = item.block
                if (block is IMaterialBlock) color = block.getColor(stack).rgb
            }
            color
        }, *ITEM.arrayIMaterialItemBlock)

        //Item
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            var color = 0xFFFFFF
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 0) color = item.getColor(stack).rgb
            color
        }, *ITEM.arrayIMaterialItem)

        //Debug Book
        itemColors.registerItemColorHandler({ _, tintIndex ->
            if (tintIndex == 1) RagiColor.RAGI_RED.rgb else 0xFFFFFF
        }, ITEM.ItemBookDebug)
    }
}