package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.block.BlockModuleMachine
import hiiragi283.material.util.HiiragiNBTKey
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class ItemBlockModuleMachine(block: BlockModuleMachine) : HiiragiItemBlock(block, 0), IModuleItem {

    //    IModuleItem    //
    private fun getMachinePropertyTag(stack: ItemStack): NBTTagCompound =
        stack.getOrCreateSubCompound(HiiragiNBTKey.BLOCK_ENTITY_TAG).getCompoundTag(HiiragiNBTKey.MACHINE_PROPERTY)

    override val processTime: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack)
            .takeIf { tag: NBTTagCompound -> tag.hasKey(IMachineProperty.KEY_PROCESS) }
            ?.getInteger(IMachineProperty.KEY_PROCESS) ?: 100
    }

    override val energyRate: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack)
            .takeIf { tag: NBTTagCompound -> tag.hasKey(IMachineProperty.KEY_RATE) }
            ?.getInteger(IMachineProperty.KEY_RATE) ?: 32
    }

    override val itemSlots: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack)
            .takeIf { tag: NBTTagCompound -> tag.hasKey(IMachineProperty.KEY_ITEM) }
            ?.getInteger(IMachineProperty.KEY_ITEM) ?: 32
    }

    override val fluidSlots: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack)
            .takeIf { tag: NBTTagCompound -> tag.hasKey(IMachineProperty.KEY_FLUID) }
            ?.getInteger(IMachineProperty.KEY_FLUID) ?: 32
    }

    override val moduleTraits: (ItemStack) -> Set<ModuleTrait> = { _ -> setOf() }

}