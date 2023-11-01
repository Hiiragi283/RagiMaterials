package hiiragi283.material.container

import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.container.SlotItemHandlerControllable
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.machine.MachineModuleItem
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.item.MaterialItemBlockCasing
import hiiragi283.material.util.*
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

        val machineBlock: ModuleMachineBlock = ModuleMachineBlock.REGISTRY[recipeType] ?: return

        val casingItem: MaterialItemBlockCasing = casing.getItemImplemented<MaterialItemBlockCasing>() ?: return
        val baseProperty: MachineProperty = casingItem.toMachineProperty(casing)
        val material: HiiragiMaterial? = casingItem.getMaterial(casing)

        val moduleProperty: MachineModuleItem? = module.getItemImplemented<MachineModuleItem>()

        if (moduleProperty == null) {
            outputInventory.setStackInSlot(0, machineBlock.createMachineStack(material, baseProperty))
        } else {

            val machineTraits: MutableSet<MachineTrait> = mutableSetOf()
            machineTraits.addAll(baseProperty.machineTraits)
            machineTraits.addAll(moduleProperty.getMachineTraits(module))

            outputInventory.setStackInSlot(
                0, machineBlock.createMachineStack(
                    material, MachineProperty.of(
                        recipeType = recipeType,
                        processTime = moduleProperty.getProcessTime(module, baseProperty.processTime),
                        energyRate = moduleProperty.getEnergyRate(module, baseProperty.energyRate),
                        itemSlots = moduleProperty.getItemSlots(module, baseProperty.itemSlots),
                        fluidSlots = moduleProperty.getFluidSlots(module, baseProperty.fluidSlots),
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
            override fun isItemValid(stack: ItemStack): Boolean = stack.isItemImplemented<MachineModuleItem>()
        })
        addSlotToContainer(object : SlotItemHandlerControllable(outputInventory, 0, getSlotPosX(6), getSlotPosY(1)) {
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