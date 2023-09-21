package hiiragi283.material.api.part

import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.findItemStack
import hiiragi283.material.util.toItemStack
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

fun createAllParts(): List<HiiragiPart> = HiiragiRegistries.SHAPE.getValues()
    .flatMap { shape: HiiragiShape ->
        HiiragiRegistries.MATERIAL.getValues()
            .map { material: HiiragiMaterial -> HiiragiPart(shape, material) }
    }

fun getPart(oreDict: String): HiiragiPart? = HiiragiRegistries.PART.getValue(oreDict)

fun getParts(oredict: String): List<HiiragiPart> = listOfNotNull(getPart(oredict))

fun getParts(oredicts: Collection<String>): List<HiiragiPart> =
    oredicts.mapNotNull { oreDict: String -> getPart(oreDict) }

fun IBlockState.getParts() = this.let(IBlockState::toItemStack).let(ItemStack::getParts)

fun ItemStack.getParts(): List<HiiragiPart> {
    if (this.isEmpty) return listOf()
    return this.let(OreDictionary::getOreIDs)
        .map(OreDictionary::getOreName)
        .mapNotNull(::getPart)
}

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) {

    fun addTooltip(tooltip: MutableList<String>) {
        material.addTooltip(tooltip, shape)
    }

    fun addTooltip(event: ItemTooltipEvent) {
        addTooltip(event.toolTip, event.itemStack)
    }

    fun addTooltip(tooltip: MutableList<String>, stack: ItemStack) {
        material.addTooltip(tooltip, shape.getTranslatedName(material), shape.scale * stack.count)
    }

    fun findItemStack(primalMod: String = "minecraft", secondaryMod: String = RMReference.MOD_ID): ItemStack? =
        findItemStack(getAllItemStack(), primalMod, secondaryMod)

    fun getAllItemStack(): List<ItemStack> = getOreDicts().flatMap { OreDictionary.getOres(it) }

    fun getDefaultItemStack(amount: Int = 1): ItemStack =
        GameRegistry.makeItemStack("${RMReference.MOD_ID}:${shape.name}", material.index, amount, null)

    fun getOreDict(): String = shape.getOreDict(material)

    fun getOreDicts(): List<String> = shape.getOreDicts(material)

    fun toMaterialStack(): MaterialStack = MaterialStack(material, shape.scale)

    override fun toString(): String = "${shape.name}:${material.name}"

}