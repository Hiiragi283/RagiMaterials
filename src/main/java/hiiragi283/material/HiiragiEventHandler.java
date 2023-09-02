package hiiragi283.material;

import hiiragi283.material.api.event.HiiragiRegistryEvent;
import hiiragi283.material.api.material.CommonMaterials;
import hiiragi283.material.api.material.ElementMaterials;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.part.HiiragiPart;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import hiiragi283.material.api.shape.HiiragiShapes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashSet;

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
            new HashSet<>(HiiragiPart.getParts(event.getItemStack())).forEach(part -> part.addTooltip(event.getToolTip()));
            //HiiragiRegistries.getStacks(event.getItemStack()).toSet().forEach { it.addTooltip(event.toolTip) }
        }

    }

}