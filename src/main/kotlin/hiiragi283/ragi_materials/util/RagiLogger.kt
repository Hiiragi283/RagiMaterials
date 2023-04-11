package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.config.RagiConfig

/**
Thanks to defeatedcrow!
Source: https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/DCLogger.java
 */

object RagiLogger {

    fun debug(info: Any?) {
        RagiMaterials.LOGGER.debug(info)
    }

    fun error(info: Any?) {
        RagiMaterials.LOGGER.error(info)
    }

    fun fatal(info: Any?) {
        RagiMaterials.LOGGER.fatal(info)
    }

    fun info(info: Any?) {
        RagiMaterials.LOGGER.info(info)
    }

    fun warn(info: Any?) {
        RagiMaterials.LOGGER.warn(info)
    }

    fun infoDebug(info: Any?) {
        if (RagiConfig.debugMode.isDebug) RagiMaterials.LOGGER.info(info)
    }

    fun warnDebug(info: Any?) {
        if (RagiConfig.debugMode.isDebug) RagiMaterials.LOGGER.warn(info)
    }

    fun errorDebug(info: Any?) {
        if (RagiConfig.debugMode.isDebug) RagiMaterials.LOGGER.error(info)
    }
}