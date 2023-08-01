package hiiragi283.material

import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.fluid.HiiragiFluids
import hiiragi283.material.integration.RMIntegrationCore
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
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
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を取得
        RMJSonHandler(event).run {
            this.writeJson()
            this.readJson()
        }
        //連携の登録
        RMIntegrationCore.onPreInit()
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
        RMItems.registerOreDict()
        //レシピの登録
        RMBlocks.registerRecipe()
        RMItems.registerRecipe()
        //連携の登録
        RMIntegrationCore.onInit()
    }

    @Mod.EventHandler
    fun onPostInit(event: FMLPostInitializationEvent) {
        //液体の登録
        HiiragiFluids.register()
        //連携の登録
        RMIntegrationCore.onPostInit()
    }

    @Mod.EventHandler
    fun onComplete(event: FMLLoadCompleteEvent) {
        if (RMConfig.MATERIAL.printMaterials) {
            MaterialRegistry.getMaterials().forEach { LOGGER.info(it.toJson(false)) }
        }
    }

}