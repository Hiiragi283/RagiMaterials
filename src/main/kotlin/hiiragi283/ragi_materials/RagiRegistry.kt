package hiiragi283.ragi_materials

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

object RagiRegistry {

    //    Block    //

    lateinit var BlockBlazingForge: Block
    lateinit var BlockForgeFurnace: Block
    lateinit var BlockFullBottleStation: Block
    lateinit var BlockIndustrialLabo: Block
    lateinit var BlockLaboratoryTable: Block
    lateinit var BlockOre1: Block
    lateinit var BlockOreDictConv: Block
    lateinit var BlockOreRainbow: Block
    lateinit var BlockSoilAir: Block
    lateinit var BlockSoilCoal: Block
    lateinit var BlockSoilLignite: Block
    lateinit var BlockSoilPeat: Block
    lateinit var BlockStoneMill: Block
    lateinit var BlockTransferEnergy: Block
    lateinit var BlockTransferFluid: Block
    lateinit var BlockTransferGas: Block
    lateinit var BlockTransferHeat: Block

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

    lateinit var ItemBlockBlazingForge: Item
    lateinit var ItemBlockForgeFurnace: Item
    lateinit var ItemBlockFullBottleStation: Item
    lateinit var ItemBlockIndustrialLabo: Item
    lateinit var ItemBlockLaboratoryTable: Item
    lateinit var ItemBlockOre1: Item
    lateinit var ItemBlockOreDictConv: Item
    lateinit var ItemBlockOreRainbow: Item
    lateinit var ItemBlockSoilCoal: Item
    lateinit var ItemBlockSoilLignite: Item
    lateinit var ItemBlockSoilPeat: Item
    lateinit var ItemBlockStoneMill: Item
    lateinit var ItemBlockTransferEnergy: Item
    lateinit var ItemBlockTransferFluid: Item
    lateinit var ItemBlockTransferGas: Item
    lateinit var ItemBlockTransferHeat: Item

    lateinit var ItemBlazingCube: Item
    lateinit var ItemBookDebug: Item
    lateinit var ItemEnderTable: Item
    lateinit var ItemForgeHammer: Item
    lateinit var ItemFullBottle: Item
    lateinit var ItemOreCrushed: Item
    lateinit var ItemOreCrushedVanilla: Item
    lateinit var ItemWaste: Item

    lateinit var ItemBlockMaterial: Item
    lateinit var ItemCrystal: Item
    lateinit var ItemDust: Item
    lateinit var ItemDustTiny: Item
    lateinit var ItemGear: Item
    lateinit var ItemIngot: Item
    lateinit var ItemIngotHot: Item
    lateinit var ItemNugget: Item
    lateinit var ItemPlate: Item
    lateinit var ItemStick: Item

    //    LootTable    //

    val OreRainbow = ResourceLocation(RagiMaterials.MOD_ID, "gameplay/ore_rainbow")

    //    Collection    //

    val setBlocks: MutableSet<Block> = mutableSetOf()
    val setIMaterialBlock: MutableSet<Block> = mutableSetOf()

    val setIMaterialItem: MutableSet<Item> = mutableSetOf()
    val setIMaterialItemBlock: MutableSet<ItemBlock> = mutableSetOf()
    val setItems: MutableSet<Item> = mutableSetOf()

}