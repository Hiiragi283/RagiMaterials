package hiiragi283.material.api.part

import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

/**
 * Pair of [HiiragiShape] and [HiiragiMaterial]
 */
data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) {

    companion object {
        @JvmField
        val EMPTY = HiiragiPart(HiiragiShape.EMPTY, HiiragiMaterial.EMPTY)

    }

    fun addTooltip(tooltip: MutableList<String>) {
        material.addTooltip(tooltip, shape)
    }

    fun isEmpty(): Boolean = this == EMPTY || this.shape.isEmpty() || this.material.isEmpty()

    fun findItemStack(primalMod: String, secondaryMod: String): ItemStack =
        hiiragi283.material.util.findItemStack(getAllItemStack(), primalMod, secondaryMod)

    fun getAllItemStack(): List<ItemStack> = getOreDicts().flatMap { OreDictionary.getOres(it) }

    fun getDefaultItemStack(amount: Int = 1): ItemStack =
        GameRegistry.makeItemStack("${RMReference.MOD_ID}:${shape.name}", material.index, amount, null)

    fun getOreDict(): String = shape.getOreDict(material)

    fun getOreDicts(): List<String> = shape.getOreDicts(material)

    fun toMaterialStack(): MaterialStack = MaterialStack(material, shape.scale)

    override fun toString(): String = "${shape.name}:${material.name}"

}