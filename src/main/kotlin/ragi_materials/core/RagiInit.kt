package ragi_materials.core

import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.oredict.OreDictionary
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.item.*
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.proxy.IProxy
import ragi_materials.core.recipe.CraftingRegistry
import ragi_materials.core.recipe.SmeltingRegistry
import ragi_materials.core.util.RagiColor
import ragi_materials.core.util.getPart
import ragi_materials.main.block.*
import ragi_materials.main.item.ItemEnderTable
import ragi_materials.main.item.ItemForgeHammer
import ragi_materials.main.item.ItemFullBottle
import ragi_materials.main.item.ItemWaste
import ragi_materials.main.tile.TileTransferEnergy
import ragi_materials.main.tile.TileTransferFluid
import ragi_materials.metallurgy.block.*

object RagiInit : IProxy {

    //    onConstruct    //

    override fun onConstruct(event: FMLConstructionEvent) {
        MaterialRegistry.registerMaterials()
    }

    //    onPreInit    //

    override fun onPreInit(event: FMLPreInitializationEvent) {
        registerBlocks()
        registerItems()
        registerCreativeTabs()
        registerFluid()
    }

    private fun registerBlocks() {
        //Experimental Feature
        if (RagiConfig.module.enableExperimental) {
            RagiRegistry.BlockSoilAir = BlockSoilAir
            RagiRegistry.BlockTransferEnergy = BlockTransferBase("energy", TileTransferEnergy::class.java, RagiColor.YELLOW)
        }
        //Magical Feature
        if (RagiConfig.module.enableMagic) {

        }
        //Main Feature
        if (RagiConfig.module.enableMain) {
            RagiRegistry.BlockFullBottleStation = BlockFullBottleStation
            RagiRegistry.BlockIndustrialLabo = BlockIndustrialLabo
            RagiRegistry.BlockLaboratoryTable = BlockLaboTable
            RagiRegistry.BlockOreDictConv = BlockOreDictConv
            RagiRegistry.BlockSoilCoal = BlockSoilCoal
            RagiRegistry.BlockSoilLignite = BlockSoilLignite
            RagiRegistry.BlockSoilPeat = BlockSoilPeat
            RagiRegistry.BlockStoneMill = BlockStoneMill
            RagiRegistry.BlockTransferFluid = BlockTransferBase("fluid", TileTransferFluid::class.java, RagiColor.AQUA)
        }
        //Metallurgic Feature
        if (RagiConfig.module.enableMetallurgy) {
            RagiRegistry.BlockBlastFurnaceCore = BlockBlastFurnaceCore
            RagiRegistry.BlockBlastFurnaceInterface = BlockBlastFurnaceInterface
            RagiRegistry.BlockBlazingForge = BlockBlazingForge
            RagiRegistry.BlockBloom = BlockBloom
            RagiRegistry.BlockBloomery = BlockBloomery
            RagiRegistry.BlockForgeFurnace = BlockForgeFurnace
            RagiRegistry.BlockOreRainbow = BlockOreRainbow("ore_rainbow")
        }
    }

    private fun registerItems() {
        //Core Feature
        RagiRegistry.ItemBlockMaterial = ItemMaterialBlock
        RagiRegistry.ItemBookDebug = ItemBookDebug
        RagiRegistry.ItemCrystal = ItemMaterialCrystal
        RagiRegistry.ItemDust = ItemMaterial(PartRegistry.DUST)
        RagiRegistry.ItemDustTiny = ItemMaterial(PartRegistry.DUST_TINY)
        RagiRegistry.ItemForgeHammer = ItemForgeHammer
        RagiRegistry.ItemMaterialMiner = ItemMaterialMiner
        RagiRegistry.ItemGear = ItemMaterial(PartRegistry.GEAR)
        RagiRegistry.ItemIngot = ItemMaterial(PartRegistry.INGOT)
        RagiRegistry.ItemNugget = ItemMaterial(PartRegistry.NUGGET)
        RagiRegistry.ItemOre = ItemMaterialOre(PartRegistry.ORE)
        RagiRegistry.ItemOreCrushed = ItemMaterialOre(PartRegistry.ORE_CRUSHED)
        RagiRegistry.ItemPlate = ItemMaterial(PartRegistry.PLATE)
        RagiRegistry.ItemStick = ItemMaterial(PartRegistry.STICK)

        //Experimental Feature
        if (RagiConfig.module.enableExperimental) {

        }
        //Magical Feature
        if (RagiConfig.module.enableMagic) {

        }
        //Main Feature
        if (RagiConfig.module.enableMain) {
            RagiRegistry.ItemBlazingCube = ItemBase(RagiMaterials.MOD_ID, "blazing_cube", 0)
            RagiRegistry.ItemEnderTable = ItemEnderTable
            RagiRegistry.ItemFullBottle = ItemFullBottle
            RagiRegistry.ItemWaste = ItemWaste
        }
        //Metallurgic Feature
        if (RagiConfig.module.enableMetallurgy) {
            //RagiRegistry.ItemOreCrushed = ItemOreCrushed()
            //RagiRegistry.ItemOreCrushedVanilla = ItemOreCrushedVanilla()
        }
    }

    private fun registerCreativeTabs() {

        RagiRegistry.TabMaterial = object : CreativeTabs("${RagiMaterials.MOD_ID}.materials") {
            override fun createIcon() = ItemStack(RagiRegistry.ItemIngot, 1, 26)
        }

        if (RagiConfig.module.enableMain) {

            RagiRegistry.TabBlock = object : CreativeTabs("${RagiMaterials.MOD_ID}.blocks") {
                override fun createIcon() = ItemStack(RagiRegistry.BlockForgeFurnace)
            }

            RagiRegistry.TabFullBottle = object : CreativeTabs("${RagiMaterials.MOD_ID}.fullbottles") {
                override fun createIcon() = ItemStack(RagiRegistry.ItemFullBottle)
            }
        }
    }

    private fun registerFluid() {
        //Fluidの登録
        for (material in MaterialRegistry.getMaterials()) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.types) FluidBase(material)
        }
    }

    //    onInit    //

    override fun onInit(event: FMLInitializationEvent) {
        registerOreDict()
        CraftingRegistry.addRecipes()
        SmeltingRegistry.addSmelting()
    }

    private fun registerOreDict() {
        //鉱石辞書の登録
        for (material in MaterialRegistry.getMaterials()) {
            for (part in material.listValidParts) {
                val stack = getPart(part, material)
                OreDictionary.registerOre(part.prefixOre + material.getOreDict(), stack)
                material.oredictAlt?.let { OreDictionary.registerOre(part.prefixOre + it, stack) }
            }
        }

        //Others
        OreDictionary.registerOre("charcoal", getPart(PartRegistry.CRYSTAL, MaterialRegistry.CHARCOAL))
        OreDictionary.registerOre("dustGunpowder", ItemStack(Items.GUNPOWDER))
        OreDictionary.registerOre("dustSugar", ItemStack(Items.SUGAR))
        OreDictionary.registerOre("fuelCoke", getPart(PartRegistry.CRYSTAL, MaterialRegistry.COKE))
        OreDictionary.registerOre("gearStone", getPart(PartRegistry.GEAR, MaterialRegistry.STONE))
        OreDictionary.registerOre("gearWood", getPart(PartRegistry.GEAR, MaterialRegistry.WOOD))
        OreDictionary.registerOre("gemCharcoal", ItemStack(Items.COAL))
        OreDictionary.registerOre("stickStone", getPart(PartRegistry.STICK, MaterialRegistry.STONE))
    }

    //    onPostInit    //

    override fun onPostInit(event: FMLPostInitializationEvent) {
        for (element in MaterialRegistry.getElements()) {
            RagiMaterials.LOGGER.debug("<element:${element.name}>")
        }
        for (material in MaterialRegistry.getMaterials()) {
            RagiMaterials.LOGGER.debug("Index: ${material.index}, <material:${material.name}>")
        }
        for (enchant in ForgeRegistries.ENCHANTMENTS.valuesCollection) {
            RagiMaterials.LOGGER.debug("Enchantment: <enchantment:${enchant.name}>")
        }
        CraftingRegistry.removeRecipes()
    }

    //    FluidBase    //

    private val location = ResourceLocation("minecraft:blocks/concrete_powder_white")

    class FluidBase(val material: RagiMaterial) : Fluid(material.name, location, location) {

        init {
            setColor(material.color)
            //MaterialTypesがGASの場合
            if (material.type.match(TypeRegistry.GAS)) {
                isGaseous = true
                density *= -1
            }
            FluidRegistry.registerFluid(this)
            FluidRegistry.addBucketForFluid(this)
        }

        override fun getUnlocalizedName(): String = "material.${this.unlocalizedName}"

        override fun getLocalizedName(stack: FluidStack?): String {
            var localized = ""
            if (stack !== null) {
                if (material.type != TypeRegistry.LIQUID && material.type != TypeRegistry.GAS) {
                    localized += "${I18n.format("fluid.molten")} "
                }
            }
            localized += I18n.format(getUnlocalizedName())
            return localized
        }
    }
}