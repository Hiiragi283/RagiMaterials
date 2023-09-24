package hiiragi283.material.entity

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.entity.IHiiragiEntity
import hiiragi283.material.chunk.IEntityChunkLoader
import hiiragi283.material.network.HiiragiMessage
import hiiragi283.material.network.HiiragiNetworkWrapper
import hiiragi283.material.util.HiiragiNBTUtil
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityMinecart
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

/**
 * Thanks to defeatedcrow!
 * [: Source](https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/machine/entity/EntityMinecartMotor.java)
 */
class EntityMinecartTank : EntityMinecart, IEntityChunkLoader, IHiiragiEntity {

    constructor(world: World, x: Double, y: Double, z: Double) : super(world, x, y, z)

    constructor(world: World) : super(world)

    val tank: HiiragiFluidTank = object : HiiragiFluidTank(16000) {
        override fun onContentsChanged() {
            getFluid()?.let { fluidStack: FluidStack ->
                HiiragiNetworkWrapper.sendToAll(
                    HiiragiMessage.MinecartTank(
                        fluidStack.writeToNBT(NBTTagCompound()),
                        entityId
                    )
                )
            }
        }
    }

    //    EntityMinecart    //

    override fun getCartItem(): ItemStack = HiiragiItems.MINECART_TANK.getItemStack()

    override fun getDefaultDisplayTile(): IBlockState = Blocks.GLASS.defaultState

    override fun getType(): Type = Type.CHEST

    override fun processInitialInteract(player: EntityPlayer, hand: EnumHand): Boolean {
        if (super.processInitialInteract(player, hand)) return true
        if (!FluidUtil.interactWithFluidHandler(player, hand, tank)) {
            if (!world.isRemote) {
                openGui(player, world, position, this)
            }
        }
        return true
    }

    override fun readEntityFromNBT(compound: NBTTagCompound) {
        super.readEntityFromNBT(compound)
        tank.deserializeNBT(compound.getCompoundTag(HiiragiNBTUtil.TANK))
    }

    override fun writeEntityToNBT(compound: NBTTagCompound) {
        super.writeEntityToNBT(compound)
        compound.setTag(HiiragiNBTUtil.TANK, tank.serializeNBT())
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean =
        capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? =
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank)
        else super.getCapability(capability, facing)

    //    IEntityChunkLoader    //

    override fun canLoad(): Boolean = true

}