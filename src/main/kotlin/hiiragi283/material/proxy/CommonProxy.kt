package hiiragi283.material.proxy

import hiiragi283.material.RMEventHandler
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import hiiragi283.material.registry.RagiRegistry
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.*

abstract class CommonProxy : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //Eventの登録
        registerEvent(RMEventHandler)
        //素材の登録
        MaterialRegistry.init()
        //部品の登録
        PartRegistry.init()
    }

    fun registerEvent(event: Any) = MinecraftForge.EVENT_BUS.register(event)

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //オブジェクトの生成
        RagiRegistry.load()
    }

    override fun onInit(event: FMLInitializationEvent) {
        //鉱石辞書の登録
        RagiRegistry.registerOreDict()
        //レシピの登録
        RagiRegistry.registerRecipe()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {}

    override fun onComplete(event: FMLLoadCompleteEvent) {}

}