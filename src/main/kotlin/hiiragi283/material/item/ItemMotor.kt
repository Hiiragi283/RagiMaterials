package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.machine.MachineModuleItem
import net.minecraft.item.ItemStack

object ItemMotor : HiiragiItem("motor"), MachineModuleItem {

    //    MachineModuleItem    //

    override fun getProcessTime(stack: ItemStack, base: Int): Int = base - 10

    override fun getEnergyRate(stack: ItemStack, base: Int): Int = base + 16

}