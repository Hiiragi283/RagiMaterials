package hiiragi283.material.config

import hiiragi283.material.RagiMaterials
import net.minecraftforge.common.config.Config

@Config(modid = RagiMaterials.MODID)
object RMConfig {

    @JvmField
    var enableLanthanides: Boolean = true

    @JvmField
    var enableTransuraniumElement: Boolean = true

    @JvmField
    var generateSampleJson: Boolean = true

    @JvmField
    var printRegisteredMaterials: Boolean = false

}