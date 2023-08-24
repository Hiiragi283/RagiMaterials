package net.shadowfacts.forgelin

import hiiragi283.plugin.RagiMaterialsPlugin
import io.github.chaosunity.forgelin.KotlinAdapter
import net.minecraftforge.fml.common.FMLModContainer
import net.minecraftforge.fml.common.ILanguageAdapter
import net.minecraftforge.fml.common.ModContainer
import net.minecraftforge.fml.relauncher.Side
import java.lang.reflect.Field
import java.lang.reflect.Method

class KotlinAdapter : ILanguageAdapter {

    init {
        RagiMaterialsPlugin.LOGGER.info("Forgelin Bridge activated!!")
    }

    private val instanceContinuous = KotlinAdapter()

    override fun getNewInstance(
        container: FMLModContainer,
        objectClass: Class<*>,
        classLoader: ClassLoader,
        factoryMarkedAnnotation: Method?
    ): Any = instanceContinuous.getNewInstance(container, objectClass, classLoader, factoryMarkedAnnotation)

    override fun supportsStatics(): Boolean = instanceContinuous.supportsStatics()

    override fun setProxy(target: Field, proxyTarget: Class<*>, proxy: Any) {
        instanceContinuous.setProxy(target, proxyTarget, proxy)
    }

    override fun setInternalProxies(mod: ModContainer?, side: Side?, loader: ClassLoader?) {
        instanceContinuous.setInternalProxies(mod, side, loader)
    }

}