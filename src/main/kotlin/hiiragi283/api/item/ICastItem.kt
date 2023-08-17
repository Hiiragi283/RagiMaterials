package hiiragi283.api.item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

interface ICastItem {

    fun getCastItem(): Item

    fun getFluidAmount(stack: ItemStack): Int

    fun getResult(stack: ItemStack, fluid: FluidStack?): ItemStack

}