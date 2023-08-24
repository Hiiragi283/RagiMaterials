package hiiragi283.plugin

import hiiragi283.material.RMReference
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import zone.rong.mixinbooter.IEarlyMixinLoader

@IFMLLoadingPlugin.Name(RagiMaterialsPlugin.NAME)
@IFMLLoadingPlugin.MCVersion("1.12.2")
class RagiMaterialsPlugin : IFMLLoadingPlugin, IEarlyMixinLoader {

    companion object {

        const val NAME = "${RMReference.MOD_NAME} Core"

        internal val LOGGER: Logger = LogManager.getLogger(NAME)

    }

    init {
        LOGGER.info("RagiMaterialsPlugin accessed!!")
    }

    //    IFMLLoadingPlugin    //

    override fun getASMTransformerClass(): Array<String> = arrayOf()

    override fun getModContainerClass(): String = FakeForgelin::class.java.name

    override fun getSetupClass(): String = ForgelinBridge::class.java.name

    override fun injectData(data: MutableMap<String, Any>?) {}

    override fun getAccessTransformerClass(): String? = null

    //    IEarlyMixinLoader    //

    override fun getMixinConfigs(): MutableList<String> = mutableListOf("mixins.ragi_materials.json")

}