package hiiragi283.api.material

import hiiragi283.core.util.append
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item

enum class CrystalType(val isCrystal: Boolean, val texture: String) {
    AMORPHOUS(true, "amorphous"),
    COAL(true, "coal"),
    CUBIC(true, "cubic"),
    DIAMOND(true, "diamond"),
    EMERALD(true, "emerald"),
    LAPIS(true, "lapis"),
    METAL(true, "cubic"),
    NONE(false, "cubic"),
    QUARTZ(true, "quartz"),
    RUBY(true, "ruby");

    fun getLocation(item: Item) = ModelResourceLocation(item.registryName!!.append("_$texture"), "inventory")

}