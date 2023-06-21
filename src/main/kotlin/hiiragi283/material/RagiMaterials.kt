package hiiragi283.material

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.awt.Color
import java.util.*

@Mod(
    modid = RagiMaterials.MODID,
    name = "RagiMaterials",
    version = "1.0.0-pre2",
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

    //各種変数の宣言
    val CALENDAR: Calendar = Calendar.getInstance()
    val COLOR: Color by lazy { Color(255, 0, 31) }
    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")

    @Mod.EventHandler
    override fun onConstruct(event: FMLConstructionEvent): Unit = CommonProxy.onConstruct(event)

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent): Unit = CommonProxy.onPreInit(event)

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent): Unit = CommonProxy.onInit(event)

    @Mod.EventHandler
    override fun onPostInit(event: FMLPostInitializationEvent): Unit = CommonProxy.onPostInit(event)

    @Mod.EventHandler
    override fun onComplete(event: FMLLoadCompleteEvent): Unit = CommonProxy.onComplete(event)

}