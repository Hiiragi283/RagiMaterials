package hiiragi283.material

import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.fluid.HiiragiFluids
import hiiragi283.material.integration.RMIntegrationCore
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.awt.Color
import java.util.*

@Mod(
    modid = RagiMaterials.MODID,
    name = "RagiMaterials",
    version = Reference.VERSION,
    dependencies = "required-after:forgelin_continuous;after:gregtech;after:jei",
    acceptedMinecraftVersions = "[1.12,1.12.2]",
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object RagiMaterials {

    //MOD IDの宣言
    const val MODID = "ragi_materials"

    //各種変数の宣言
    val CALENDAR: Calendar = Calendar.getInstance()
    val COLOR: Color by lazy { Color(255, 0, 31) }
    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")

    private lateinit var jsonHandler: RMJSonHandler

    init {
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
    fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
        //素材の登録
        MaterialRegistry.init()
        //部品の登録
        ShapeRegistry.init()
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        //他modとの連携のためタイミングをずらす
        jsonHandler = RMJSonHandler(event)
        //液体の登録
        HiiragiFluids.register()
        //連携の登録
        RMIntegrationCore.onPreInit()
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        //configから素材を登録
        jsonHandler.writeJson()
        jsonHandler.readJson()
        //以降の素材登録を停止
        MaterialRegistry.lock()
        //以降の部品登録を停止
        ShapeRegistry.lock()
        //MaterialPartとの紐づけ
        PartRegistry.init()
        //鉱石辞書の登録
        RMItems.registerOreDict()
        //レシピの登録
        RMItems.registerRecipe()
        //連携の登録
        RMIntegrationCore.onInit()
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
        //連携の登録
        RMIntegrationCore.onPostInit()
    }

    @Mod.EventHandler
    fun onComplete(event: FMLLoadCompleteEvent) {
        if (RMConfig.MISC.printMaterials) {
            MaterialRegistry.getMaterials().forEach { LOGGER.info(it.toJson(false)) }
        }
    }

}