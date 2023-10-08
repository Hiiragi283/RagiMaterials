package hiiragi283.material.api.item

import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.machine.MachinePropertyItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.*
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ModuleMachineItemBlock(block: ModuleMachineBlock) : HiiragiItemBlock(block, 32767), MachinePropertyItem {

    val type: MachineType = block.type

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        this.addTooltip(stack, tooltip)
    }

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.let(type::getTranslatedName)
            ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter(HiiragiMaterial::isValidIndex)
            .filter(HiiragiShapes.CASING::isValid)
            .map(::itemStack)
            .forEach(subItems::add)
    }

    //    MachinePropertyItem    //

    private fun getMachinePropertyTag(stack: ItemStack): NBTTagCompound =
        stack.getOrCreateSubCompound(HiiragiNBTUtil.BLOCK_ENTITY_TAG)
            .getOrCreateCompoundTag(HiiragiNBTUtil.MACHINE_PROPERTY)

    override val recipeType: (ItemStack) -> MachineType = { _ -> type }

    override val processTime: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_PROCESS) ?: 100
    }

    override val energyRate: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_RATE) ?: 32
    }

    override val itemSlots: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_ITEM) ?: 1
    }

    override val fluidSlots: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_FLUID) ?: 0
    }

    override val machineTraits: (ItemStack) -> Set<MachineTrait> = { stack ->
        getMachinePropertyTag(stack).getTagListOrNull(MachineProperty.KEY_TRAIT, Constants.NBT.TAG_STRING)
            ?.filterIsInstance<NBTTagString>()
            ?.map(NBTTagString::getString)
            ?.mapNotNull(MachineTrait.Companion::from)
            ?.toSet() ?: setOf()
    }

}