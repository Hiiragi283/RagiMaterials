package hiiragi283.material.proxy

import hiiragi283.material.RMEventHandler
import hiiragi283.material.RagiMaterials
import hiiragi283.material.fluid.HiiragiFluid
import hiiragi283.material.init.RMItems
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.*

abstract class CommonProxy : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
        //素材の登録
        MaterialRegistry.init()
        //部品の登録
        PartRegistry.init()
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //液体の登録
        HiiragiFluid.register()
        //MaterialPartとの紐づけ
        RMItems.registerMaterialPart()
    }

    override fun onInit(event: FMLInitializationEvent) {
        //鉱石辞書の登録
        RMItems.registerOreDict()
        //レシピの登録
        RMItems.registerRecipe()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {}

    override fun onComplete(event: FMLLoadCompleteEvent) {
        MaterialRegistry.getMaterials()
            .forEach {
                RagiMaterials.LOGGER.debug(Json.encodeToString(it))
            }
    }

}