package hiiragi283.material.api.shape

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import rechellatek.snakeToLowerCamelCase

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

class HiiragiShape(val name: String, val scale: Int) {

    fun getItemStack(material: HiiragiMaterial, count: Int = 1): ItemStack? =
        HiiragiRegistries.MATERIAL_ITEM.getValue(this)?.getItemStack(material, count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = HiiragiRegistries.MATERIAL_INDEX.getValues()
        .map(::getPart)
        .flatMap { it.getItemStacks(count) }

    fun getOreDict(material: HiiragiMaterial): String = name.snakeToLowerCamelCase() + material.getOreDictName()

    fun getOreDicts(material: HiiragiMaterial): List<String> {
        val list: MutableList<String> = mutableListOf()
        list.add(getOreDict(material))
        if (material.hasOreDictAlt()) {
            material.getOreDictNameAlt().forEach { oreDict ->
                list.add(StringBuilder().also {
                    it.append(name.snakeToLowerCamelCase())
                    it.append(oreDict)
                }.toString())
            }
        }
        return list.filter(String::isNotEmpty)
    }

    fun getPart(material: HiiragiMaterial): HiiragiPart = HiiragiPart(this, material)

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    fun hasScale(): Boolean = scale > 0

    fun isValid(material: HiiragiMaterial): Boolean =
        material.shapeType == HiiragiShapeTypes.WILDCARD || this in material.shapeType.shapes

    override fun toString(): String = "Shape:$name"

    //    Registration    //

    fun register() {
        HiiragiRegistries.SHAPE.register(name, this)
    }

}