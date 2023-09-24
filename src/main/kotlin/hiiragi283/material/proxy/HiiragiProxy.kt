package hiiragi283.material.proxy

import hiiragi283.material.*
import hiiragi283.material.api.fluid.MaterialFluid
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.chunk.HiiragiChunkLoadCallback
import hiiragi283.material.compat.HiiragiPlugin
import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
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
        //configから素材を取得
        RMJSonHandler(event).run {
            this.writeJson()
            this.readJson()
        }
        //レジストリへの登録
        HiiragiBlocks
        HiiragiEntities
        HiiragiItems
        HiiragiRegistries.registerShape()
        HiiragiRegistries.registerShapeType()
        HiiragiRegistries.registerMaterial()
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
        HiiragiRecipes.postInit()
        //連携の登録
        HiiragiPlugin.onPostInit(event)
    }

    override fun onComplete(event: FMLLoadCompleteEvent) {
        //MaterialRegistryからログに出力
        if (RMConfig.MATERIAL.printMaterials) {
            HiiragiRegistries.MATERIAL.getValues().forEach(RagiMaterials.LOGGER::info)
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