package ragi_materials.core

import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.proxy.IProxy
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.awt.Color
import java.io.File

@Mod(
        modid = RagiMaterials.MOD_ID,
        name = RagiMaterials.MOD_NAME,
        version = RagiMaterials.VERSION,
        dependencies = RagiMaterials.DEPENDENCIES,
        acceptedMinecraftVersions = RagiMaterials.MC_VERSIONS,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object RagiMaterials : IProxy {

    //MOD IDの宣言
    const val MOD_ID = "ragi_materials"

    //Mod名の宣言
    const val MOD_NAME = "Ragi Materials"

    //Modのバージョンの宣言
    const val VERSION = "v0.8.1a"

    //依存関係の宣言
    const val DEPENDENCIES = "required-after:forgelin;after:jei"

    //対応するMCのバージョンの宣言
    const val MC_VERSIONS = "[1.12,1.12.2]"

    //Instanceの宣言
    @Mod.Instance(MOD_ID)
    var INSTANCE: RagiMaterials = this

    //Proxyの宣言
    @SidedProxy(clientSide = "ragi_materials.core.proxy.ClientProxy", serverSide = "ragi_materials.core.proxy.ServerProxy")
    lateinit var proxy: CommonProxy

    //各種変数の宣言
    lateinit var CONFIG: File
    val LOGGER: Logger = LogManager.getLogger(MOD_ID)
    val COLOR = Color(255, 0, 31)

    @Mod.EventHandler
    override fun onConstruct(event: FMLConstructionEvent) {
        if (!Loader.isModLoaded("gregtech")) proxy.onConstruct(event)
    }

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent) {
        if (!Loader.isModLoaded("gregtech")) proxy.onPreInit(event)
    }

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent) {
        if (!Loader.isModLoaded("gregtech")) proxy.onInit(event)
    }

    @Mod.EventHandler
    override fun onPostInit(event: FMLPostInitializationEvent) {
        if (!Loader.isModLoaded("gregtech")) proxy.onPostInit(event)
    }

}