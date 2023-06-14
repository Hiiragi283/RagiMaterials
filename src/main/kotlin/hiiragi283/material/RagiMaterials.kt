package hiiragi283.material

import hiiragi283.material.proxy.CommonProxy
import hiiragi283.material.proxy.IProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.awt.Color

@Mod(
    modid = RagiMaterials.MODID,
    name = "RagiMaterials",
    version = "v0.9.0a",
    dependencies = "required-after:forgelin;after:jei",
    acceptedMinecraftVersions = "[1.12,1.12.2]",
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object RagiMaterials : IProxy {

    //MOD IDの宣言
    const val MODID = "ragi_materials"

    //Instanceの宣言
    @Mod.Instance(MODID)
    var INSTANCE: RagiMaterials = this

    //Proxyの宣言
    @SidedProxy(
        clientSide = "hiiragi283.material.proxy.ClientProxy",
        serverSide = "hiiragi283.material.proxy.ServerProxy"
    )
    lateinit var proxy: CommonProxy

    //各種変数の宣言
    val LOGGER: Logger by lazy { LogManager.getLogger(MODID) }
    val COLOR: Color by lazy { Color(255, 0, 31) }

    @Mod.EventHandler
    override fun onConstruct(event: FMLConstructionEvent): Unit = proxy.onConstruct(event)

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent): Unit = proxy.onPreInit(event)

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent): Unit = proxy.onInit(event)

    @Mod.EventHandler
    override fun onPostInit(event: FMLPostInitializationEvent): Unit = proxy.onPostInit(event)

    @Mod.EventHandler
    override fun onComplete(event: FMLLoadCompleteEvent): Unit = proxy.onComplete(event)

}