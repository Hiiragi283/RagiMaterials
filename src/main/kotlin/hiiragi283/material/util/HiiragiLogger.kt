package hiiragi283.material.util

import hiiragi283.material.RMReference
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

private val LOGGER: Logger = LogManager.getLogger(RMReference.MOD_NAME)

object HiiragiLogger : Logger by LOGGER {

    fun debugInfo(message: Any) {
        if (isDeobf()) info(message)
    }

}