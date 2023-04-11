package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.item.ItemFullBottle
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.OreProperty
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object CommonRegistryEvent {

    fun register() {
        registerFluid()
        registerOreDict()
    }

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        //Blockの自動登録
        RagiRegistry.setBlocks.forEach {
            it.creativeTab = RagiRegistry.TabBlock
            event.registry.register(it)
            RagiLogger.infoDebug("The block ${it.registryName} is registered!")
        }
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        //Itemの自動登録
        RagiRegistry.setItems.forEach {
            if (it is ItemFullBottle) it.setCreativeTab(RagiRegistry.TabFullBottle)
            if (it is ItemMaterial) it.setCreativeTab(RagiRegistry.TabMaterial)
            event.registry.register(it)
            RagiLogger.infoDebug("The item ${it.registryName} is registered!")
        }
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
}