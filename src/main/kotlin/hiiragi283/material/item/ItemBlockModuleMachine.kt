package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItemBlock
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.IMachinePropertyItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.block.BlockModuleMachine
import hiiragi283.material.util.HiiragiNBTKey
import hiiragi283.material.util.getIntegerOrNull
import hiiragi283.material.util.getOrCreateCompoundTag
import hiiragi283.material.util.getTagListOrNull
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.NonNullList
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ItemBlockModuleMachine(block: BlockModuleMachine) : HiiragiItemBlock(block, 32767), IMachinePropertyItem {

    val type: IMachineRecipe.Type = block.type

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun getItemStackDisplayName(stack: ItemStack): String =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.let(type::getTranslatedName)
            ?: super.getItemStackDisplayName(stack)

    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs, subItems: NonNullList<ItemStack>) {
        if (!isInCreativeTab(tab)) return
        HiiragiRegistries.MATERIAL_INDEX.getValues()
            .filter { it.isValidIndex() && it.isSolid() && HiiragiShapes.CASING.isValid(it) }
            .map { getItemStack(it) }
            .forEach(subItems::add)
    }

    //    IMachinePropertyItem    //

    private fun getMachinePropertyTag(stack: ItemStack): NBTTagCompound =
        stack.getOrCreateSubCompound(HiiragiNBTKey.BLOCK_ENTITY_TAG)
            .getOrCreateCompoundTag(HiiragiNBTKey.MACHINE_PROPERTY)

    override val recipeType: (ItemStack) -> IMachineRecipe.Type = { _ -> type }

    override val processTime: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(IMachineProperty.KEY_PROCESS) ?: 100
    }

    override val energyRate: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(IMachineProperty.KEY_RATE) ?: 32
    }

    override val itemSlots: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(IMachineProperty.KEY_ITEM) ?: 1
    }

    override val fluidSlots: (ItemStack) -> Int = { stack ->
        getMachinePropertyTag(stack).getIntegerOrNull(IMachineProperty.KEY_FLUID) ?: 0
    }

    override val machineTraits: (ItemStack) -> Set<MachineTrait> = { stack ->
        getMachinePropertyTag(stack).getTagListOrNull(IMachineProperty.KEY_TRAIT, Constants.NBT.TAG_STRING)
            ?.filterIsInstance<NBTTagString>()
            ?.map(NBTTagString::getString)
            ?.map(MachineTrait::valueOf)
            ?.toSet() ?: setOf()
    }

}