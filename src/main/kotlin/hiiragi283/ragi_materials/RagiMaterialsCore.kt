package hiiragi283.ragi_materials

import hiiragi283.ragi_materials.proxy.CommonProxy
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
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
        if (!isLoadedGT) proxy!!.onConstruct(event)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        if (!isLoadedGT) proxy!!.onPreInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        if (!isLoadedGT) proxy!!.onInit(event)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        if (!isLoadedGT) proxy!!.onPostInit(event)
    }
}