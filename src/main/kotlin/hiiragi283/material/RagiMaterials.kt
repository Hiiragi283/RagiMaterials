package hiiragi283.material

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.capability.HiiragiCapability
import hiiragi283.api.fluid.MaterialFluid
import hiiragi283.integration.RMIntegrationCore
import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.network.HiiragiNetworkManager
import net.minecraft.init.Blocks
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
    modid = RMReference.MOD_ID,
    name = RMReference.MOD_NAME,
    version = RMReference.VERSION,
    dependencies = "after-required:forgelin_continuous;after-required:modularui;after:gregtech;after:jei@[4.24.5,)",
    acceptedMinecraftVersions = "[1.12,1.12.2]",
    //modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object RagiMaterials : HiiragiProxy {

    //各種変数の宣言
    internal val CALENDAR: Calendar = Calendar.getInstance()
    internal val COLOR: Color = Color(255, 0, 31)
    internal val LOGGER: Logger = LogManager.getLogger(RMReference.MOD_NAME)

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
        //連携の登録
        RMIntegrationCore.INSTANCE.onConstruct(event)
    }

    @Mod.EventHandler
    override fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を取得
        RMJSonHandler(event).run {
            this.writeJson()
            this.readJson()
        }
        //レジストリの初期化
        HiiragiRegistry.initShape()
        HiiragiRegistry.initMaterial()
        RMBlocks.init()
        RMItems.init()
        FluidRegistry.registerFluid(MaterialFluid.EMPTY.setBlock(Blocks.AIR))
        //Capability登録
        HiiragiCapability.register()
        //連携の登録
        RMIntegrationCore.INSTANCE.onPreInit(event)
    }

    @Mod.EventHandler
    override fun onInit(event: FMLInitializationEvent) {
        //レジストリの初期化
        HiiragiRegistry.initPart()
        HiiragiRegistry.initHeatSource()
        HiiragiRegistry.initFluid()
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
            HiiragiRegistry.getMaterials().forEach { LOGGER.info(it.toJson(false)) }
        }
        //パケット送信の登録
        HiiragiNetworkManager.load()
    }

}