package hiiragi283.material

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RMEventHandler {

    //    Registration    //

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        RMBlocks.register(event.registry)
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        RMItems.register(event.registry)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerBlockColor(event: ColorHandlerEvent.Block) {
        RMBlocks.registerColorBlock(event.blockColors)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerItemColor(event: ColorHandlerEvent.Item) {
        RMBlocks.registerColorItem(event.itemColors)
        RMItems.registerColorItem(event.itemColors)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent) {
        RMBlocks.registerModel()
        RMItems.registerModel()
    }

}