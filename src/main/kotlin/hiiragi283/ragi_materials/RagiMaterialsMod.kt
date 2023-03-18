package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.init.*
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.packet.RagiPacket
import hiiragi283.ragi_materials.init.proxy.CommonProxy
import hiiragi283.ragi_materials.recipe.RecipeRegistry
import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipeRegistry
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry

//Modの定義
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.VERSION,
        dependencies = Reference.DEPENDENCIES,
        acceptedMinecraftVersions = Reference.MC_VERSIONS
)
class RagiMaterialsMod {

    companion object {
        val isLoadedGT = Loader.isModLoaded("gregtech")

        //Proxyの定義
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null
    }

    @Mod.EventHandler
    fun onConstruct(event: FMLConstructionEvent) {
        if (!isLoadedGT) {
            //各種登録イベントの登録
            MinecraftForge.EVENT_BUS.register(RagiRegistry())
            FluidRegistry.enableUniversalBucket()
        }
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        if (!isLoadedGT) {
            //proxyの読み込み
            proxy!!.loadPreInit()
            //連携要素の登録
            IntegrationCore.loadPreInit()
        }
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent?) {
        if (!isLoadedGT) {
            //鉱石辞書の登録
            OreDictRegistry
            //レシピの登録
            RecipeRegistry
            //LootTableの登録
            LootTableRegistry
            //パケットの登録
            RagiPacket
            //GUI描画の登録
            NetworkRegistry.INSTANCE.registerGuiHandler(this, RagiGuiHandler())
            //proxyの読み込み
            proxy!!.loadInit()
            //連携要素の登録
            IntegrationCore.loadInit()
        }
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent?) {
        if (!isLoadedGT) {
            //コンフィグからレシピを追加
            RagiConfig.registerRecipe()
            //各種装置のレシピ登録
            LaboRecipeRegistry
            //デバッグ用
            if (RagiConfig.debugMode.isDebug) {
                MaterialUtil.printMap()
                LaboRecipeRegistry.printMap()
            }
            //proxyの読み込み
            proxy!!.loadPostInit()
            //連携要素の登録
            IntegrationCore.loadPostInit()
        }
    }
}