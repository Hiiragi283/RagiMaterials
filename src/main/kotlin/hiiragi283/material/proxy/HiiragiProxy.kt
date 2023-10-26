package hiiragi283.material.proxy

import com.google.gson.JsonElement
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.RagiMaterialsPlugin
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.config.HiiragiJSonHandler
import hiiragi283.material.init.*
import hiiragi283.material.network.HiiragiNetworkManager
import hiiragi283.material.util.shareOredict
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.network.NetworkRegistry

@Suppress("DEPRECATION")
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
        RagiMaterialsPlugin.onConstruct(event)
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
        RagiMaterialsPlugin.onPreInit(event)
    }

    override fun onInit(event: FMLInitializationEvent) {
        //鉱石辞書の登録
        HiiragiRegistries.BLOCK.getValues()
            .filterIsInstance<HiiragiEntry.BLOCK>()
            .forEach(HiiragiEntry.BLOCK::onInit)
        HiiragiRegistries.ITEM.getValues()
            .filterIsInstance<HiiragiEntry.ITEM>()
            .forEach(HiiragiEntry.ITEM::onInit)
        //連携の登録
        RagiMaterialsPlugin.onInit(event)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        //鉱石辞書とHiiragiPartの同期処理
        PartDictionary.init()
        //レシピの登録
        HiiragiJSonHandler.writeRecipe()
        HiiragiJSonHandler.registerRecipe()
        HiiragiRegistries.BLOCK.getValues()
            .filterIsInstance<HiiragiEntry.BLOCK>()
            .forEach(HiiragiEntry.BLOCK::onPostInit)
        HiiragiRegistries.ITEM.getValues()
            .filterIsInstance<HiiragiEntry.ITEM>()
            .forEach(HiiragiEntry.ITEM::onPostInit)
        HiiragiRecipes.init()
        //鉱石辞書の同期処理
        shareOreDictAlts()
        //連携の登録
        RagiMaterialsPlugin.onPostInit(event)
    }

    private fun shareOreDictAlts() {
        HiiragiPart.createAllParts()
            .filter { it.material.hasOreDictAlt() }
            .forEach { part: HiiragiPart ->
                part.getOreDictAlts().forEach { oreDict: String ->
                    shareOredict(part.getOreDict(), oreDict)
                }
            }
    }

    override fun onComplete(event: FMLLoadCompleteEvent) {
        //ログに出力
        if (HiiragiConfigs.COMMON.printValues) printValues()
        //GUi操作を登録
        NetworkRegistry.INSTANCE.registerGuiHandler(RagiMaterials.Instance, HiiragiGuiHandler)
        //パケット送信の登録
        HiiragiNetworkManager.register()
        //チャンク読み込みの登録
        HiiragiChunkLoadCallback
    }

    private fun printValues() {
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

    class Server : HiiragiProxy()

    class Client : HiiragiProxy() {

        override fun onConstruct(event: FMLConstructionEvent) {
            super.onConstruct(event)
            //Eventを登録
            MinecraftForge.EVENT_BUS.register(HiiragiEventHandler.Client)
        }

    }

}