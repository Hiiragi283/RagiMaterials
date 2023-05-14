package hiiragi283.material

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RagiMaterials : ModInitializer {

    const val MOD_ID = "ragi_materials"

    const val MOD_NAME = "RagiMaterials"

    val LOGGER: Logger = LogManager.getLogger(MOD_ID)

    //    ModInitializer    //

    override fun onInitialize() {

        //オブジェクトの生成を行う
        RagiRegistry.load()
        //オブジェクトの登録を行う
        RagiRegistry.register()

    }
}