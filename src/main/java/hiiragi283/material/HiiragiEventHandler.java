package hiiragi283.material;

import hiiragi283.material.api.event.HiiragiRegistryEvent;
import hiiragi283.material.api.material.CommonMaterials;
import hiiragi283.material.api.material.ElementMaterials;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.material.MaterialStack;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.registry.HiiragiRegister;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
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
        HiiragiRegister.register(new HiiragiShapes(), HiiragiShape.class, shape -> event.getRegistry().register(shape.name(), shape));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterial(HiiragiRegistryEvent.Material event) throws IllegalAccessException {

        HiiragiRegistry<String, HiiragiMaterial> registry = event.getRegistry();

        HiiragiRegister.register(new ElementMaterials(), HiiragiMaterial.class, material -> registry.register(material.name, material));
        HiiragiRegister.register(new CommonMaterials(), HiiragiMaterial.class, material -> registry.register(material.name, material));

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        HiiragiBlocks.INSTANCE.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        HiiragiItems.INSTANCE.register(event.getRegistry());
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
            OptionalUtil.getStackNotEmpty(event.getItemStack()).ifPresent(stack -> {
                //Ore Dictionary -> HiiragiPart Tooltips
                new HashSet<>(HiiragiPart.getParts(event.getItemStack())).forEach(part -> part.addTooltip(event.getToolTip()));
                //Fluid Containers -> MaterialStack Tooltips
                OptionalUtil.getCapability(stack, CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                        .ifPresent(handler -> Arrays.stream(handler.getTankProperties())
                                .filter(Objects::nonNull)
                                .map(IFluidTankProperties::getContents)
                                .filter(Objects::nonNull)
                                .map(MaterialStack::new)
                                .filter(materialStack -> !materialStack.isEmpty())
                                .collect(Collectors.toSet())
                                .forEach(materialStack -> materialStack.addTooltip(event.getToolTip())));
                //IModuleItem -> Module Tooltips
                OptionalUtil.getItemImplemented(stack, IModuleItem.class).ifPresent(module -> module.addTooltip(stack, event.getToolTip()));
            });
        }

    }

}