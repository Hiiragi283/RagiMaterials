package hiiragi283.material.item

import hiiragi283.material.api.item.MaterialItemBlock
import hiiragi283.material.api.machine.MachinePropertyItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.block.MaterialBlockCasing
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MaterialItemBlockCasing(block: MaterialBlockCasing) : MaterialItemBlock(block), MachinePropertyItem {

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        this.addTooltip(stack, tooltip)
    }

    //    MachinePropertyItem    //

    override fun getProcessTime(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.processTime
        ?: super.getProcessTime(stack)

    override fun getEnergyRate(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.energyRate
        ?: super.getEnergyRate(stack)

    override fun getItemSlots(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.itemSlots
        ?: super.getItemSlots(stack)

    override fun getFluidSlots(stack: ItemStack): Int = getMaterial(stack)
        ?.machineProperty
        ?.fluidSlots
        ?: super.getFluidSlots(stack)

    override fun getMachineTraits(stack: ItemStack): Set<MachineTrait> = getMaterial(stack)
        ?.machineProperty
        ?.machineTraits
        ?: super.getMachineTraits(stack)

}