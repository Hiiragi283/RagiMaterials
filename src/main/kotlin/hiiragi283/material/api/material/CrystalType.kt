package hiiragi283.material.api.material

import hiiragi283.material.util.append
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistryEntry

enum class CrystalType {

    AMORPHOUS,
    COAL,
    CUBIC,
    DIAMOND,
    EMERALD,
    LAPIS,
    NONE,
    QUARTZ,
    RUBY;

    fun getModelLocation(entry: IForgeRegistryEntry<*>): ResourceLocation =
        entry.registryName!!.append("_${name.toLowerCase()}")

}