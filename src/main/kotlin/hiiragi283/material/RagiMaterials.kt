package hiiragi283.material

import hiiragi283.material.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.awt.Color

@Mod(
    modid = RagiMaterials.MOD_ID,
    name = RagiMaterials.MOD_NAME,
    version = RagiMaterials.VERSION,
    dependencies = RagiMaterials.DEPENDENCIES,
    acceptedMinecraftVersions = RagiMaterials.MC_VERSIONS,
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object RagiMaterials {

    //MOD IDの宣言
    const val MOD_ID = "ragi_materials"

    //Mod名の宣言
    const val MOD_NAME = "Ragi Materials"

    //Modのバージョンの宣言
    const val VERSION = "v0.9.0a"

    //依存関係の宣言
    const val DEPENDENCIES = "required-after:forgelin;after:jei"

    //対応するMCのバージョンの宣言
    const val MC_VERSIONS = "[1.12,1.12.2]"

    //Instanceの宣言
    @Mod.Instance(MOD_ID)
    var INSTANCE: RagiMaterials = this

    //Proxyの宣言
    @SidedProxy(
        clientSide = "hiiragi283.material.proxy.ClientProxy",
        serverSide = "hiiragi283.material.proxy.ServerProxy"
    )
    lateinit var proxy: CommonProxy

    //各種変数の宣言
    val LOGGER: Logger = LogManager.getLogger(MOD_ID)
    val COLOR = Color(255, 0, 31)

    @Mod.EventHandler
    fun onConstruct(event: FMLConstructionEvent) {
        proxy.onConstruct()
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        proxy.onPreInit()
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        proxy.onInit()
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
        proxy.onPostInit()
    }

}