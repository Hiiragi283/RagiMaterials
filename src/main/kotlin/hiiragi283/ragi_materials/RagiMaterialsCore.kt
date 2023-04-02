package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.config.JsonConfig
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.crafting.CraftingRegistry
import hiiragi283.ragi_materials.init.*
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.packet.PacketManager
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.recipe.FFRecipe
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.util.RagiLogger
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
import java.io.File

//Modの定義
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, acceptedMinecraftVersions = Reference.MC_VERSIONS)
class RagiMaterialsCore {

    companion object {

        val isLoadedGT = Loader.isModLoaded("gregtech")

        var config: File? = null

        //Instanceの宣言
        @Mod.Instance(Reference.MOD_ID)
        var INSTANCE: RagiMaterialsCore? = null

        //Proxyの宣言
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        var proxy: CommonProxy? = null

    }

    @Mod.EventHandler
    fun onConstruct(event: FMLConstructionEvent) {
        if (!isLoadedGT) {
            //各種登録イベントの登録
            MinecraftForge.EVENT_BUS.register(RagiRegistry)
            FluidRegistry.enableUniversalBucket()
        }
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        if (!isLoadedGT) {
            /*
              Thanks to defeatedcrow!
              Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/JsonSampleCore.java
            */
            //configフォルダーの取得
            config = File(event.modConfigurationDirectory, "${Reference.MOD_ID}/")
            RagiLogger.infoDebug(("Config path: ${config?.absolutePath}"))
            //鉱石生成の登録
            //MinecraftForge.ORE_GEN_BUS.register(OreGenRegistry())
            //GUI描画の登録
            NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, RagiGuiHandler)
            //proxyの読み込み
            proxy!!.loadPreInit()
            //連携要素の登録
            IntegrationCore.loadPreInit()
        }
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        if (!isLoadedGT) {
            //液体の登録
            RagiRegistry.registerFluid()
            //鉱石辞書の登録
            OreDictRegistry.load()
            //レシピの登録
            CraftingRegistry.load()
            //LootTableの登録
            LootTableRegistry.load()
            //パケットの登録
            PacketManager.load()
            //proxyの読み込み
            proxy!!.loadInit()
            //連携要素の登録
            IntegrationCore.loadInit()
        }
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        if (!isLoadedGT) {
            //Json configの読み取り
            JsonConfig.loadJson()
            //Json configの生成
            JsonConfig.generateJson()
            //レシピの登録
            FFRecipe.Registry.load()
            LaboRecipe.Registry.load()
            MillRecipe.Registry.load()
            //デバッグ用
            if (RagiConfig.debugMode.isDebug) {
                MaterialUtil.printMap()
                FFRecipe.Registry.printMap()
                LaboRecipe.Registry.printMap()
                MillRecipe.Registry.printMap()
            }
            //proxyの読み込み
            proxy!!.loadPostInit()
            //連携要素の登録
            IntegrationCore.loadPostInit()
        }
    }
}