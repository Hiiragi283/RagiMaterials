package hiiragi283.material

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hiiragi283.material.proxy.HiiragiProxy
import hiiragi283.material.proxy.IHiiragiProxy
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.*
import java.awt.Color
import java.util.*

@Mod(
    modid = RMReference.MOD_ID,
    name = RMReference.MOD_NAME,
    version = RMReference.VERSION,
    dependencies = "after-required:forgelin;after:gregtech;after:jei",
    acceptedMinecraftVersions = "[1.12,1.12.2]",
    modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
    //modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object RagiMaterials : IHiiragiProxy {

    //各種変数の宣言
    internal val CALENDAR: Calendar = Calendar.getInstance()
    internal val COLOR: Color = Color(255, 0, 31)
    internal val GSON: Gson = GsonBuilder().serializeNulls().create()

    @SidedProxy(
        serverSide = "hiiragi283.material.proxy.HiiragiProxy\$Server",
        clientSide = "hiiragi283.material.proxy.HiiragiProxy\$Client"
    )
    lateinit var proxy: HiiragiProxy

    @Mod.Instance
    lateinit var Instance: RagiMaterials

    init {
        checkGregTech()
    }

    private fun checkGregTech() {
        if (Loader.isModLoaded("gregtech")) {
            throw RuntimeException(
                "\n" +
                        "=====================================================\n" +
                        "RagiMaterials detected GregTech in this environment!!\n" +
                        "Remove RagiMaterials or GregTech from mods folder\n" +
                        "====================================================="
            )
        }
    }

    @Mod.EventHandler
    override fun onConstruct(event: FMLConstructionEvent) = proxy.onConstruct(event)

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent) = proxy.onPreInit(event)

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent) = proxy.onInit(event)

    @Mod.EventHandler
    override fun onPostInit(event: FMLPostInitializationEvent) = proxy.onPostInit(event)

    @Mod.EventHandler
    override fun onComplete(event: FMLLoadCompleteEvent) = proxy.onComplete(event)

}