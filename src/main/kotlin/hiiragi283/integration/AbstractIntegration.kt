package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial

abstract class AbstractIntegration {

    abstract fun onPreInit()

    abstract fun registerMaterial(registry: MutableList<HiiragiMaterial>)

    abstract fun onInit()

    abstract fun onPostInit()

    abstract fun onComplete()

}