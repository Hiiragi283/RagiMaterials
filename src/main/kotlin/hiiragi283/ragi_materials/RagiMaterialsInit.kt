package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.items.ItemBookDebug
import hiiragi283.ragi_materials.items.ItemMaterialDust
import hiiragi283.ragi_materials.items.ItemMaterialIngot
import hiiragi283.ragi_materials.items.ItemMaterialPlate
import hiiragi283.ragi_materials.util.RagiColor
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiMaterialsInit {

    //Blockの定義

    //Fluidの定義
    var FluidRagi: Fluid = FluidBase("hiiragi").setColor(RagiColor.RAGI_RED).setTemperature(283)


    //Itemの定義
    val ItemBookDebug = ItemBookDebug()
    val ItemDust = ItemMaterialDust()
    val ItemIngot = ItemMaterialIngot()
    val ItemPlate = ItemMaterialPlate()

    //Blockを登録するメソッド
    fun registerBlocks() {}

    //Eventを登録するメソッド
    fun registerEvents() {
        //MinecraftForge.EVENT_BUS.register()
    }

    //Fluidを登録するメソッド
    fun registerFluids() {
        registerFluid(FluidRagi)
    }

    //Itemを登録するメソッド
    fun registerItems() {
        ForgeRegistries.ITEMS.register(ItemBookDebug)
        ForgeRegistries.ITEMS.register(ItemDust)
        ForgeRegistries.ITEMS.register(ItemIngot)
        ForgeRegistries.ITEMS.register(ItemPlate)
    }

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {}

    //FluidとBucketを登録するメソッド
    private fun registerFluid(fluid: Fluid) {
        FluidRegistry.addBucketForFluid(fluid)
        FluidRegistry.registerFluid(fluid)
    }
}