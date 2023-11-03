package hiiragi283.material.proxy

import com.google.gson.JsonElement
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.RagiMaterialsPlugin
import hiiragi283.material.compat.crt.HiiragiCrTPlugin
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.config.HiiragiJSonHandler
import hiiragi283.material.init.*
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import hiiragi283.material.init.materials.MaterialElements
import hiiragi283.material.network.HiiragiNetworkManager
import hiiragi283.material.util.HiiragiLogger
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.network.NetworkRegistry

@Suppress("unused")
abstract class HiiragiProxy : IHiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //Eventを登録
        MinecraftForge.EVENT_BUS.register(HiiragiEventHandler)
        MinecraftForge.EVENT_BUS.register(PartDictionary)
        //レジストリの初期化
        IMachineRecipe.REGISTRY.init()
        //連携の登録
        RagiMaterialsPlugin.onConstruct(event)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //configフォルダのパスを取得
        HiiragiJSonHandler.configFile = event.modConfigurationDirectory
        HiiragiJSonHandler.init()
        //Shapeの登録
        HiiragiShapes.init()
        HiiragiJSonHandler.writeShape()
        HiiragiJSonHandler.readShape()
        HiiragiShape.REGISTRY.init()
        //Materialの登録
        MaterialElements.init()
        MaterialCompats.init()
        MaterialCommons.init()
        HiiragiCrTPlugin.registerMaterial()
        HiiragiJSonHandler.writeMaterial()
        HiiragiJSonHandler.readMaterial()
        HiiragiMaterial.REGISTRY.init()
        //レジストリへの登録
        HiiragiBlocks
        HiiragiEntities
        HiiragiItems
        ModuleMachineBlock.REGISTRY.init()
        RecipeModuleItem.REGISTRY.init()
        MaterialFluid.register()
        //連携の登録
        RagiMaterialsPlugin.onPreInit(event)
    }

    override fun onInit(event: FMLInitializationEvent) {
        //鉱石辞書の登録
        PartDictionary.reloadOreDicts()
        HiiragiEntry.BLOCK.REGISTRY.getValues()
            .filterIsInstance<HiiragiEntry.BLOCK>()
            .forEach(HiiragiEntry.BLOCK::onInit)
        HiiragiEntry.ITEM.REGISTRY.getValues()
            .filterIsInstance<HiiragiEntry.ITEM>()
            .forEach(HiiragiEntry.ITEM::onInit)
        //レシピの登録
        HiiragiJSonHandler.writeRecipe()
        HiiragiJSonHandler.registerRecipe()
        HiiragiRecipes.init()
        //連携の登録
        RagiMaterialsPlugin.onInit(event)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        //連携の登録
        RagiMaterialsPlugin.onPostInit(event)
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
        HiiragiLogger.info("Printing registered HiiragiShape values...")
        HiiragiShape.REGISTRY.getValues()
            .map(HiiragiShape::getJsonElement)
            .map<JsonElement, String>(RagiMaterials.GSON::toJson)
            .forEach(HiiragiLogger::info)
        HiiragiLogger.info("Printing registered HiiragiMaterial values...")
        HiiragiMaterial.REGISTRY.getValues()
            .map(HiiragiMaterial::getJsonElement)
            .map<JsonElement, String>(RagiMaterials.GSON::toJson)
            .forEach(HiiragiLogger::info)
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