package hiiragi283.core

import hiiragi283.api.event.MaterialRegistryEvent
import hiiragi283.api.event.ShapeRegistryEvent
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialElements
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.api.shape.ShapeRegistry
import hiiragi283.chemistry.RCCore
import hiiragi283.core.config.RMConfig
import hiiragi283.core.config.RMJSonHandler
import hiiragi283.integration.RMIntegrationCore
import hiiragi283.material.RMCore
import hiiragi283.material.RMReference
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
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
object RagiMaterials : HiiragiProxy {

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
    override fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //イベントを登録
        MinecraftForge.EVENT_BUS.register(this)
        //RagiMaterials側の処理
        RMCore.onConstruct(event)
        //RagiChemistry側の処理
        RCCore.onConstruct(event)
    }

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を取得
        RMJSonHandler(event).run {
            this.writeJson()
            this.readJson()
        }
        //素材レジストリの初期化
        MaterialRegistry.init()
        //形状レジストリの初期化
        ShapeRegistry.init()
        //RagiMaterials側の処理
        RMCore.onPreInit(event)
        //RagiChemistry側の処理
        RCCore.onPreInit(event)
        //連携の登録
        RMIntegrationCore.INSTANCE.onPreInit(event)
    }

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent) {
        //部品レジストリの初期化
        PartRegistry.init()
        //RagiMaterials側の処理
        RMCore.onInit(event)
        //RagiChemistry側の処理
        RCCore.onInit(event)
        //連携の登録
        RMIntegrationCore.INSTANCE.onInit(event)
    }

    @Mod.EventHandler
    override fun onPostInit(event: FMLPostInitializationEvent) {
        //RagiMaterials側の処理
        RMCore.onPostInit(event)
        //RagiChemistry側の処理
        RCCore.onPostInit(event)
        //連携の登録
        RMIntegrationCore.INSTANCE.onPostInit(event)
    }

    @Mod.EventHandler
    override fun onComplete(event: FMLLoadCompleteEvent) {
        //MaterialRegistryからログに出力
        if (RMConfig.MATERIAL.printMaterials) {
            MaterialRegistry.getMaterials().forEach { LOGGER.info(it.toJson(false)) }
        }
        //RagiMaterials側の処理
        RMCore.onComplete(event)
        //RagiChemistry側の処理
        RCCore.onComplete(event)
    }

    //    Events    //

    /**
     * @see <a href = https://github.com/ACGaming/UniversalTweaks/blob/main/src/main/java/mod/acgaming/universaltweaks/config/UTConfig.java>Reference </a>
     */

    @SubscribeEvent
    fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
        if (event.modID != RMReference.MOD_ID) return
        ConfigManager.sync(RMReference.MOD_ID, Config.Type.INSTANCE)
        LOGGER.info("Config Reloaded!")
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: MaterialRegistryEvent) {
        event.registry.run {

            LOGGER.info("Registering Elemental Materials...")
            MaterialElements.register(this)

            LOGGER.info("Registering Common Materials...")
            MaterialCommon.register(this)

            LOGGER.info("Registering Materials for Integration...")
            RMIntegrationCore.INSTANCE.registerMaterial(this)

            LOGGER.info("Registering Materials from JSON...")
            RMJSonHandler.register(this)

        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerShapes(event: ShapeRegistryEvent) {
        LOGGER.info("Registering Shapes...")
        HiiragiShapes.register(event.registry)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun onTooltip(event: ItemTooltipEvent) {
        PartRegistry.getParts(event.itemStack).toSet().forEach { it.addTooltip(event.toolTip) }
    }

}