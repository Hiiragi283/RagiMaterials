package hiiragi283.material

import hiiragi283.material.api.material_part.MaterialPart
import hiiragi283.material.api.material_part.MaterialPartRegistry
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
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

        event.itemStack
            .let(MaterialPartRegistry::getMaterialParts)
            .filterNot(MaterialPart::isEmpty)
            .forEach { it.material.getTooltip(event.toolTip, it.part) }

        event.itemStack
            .takeUnless(ItemStack::isEmpty)
            ?.let(OreDictionary::getOreIDs)
            ?.map(OreDictionary::getOreName)
            ?.map(MaterialPartRegistry::getMaterialPart)
            ?.filterNot(MaterialPart::isEmpty)
            ?.forEach { it.material.getTooltip(event.toolTip, it.part) }

    }

}