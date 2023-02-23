package hiiragi283.ragi_materials.integration

import net.minecraftforge.fml.common.Loader

object IntegrationCore {

    val enableEIO = Loader.isModLoaded("enderio")
    val enableJEI = Loader.isModLoaded("jei")
    val enableMek = Loader.isModLoaded("mekanism")
    val enableTE = Loader.isModLoaded("thermalexpansion")
    val enableTF = Loader.isModLoaded("thermalfoundation")

    fun init() {
        PluginVanilla.init()
    }
}