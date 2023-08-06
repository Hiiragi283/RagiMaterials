package hiiragi283.material

import hiiragi283.core.HiiragiProxy
import hiiragi283.core.config.RMConfig
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.*

object RMCore : HiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        if (RMConfig.MODULE.disableMaterialModule) return
        //イベントを登録
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        if (RMConfig.MODULE.disableMaterialModule) return
    }

    override fun onInit(event: FMLInitializationEvent) {
        if (RMConfig.MODULE.disableMaterialModule) return
        //鉱石辞書の登録
        RMBlocks.registerOreDict()
        RMItems.registerOreDict()
        //レシピの登録
        RMBlocks.registerRecipe()
        RMItems.registerRecipe()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        if (RMConfig.MODULE.disableMaterialModule) return
        //液体の登録
        RMFluids.register()
    }

    override fun onComplete(event: FMLLoadCompleteEvent) {
        if (RMConfig.MODULE.disableMaterialModule) return
    }

}