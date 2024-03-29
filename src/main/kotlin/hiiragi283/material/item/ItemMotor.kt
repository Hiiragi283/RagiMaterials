package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.machine.MachineModuleItem
import hiiragi283.material.api.machine.MachineTrait
import net.minecraft.item.ItemStack

object ItemMotor : HiiragiItem("motor"), MachineModuleItem {

    //    MachineModuleItem    //

    override val processTime: (stack: ItemStack, base: Int) -> Int = { _, base -> base - 10 }

    override val energyRate: (stack: ItemStack, base: Int) -> Int = { _, base -> base + 16 }

    override val itemSlots: (stack: ItemStack, base: Int) -> Int = { _, base -> base }

    override val fluidSlots: (stack: ItemStack, base: Int) -> Int = { _, base -> base }

    override val machineTraits: (ItemStack) -> Set<MachineTrait> = { _ -> setOf() }

}