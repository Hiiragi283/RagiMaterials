package hiiragi283.material.tile

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.capability.energy.HiiragiEnergyStorage
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.material.api.capability.fluid.ModuleMachineFluidTank
import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.material.api.capability.item.ModuleMachineInputItemHandler
import hiiragi283.material.api.machine.MachineProperty
import hiiragi283.material.api.machine.MachinePropertyItem
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.dropInventoriesItems
import hiiragi283.material.util.getItemImplemented
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import kotlin.math.min


class TileEntityModuleMachine : HiiragiTileEntity(), ITickable, HiiragiMaterial.TILE {

    override fun getDisplayName(): ITextComponent =
        TextComponentTranslation(machineProperty.recipeType.translationKey, material?.getTranslatedName())

    override var material: HiiragiMaterial? = null

    //    NBT    //

    var machineProperty: MachineProperty = MachineProperty.of()

    override fun readFromNBT(compound: NBTTagCompound) {
        inventoryInput.deserializeNBT(compound.getCompoundTag("InventoryInput"))
        inventoryOutput.deserializeNBT(compound.getCompoundTag("InventoryOutput"))
        tankInput0.deserializeNBT(compound.getCompoundTag("TankInput0"))
        tankInput1.deserializeNBT(compound.getCompoundTag("TankInput1"))
        tankInput2.deserializeNBT(compound.getCompoundTag("TankInput2"))
        tankOutput0.deserializeNBT(compound.getCompoundTag("TankOutput0"))
        tankOutput1.deserializeNBT(compound.getCompoundTag("TankOutput1"))
        tankOutput2.deserializeNBT(compound.getCompoundTag("TankOutput2"))
        energyStorage.deserializeNBT(compound.getCompoundTag(HiiragiNBTUtil.BATTERY))
        machineProperty = MachineProperty.of(compound.getCompoundTag(HiiragiNBTUtil.MACHINE_PROPERTY))
        material = HiiragiRegistries.MATERIAL.getValue(compound.getString(HiiragiNBTUtil.MATERIAL))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag("InventoryInput", inventoryInput.serializeNBT())
        compound.setTag("InventoryOutput", inventoryOutput.serializeNBT())
        compound.setTag("TankInput0", tankInput0.serializeNBT())
        compound.setTag("TankInput1", tankInput1.serializeNBT())
        compound.setTag("TankInput2", tankInput2.serializeNBT())
        compound.setTag("TankOutput0", tankOutput0.serializeNBT())
        compound.setTag("TankOutput1", tankOutput1.serializeNBT())
        compound.setTag("TankOutput2", tankOutput2.serializeNBT())
        compound.setTag(HiiragiNBTUtil.BATTERY, energyStorage.serializeNBT())
        compound.setTag(HiiragiNBTUtil.MACHINE_PROPERTY, machineProperty.serialize())
        material?.name?.let { name: String -> compound.setString(HiiragiNBTUtil.MATERIAL, name) }
        return super.writeToNBT(compound)
    }

    //    Capability    //

    val inventoryInput: ModuleMachineInputItemHandler = ModuleMachineInputItemHandler(this)
    val inventoryOutput: HiiragiItemHandler = HiiragiItemHandler(6, IOControllable.Type.OUTPUT, this)

    private val tankInput0: ModuleMachineFluidTank = ModuleMachineFluidTank(0, this)
    private val tankInput1: ModuleMachineFluidTank = ModuleMachineFluidTank(1, this)
    private val tankInput2: ModuleMachineFluidTank = ModuleMachineFluidTank(2, this)
    private val tankOutput0: ModuleMachineFluidTank = ModuleMachineFluidTank(3, this, IOControllable.Type.OUTPUT)
    private val tankOutput1: ModuleMachineFluidTank = ModuleMachineFluidTank(4, this, IOControllable.Type.OUTPUT)
    private val tankOutput2: ModuleMachineFluidTank = ModuleMachineFluidTank(5, this, IOControllable.Type.OUTPUT)

    fun getTank(index: Int): ModuleMachineFluidTank = when (index % 6) {
        1 -> tankInput1
        2 -> tankInput2
        3 -> tankOutput0
        4 -> tankOutput1
        5 -> tankOutput2
        else -> tankInput0
    }

    val energyStorage: HiiragiEnergyStorage = HiiragiEnergyStorage(0)

    private val listCapability: List<Capability<*>> = listOf(
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
        CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
        CapabilityEnergy.ENERGY
    )

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean =
        capability in listCapability || super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? = when (capability) {

        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
            .cast(HiiragiItemHandlerWrapper(inventoryInput, inventoryOutput))

        CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY -> CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
            .cast(HiiragiFluidTankWrapper(tankInput0, tankInput1, tankInput2, tankOutput0, tankOutput1, tankOutput2))

        CapabilityEnergy.ENERGY -> CapabilityEnergy.ENERGY
            .cast(energyStorage)

        else -> super.getCapability(capability, facing)

    }

    //    Event    //

    override fun onTilePlaced(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
        material = HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
        stack.getItemImplemented<MachinePropertyItem>()?.toMachineProperty(stack)?.let { property ->
            machineProperty = property
            inventoryInput.maxSlots = min(property.itemSlots, 6)
            if (property.fluidSlots >= 1) {
                tankInput0.setIOType(IOControllable.Type.INPUT)
            }
            if (property.fluidSlots >= 2) {
                tankInput1.setIOType(IOControllable.Type.INPUT)
            }
            if (property.fluidSlots >= 3) {
                tankInput2.setIOType(IOControllable.Type.INPUT)
            }
            energyStorage.capacity = property.getEnergyCapacity()
        }
    }

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropInventoriesItems(world, pos, inventoryInput, inventoryOutput)
    }

    //    Tickable    //

    var currentCount: Int = 0

    fun getProgress(): Double = currentCount.toDouble() / machineProperty.processTime.toDouble()

    override fun update() {
        if (currentCount >= machineProperty.processTime) {
            if (!world.isRemote) {
                processRecipe()
            }
            currentCount = 0
        } else {
            currentCount++
        }
    }

    fun processRecipe() {
        HiiragiRegistries.MACHINE_RECIPE.getValue(machineProperty.recipeType)?.getValues()
            ?.firstOrNull { recipe: IMachineRecipe -> IMachineRecipe.matches(recipe, this) }
            ?.let { recipe: IMachineRecipe -> IMachineRecipe.process(recipe, this) }
    }

}