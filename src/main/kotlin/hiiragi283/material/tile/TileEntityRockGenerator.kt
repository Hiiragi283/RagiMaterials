package hiiragi283.material.tile

import com.cleanroommc.modularui.api.IGuiHolder
import com.cleanroommc.modularui.api.drawable.IDrawable.DrawableWidget
import com.cleanroommc.modularui.drawable.GuiTextures
import com.cleanroommc.modularui.manager.GuiCreationContext
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.utils.Alignment
import com.cleanroommc.modularui.value.sync.GuiSyncManager
import com.cleanroommc.modularui.widget.EmptyWidget
import com.cleanroommc.modularui.widgets.FluidSlot
import com.cleanroommc.modularui.widgets.ItemSlot
import com.cleanroommc.modularui.widgets.ProgressWidget
import com.cleanroommc.modularui.widgets.SlotGroupWidget
import com.cleanroommc.modularui.widgets.layout.Column
import com.cleanroommc.modularui.widgets.layout.Row
import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.fluid.HiiragiFluidTank
import hiiragi283.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.api.capability.item.HiiragiItemHandler
import hiiragi283.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.api.tile.HiiragiProvider
import hiiragi283.api.tile.HiiragiProvider.Tank
import hiiragi283.api.tile.HiiragiTileEntity
import hiiragi283.material.util.HiiragiModularUtil
import hiiragi283.material.util.playSound
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class TileEntityRockGenerator : HiiragiTileEntity.Tickable(20 * 5), HiiragiProvider.Inventory, Tank, IGuiHolder {

    //    HiiragiProvider    //

    private lateinit var invCatalyst: HiiragiItemHandler
    private lateinit var invOutput: HiiragiItemHandler
    override fun createInventory(): HiiragiCapabilityProvider<IItemHandler> {
        invCatalyst = object : HiiragiItemHandler() {
            override fun getSlotLimit(slot: Int): Int = 1
        }.setIOType(IOType.CATALYST)
        invOutput = HiiragiItemHandler().setIOType(IOType.OUTPUT)
        inventory = HiiragiItemHandlerWrapper(invCatalyst, invOutput)
        return HiiragiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory)
    }

    private lateinit var tankWater: HiiragiFluidTank
    private lateinit var tankLava: HiiragiFluidTank

    override fun createTank(): HiiragiCapabilityProvider<IFluidHandler> {
        tankWater = object : HiiragiFluidTank(1000) {
            override fun canFillFluidType(fluid: FluidStack?): Boolean =
                fluid != null && fluid.fluid === FluidRegistry.WATER
        }
        tankLava = object : HiiragiFluidTank(1000) {
            override fun canFillFluidType(fluid: FluidStack?): Boolean =
                fluid != null && fluid.fluid === FluidRegistry.LAVA
        }
        tank = HiiragiFluidTankWrapper(tankWater, tankLava)
        return HiiragiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank)
    }

    //    IGuiHolder    //
    override fun buildUI(
        creationContext: GuiCreationContext,
        syncManager: GuiSyncManager,
        isClient: Boolean
    ): ModularPanel {

        syncManager.registerSlotGroup("rock_generator_catalyst", 1)
        syncManager.registerSlotGroup("rock_generator_output", 1)

        syncManager.syncValue("rock_generator_water", 0, HiiragiModularUtil.fluidSlot(tankWater))
        syncManager.syncValue("rock_generator_lava", 1, HiiragiModularUtil.fluidSlot(tankLava))

        return ModularPanel("rock_generator")
            .also { it.flex().align(Alignment.Center) }
            .child(Column()
                .padding(7)
                //Player Inventory
                .child(SlotGroupWidget.playerInventory())
                //Catalyst
                .child(SlotGroupWidget.builder()
                    .row("I")
                    .key('I') {
                        ItemSlot().slot(
                            HiiragiModularUtil.itemSlot(invCatalyst, 0)
                                .slotGroup("rock_generator_catalyst")
                        )
                    }
                    .build()
                )
                .child(DrawableWidget(GuiTextures.DOWNLOAD))
                .child(
                    Row() //Water, Output, Lava
                        .child(SlotGroupWidget.builder()
                            .row("  W>I<L")
                            .key(' ') { EmptyWidget() }
                            .key('W') { FluidSlot().syncHandler("rock_generator_water", 0) }
                            .key('>') {
                                ProgressWidget()
                                    .progress { getProgress() }
                                    .texture(GuiTextures.PROGRESS_ARROW, 20)
                            }
                            .key('I') {
                                ItemSlot().slot(
                                    HiiragiModularUtil.itemSlotOut(invOutput, 0)
                                        .slotGroup("rock_generator_output")
                                )
                            }
                            .key('<') {
                                ProgressWidget()
                                    .progress { 1.0 - getProgress() }
                                    .texture(HiiragiModularUtil.PROGRESS_ARROW_INVERTED, 20)
                            }
                            .key('L') { FluidSlot().syncHandler("rock_generator_lava", 1) }
                            .build())
                )
            )
    }

    private fun getProgress() = countdown.toDouble() / maxCount

    //    ITickable    //
    override fun onUpdateServer() {
        HiiragiRegistry.ROCK_GENERATION.valuesCollection
            .firstOrNull { it.matches(invCatalyst.getStackInSlot(0).copy()) }
            ?.let {
                val attempt: ItemStack = invOutput.insertItem(0, it.getOutput(), true)
                if (attempt.isEmpty) {
                    invOutput.insertItem(0, it.getOutput(), false)
                    playSound(this, SoundEvents.BLOCK_STONE_BREAK)
                }
            }
    }

}