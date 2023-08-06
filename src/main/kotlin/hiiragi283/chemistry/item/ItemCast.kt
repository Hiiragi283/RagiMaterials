package hiiragi283.chemistry.item

import hiiragi283.api.item.HiiragiItem
import hiiragi283.api.item.ICastItem
import hiiragi283.api.item.ItemMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialRegistry
import hiiragi283.core.RMCreativeTabs
import net.minecraft.client.renderer.color.ItemColors
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemCast(val amount: Int, val item: ItemMaterial) : HiiragiItem("cast_${item.shape.name}", 0), ICastItem {

    init {
        creativeTab = RMCreativeTabs.COMMON
        maxDamage = 63
        maxStackSize = 1
    }

    //    ICastItem    //

    override fun getFluidAmount(stack: ItemStack): Int = amount

    override fun getResult(stack: ItemStack, fluid: FluidStack?): ItemStack {
        return if (fluid != null) {
            val material = MaterialRegistry.getMaterial(fluid.fluid.name)
            if (material.isSolid() && item.shape.isValid(material)) item.getItemStack(material)
            else ItemStack.EMPTY
        } else ItemStack.EMPTY
    }

    //    HiiragiEntry    //

    @SideOnly(Side.CLIENT)
    override fun registerColorItem(itemColors: ItemColors) {
        itemColors.registerItemColorHandler(
            { stack, tintIndex -> if (tintIndex == 1) MaterialCommon.WOOD.color else -1 },
            this
        )
    }

}