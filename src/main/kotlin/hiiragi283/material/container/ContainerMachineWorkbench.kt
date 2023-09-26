package hiiragi283.material.container

import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.container.SlotOutputItemHandler
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.item.MaterialItemBlockCasing
import hiiragi283.material.util.dropInventoryItems
import hiiragi283.material.util.getItemImplemented
import hiiragi283.material.util.isItemImplemented
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler

class ContainerMachineWorkbench(player: EntityPlayer) : HiiragiContainer(player) {

    private val inputInventory = object : HiiragiItemHandler(3) {

        override fun getSlotLimit(slot: Int): Int = 1

        override fun onContentsChanged(slot: Int) {
            super.onContentsChanged(slot)
            updateOutput()
        }

    }

    private val outputInventory = HiiragiItemHandler(1)

    fun updateOutput() {

        if (!outputInventory.isEmpty()) {
            outputInventory.setStackInSlot(0, ItemStack.EMPTY)
        }

        val casing: ItemStack = inputInventory.getStackInSlot(0)
        val recipeModule: ItemStack = inputInventory.getStackInSlot(1)
        val module: ItemStack = inputInventory.getStackInSlot(2)

        val recipeType: MachineType = recipeModule.getItemImplemented<RecipeModuleItem>()
            ?.recipeType ?: return

        val machineBlock: ModuleMachineBlock = HiiragiRegistries.MODULE_MACHINE.getValue(recipeType) ?: return

        val baseProperty: IMachineProperty = casing.getItemImplemented<MaterialItemBlockCasing>()
            ?.toMachineProperty(casing)
            ?: return

        val moduleProperty: IModuleItem? = module.getItemImplemented<IModuleItem>()

        if (moduleProperty == null) {
            outputInventory.setStackInSlot(0, machineBlock.createMachineStack(casing.metadata, baseProperty))
        } else {

            val machineTraits: MutableSet<MachineTrait> = mutableSetOf()
            machineTraits.addAll(baseProperty.machineTraits)
            machineTraits.addAll(moduleProperty.machineTraits(module))

            outputInventory.setStackInSlot(
                0, machineBlock.createMachineStack(
                    casing.metadata, IMachineProperty.of(
                        recipeType = recipeType,
                        processTime = moduleProperty.processTime(module, baseProperty.processTime),
                        energyRate = moduleProperty.energyRate(module, baseProperty.energyRate),
                        itemSlots = moduleProperty.itemSlots(module, baseProperty.itemSlots),
                        fluidSlots = moduleProperty.fluidSlots(module, baseProperty.fluidSlots),
                        machineTraits = machineTraits
                    )
                )
            )

        }

    }

    init {
        addSlotToContainer(object : SlotItemHandler(inputInventory, 0, getSlotPosX(2), getSlotPosY(1)) {
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<MaterialItemBlockCasing>()
        })
        addSlotToContainer(object : SlotItemHandler(inputInventory, 1, getSlotPosX(3), getSlotPosY(1)) {
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<RecipeModuleItem>()
        })
        addSlotToContainer(object : SlotItemHandler(inputInventory, 2, getSlotPosX(4), getSlotPosY(1)) {
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<IModuleItem>()
        })
        addSlotToContainer(object : SlotOutputItemHandler(outputInventory, 0, getSlotPosX(6), getSlotPosY(1)) {
            override fun onTake(player: EntityPlayer, stack: ItemStack): ItemStack {
                inputInventory.clear()
                return super.onTake(player, stack)
            }
        })
        initSlotsPlayer(84)
    }

    override fun transferStackInSlot(player: EntityPlayer, index: Int): ItemStack {
        var stack: ItemStack = ItemStack.EMPTY
        val slot: Slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot: ItemStack = slot.stack
            stack = stackSlot.copy()
            if (index in (0..3)) {
                if (!mergeItemStack(stackSlot, 4, inventorySlots.size, true)) return ItemStack.EMPTY
            } else {
                if (!mergeItemStack(stackSlot, 0, 3, false)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) {
                slot.putStack(ItemStack.EMPTY)
            } else slot.onSlotChanged()
        }
        return stack
    }

    override fun onContainerClosed(player: EntityPlayer) {
        super.onContainerClosed(player)
        dropInventoryItems(player.world, player.position, inputInventory)
    }

}