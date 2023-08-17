package hiiragi283.material

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import zone.rong.mixinbooter.IEarlyMixinLoader

@IFMLLoadingPlugin.Name(RMReference.MOD_NAME)
@IFMLLoadingPlugin.MCVersion("1.12.2")
class RMMixinLoader : IFMLLoadingPlugin, IEarlyMixinLoader {

    private val logger: Logger = LogManager.getLogger("${RMReference.MOD_NAME} Mixin")

    init {
        logger.info("Loading Mixin for RagiMaterials...")
    }

    //    IFMLLoadingPlugin    //

    override fun getASMTransformerClass(): Array<String> = arrayOf()

    override fun getModContainerClass(): String? = null

    /**
     * @see <a href = https://github.com/serenibyss/Serendustry/blob/master/src/main/kotlin/serendustry/adapter/SerendustryPlugin.kt>Source Code </a>
     */

    override fun getSetupClass(): String = "hiiragi283.material.adaptor.HiiragiCallHook"

    override fun injectData(data: MutableMap<String, Any>?) {}

    override fun getAccessTransformerClass(): String? = null

    //    IEarlyMixinLoader    //

    override fun getMixinConfigs(): MutableList<String> = mutableListOf("mixins.ragi_materials.json")

}