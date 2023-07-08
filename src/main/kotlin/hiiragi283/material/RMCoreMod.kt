package hiiragi283.material

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import zone.rong.mixinbooter.IEarlyMixinLoader

@IFMLLoadingPlugin.Name("RagiMaterials")
@IFMLLoadingPlugin.MCVersion("1.12.2")
class RMCoreMod : IFMLLoadingPlugin, IEarlyMixinLoader {

    init {
        RagiMaterials.LOGGER.info("Loading Mixin for RagiMaterials...")
    }

    //    IFMLLoadingPlugin    //

    override fun getASMTransformerClass(): Array<String> = arrayOf()

    override fun getModContainerClass(): String? = null

    override fun getSetupClass(): String? = null

    override fun injectData(data: MutableMap<String, Any>?) {}

    override fun getAccessTransformerClass(): String? = null

    //    IEarlyMixinLoader    //

    override fun getMixinConfigs(): MutableList<String> = mutableListOf("mixin.ragi_materials.json")

}