package hiiragi283.plugin

import net.minecraftforge.fml.relauncher.IFMLCallHook

class ForgelinBridge : IFMLCallHook {

    override fun injectData(data: Map<String, Any>) {
        val loader: ClassLoader = data["classLoader"] as ClassLoader
        try {
            loader.loadClass("net.shadowfacts.forgelin.KotlinAdapter")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Couldn't find Forgelin language adapter, this shouldn't be happening", e)
        }
    }

    @Throws(Exception::class)
    override fun call(): Void? = null

}