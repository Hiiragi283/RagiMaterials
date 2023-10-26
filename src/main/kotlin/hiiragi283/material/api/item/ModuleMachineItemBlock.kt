package hiiragi283.material.api.item

import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.machine.MachinePropertyItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapes
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
            .filter(HiiragiShapes.CASING::canCreateMaterialItem)
            .map(::itemStack)
            .forEach(subItems::add)
    }

    //    MachinePropertyItem    //

    private fun getMachinePropertyTag(stack: ItemStack): NBTTagCompound =
        stack.getOrCreateSubCompound(HiiragiNBTUtil.BLOCK_ENTITY_TAG)
            .getOrCreateCompoundTag(HiiragiNBTUtil.MACHINE_PROPERTY)

    override fun getRecipeType(stack: ItemStack): MachineType = type

    override fun getProcessTime(stack: ItemStack): Int =
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_PROCESS) ?: super.getProcessTime(stack)

    override fun getEnergyRate(stack: ItemStack): Int =
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_RATE) ?: super.getEnergyRate(stack)

    override fun getItemSlots(stack: ItemStack): Int =
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_ITEM) ?: super.getItemSlots(stack)

    override fun getFluidSlots(stack: ItemStack): Int =
        getMachinePropertyTag(stack).getIntegerOrNull(MachineProperty.KEY_FLUID) ?: super.getFluidSlots(stack)

    override fun getMachineTraits(stack: ItemStack): Set<MachineTrait> =
        getMachinePropertyTag(stack).getTagListOrNull(MachineProperty.KEY_TRAIT, Constants.NBT.TAG_STRING)
            ?.filterIsInstance<NBTTagString>()
            ?.map(NBTTagString::getString)
            ?.mapNotNull(MachineTrait.Companion::from)
            ?.toSet() ?: super.getMachineTraits(stack)

}