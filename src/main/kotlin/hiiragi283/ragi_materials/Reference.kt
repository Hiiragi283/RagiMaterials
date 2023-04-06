package hiiragi283.ragi_materials

import net.minecraft.client.Minecraft
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.client.multiplayer.WorldClient

object Reference {
    //MOD IDの定義
    const val MOD_ID = "ragi_materials"

    //Mod名の定義
    const val MOD_NAME = "Ragi Materials"

    //Modのバージョンの定義
    const val VERSION = "v0.6.1b"

    //依存関係の定義
    const val DEPENDENCIES = "required-before:forgelin"

    //対応するMCのバージョンの定義
    const val MC_VERSIONS = "[1.12,1.12.2]"

    //Client側のProxyの定義
    const val CLIENT_PROXY_CLASS = "hiiragi283.ragi_materials.proxy.ClientProxy"

    //Server側のProxyの定義
    const val SERVER_PROXY_CLASS = "hiiragi283.ragi_materials.proxy.ServerProxy"

    //各種変数の宣言
    val SERVER = Minecraft.getMinecraft().integratedServer?.server

    val WORLD_CLIENT: WorldClient = Minecraft.getMinecraft().world

    val PLAYER_CLIENT: EntityPlayerSP = Minecraft.getMinecraft().player
}