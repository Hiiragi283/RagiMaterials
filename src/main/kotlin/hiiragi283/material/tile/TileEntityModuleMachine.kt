package hiiragi283.material.tile

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.capability.energy.HiiragiEnergyStorage
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.material.api.capability.fluid.ModuleMachineFluidTank
import hiiragi283.material.api.capability.item.HiiragiItemHandler
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.material.api.capability.item.ModuleMachineInputItemHandler
import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.MachineContents
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.HiiragiNBTKey
import hiiragi283.material.util.dropInventoriesItems
import hiiragi283.material.util.getItemImplemented
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentString
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.items.CapabilityItemHandler


class TileEntityModuleMachine : HiiragiTileEntity.Tickable(100) {

    override fun getDisplayName(): ITextComponent = TextComponentString("Module Machine")

    //    NBT    //

    var machineProperty: IMachineProperty = IMachineProperty.of()

    override fun readFromNBT(compound: NBTTagCompound) {
        inventoryInput.deserializeNBT(compound.getCompoundTag("InventoryInput"))
        inventoryOutput.deserializeNBT(compound.getCompoundTag("InventoryOutput"))
        tankInput0.deserializeNBT(compound.getCompoundTag("TankInput0"))
        tankInput1.deserializeNBT(compound.getCompoundTag("TankInput1"))
        tankInput2.deserializeNBT(compound.getCompoundTag("TankInput2"))
        tankOutput0.deserializeNBT(compound.getCompoundTag("TankOutput0"))
        tankOutput1.deserializeNBT(compound.getCompoundTag("TankOutput1"))
        tankOutput2.deserializeNBT(compound.getCompoundTag("TankOutput2"))
        energyStorage.deserializeNBT(compound.getCompoundTag(HiiragiNBTKey.BATTERY))
        machineProperty.deserializeNBT(compound.getCompoundTag(HiiragiNBTKey.MACHINE_PROPERTY))
        material = HiiragiRegistries.MATERIAL.getValue(compound.getString(HiiragiNBTKey.MATERIAL))
        initMachineProperty(machineProperty)
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
        compound.setTag(HiiragiNBTKey.BATTERY, energyStorage.serializeNBT())
        compound.setTag(HiiragiNBTKey.MACHINE_PROPERTY, machineProperty.serializeNBT())
        material?.name?.let { name: String -> compound.setString(HiiragiNBTKey.MATERIAL, name) }
        return super.writeToNBT(compound)
    }

    //    Capability    //

    val inventoryInput: ModuleMachineInputItemHandler = ModuleMachineInputItemHandler(this)
    val inventoryOutput: HiiragiItemHandler = HiiragiItemHandler(6, IOControllable.Type.OUTPUT, this)

    val tankInput0: ModuleMachineFluidTank = ModuleMachineFluidTank(0, IOControllable.Type.INPUT, this)
    val tankInput1: ModuleMachineFluidTank = ModuleMachineFluidTank(1, IOControllable.Type.INPUT, this)
    val tankInput2: ModuleMachineFluidTank = ModuleMachineFluidTank(2, IOControllable.Type.INPUT, this)
    val tankOutput0: ModuleMachineFluidTank = ModuleMachineFluidTank(3, IOControllable.Type.OUTPUT, this)
    val tankOutput1: ModuleMachineFluidTank = ModuleMachineFluidTank(4, IOControllable.Type.OUTPUT, this)
    val tankOutput2: ModuleMachineFluidTank = ModuleMachineFluidTank(5, IOControllable.Type.OUTPUT, this)

    fun getTank(index: Int): ModuleMachineFluidTank = when (index) {
        1 -> tankInput1
        2 -> tankInput2
        3 -> tankOutput0
        4 -> tankOutput1
        5 -> tankOutput2
        else -> tankInput0
    }

    var tank: HiiragiFluidTankWrapper = HiiragiFluidTankWrapper(tankOutput0, tankOutput1, tankOutput2)

    var energyStorage: HiiragiEnergyStorage = HiiragiEnergyStorage(0)

    fun initMachineProperty(property: IMachineProperty) {
        machineProperty = property
        maxCount = property.getProcessTime()
        inventoryInput.maxSlots = property.getItemSlots()
        val list: MutableList<HiiragiFluidTank> = mutableListOf()
        if (property.getFluidSlots() >= 1) {
            list.add(tankInput0)
        }
        if (property.getFluidSlots() >= 2) {
            list.add(tankInput1)
        }
        if (property.getFluidSlots() >= 3) {
            list.add(tankInput2)
        }
        list.add(tankOutput0)
        list.add(tankOutput1)
        list.add(tankOutput2)
        tank = HiiragiFluidTankWrapper(list)
        energyStorage.setCapacity(property.getEnergyCapacity())
    }

    fun getMachineContents(): MachineContents =
        MachineContents(inventoryInput, tankInput0, tankInput1, tankInput2, machineProperty)

    private val listCapability: List<Capability<*>> = listOf(
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
        CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
        CapabilityEnergy.ENERGY
    )

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability in listCapability

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? = when (capability) {

        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY -> CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
            .cast(HiiragiItemHandlerWrapper(inventoryInput, inventoryOutput))

        CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY -> CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
            .cast(tank)

        CapabilityEnergy.ENERGY -> CapabilityEnergy.ENERGY
            .cast(energyStorage)

        else -> super.getCapability(capability, facing)

    }

    //    Event    //

    var material: HiiragiMaterial? = null

    override fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean {
        if (!world.isRemote) openGui(player, world, pos)
        return true
    }

    override fun onTilePlaced(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        placer: EntityLivingBase,
        stack: ItemStack
    ) {
        material = HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
        stack.getItemImplemented<IModuleItem>()?.toMachineProperty(stack)?.let(this::initMachineProperty)
    }

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropInventoriesItems(world, pos, inventoryInput, inventoryOutput)
    }

}