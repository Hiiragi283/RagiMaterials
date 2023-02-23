package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.event.RightClickBlock
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.init.OreDictRegistry
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.recipe.RecipeRegistry
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRegistry
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

//Modの定義
@Mod(
    modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    version = Reference.VERSION,
    dependencies = Reference.DEPENDENCIES,
    acceptedMinecraftVersions = Reference.MC_VERSIONS
)
class RagiMaterials {

    init {
        //Universal Bucketの使用
        FluidRegistry.enableUniversalBucket()
    }

    companion object {
        val isLoadedGT = Loader.isModLoaded("gregtech")

        //Proxyの定義
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null
    }

    //Pre-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        if (!isLoadedGT) {
            //素材の一覧の登録
            MaterialRegistry.addExtra()
            //Block, Itemの登録
            RagiInit.init()
            //Eventの登録
            MinecraftForge.EVENT_BUS.register(RightClickBlock())
            //proxyの読み込み
            proxy!!.loadPreInit()
        }
    }

    //Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        if (!isLoadedGT) {
            //鉱石辞書の登録
            OreDictRegistry.init()
            //レシピの登録
            RecipeRegistry.init()
            //proxyの読み込み
            proxy!!.loadInit()
        }
    }

    //Post-Initializationの段階で呼ばれるevent
    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        if (!isLoadedGT) {
            //連携要素の登録
            IntegrationCore.init()
            //コンフィグからレシピを追加
            RagiConfig.registerRecipe()
            //Forge Furnaceのレシピの登録
            FFRegistry.init()
            //proxyの読み込み
            proxy!!.loadPostInit()
        }
    }
}