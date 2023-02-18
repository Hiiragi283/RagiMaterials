package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.event.*
import net.minecraftforge.common.MinecraftForge

class ClientProxy : CommonProxy() {

    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(ColorHandler())
        MinecraftForge.EVENT_BUS.register(ItemTooltip())
        MinecraftForge.EVENT_BUS.register(ModelRegistry())
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {}

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}