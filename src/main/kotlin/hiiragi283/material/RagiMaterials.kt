package hiiragi283.material

import hiiragi283.api.capability.HiiragiCapability
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.shape.ShapeRegistry
import hiiragi283.integration.RMIntegrationCore
import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.network.HiiragiNetworkManager
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
    dependencies = "after:gregtech;after:jei",
    acceptedMinecraftVersions = "[1.12,1.12.2]"
)
class RagiMaterials : HiiragiProxy {

    companion object {

        //各種変数の宣言
        val CALENDAR: Calendar = Calendar.getInstance()
        val COLOR: Color by lazy { Color(255, 0, 31) }
        val LOGGER: Logger = LogManager.getLogger(RMReference.MOD_NAME)

        //Instanceの宣言
        @Mod.Instance(RMReference.MOD_ID)
        lateinit var INSTANCE: RagiMaterials

    }

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
    override fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //Eventを登録
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
        MinecraftForge.EVENT_BUS.register(RMEventHandler.Client)
    }

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を取得
        RMJSonHandler(event).run {
            this.writeJson()
            this.readJson()
        }
        //レジストリへのエントリを初期化
        RMBlocks.init()
        RMItems.init()
        //素材レジストリの初期化
        MaterialRegistry.init()
        //形状レジストリの初期化
        ShapeRegistry.init()
        //Capability登録
        HiiragiCapability.register()
        //連携の登録
        RMIntegrationCore.INSTANCE.onPreInit(event)
    }

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent) {
        //部品レジストリの初期化
        PartRegistry.init()
        //鉱石辞書の登録
        RMBlocks.registerOreDict()
        RMItems.registerOreDict()
        //レシピの登録
        RMBlocks.registerRecipe()
        RMItems.registerRecipe()
        RMRecipes.init()
        //連携の登録
        RMIntegrationCore.INSTANCE.onInit(event)
    }

    @Mod.EventHandler
    override fun onPostInit(event: FMLPostInitializationEvent) {
        //連携の登録
        RMIntegrationCore.INSTANCE.onPostInit(event)
    }

    @Mod.EventHandler
    override fun onComplete(event: FMLLoadCompleteEvent) {
        //MaterialRegistryからログに出力
        if (RMConfig.MATERIAL.printMaterials) {
            MaterialRegistry.getMaterials().forEach { LOGGER.info(it.toJson(false)) }
        }
        //GuiHandlerの登録
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, RMGuiHandler)
        //パケット送信の登録
        HiiragiNetworkManager.load()
    }

}