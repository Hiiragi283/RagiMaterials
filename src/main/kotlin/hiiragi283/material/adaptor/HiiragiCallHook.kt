package hiiragi283.material.adaptor

import net.minecraftforge.fml.relauncher.IFMLCallHook

/**
 * @see <a href = https://github.com/serenibyss/Serendustry/blob/master/src/main/kotlin/serendustry/adapter/SerendustryCallHook.kt>Source Code </a>
 */

class HiiragiCallHook : IFMLCallHook {

    override fun call() = null

    override fun injectData(data: MutableMap<String, Any>) {
        try {
            (data["classLoader"] as ClassLoader).loadClass("hiiragi283.material.adaptor.HiiragiKotlinAdaptor")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Couldn't find HiiragiKotlinAdaptor!")
        }
    }

}