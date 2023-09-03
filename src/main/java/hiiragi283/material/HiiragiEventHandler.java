package hiiragi283.material;

import hiiragi283.material.api.capability.HiiragiCapability;
import hiiragi283.material.api.event.HiiragiRegistryEvent;
import hiiragi283.material.api.material.CommonMaterials;
import hiiragi283.material.api.material.ElementMaterials;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.material.MaterialStack;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.api.tile.HiiragiProvider;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = RMReference.MOD_ID)
public class HiiragiEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerShape(HiiragiRegistryEvent.Shape event) throws IllegalAccessException {

        HiiragiRegistry<String, HiiragiShape> registry = event.getRegistry();

        HiiragiShapes.register(new HiiragiShapes(), HiiragiShape.class, registry, shape -> registry.register(shape.name(), shape));

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterial(HiiragiRegistryEvent.Material event) throws IllegalAccessException {

        HiiragiRegistry<String, HiiragiMaterial> registry = event.getRegistry();

        ElementMaterials.register(new ElementMaterials(), HiiragiMaterial.class, registry, material -> registry.register(material.name(), material));
        CommonMaterials.register(new CommonMaterials(), HiiragiMaterial.class, registry, material -> registry.register(material.name(), material));

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        HiiragiBlocks.INSTANCE.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        HiiragiItems.INSTANCE.register(event.getRegistry());
    }

    private static final ResourceLocation INVENTORY = HiiragiUtil.getLocation(HiiragiUtil.INVENTORY);
    private static final ResourceLocation TANK = HiiragiUtil.getLocation(HiiragiUtil.TANK);
    private static final ResourceLocation BATTERY = HiiragiUtil.getLocation(HiiragiUtil.BATTERY);
    private static final ResourceLocation MACHINE = HiiragiUtil.getLocation(HiiragiUtil.MACHINE_PROPERTY);

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<TileEntity> event) {
        TileEntity tile = event.getObject();
        if (tile instanceof HiiragiProvider.Inventory)
            event.addCapability(INVENTORY, ((HiiragiProvider.Inventory) tile).createInventory());
        if (tile instanceof HiiragiProvider.Tank)
            event.addCapability(TANK, ((HiiragiProvider.Tank) tile).createTank());
        if (tile instanceof HiiragiProvider.Battery)
            event.addCapability(BATTERY, ((HiiragiProvider.Battery) tile).createBattery());
        if (tile instanceof HiiragiProvider.Machine)
            event.addCapability(MACHINE, ((HiiragiProvider.Machine) tile).createMachineProperty());
    }

    @Mod.EventBusSubscriber(modid = RMReference.MOD_ID, value = Side.CLIENT)
    public static class Client {

        @SubscribeEvent
        public static void registerBlockColor(ColorHandlerEvent.Block event) {
            HiiragiBlocks.INSTANCE.registerBlockColor(event.getBlockColors());
        }

        @SubscribeEvent
        public static void registerItemColor(ColorHandlerEvent.Item event) {
            HiiragiBlocks.INSTANCE.registerItemColor(event.getItemColors());
            HiiragiItems.INSTANCE.registerItemColor(event.getItemColors());
        }

        @SubscribeEvent
        public static void registerModel(ModelRegistryEvent event) {
            HiiragiBlocks.INSTANCE.registerModel();
            HiiragiItems.INSTANCE.registerModel();
        }

        @SubscribeEvent
        public static void onTooltip(ItemTooltipEvent event) {

            ItemStack stack = event.getItemStack();
            if (stack.isEmpty()) return;

            new HashSet<>(HiiragiPart.getParts(event.getItemStack())).forEach(part -> part.addTooltip(event.getToolTip()));

            HiiragiUtil.getCapability(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                    .ifPresent(handler -> Arrays.stream(handler.getTankProperties())
                            .filter(Objects::nonNull)
                            .map(IFluidTankProperties::getContents)
                            .filter(Objects::nonNull)
                            .map(MaterialStack::new)
                            .collect(Collectors.toSet())
                            .forEach(materialStack -> materialStack.addTooltip(event.getToolTip())));

            HiiragiUtil.getCapability(stack, HiiragiCapability.MACHINE_PROPERTY, null)
                    .ifPresent(handler -> handler.addTooltip(event.getToolTip()));

        }

    }

}