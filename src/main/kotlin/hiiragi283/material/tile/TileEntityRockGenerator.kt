package hiiragi283.material.tile;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.drawable.GuiTextures;
import com.cleanroommc.modularui.manager.GuiCreationContext;
import com.cleanroommc.modularui.manager.GuiInfos;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.sync.GuiSyncManager;
import com.cleanroommc.modularui.value.sync.SyncHandlers;
import com.cleanroommc.modularui.widget.EmptyWidget;
import com.cleanroommc.modularui.widgets.FluidSlot;
import com.cleanroommc.modularui.widgets.ItemSlot;
import com.cleanroommc.modularui.widgets.ProgressWidget;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.layout.Column;
import com.cleanroommc.modularui.widgets.layout.Row;
import hiiragi283.api.HiiragiRegistry;
import hiiragi283.api.capability.HiiragiCapabilityProvider;
import hiiragi283.api.capability.IOType;
import hiiragi283.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.api.capability.fluid.HiiragiFluidTankWrapper;
import hiiragi283.api.capability.item.HiiragiItemHandler;
import hiiragi283.api.capability.item.HiiragiItemHandlerWrapper;
import hiiragi283.api.drawable.HiiragiGuiTextures;
import hiiragi283.api.tileentity.HiiragiProvider;
import hiiragi283.api.tileentity.HiiragiTileEntity;
import hiiragi283.api.widgets.ModularSlotOut;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityRockGenerator extends HiiragiTileEntity.Tickable implements HiiragiProvider.Inventory, HiiragiProvider.Tank, IGuiHolder {

    public TileEntityRockGenerator() {
        super(20 * 5);
    }

    //    HiiragiTileEntity    //

    @Override
    public boolean onTileActivated(@NotNull World world, @NotNull BlockPos pos, @NotNull EntityPlayer player, @NotNull EnumHand hand, @NotNull EnumFacing facing) {
        if (!world.isRemote) GuiInfos.TILE_ENTITY.open(player, world, pos);
        return true;
    }

    //    HiiragiProvider    //

    private HiiragiItemHandler invCatalyst;
    private HiiragiItemHandler invOutput;

    @NotNull
    @Override
    public HiiragiCapabilityProvider<IItemHandler> createInventory() {
        invCatalyst = new HiiragiItemHandler() {
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        }.setIOType(IOType.CATALYST);
        invOutput = new HiiragiItemHandler().setIOType(IOType.OUTPUT);
        inventory = new HiiragiItemHandlerWrapper(invCatalyst, invOutput);
        return new HiiragiCapabilityProvider<>(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory);
    }

    private HiiragiFluidTank tankWater;
    private HiiragiFluidTank tankLava;

    @NotNull
    @Override
    public HiiragiCapabilityProvider<IFluidHandler> createTank() {
        tankWater = new HiiragiFluidTank(1000) {
            @Override
            public boolean canFillFluidType(@Nullable FluidStack fluid) {
                return fluid != null && fluid.getFluid() == FluidRegistry.WATER;
            }
        };
        tankLava = new HiiragiFluidTank(1000) {
            @Override
            public boolean canFillFluidType(@Nullable FluidStack fluid) {
                return fluid != null && fluid.getFluid() == FluidRegistry.LAVA;
            }
        };
        tank = new HiiragiFluidTankWrapper(tankWater, tankLava);
        return new HiiragiCapabilityProvider<>(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank);
    }

    //    IGuiHolder    //

    @Override
    public ModularPanel buildUI(GuiCreationContext guiCreationContext, GuiSyncManager guiSyncManager, boolean b) {

        guiSyncManager.registerSlotGroup("rock_generator_catalyst", 1);
        guiSyncManager.registerSlotGroup("rock_generator_output", 1);

        guiSyncManager.syncValue("rock_generator_water", 0, SyncHandlers.fluidSlot(this.tankWater));
        guiSyncManager.syncValue("rock_generator_lava", 1, SyncHandlers.fluidSlot(this.tankLava));

        ModularPanel panel = new ModularPanel("rock_generator");

        panel.flex().align(Alignment.Center);

        panel.bindPlayerInventory()
                .child(new Column()
                        .padding(7)
                        //Player Inventory
                        .child(SlotGroupWidget.playerInventory())
                        //Catalyst
                        .child(SlotGroupWidget.builder()
                                .row("I")
                                .key('I', index -> new ItemSlot().slot(SyncHandlers.itemSlot(this.invCatalyst, 0).slotGroup("rock_generator_catalyst")))
                                .build()
                                .marginBottom(18))
                        .child(new Row()
                                //Water, Output, Lava
                                .child(SlotGroupWidget.builder()
                                        .row("  W>I<L")
                                        .key('W', index -> new FluidSlot().syncHandler("rock_generator_water", 0))
                                        .key('>', index -> new ProgressWidget().progress(this::getProgress).texture(GuiTextures.PROGRESS_ARROW, 20))
                                        .key('I', index -> new ItemSlot().slot(new ModularSlotOut(this.invOutput, 0).slotGroup("rock_generator_output")))
                                        .key('<', index -> new ProgressWidget().progress(() -> 1.0 - this.getProgress()).texture(HiiragiGuiTextures.PROGRESS_ARROW_INVERTED, 20))
                                        .key('L', index -> new FluidSlot().syncHandler("rock_generator_lava", 1))
                                        .key(' ', index -> new EmptyWidget())
                                        .build())
                        )
                );
        return panel;
    }

    private double getProgress() {
        return (double) this.getCountdown() / this.getMaxCount();
    }

    //    ITickable    //

    @Override
    public void onUpdateServer() {
        HiiragiRegistry.ROCK_GENERATION
                .getValuesCollection()
                .stream()
                .filter(recipe -> recipe.matches(this.invCatalyst.getStackInSlot(0).copy()))
                .findFirst()
                .ifPresent(recipe -> {
                    ItemStack attempt = this.invOutput.insertItem(0, recipe.getOutput(), true);
                    if (attempt.isEmpty()) {
                        this.invOutput.insertItem(0, recipe.getOutput(), false);
                        HiiragiUtil.playSound(this, SoundEvents.BLOCK_STONE_BREAK);
                    }
                });
    }

}