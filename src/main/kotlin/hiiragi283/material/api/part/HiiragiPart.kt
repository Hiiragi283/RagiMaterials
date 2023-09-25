package hiiragi283.material.api.part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.getOreDicts
import hiiragi283.material.util.notEmpty
import hiiragi283.material.util.toItemStack
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.oredict.OreDictionary

fun createAllParts(): List<HiiragiPart> = HiiragiRegistries.SHAPE.getValues()
    .flatMap { shape: HiiragiShape -> HiiragiRegistries.MATERIAL.getValues().map(shape::getPart) }

fun getPart(oreDict: String): HiiragiPart? = HiiragiRegistries.PART.getValue(oreDict)

fun getParts(oredict: String): List<HiiragiPart> = listOfNotNull(getPart(oredict))

fun getParts(oredicts: Collection<String>): List<HiiragiPart> = oredicts.mapNotNull(::getPart)

fun IBlockState.getParts() = this.let(IBlockState::toItemStack).let(ItemStack::getParts)

fun ItemStack.getParts(): List<HiiragiPart> = this.notEmpty()?.getOreDicts()?.mapNotNull(::getPart) ?: listOf()

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) {

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

    fun getItemStack(count: Int = 1) = HiiragiRegistries.MATERIAL_ITEM.getValue(shape)?.getItemStack(material, count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = getOreDicts().flatMap(OreDictionary::getOres)
        .map { stack: ItemStack ->
            stack.count = count
            return@map stack
        }

    fun getOreDict(): String = shape.getOreDict(material)

    fun getOreDicts(): List<String> = shape.getOreDicts(material)

    fun isInOreDictionary(): Boolean {
        getOreDicts().forEach { oreDict: String ->
            if (OreDictionary.doesOreNameExist(oreDict)) {
                return true
            }
        }
        return false
    }

    override fun toString(): String = "${shape.name}:${material.name}"

}