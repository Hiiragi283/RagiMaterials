package hiiragi283.material.api.part

import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.ItemTooltipEvent

data class PartStack(val part: HiiragiPart, var count: Int = 1) {

    companion object {

        @JvmStatic
        fun fromStack(itemStack: ItemStack): PartStack? =
            PartDictionary.getPart(itemStack)?.getPartStack(itemStack.count)

    }

    //    Conversion    //

    fun addTooltip(event: ItemTooltipEvent) {
        addTooltip(event.toolTip)
    }

    fun addTooltip(tooltip: MutableList<String>) {
        part.addTooltip(tooltip, count)
    }

    fun getScale(): Int = part.getScale() * count

    fun toItemStack(): ItemStack? = PartDictionary.getStack(part, count)

}