package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.ForgeRegistries

//Modの定義
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION,
        dependencies = Reference.DEPENDENCIES,
        acceptedMinecraftVersions = Reference.MC_VERSIONS
)
class RagiMaterials {

    companion object {
        //Proxyの定義
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null
    }

    init {
        //Universal Bucketの使用
        FluidRegistry.enableUniversalBucket()
    }

    //Pre-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        //configの読み込み
        RagiConfig.load(event!!.modConfigurationDirectory)
        //Block, Event, Itemの登録
        RagiMaterialsInit.registerBlocks()
        RagiMaterialsInit.registerEvents()
        RagiMaterialsInit.registerFluids()
        RagiMaterialsInit.registerItems()
        //proxyの読み込み
        proxy!!.loadPreInit()
    }

    //Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        //鉱石辞書の登録
        //proxyの読み込み
        proxy!!.loadInit()
    }

    //Post-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        //Itemの最大スタック数を上書きする
        //どんな名前のclassにしたらいいか思いつかなかった
        for (name in ForgeRegistries.ITEMS.keys) {
            val item = RagiUtils.getItem(name.toString())
            //itemの耐久値が0の場合、最大スタック数を64に上書きする
            if (item.maxDamage == 0) item.setMaxStackSize(64)
        }
        //proxyの読み込み
        proxy!!.loadPostInit()
    }
}