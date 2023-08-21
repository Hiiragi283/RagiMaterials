package hiiragi283.material.tile

import com.cleanroommc.modularui.api.IGuiHolder
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
import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.fluid.HiiragiFluidTank
import hiiragi283.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.api.capability.item.HiiragiItemHandler
import hiiragi283.api.capability.item.HiiragiItemHandlerWrapper
import hiiragi283.api.tileentity.HiiragiProvider
import hiiragi283.api.tileentity.HiiragiTileEntity
import hiiragi283.material.util.HiiragiModularUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

class TileEntityCrucibleNew : HiiragiTileEntity(), HiiragiProvider.Inventory, HiiragiProvider.Tank, IGuiHolder {

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

    //    HiiragiProvider    //

    private lateinit var invInput: HiiragiItemHandler
    private lateinit var invCast: HiiragiItemHandler
    private lateinit var invOutput: HiiragiItemHandler

    override fun createInventory(): HiiragiCapabilityProvider<IItemHandler> {
        invInput = object : HiiragiItemHandler() {
            override fun onContentsChanged(slot: Int) {
                checkRecipe()
            }
        }
        invCast = object : HiiragiItemHandler() {
            override fun onContentsChanged(slot: Int) {
                checkRecipe()
            }
        }.setIOType(IOType.CATALYST)
        invOutput = HiiragiItemHandler().setIOType(IOType.OUTPUT)
        inventory = HiiragiItemHandlerWrapper(invInput, invCast, invOutput)
        return HiiragiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory)
    }

    private lateinit var tankTemp: HiiragiFluidTank

    override fun createTank(): HiiragiCapabilityProvider<IFluidHandler> {
        tankTemp = HiiragiFluidTank(144 * 9).setIOType(IOType.GENERAL)
        tank = HiiragiFluidTankWrapper(tankTemp)
        return HiiragiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank)
    }

    private fun checkRecipe() {}

    //    IGuiHolder    //

    override fun buildUI(
        guiCreationContext: GuiCreationContext,
        guiSyncManager: GuiSyncManager,
        isClient: Boolean
    ): ModularPanel {

        guiSyncManager.registerSlotGroup("crucible_input", 1)
        guiSyncManager.registerSlotGroup("crucible_cast", 1)
        guiSyncManager.registerSlotGroup("crucible_output", 1)

        guiSyncManager.syncValue("crucible_tank", 0, HiiragiModularUtil.fluidSlot(tankTemp))

        val panel = ModularPanel("crucible").also { it.flex().align(Alignment.Center) }

        panel.bindPlayerInventory()
            .child(Column()
                .padding(7)
                //Player Inventory
                .child(SlotGroupWidget.playerInventory())
                //Cast
                .child(SlotGroupWidget.builder()
                    .row("I")
                    .key('I') {
                        ItemSlot().slot(
                            HiiragiModularUtil.itemSlot(invCast, 0).slotGroup("crucible_cast")
                        )
                    }
                    .build()
                    .marginBottom(18)
                )
                .child(
                    Row()
                        .child(
                            SlotGroupWidget.builder()
                                .row("  I>F>O")
                                .key(' ') { EmptyWidget() }
                                .key('I') {
                                    ItemSlot().slot(
                                        HiiragiModularUtil.itemSlot(invInput, 0).slotGroup("crucible_input")
                                    )
                                }
                                .key('>') {
                                    ProgressWidget()
                                        .progress { 1.0 }
                                        .texture(GuiTextures.PROGRESS_ARROW, 20)
                                }
                                .key('F') { FluidSlot().syncHandler("crucible_tank", 0) }
                                .key('O') {
                                    ItemSlot().slot(
                                        HiiragiModularUtil.itemSlotOut(invOutput, 0).slotGroup("crucible_output")
                                    )
                                }
                                .build()
                        )
                )

            )

        return panel
    }

}