package hiiragi283.material.tile;

import hiiragi283.material.api.capability.IOControllable;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper;
import hiiragi283.material.api.capability.item.HiiragiItemHandler;
import hiiragi283.material.api.capability.item.HiiragiItemHandlerWrapper;
import hiiragi283.material.api.tile.MaterialTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TileEntityTestMachine extends MaterialTileEntity {

    private final HiiragiFluidTank inputTank0 = new HiiragiFluidTank(64000, IOControllable.Type.INPUT);
    private final HiiragiFluidTank inputTank1 = new HiiragiFluidTank(64000, IOControllable.Type.INPUT);
    private final HiiragiFluidTank inputTank2 = new HiiragiFluidTank(64000, IOControllable.Type.INPUT);
    private final HiiragiFluidTank outputTank0 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    private final HiiragiFluidTank outputTank1 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    private final HiiragiFluidTank outputTank2 = new HiiragiFluidTank(64000, IOControllable.Type.OUTPUT);
    private final HiiragiItemHandler inputInv = new HiiragiItemHandler(6, IOControllable.Type.INPUT);
    private final HiiragiItemHandler outputInv = new HiiragiItemHandler(6, IOControllable.Type.OUTPUT);

    public TileEntityTestMachine() {
        this.inventory = new HiiragiItemHandlerWrapper(inputInv, outputInv);
        this.tank = new HiiragiFluidTankWrapper(inputTank0, inputTank1, inputTank2, outputTank0, outputTank1, outputTank2);
    }

    //    Event    //

    @Override
    public boolean onTileActivated(World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing) {
        //if (!world.isRemote) GuiInfos.TILE_ENTITY.open(player, world, pos);
        return true;
    }

    //    Capability    //

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank);
        } else {
            return null;
        }
    }

    //    IGuiHolder    //

    /*@Override
    public ModularPanel buildUI(GuiCreationContext creationContext, GuiSyncManager syncManager, boolean isClient) {

        syncManager.registerSlotGroup("inputInv", 3);
        syncManager.registerSlotGroup("outputInv", 3);

        syncManager.syncValue("tank", 0, SyncHandlers.fluidSlot(inputTank0));
        syncManager.syncValue("tank", 1, SyncHandlers.fluidSlot(inputTank1));
        syncManager.syncValue("tank", 2, SyncHandlers.fluidSlot(inputTank2));
        syncManager.syncValue("tank", 3, SyncHandlers.fluidSlot(outputTank0));
        syncManager.syncValue("tank", 4, SyncHandlers.fluidSlot(outputTank1));
        syncManager.syncValue("tank", 5, SyncHandlers.fluidSlot(outputTank2));

        ModularPanel panel = new ModularPanel("test_machine");
        panel.flex().align(Alignment.Center);
        panel.bindPlayerInventory();

        panel.child(new Column()
                .padding(16)
                .child(SlotGroupWidget.playerInventory())
                .child(SlotGroupWidget.builder()
                        .matrix("III OOO",
                                "III>OOO",
                                "FFF FFF")
                        .key('I', index -> new ItemSlot().slot(SyncHandlers.itemSlot(inputInv, index).slotGroup("inputInv")))
                        .key('O', index -> new ItemSlot().slot(new ModularOutputSlot(outputInv, index).slotGroup("outputInv")))
                        .key('>', index -> new ProgressWidget().progress(() -> 1).texture(GuiTextures.PROGRESS_ARROW, 20))
                        .key('F', index -> new FluidSlot().syncHandler("tank", index))
                        .build())
        );

        return panel;
    }*/

}