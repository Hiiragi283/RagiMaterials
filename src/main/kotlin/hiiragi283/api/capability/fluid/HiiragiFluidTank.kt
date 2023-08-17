package hiiragi283.api.capability.fluid

import hiiragi283.api.capability.CapabilityIO
import hiiragi283.api.capability.IOType
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.*
import net.minecraftforge.fluids.FluidEvent.FluidDrainingEvent
import net.minecraftforge.fluids.FluidEvent.FluidFillingEvent
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fluids.capability.IFluidTankProperties
import kotlin.math.min


open class HiiragiFluidTank(@JvmField var fluid: FluidStack?, @JvmField var capacity: Int) : IFluidTank, IFluidHandler,
    CapabilityIO<HiiragiFluidTank>, INBTSerializable<NBTTagCompound> {

    @JvmField
    var tile: TileEntity? = null

    @JvmField
    var canFill = true

    @JvmField
    var canDrain = true

    @JvmField
    var tankProperties: Array<IFluidTankProperties> = arrayOf(HiiragiTankProperties(this))

    constructor(capacity: Int) : this(null, capacity)
    constructor(fluid: Fluid?, amount: Int, capacity: Int) : this(FluidStack(fluid, amount), capacity)

    //    IFluidTank    //

    override fun getFluid(): FluidStack? = fluid

    open fun setFluid(fluid: FluidStack?): HiiragiFluidTank = also { this.fluid = fluid }

    override fun getFluidAmount(): Int = fluid?.amount ?: 0

    override fun getCapacity(): Int = capacity

    fun setTileEntity(tile: TileEntity?): HiiragiFluidTank = also { this.tile = tile }

    override fun getInfo(): FluidTankInfo = FluidTankInfo(this)

    //    IFluidHandler    //

    override fun getTankProperties(): Array<IFluidTankProperties> = tankProperties

    override fun fill(resource: FluidStack?, doFill: Boolean): Int =
        if (canFillFluidType(resource)) fillInternal(resource, doFill) else 0

    open fun fillInternal(resource: FluidStack?, doFill: Boolean): Int {
        if (resource == null || resource.amount <= 0) return 0
        if (!doFill) {
            if (fluid == null) return min(capacity, resource.amount)
            return if (!fluid!!.isFluidEqual(resource)) 0 else min((capacity - fluid!!.amount), resource.amount)
        }
        if (fluid == null) {
            fluid = FluidStack(resource, min(capacity, resource.amount))
            onContentsChanged()
            tile?.let {
                FluidEvent.fireEvent(FluidFillingEvent(fluid, it.world, it.pos, this, fluid!!.amount))
            }
            return fluid!!.amount
        }
        if (!fluid!!.isFluidEqual(resource)) return 0
        var filled: Int = capacity - fluid!!.amount
        if (resource.amount < filled) {
            fluid!!.amount += resource.amount
            filled = resource.amount
        } else {
            fluid!!.amount = capacity
        }
        onContentsChanged()
        tile?.let {
            FluidEvent.fireEvent(FluidFillingEvent(fluid, it.world, it.pos, this, filled))
        }
        return filled
    }

    override fun drain(resource: FluidStack?, doDrain: Boolean): FluidStack? =
        if (!canDrainFluidType(getFluid())) null else drainInternal(resource, doDrain)

    override fun drain(maxDrain: Int, doDrain: Boolean): FluidStack? =
        if (!canDrainFluidType(fluid)) null else drainInternal(maxDrain, doDrain)

    open fun drainInternal(resource: FluidStack?, doDrain: Boolean): FluidStack? =
        if (resource == null || !resource.isFluidEqual(getFluid())) null else drainInternal(resource.amount, doDrain)

    open fun drainInternal(maxDrain: Int, doDrain: Boolean): FluidStack? {
        if (fluid == null || maxDrain <= 0) return null
        var drained: Int = min(fluid!!.amount, maxDrain)
        val stack = FluidStack(fluid, drained)
        if (doDrain) {
            fluid!!.amount -= drained
            if (fluid!!.amount <= 0) fluid = null
            onContentsChanged()
            tile?.let {
                FluidEvent.fireEvent(FluidDrainingEvent(fluid, it.world, it.pos, this, drained))
            }
        }
        return stack
    }

    open fun canFillFluidType(fluid: FluidStack?): Boolean = canFill

    open fun canDrainFluidType(fluid: FluidStack?): Boolean = fluid != null && canDrain

    protected open fun onContentsChanged() {}

    //    ICapabilityIO    //

    override var ioType: IOType = IOType.INPUT

    override fun getIOType(): IOType = ioType

    override fun setIOType(type: IOType): HiiragiFluidTank = also { ioType = type }

    //    INBTSerializable    //

    override fun serializeNBT() = NBTTagCompound().also { fluid?.writeToNBT(it) }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        fluid = FluidStack.loadFluidStackFromNBT(tag ?: NBTTagCompound())
    }

}