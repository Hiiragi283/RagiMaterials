package hiiragi283.material

import hiiragi283.material.init.RMItems
import hiiragi283.material.material_part.MaterialPartRegistry
import net.minecraft.item.Item
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary

object RMEventHandler {

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>): Unit = RMItems.register(event.registry)

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerColor(event: ColorHandlerEvent.Item): Unit = RMItems.registerColorItem(event.itemColors)

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent): Unit = RMItems.registerModel()

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun onTooltip(event: ItemTooltipEvent) {

        val stack = event.itemStack
        if (stack.isEmpty) return

        OreDictionary.getOreIDs(stack)
            .map { OreDictionary.getOreName(it) }
            .map { MaterialPartRegistry.getMaterialPart(it) }
            .firstOrNull { !it.isEmpty() }
            ?.let { it.material.getTooltip(event.toolTip, it.part) }

    }

}