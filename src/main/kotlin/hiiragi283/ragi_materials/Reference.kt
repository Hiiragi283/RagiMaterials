package hiiragi283.ragi_materials

import net.minecraft.client.Minecraft
import net.minecraft.server.MinecraftServer

object Reference {
    //MOD IDの定義
    const val MOD_ID = "ragi_materials"

    //Mod名の定義
    const val MOD_NAME = "Ragi Materials"

    //Modのバージョンの定義
    const val VERSION = "v0.0.1"

    //依存関係の定義
    const val DEPENDENCIES = "required-after:forgelin;required-after:ragi_materials"

    //対応するMCのバージョンの定義
    const val MC_VERSIONS = "[1.12,1.12.2]"

    //Client側のProxyの定義
    const val CLIENT_PROXY_CLASS = "hiiragi283.ragi_materials.proxy.ClientProxy"

    //Server側のProxyの定義
    const val SERVER_PROXY_CLASS = "hiiragi283.ragi_materials.proxy.ServerProxy"

    //Serverの定義 (コマンド実行用)
    val SERVER: MinecraftServer? = Minecraft.getMinecraft().integratedServer
}