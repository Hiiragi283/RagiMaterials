package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.block.BlockOreMaterial
import hiiragi283.ragi_materials.block.BlockSoilFuel
import hiiragi283.ragi_materials.block.IMaterialBlock
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.client.render.model.ModelRegistry
import hiiragi283.ragi_materials.client.render.model.RagiModelManager
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiCreativeTabs
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.item.ItemBookDebug
import hiiragi283.ragi_materials.item.ItemFullBottle
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.block.Block
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class RagiRegistry {

    init {
        registerFluid()
    }

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        //Blockの自動登録
        val fields = RagiBlock::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val block = field.get(this)
                if (block is Block) {
                    block.setCreativeTab(RagiCreativeTabs.BLOCK)
                    event.registry.register(block)
                    RagiLogger.infoDebug("The block ${block.registryName} is registered!")
                }
            } catch(e: Exception) {
                RagiLogger.error(e)
            }
        }
    }

    private fun registerFluid() {
        //Fluidの登録
        for (material in MaterialRegistry.list) {
            //typeがINTERNALでない，かつmaterialのtypeがfluidの場合
            if (material.type != TypeRegistry.INTERNAL && EnumMaterialType.LIQUID in material.type.list) {
                //Fluidの登録
                val fluid = FluidBase(material.name)
                fluid.setColor(material.color)
                //MaterialTypesがGASの場合
                if (material.type == TypeRegistry.GAS) {
                    fluid.isGaseous = true
                    fluid.density = fluid.density * -1
                }
                FluidRegistry.registerFluid(fluid)
                //fluid入りバケツを登録
                FluidRegistry.addBucketForFluid(fluid)
            }
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        //Itemの自動登録
        val fields = RagiItem::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val item = field.get(this)
                if (item is Item) {
                    if (item is ItemFullBottle) item.setCreativeTab(RagiCreativeTabs.FULLBOTTLE)
                    if (item is ItemMaterial) item.setCreativeTab(RagiCreativeTabs.MATERIAL)
                    event.registry.register(item)
                    RagiLogger.infoDebug("The item ${item.registryName} is registered!")
                }
            } catch(e: Exception) {
                RagiLogger.error(e)
            }
        }
    }

    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent) {
        //モデルの自動登録
        val fields = RagiItem::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            RagiLogger.infoDebug(field.name)
            try {
                val item = field.get(this)
                if (item is ItemBlock) {
                    if (item.block !is BlockOreMaterial) {
                        RagiModelManager.setModel(item)
                        RagiLogger.infoDebug("The model for item block ${item.registryName} is registered!")
                    }
                } else if (item is Item) {
                    if (item !is ItemMaterial && item !is ItemBookDebug) {
                        RagiModelManager.setModel(item)
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
    fun registerColor(event: ColorHandlerEvent.Item) {

        val blockColors = event.blockColors
        val itemColors = event.itemColors

        blockColors.registerBlockColorHandler(IBlockColor { state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && block is IMaterialBlock) block.getMaterialBlock(world, pos!!, state).color.rgb else 0xFFFFFF
        },
                RagiBlock.BlockOre1
        )

        blockColors.registerBlockColorHandler(IBlockColor { state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && pos !== null && block is BlockSoilFuel) {
                val color = block.getMaterialBlock(world, pos, state).color
                val age = block.getAge(state)
                val ageMax = block.getAgeMax(state)
                RagiColorManager.mixColor(color to age, RagiColorManager.WHITE to (ageMax - age)).rgb
            } else 0xFFFFFF
        },
                RagiBlock.BlockSoilCoal,
                RagiBlock.BlockSoilLignite,
                RagiBlock.BlockSoilPeat
        )

        itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            var color: IMaterialItem? = null
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 0) {
                color = item
            } else if (item is ItemBlock && item.block is IMaterialItem) {
                color = item.block as IMaterialItem
            }
            color?.getMaterial(stack)?.color?.rgb ?: 0xFFFFFF

        },
                RagiItem.ItemBlockMaterial,
                RagiItem.ItemBlockOre1,
                RagiItem.ItemBlockSoilCoal,
                RagiItem.ItemBlockSoilLignite,
                RagiItem.ItemBlockSoilPeat,
                RagiItem.ItemCrystal,
                RagiItem.ItemDust,
                RagiItem.ItemDustTiny,
                RagiItem.ItemFullBottle,
                RagiItem.ItemGear,
                RagiItem.ItemIngot,
                RagiItem.ItemIngotHot,
                RagiItem.ItemNugget,
                RagiItem.ItemOre,
                RagiItem.ItemOreEnd,
                RagiItem.ItemOreNether,
                RagiItem.ItemPlate,
                RagiItem.ItemStick,
                RagiItem.ItemWaste
        )

        /*itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 1) item.getMaterial(stack).color.rgb else 0xFFFFFF
        },
                *RagiInit.ItemsAxe,
                *RagiInit.ItemsHammer,
                *RagiInit.ItemsPickaxe,
                *RagiInit.ItemsSpade,
                *RagiInit.ItemsSword
        )*/

    }

}