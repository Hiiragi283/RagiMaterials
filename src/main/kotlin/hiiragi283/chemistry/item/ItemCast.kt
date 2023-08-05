package hiiragi283.chemistry.item

import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ICastItem
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.material.RMItems
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

object ItemCast : HiiragiItem("cast", 15), ICastItem {

    /**
     * 0 -> Ingot
     * 1 -> Nugget
     * 2 -> Plate
     * 3 -> Stick
     * 4 -> Gear
     * 5 ->
     * 6 ->
     * 7 ->
     * 8 ->
     * 9 ->
     * 10 ->
     * 11 ->
     * 12 ->
     * 13 ->
     * 14 ->
     * 15 ->
     */

    override fun getFluidAmount(stack: ItemStack): Int = when (stack.metadata) {
        0 -> 144
        1 -> 144 / 9
        2 -> 144
        3 -> 144 / 2
        4 -> 144 * 4
        else -> 0
    }

    override fun getResult(stack: ItemStack, fluid: FluidStack?): ItemStack {
        if (fluid != null) {
            val material = MaterialRegistry.getMaterial(fluid.fluid.name)
            if (material.isEmpty()) return ItemStack.EMPTY
            return when (stack.metadata) {
                0 -> RMItems.MATERIAL_INGOT.getItemStack(material)
                1 -> RMItems.MATERIAL_NUGGET.getItemStack(material)
                2 -> RMItems.MATERIAL_PLATE.getItemStack(material)
                3 -> RMItems.MATERIAL_STICK.getItemStack(material)
                4 -> RMItems.MATERIAL_GEAR.getItemStack(material)
                else -> ItemStack.EMPTY
            }
        } else return ItemStack.EMPTY
    }

}