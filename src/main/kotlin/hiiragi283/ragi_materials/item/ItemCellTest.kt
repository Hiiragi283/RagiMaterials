package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.ItemBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack

class ItemCellTest: ItemBase(Reference.MOD_ID, "cell_test", 0) {

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/Hiiragi283/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/food/item/ItemSilverCup.java#L331
    */

    override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider {
        return CellProvider(stack)
    }

   private class CellProvider(val stack: ItemStack) : ICapabilityProvider {

       //capabilityを持っているか判別するメソッド
       override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
           return capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY
       }

       //capabilityを取得するメソッド
       override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
           return if (capability == CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY) FluidHandlerItemStack(stack, 1000) as T else null
       }
   }
}