package hiiragi283.material

import hiiragi283.material.config.RMConfig
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.fluid.HiiragiFluid
import hiiragi283.material.init.RMItems
import hiiragi283.material.material.MaterialRegistry
import hiiragi283.material.part.PartRegistry
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.common.event.*

object CommonProxy : IProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        //Universal Bucketを有効化
        FluidRegistry.enableUniversalBucket()
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(RMEventHandler)
        //素材の登録
        MaterialRegistry.init()
        //部品の登録
        PartRegistry.init()
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        //configから素材を登録
        val jsonHandler = RMJSonHandler(event)
        jsonHandler.writeJson()
        jsonHandler.readJson()
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
        if (RMConfig.printRegisteredMaterials) {
            MaterialRegistry.getMaterials()
                .forEach { RagiMaterials.LOGGER.debug(it.toJson(false)) }
        }
    }
}