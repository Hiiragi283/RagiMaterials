package hiiragi283.ragi_materials.integration

import net.minecraftforge.fml.common.Loader

object IntegrationCore {

    val enableJEI = Loader.isModLoaded("jei")

    fun init() {
        PluginVanilla.init()
    }
}