package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import java.io.File

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/JsonSampleMod/blob/main/src/main/java/com/defeatedcrow/jsonsample/JsonSampleCore.java
*/

object RagiDirectory {

    var config: File? = null

    fun getPath(event: FMLPreInitializationEvent) {
        //configフォルダーの取得
        config = File(event.modConfigurationDirectory, "${Reference.MOD_ID}/")
        RagiLogger.infoDebug(("Config path: ${config?.absolutePath}"))

    }

}