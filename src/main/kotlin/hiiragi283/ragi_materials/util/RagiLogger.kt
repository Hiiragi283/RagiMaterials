package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.config.RagiConfig
import org.apache.logging.log4j.LogManager

object RagiLogger {
    private val LOGGER_RAGI = LogManager.getLogger(Reference.MOD_ID)
    fun info(info: Any?) {
        LOGGER_RAGI.info(info)
    }

    fun warn(info: Any?) {
        LOGGER_RAGI.warn(info)
    }

    fun error(info: Any?) {
        LOGGER_RAGI.error(info)
    }

    fun infoDebug(info: Any?) {
        if (RagiConfig.isDebug) {
            LOGGER_RAGI.info(info)
        }
    }

    fun warnDebug(info: Any?) {
        if (RagiConfig.isDebug) {
            LOGGER_RAGI.warn(info)
        }
    }

    fun errorDebug(info: Any?) {
        if (RagiConfig.isDebug) {
            LOGGER_RAGI.error(info)
        }
    }
}