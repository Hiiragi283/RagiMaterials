package hiiragi283.material.proxy

import com.google.gson.JsonElement
import hiiragi283.material.*
import hiiragi283.material.api.chunk.HiiragiChunkLoadCallback
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.HiiragiPlugin
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.config.HiiragiJSonHandler
import hiiragi283.material.network.HiiragiNetworkManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.network.NetworkRegistry

abstract class HiiragiProxy : IHiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //Eventを登録
        MinecraftForge.EVENT_BUS.register(HiiragiEventHandler)
        MinecraftForge.EVENT_BUS.register(HiiragiEventHandler.Client)
        //レジストリの初期化
        HiiragiRegistries.initRecipeType()
        //連携の登録
        HiiragiPlugin.onConstruct(event)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //configフォルダのパスを取得
        HiiragiJSonHandler.configFile = event.modConfigurationDirectory
        HiiragiJSonHandler.init()
        //Shapeの登録
        HiiragiJSonHandler.writeShape()
        HiiragiJSonHandler.readShape()
        HiiragiRegistries.registerShape()
        //Materialの登録
        HiiragiJSonHandler.writeMaterial()
        HiiragiJSonHandler.readMaterial()
        HiiragiRegistries.registerMaterial()
        //レジストリへの登録
        HiiragiBlocks
        HiiragiEntities
        HiiragiItems
        HiiragiRegistries.registerModuleMachine()
        HiiragiRegistries.registerRecipeModule()
        MaterialFluid.register()
        //連携の登録
        HiiragiPlugin.onPreInit(event)
    }

    override fun onInit(event: FMLInitializationEvent) {
        //レジストリへの登録
        HiiragiRegistries.registerPart()
        //鉱石辞書の登録
        HiiragiRegistries.BLOCK.registerOreDict()
        HiiragiRegistries.ITEM.registerOreDict()
        //レシピの登録
        HiiragiRegistries.BLOCK.registerRecipe()
        HiiragiRegistries.ITEM.registerRecipe()
        HiiragiRecipes.init()
        //連携の登録
        HiiragiPlugin.onInit(event)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        //レシピの登録
        HiiragiJSonHandler.writeRecipe()
        HiiragiJSonHandler.registerRecipe()
        HiiragiRecipes.postInit()
        //連携の登録
        HiiragiPlugin.onPostInit(event)
    }

    override fun onComplete(event: FMLLoadCompleteEvent) {
        //ログに出力
        if (HiiragiConfigs.COMMON.printValues) {
            RagiMaterials.LOGGER.info("Printing registered HiiragiShape values...")
            HiiragiRegistries.SHAPE.getValues()
                .map(HiiragiShape::getJsonElement)
                .map<JsonElement, String>(RagiMaterials.GSON::toJson)
                .forEach(RagiMaterials.LOGGER::info)
            RagiMaterials.LOGGER.info("Printing registered HiiragiMaterial values...")
            HiiragiRegistries.MATERIAL.getValues()
                .map(HiiragiMaterial::getJsonElement)
                .map<JsonElement, String>(RagiMaterials.GSON::toJson)
                .forEach(RagiMaterials.LOGGER::info)
        }
        //GUi操作を登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterials.Instance, HiiragiGuiHandler)
        //パケット送信の登録
        HiiragiNetworkManager.register()
        //チャンク読み込みの登録
        HiiragiChunkLoadCallback
    }

    class Server : HiiragiProxy()

    class Client : HiiragiProxy() {

        override fun onConstruct(event: FMLConstructionEvent) {
            super.onConstruct(event)
            //Eventを登録
            MinecraftForge.EVENT_BUS.register(HiiragiEventHandler.Client)
        }

    }

}