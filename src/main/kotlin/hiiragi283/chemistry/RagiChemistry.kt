package hiiragi283.chemistry

import hiiragi283.core.HiiragiProxy
import hiiragi283.core.RMGuiHandler
import hiiragi283.core.RagiMaterials
import hiiragi283.core.network.HiiragiNetworkManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.network.NetworkRegistry

object RagiChemistry : HiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //イベントを登録
        MinecraftForge.EVENT_BUS.register(RCEventHandler)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {}

    override fun onInit(event: FMLInitializationEvent) {
        //鉱石辞書の登録
        RCBlocks.registerOreDict()
        RCItems.registerOreDict()
        //レシピの登録
        RCBlocks.registerRecipe()
        RCItems.registerRecipe()
        RCRecipes.init()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {}

    override fun onComplete(event: FMLLoadCompleteEvent) {
        //GuiHandlerの登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterials.INSTANCE, RMGuiHandler)
        //パケット送信の登録
        HiiragiNetworkManager.load()
    }

}