package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.base.FluidBase
import hiiragi283.ragi_materials.base.FluidBlockBase
import hiiragi283.ragi_materials.base.ItemBlockBase
import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.items.ItemBookDebug
import hiiragi283.ragi_materials.items.ItemMaterialDust
import hiiragi283.ragi_materials.items.ItemMaterialIngot
import hiiragi283.ragi_materials.items.ItemMaterialPlate
import hiiragi283.ragi_materials.util.RagiColor
import net.minecraft.item.ItemBlock
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.registry.ForgeRegistries


object RagiMaterialsInit {

    //Blockの定義

    //Fluidの定義
    val FluidRagi: Fluid = FluidBase("hiiragi").setColor(RagiColor.RAGI_RED).setTemperature(283)

    //Itemの定義
    val ItemBookDebug = ItemBookDebug()
    val ItemDust = ItemMaterialDust()
    val ItemIngot = ItemMaterialIngot()
    val ItemPlate = ItemMaterialPlate()

    //Blockを登録するメソッド
    fun registerBlocks() {}

    //Eventを登録するメソッド
    fun registerEvents() {
        MinecraftForge.EVENT_BUS.register(RightClickBlock())
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
        //fluidを登録
        FluidRegistry.registerFluid(fluid)
        //fluid入りバケツを登録
        FluidRegistry.addBucketForFluid(fluid)

        /*
          Thanks to defeatedcrow!
          Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/MainMaterialRegister.java
        */

        //液体ブロックを生成・登録・割り当て
        val fluidBlock = FluidBlockBase(fluid)
        ForgeRegistries.BLOCKS.register(fluidBlock)
        fluid.block = fluidBlock
    }
}