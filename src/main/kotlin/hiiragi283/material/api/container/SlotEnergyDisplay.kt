package hiiragi283.material.api.container

import net.minecraft.inventory.InventoryBasic
import net.minecraft.inventory.Slot

class SlotEnergyDisplay(xPosition: Int, yPosition: Int) : Slot(InventoryBasic("", true, 0), 0, xPosition, yPosition)