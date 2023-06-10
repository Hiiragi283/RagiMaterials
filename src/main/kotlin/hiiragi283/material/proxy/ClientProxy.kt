package hiiragi283.material.proxy

import hiiragi283.material.registry.RagiRegistry
import net.minecraftforge.common.MinecraftForge

class ClientProxy : CommonProxy() {

    override fun onConstruct() {
        super.onConstruct()
        //モデルや着色システムの登録
        MinecraftForge.EVENT_BUS.register(RagiRegistry.Client)
        //鉱石辞書を介してtooltipを追加するイベント
        //MinecraftForge.EVENT_BUS.register(TooltipEvent)
    }

}