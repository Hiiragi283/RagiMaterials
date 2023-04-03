package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.ItemBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.block.*
import hiiragi283.ragi_materials.client.render.color.ColorManager
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
        val BlockSoilCoal = BlockSoilCoal()
        val BlockSoilLignite = BlockSoilLignite()
        val BlockSoilPeat = BlockSoilPeat()
        val BlockStoneMill = BlockStoneMill()

        val BlockSoilAir = BlockSoilAir()

    }

    object CREATIVETAB {

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

    }

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        //Blockの自動登録
        val fields = BLOCK::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val block = field.get(this)
                if (block is Block) {
                    block.creativeTab = CREATIVETAB.BLOCK
                    event.registry.register(block)
                    RagiLogger.infoDebug("The block ${block.registryName} is registered!")
                }
            } catch (e: Exception) {
                RagiLogger.error(e)
            }
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        //Itemの自動登録
        val fields = ITEM::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val item = field.get(this)
                if (item is Item) {
                    if (item is ItemFullBottle) item.setCreativeTab(CREATIVETAB.FULLBOTTLE)
                    if (item is ItemMaterial) item.setCreativeTab(CREATIVETAB.MATERIAL)
                    event.registry.register(item)
                    RagiLogger.infoDebug("The item ${item.registryName} is registered!")
                }
            } catch (e: Exception) {
                RagiLogger.error(e)
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    fun registerModel(event: ModelRegistryEvent) {
        //モデルの自動登録
        val fields = ITEM::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val item = field.get(this)
                if (item is ItemBlockBase) {
                    if (item.block !is ICustomModel) {
                        ModelManager.setModel(item)
                        RagiLogger.infoDebug("The model for item block ${item.registryName} is registered!")
                    }
                } else if (item is Item) {
                    if (item !is ICustomModel) {
                        ModelManager.setModel(item)
                        RagiLogger.infoDebug("The model for item ${item.registryName} is registered!")
                    }
                }
            } catch (e: Exception) {
                RagiLogger.error(e)
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

        //Ore
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            val block = state.block
            val list = OreProperty.listOre1
            val index = block.getMetaFromState(state) % list.size
            if (world !== null && block is BlockOreMaterial) list[index].second.getColor().rgb else 0xFFFFFF
        },
                BLOCK.BlockOre1
        )

        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val list = OreProperty.listOre1
            val index = stack.metadata % list.size
            list[index].second.getColor().rgb
        },
                ITEM.ItemBlockOre1,
                ITEM.ItemOreCrushed
        )

        itemColors.registerItemColorHandler({ stack, tintIndex ->
            val list = OreProperty.listVanilla
            val index = stack.metadata % list.size
            list[index].second.getColor().rgb
        },
                ITEM.ItemOreCrushedVanilla
        )

        //Fuel Soil
        blockColors.registerBlockColorHandler({ state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && pos !== null && block is BlockSoilFuel) {
                val color = block.getMaterialBlock(world, pos, state).color
                val age = block.getAge(state)
                val ageMax = block.getAgeMax()
                ColorManager.mixColor(color to age, RagiColor.WHITE to (ageMax - age)).rgb
            } else 0xFFFFFF
        },
                BLOCK.BlockSoilCoal,
                BLOCK.BlockSoilLignite,
                BLOCK.BlockSoilPeat
        )

        //Material Parts
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            var itemColored: IMaterialItem? = null
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 0) {
                itemColored = item
            } else if (item is ItemBlockBase && item.block is IMaterialItem) {
                itemColored = item.block as IMaterialItem
            }
            itemColored?.getMaterial(stack)?.color?.rgb ?: 0xFFFFFF
        },
                ITEM.ItemBlockMaterial,
                ITEM.ItemBlockSoilCoal,
                ITEM.ItemBlockSoilLignite,
                ITEM.ItemBlockSoilPeat,
                ITEM.ItemCrystal,
                ITEM.ItemDust,
                ITEM.ItemDustTiny,
                ITEM.ItemFullBottle,
                ITEM.ItemGear,
                ITEM.ItemIngot,
                ITEM.ItemIngotHot,
                ITEM.ItemNugget,
                ITEM.ItemPlate,
                ITEM.ItemStick,
                ITEM.ItemWaste
        )

        //Debug Book
        itemColors.registerItemColorHandler({ stack, tintIndex ->
            if (tintIndex == 1) RagiColor.RAGI_RED.rgb else 0xFFFFFF
        },
                ITEM.ItemBookDebug
        )
    }
}