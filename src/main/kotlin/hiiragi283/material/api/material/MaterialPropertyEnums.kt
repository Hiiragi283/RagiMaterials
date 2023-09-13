package hiiragi283.material.api.material

import hiiragi283.material.util.append
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

    companion object {
        @JvmStatic
        @Throws(NoSuchElementException::class)
        fun fromString(name: String): CrystalType = CrystalType.values().first { it.toString() == name }

    }

    fun getLocation(item: Item) = ModelResourceLocation(item.registryName!!.append("_$texture"), "inventory")

}

enum class MaterialHardness(val value: Float) {
    FLUID(0.0f),
    SOFT(2.5f),
    NORMAL(5.0f),
    HARD(10.0f);

    companion object {
        @JvmStatic
        @Throws(NoSuchElementException::class)
        fun fromString(name: String): MaterialHardness = MaterialHardness.values().first { it.toString() == name }

    }

}

enum class MaterialState {
    SOLID, LIQUID, GAS;
}