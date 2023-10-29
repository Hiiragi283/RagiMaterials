package hiiragi283.material.api.part

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.HiiragiLogger
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import rechellatek.camelToSnakeCase

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) : HiiragiJsonSerializable {

    val materialStack: MaterialStack = MaterialStack(material, getScale())

    companion object {

        @JvmSynthetic
        fun createAllParts(): List<HiiragiPart> = HiiragiRegistries.SHAPE.getValues()
            .flatMap { shape: HiiragiShape -> HiiragiRegistries.MATERIAL.getValues().map(shape::getPart) }

        @JvmStatic
        fun fromOreDict(oreDict: String): HiiragiPart? {
            val list: List<String> = oreDict.camelToSnakeCase().split('_')
            HiiragiLogger.debugInfo("Entry: $list")
            for (index: Int in list.indices) {
                val shapeName: String = list[index]
                HiiragiLogger.debugInfo("Shape: $shapeName")
                val shape: HiiragiShape = HiiragiRegistries.SHAPE.getValue(shapeName) ?: continue
                val materialName: String = list.subList(index + 1, list.size).joinToString(separator = "_")
                HiiragiLogger.debugInfo("Material: $materialName")
                val material: HiiragiMaterial = HiiragiRegistries.MATERIAL.getValue(materialName) ?: continue
                HiiragiLogger.debugInfo("HiiragiPart.fromOreDict is succeeded!")
                return HiiragiPart(shape, material)
            }
            HiiragiLogger.debugInfo("HiiragiPart.fromOreDict is failed...")
            return null
        }

    }

    //    Conversion    //

    fun addTooltip(tooltip: MutableList<String>) {
        material.addTooltip(tooltip, shape)
    }

    fun addTooltip(event: ItemTooltipEvent) {
        material.addTooltip(event.toolTip, getTranslatedName(), getScale() * event.itemStack.count)
    }

    fun getItemStack(count: Int = 1): ItemStack = PartDictionary.getStack(this, count) ?: ItemStack.EMPTY

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDict(): String = shape.getOreDict(material)

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