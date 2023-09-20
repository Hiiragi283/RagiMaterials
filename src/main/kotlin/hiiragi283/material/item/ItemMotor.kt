package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.module.IModuleItem
import net.minecraft.item.ItemStack

object ItemMotor : HiiragiItem("motor"), IModuleItem {

    //    IModuleItem    //

    override val processTime: (stack: ItemStack, base: Int) -> Int = { _, base -> base - 10 }

    override val energyRate: (stack: ItemStack, base: Int) -> Int = { _, base -> base + 16 }

    override val itemSlots: (stack: ItemStack, base: Int) -> Int = { _, base -> base }

    override val fluidSlots: (stack: ItemStack, base: Int) -> Int = { _, base -> base }

    override val machineTraits: (ItemStack) -> Set<MachineTrait> = { _ -> setOf() }

}