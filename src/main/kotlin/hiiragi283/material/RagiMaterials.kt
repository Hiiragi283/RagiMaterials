package hiiragi283.material

import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.fluid.HiiragiFluid
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
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
object RagiMaterials {

    //MOD IDの宣言
    const val MODID = "ragi_materials"

    //各種変数の宣言
    val CALENDAR: Calendar = Calendar.getInstance()
    val COLOR: Color by lazy { Color(255, 0, 31) }
    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")

    @Mod.EventHandler
    fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
        //素材の登録
        MaterialRegistry.init()
        //部品の登録
        PartRegistry.init()
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を登録
        val jsonHandler = RMJSonHandler(event)
        jsonHandler.writeJson()
        jsonHandler.readJson()
        //液体の登録
        HiiragiFluid.register()
        //MaterialPartとの紐づけ
        RMItems.registerMaterialPart()
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        //鉱石辞書の登録
        RMItems.registerOreDict()
        //レシピの登録
        RMItems.registerRecipe()
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
    }

    @Mod.EventHandler
    fun onComplete(event: FMLLoadCompleteEvent) {
        if (RMConfig.printRegisteredMaterials) {
            MaterialRegistry.getMaterials()
                .forEach { LOGGER.debug(it.toJson(false)) }
        }
    }

}