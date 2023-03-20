package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.block.BlockOreMaterial
import hiiragi283.ragi_materials.block.BlockSoilFuel
import hiiragi283.ragi_materials.client.render.color.RagiColor
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.client.render.model.ModelRegistry
import hiiragi283.ragi_materials.client.render.model.RagiModelManager
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiCreativeTabs
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.item.*
import hiiragi283.ragi_materials.material.RagiMaterial
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
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class RagiRegistry {

    companion object {
        fun registerFluid() {
            //Fluidの登録
            for (material in RagiMaterial.list) {
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
    @SideOnly(Side.CLIENT)
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
                    if (item !is ItemMaterial && item !is ItemBookDebug && item !is ItemOreCrushed) {
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
    @SideOnly(Side.CLIENT)
    fun registerColor(event: ColorHandlerEvent.Item) {

        val blockColors = event.blockColors
        val itemColors = event.itemColors

        //Ore Block
        blockColors.registerBlockColorHandler(IBlockColor { state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && block is BlockOreMaterial) block.list[block.getMetaFromState(state)].rgb else 0xFFFFFF
        },
                RagiBlock.BlockOre1
        )

        itemColors.registerItemColorHandler(IItemColor {stack, tintIndex ->
            var color = 0xFFFFFF
            val item = stack.item
            if (item is ItemBlock) {
                val block = item.block
                if (block is BlockOreMaterial) {
                    color = block.list[stack.metadata % block.list.size].rgb
                }
            }
            color
        },
                RagiItem.ItemBlockOre1
        )

        itemColors.registerItemColorHandler(IItemColor {stack, tintIndex ->
            val block = RagiBlock.BlockOre1
            block.list[stack.metadata % block.list.size].rgb
        },
                RagiItem.ItemOreCrushed
        )

        //Fuel Soil
        blockColors.registerBlockColorHandler(IBlockColor { state, world, pos, tintIndex ->
            val block = state.block
            if (world !== null && pos !== null && block is BlockSoilFuel) {
                val color = block.getMaterialBlock(world, pos, state).color
                val age = block.getAge(state)
                val ageMax = block.getAgeMax()
                RagiColorManager.mixColor(color to age, RagiColor.WHITE to (ageMax - age)).rgb
            } else 0xFFFFFF
        },
                RagiBlock.BlockSoilCoal,
                RagiBlock.BlockSoilLignite,
                RagiBlock.BlockSoilPeat
        )

        //Material Parts
        itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            var itemColored: IMaterialItem? = null
            val item = stack.item
            if (item is IMaterialItem && tintIndex == 0) {
                itemColored = item
            } else if (item is ItemBlock && item.block is IMaterialItem) {
                itemColored = item.block as IMaterialItem
            }
            itemColored?.getMaterial(stack)?.color?.rgb ?: 0xFFFFFF

        },
                RagiItem.ItemBlockMaterial,
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
                RagiItem.ItemPlate,
                RagiItem.ItemStick,
                RagiItem.ItemWaste
        )
    }
}