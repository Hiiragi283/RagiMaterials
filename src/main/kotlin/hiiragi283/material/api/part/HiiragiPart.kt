package hiiragi283.material.api.part

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.HiiragiLogger
import net.minecraft.item.ItemStack
import rechellatek.camelToSnakeCase

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) : HiiragiJsonSerializable {

    companion object {

        @JvmSynthetic
        fun createAllParts(): List<HiiragiPart> = HiiragiShape.REGISTRY.getValues()
            .flatMap { shape: HiiragiShape -> HiiragiMaterial.REGISTRY.getValues().map(shape::getPart) }

        @JvmStatic
        fun fromOreDict(oreDict: String): HiiragiPart? {
            val list: List<String> = oreDict.camelToSnakeCase().split('_')
            HiiragiLogger.debugInfo("Entry: $list")
            for (index: Int in list.indices) {
                val shapeName: String = list.subList(0, index + 1).joinToString(separator = "_")
                HiiragiLogger.debugInfo("Shape: $shapeName")
                val shape: HiiragiShape = HiiragiShape.REGISTRY[shapeName] ?: continue
                val materialName: String = list.subList(index + 1, list.size).joinToString(separator = "_")
                HiiragiLogger.debugInfo("Material: $materialName")
                val material: HiiragiMaterial = HiiragiMaterial.REGISTRY[materialName] ?: continue
                HiiragiLogger.debugInfo("HiiragiPart.fromOreDict is succeeded!")
                return HiiragiPart(shape, material)
            }
            HiiragiLogger.debugInfo("HiiragiPart.fromOreDict is failed...")
            return null
        }

    }

    //    Conversion    //

    fun addTooltip(tooltip: MutableList<String>, multiplier: Int) {
        material.addTooltip(tooltip, getTranslatedName(), getScale() * multiplier)
    }

    fun getItemStack(count: Int = 1): ItemStack = PartDictionary.getStack(this, count) ?: ItemStack.EMPTY

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDict(): String = shape.getOreDict(material)

    fun getPartStack(count: Int = 1) = PartStack(this, count)

    fun getScale(): Int = shape.getScale(material)

    fun getTranslatedName(): String = shape.getTranslatedName(material)

    //    Predicate    //

    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    //    Any    //

    override fun toString(): String = "${shape.name}:${material.name}"

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        root.addProperty("shape", shape.name)
        root.addProperty("material", material.name)

        return root

    }

}