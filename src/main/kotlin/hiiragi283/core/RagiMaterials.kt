package hiiragi283.core

import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.shape.ShapeRegistry
import hiiragi283.chemistry.RCBlocks
import hiiragi283.chemistry.RCEventHandler
import hiiragi283.chemistry.RCItems
import hiiragi283.material.RMBlocks
import hiiragi283.material.RMEventHandler
import hiiragi283.material.RMItems
import hiiragi283.material.RMReference
import hiiragi283.core.config.RMConfig
import hiiragi283.core.config.RMJSonHandler
import hiiragi283.material.RMFluids
import hiiragi283.integration.RMIntegrationCore
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.network.NetworkRegistry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.awt.Color
import java.util.*

@Mod(
    modid = RMReference.MOD_ID,
    name = RMReference.MOD_NAME,
    version = RMReference.VERSION,
    dependencies = "required-after:forgelin_continuous;after:gregtech;after:jei",
    acceptedMinecraftVersions = "[1.12,1.12.2]",
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object RagiMaterials {

    //各種変数の宣言
    val CALENDAR: Calendar = Calendar.getInstance()
    val COLOR: Color by lazy { Color(255, 0, 31) }
    val LOGGER: Logger = LogManager.getLogger(RMReference.MOD_NAME)

    //Instanceの宣言
    @Mod.Instance(RMReference.MOD_ID)
    var INSTANCE: RagiMaterials = this

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
        //イベントを登録
        MinecraftForge.EVENT_BUS.register(RCEventHandler)
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を取得
        RMJSonHandler(event).run {
            this.writeJson()
            this.readJson()
        }
        //連携の登録
        RMIntegrationCore.INSTANCE.onPreInit()
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        //素材レジストリの初期化
        MaterialRegistry.init()
        //形状レジストリの初期化
        ShapeRegistry.init()
        //部品レジストリの初期化
        PartRegistry.init()
        //鉱石辞書の登録
        RMBlocks.registerOreDict()
        RCBlocks.registerOreDict()
        RMItems.registerOreDict()
        RCItems.registerOreDict()
        //レシピの登録
        RMBlocks.registerRecipe()
        RCBlocks.registerRecipe()
        RMItems.registerRecipe()
        RCItems.registerRecipe()
        //連携の登録
        RMIntegrationCore.INSTANCE.onInit()
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
        //液体の登録
        RMFluids.register()
        //連携の登録
        RMIntegrationCore.INSTANCE.onPostInit()
    }

    @Mod.EventHandler
    fun onComplete(event: FMLLoadCompleteEvent) {
        //GuiHandlerの登録
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, RMGuiHandler)
        //MaterialRegistryからログに出力
        if (RMConfig.MATERIAL.printMaterials) {
            MaterialRegistry.getMaterials().forEach { LOGGER.info(it.toJson(false)) }
        }
        //連携の登録
        RMIntegrationCore.INSTANCE.onComplete()
    }

}