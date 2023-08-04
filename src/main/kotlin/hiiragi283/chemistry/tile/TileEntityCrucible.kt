package hiiragi283.chemistry.tile

import hiiragi283.api.block.IHeatSource
import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.fluid.HiiragiFluidTank
import hiiragi283.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.api.capability.item.HiiragiItemHandler
import hiiragi283.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.api.registry.HeatSourceRegistry
import hiiragi283.api.tileentity.HiiragiInteractionObject
import hiiragi283.api.tileentity.HiiragiProvider
import hiiragi283.api.tileentity.HiiragiTileEntity
import hiiragi283.chemistry.container.ContainerCrucible
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class TileEntityCrucible : HiiragiTileEntity(), HiiragiProvider.Inventory, HiiragiProvider.Tank,
    HiiragiInteractionObject<ContainerCrucible> {

    private fun getHeat(world: World, pos: BlockPos): Int {
        val state = world.getBlockState(pos)
        val block = state.block
        return if (block is IHeatSource) block.getHeat(world, pos.down(), state) else HeatSourceRegistry.getHeat(state)
    }

    //    RCTileEntity    //

    override fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean {
        if (!world.isRemote) {
            //openGui(player)
            player.sendMessage(TextComponentString("Heat Temperature: ${getHeat(world, pos.down())}"))
            return true
        }
        return false
    }

    //    HiiragiProvider    //

    lateinit var invInput: HiiragiItemHandler
    lateinit var tankOutput: HiiragiFluidTank

    override fun createInventory(): HiiragiCapabilityProvider<IItemHandler> {
        invInput = HiiragiItemHandler(1, this)
        inventory = HiiragiItemHandlerWrapper(invInput)
        return HiiragiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory)
    }

    override fun createTank(): HiiragiCapabilityProvider<IFluidHandler> {
        tankOutput = HiiragiFluidTank(144 * 9)
        tank = HiiragiFluidTankWrapper(tankOutput)
        return HiiragiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank)
    }

    //    HiiragiInteractionObject    //

    override val classContainer: Class<ContainerCrucible> = ContainerCrucible::class.java

    override val guiName: String = "crucible"

    override fun getDisplayName(): TextComponentTranslation = super<HiiragiInteractionObject>.getDisplayName()

}