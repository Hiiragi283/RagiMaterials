package hiiragi283.material.api.part

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.util.HiiragiJsonSerializable
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.ItemTooltipEvent

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) : HiiragiJsonSerializable {

    val materialStack: MaterialStack = MaterialStack(material, shape.scale)

    fun addTooltip(tooltip: MutableList<String>) {
        material.addTooltip(tooltip, shape)
    }

    fun addTooltip(event: ItemTooltipEvent) {
        addTooltip(event.toolTip, event.itemStack)
    }

    fun addTooltip(tooltip: MutableList<String>, stack: ItemStack) {
        material.addTooltip(tooltip, shape.getTranslatedName(material), shape.scale * stack.count)
    }

    fun getItemStack(count: Int = 1): ItemStack = PartDictionary.getPrimalStack(this, count) ?: ItemStack.EMPTY

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getItemStacks(this, count)

    fun getOreDict(): String = shape.getOreDict(material)

    fun getOreDicts(): List<String> = shape.getOreDicts(material)

    override fun toString(): String = "${shape.name}:${material.name}"

    companion object {

        @JvmSynthetic
        fun createAllParts(): List<HiiragiPart> = HiiragiRegistries.SHAPE.getValues()
            .flatMap { shape: HiiragiShape -> HiiragiRegistries.MATERIAL.getValues().map(shape::getPart) }

    }

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {

        val root = JsonObject()

        root.addProperty("shape", shape.name)
        root.addProperty("material", material.name)

        return root

    }

}