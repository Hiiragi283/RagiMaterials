package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.block.BlockContainerBase
import hiiragi283.ragi_materials.item.ItemBase
import hiiragi283.ragi_materials.item.ItemBlockBase
import hiiragi283.ragi_materials.tile.TileBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.color.RagiColor
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.tile.TileTransferEnergy
import hiiragi283.ragi_materials.tile.TileTransferFluid
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

object RagiRegistry {

    //    Block    //

    val BlockBlazingForge = BlockBlazingForge()
    val BlockForgeFurnace = BlockForgeFurnace()
    val BlockFullBottleStation = BlockFullBottleStation()
    val BlockIndustrialLabo = BlockIndustrialLabo()
    val BlockLaboratoryTable = BlockLaboTable()
    val BlockOre1 = BlockOreMaterial("ore_block")
    val BlockOreDictConv = BlockOreDictConv()
    val BlockOreRainbow = BlockOreRainbow("ore_rainbow")
    val BlockSoilCoal = BlockSoilCoal()
    val BlockSoilLignite = BlockSoilLignite()
    val BlockSoilPeat = BlockSoilPeat()
    val BlockStoneMill = BlockStoneMill()
    val BlockTransferEnergy = BlockTransferBase("energy", TileTransferEnergy::class.java, RagiColor.YELLOW)
    val BlockTransferFluid = BlockTransferBase("fluid", TileTransferFluid::class.java, RagiColor.AQUA)

    val BlockSoilAir = BlockSoilAir()

    //    Creative Tab    //

    val TabBlock = object : CreativeTabs("${RagiMaterials.MOD_ID}.blocks") {
        override fun createIcon() = ItemStack(ItemBlockForgeFurnace)
    }
    val TabFullBottle = object : CreativeTabs("${RagiMaterials.MOD_ID}.fullbottles") {
        override fun createIcon() = ItemStack(ItemFullBottle)
    }
    val TabMaterial = object : CreativeTabs("${RagiMaterials.MOD_ID}.materials") {
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
    val ItemBlockOreRainbow = ItemBlockBase(BlockOreRainbow)
    val ItemBlockSoilCoal = ItemBlockBase(BlockSoilCoal)
    val ItemBlockSoilLignite = ItemBlockBase(BlockSoilLignite)
    val ItemBlockSoilPeat = ItemBlockBase(BlockSoilPeat)
    val ItemBlockStoneMill = ItemBlockBase(BlockStoneMill)
    val ItemBlockTransferEnergy = ItemBlockBase(BlockTransferEnergy)
    val ItemBlockTransferFluid = ItemBlockBase(BlockTransferFluid)

    val ItemBlazingCube = ItemBase(RagiMaterials.MOD_ID, "blazing_cube", 0)
    val ItemBookDebug = ItemBookDebug()
    val ItemEnderTable = ItemEnderTable()
    val ItemForgeHammer = ItemForgeHammer()
    val ItemFullBottle = ItemFullBottle()
    val ItemOreCrushed = ItemOreCrushed()
    val ItemOreCrushedVanilla = ItemOreCrushedVanilla()
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

    val OreRainbow = ResourceLocation(RagiMaterials.MOD_ID, "gameplay/ore_rainbow")

    //    Collection    //

    val setBlocks: MutableSet<Block> = mutableSetOf()
    val setItems: MutableSet<Item> = mutableSetOf()

    val setBlockContainers: MutableSet<BlockContainerBase<*>> = mutableSetOf()

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

        setBlocks.forEach {
            if (it is BlockContainerBase<*>) setBlockContainers.add(it)
            if (it is IMaterialBlock) setIMaterialBlock.add(it)
        }
        setItems.forEach {
            if (it is ItemBlock) {
                val block = it.block
                if (block is IMaterialBlock) setIMaterialItemBlock.add(it)
            } else if (it is IMaterialItem) setIMaterialItem.add(it)
        }
    }

    fun printTiles() {
        setBlockContainers.forEach {
            val tile = it.tile.newInstance()
            if (tile is TileBase) RagiLogger.infoDebug("TileEntity: <${it.tile.name}:${tile.type}>")
        }
    }
}