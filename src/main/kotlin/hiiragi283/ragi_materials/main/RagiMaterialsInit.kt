package hiiragi283.ragi_materials.main

import hiiragi283.ragi_materials.main.items.ItemMaterialDust
import hiiragi283.ragi_materials.main.items.ItemMaterialIngot
import hiiragi283.ragi_materials.main.items.ItemMaterialPlate
import net.minecraftforge.fml.common.registry.ForgeRegistries

object RagiMaterialsInit {

    //Blockの定義

    //Itemの定義
    val ItemDust = ItemMaterialDust()
    val ItemIngot = ItemMaterialIngot()
    val ItemPlate = ItemMaterialPlate()

    //Blockを登録するメソッド
    fun registerBlocks() {}

    //Eventを登録するメソッド
    fun registerEvents() {
        //MinecraftForge.EVENT_BUS.register()
    }

    //Itemを登録するメソッド
    fun registerItems() {
        ForgeRegistries.ITEMS.register(ItemDust)
        ForgeRegistries.ITEMS.register(ItemIngot)
        ForgeRegistries.ITEMS.register(ItemPlate)
    }

    //鉱石辞書を登録するメソッド
    fun registerOreDict() {}
}