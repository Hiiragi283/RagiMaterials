package hiiragi283.material.proxy

import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.material.element.ElementRegistry
import hiiragi283.material.registry.RagiRegistry
import net.minecraftforge.common.MinecraftForge

open class CommonProxy : IProxy {

    override fun onConstruct() {
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(RagiRegistry.CommonEvent)
        //元素の登録
        ElementRegistry.load()
        //素材の登録
        MaterialRegistry.load()
    }

    override fun onPreInit() {
        //オブジェクトの生成
        RagiRegistry.load()
    }

    override fun onInit() {
        //鉱石辞書の登録
        RagiRegistry.registerOreDict()
        //レシピの登録
        RagiRegistry.registerRecipe()
    }

    override fun onPostInit() {}

}