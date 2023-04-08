package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.model.ICustomModel
import hiiragi283.ragi_materials.client.model.ModelManager
import hiiragi283.ragi_materials.client.model.ModelRegistry
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RagiRegistry {

    //    Block    //

    val BlockBlazingForge = BlockBlazingForge()
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockFullBottleStation = BlockFullBottleStation()
    val BlockIndustrialLabo = BlockIndustrialLabo()
    val BlockLaboratoryTable = BlockLaboTable()
    val BlockOre1 = BlockOreMaterial("ore_block")
    val BlockOreDictConv = BlockOreDictConv()
    val BlockOreDictConvNew = BlockOreDictConvNew()
    val BlockOreRainbow = BlockOreRainbow("ore_rainbow")
    val BlockQuartzAntenna = BlockQuartzAntenna()
    val BlockSoilCoal = BlockSoilCoal()
    val BlockSoilLignite = BlockSoilLignite()
    val BlockSoilPeat = BlockSoilPeat()
    val BlockStoneMill = BlockStoneMill()

    val BlockSoilAir = BlockSoilAir()

    //    Creative Tab    //

    val TabBlock = object : CreativeTabs("${Reference.MOD_ID}.blocks") {
        override fun createIcon() = ItemStack(ItemBlockForgeFurnace)
    }
    val TabFullBottle = object : CreativeTabs("${Reference.MOD_ID}.fullbottles") {
        override fun createIcon() = ItemStack(ItemFullBottle)
    }
    val TabMaterial = object : CreativeTabs("${Reference.MOD_ID}.materials") {
        override fun createIcon() = ItemStack(ItemIngot, 1, 26)
    }

    //    Item    //

    val ItemBlockBlazingForge = ItemBlockBase(BlockBlazingForge)
    val ItemBlockForgeFurnace = ItemBlockBase(BlockForgeFurnace)
    val ItemBlockFullBottleStation = ItemBlockBase(BlockFullBottleStation)
    val ItemBlockIndustrialLabo = ItemBlockBase(BlockIndustrialLabo)
    val ItemBlockLaboratoryTable = ItemBlockBase(BlockLaboratoryTable)
    val ItemBlockOre1 = ItemBlockBase(BlockOre1, OreProperty.mapOre1.size - 1)
    val ItemBlockOreDictConv = ItemBlockBase(BlockOreDictConv)
    val ItemBlockOreDictConvNew = ItemBlockBase(BlockOreDictConvNew)
    val ItemBlockOreRainbow = ItemBlockBase(BlockOreRainbow)
    val ItemBlockQuartzAntenna = ItemBlockBase(BlockQuartzAntenna)
    val ItemBlockSoilCoal = ItemBlockBase(BlockSoilCoal)
    val ItemBlockSoilLignite = ItemBlockBase(BlockSoilLignite)
    val ItemBlockSoilPeat = ItemBlockBase(BlockSoilPeat)
    val ItemBlockStoneMill = ItemBlockBase(BlockStoneMill)

    val ItemBlazingCube = ItemBase(Reference.MOD_ID, "blazing_cube", 0)
    val ItemBookDebug = ItemBookDebug()
    val ItemEnderTable = ItemEnderTable()
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

    //    LootTable    //

    val OreRainbow = ResourceLocation(Reference.MOD_ID, "gameplay/ore_rainbow")

    //    NetWork    //

    val RagiNetworkWrapper: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID)

    //    Collection    //

    val setBlocks: MutableSet<Block> = mutableSetOf()
    val setItems: MutableSet<Item> = mutableSetOf()

    val setIMaterialBlock: MutableSet<Block> = mutableSetOf()
    val setIMaterialItem: MutableSet<Item> = mutableSetOf()
    val setIMaterialItemBlock: MutableSet<ItemBlock> = mutableSetOf()

    init {
        //collectionへの自動登録
        val fields = this::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val obj = field.get(this)
                if (obj is Block) setBlocks.add(obj)
                else if (obj is Item) setItems.add(obj)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        setBlocks.forEach { if (it is IMaterialBlock) setIMaterialBlock.add(it) }
        setItems.forEach {
            if (it is ItemBlock) {
                val block = it.block
                if (block is IMaterialBlock) setIMaterialItemBlock.add(it)
            } else if (it is IMaterialItem) setIMaterialItem.add(it)
        }

    }

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        //Blockの自動登録
        setBlocks.forEach {
            it.creativeTab = TabBlock
            event.registry.register(it)
            RagiLogger.infoDebug("The block ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        //Itemの自動登録
        setItems.forEach {
            if (it is ItemFullBottle) it.setCreativeTab(TabFullBottle)
            if (it is ItemMaterial) it.setCreativeTab(TabMaterial)
            event.registry.register(it)
            RagiLogger.infoDebug("The item ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun registerModel(event: ModelRegistryEvent) {
        //モデルの自動登録
        setItems.forEach {
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
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && pos !== null && block is IMaterialBlock) block.getColor(world, pos, state, tintIndex).rgb else 0xFFFFFF
        }, *setIMaterialBlock.toTypedArray())

        //Item Block
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val item = stack.item as ItemBlock
            val block = item.block
            if (block is IMaterialBlock) block.getColor(stack, tintIndex).rgb else 0xFFFFFF
        }, *setIMaterialItemBlock.toTypedArray())

        //Item
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val item = stack.item
            if (item is IMaterialItem) item.getColor(stack, tintIndex).rgb else 0xFFFFFF
        }, *setIMaterialItem.toTypedArray())

    }
}