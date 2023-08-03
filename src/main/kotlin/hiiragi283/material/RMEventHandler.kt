package hiiragi283.material

import hiiragi283.api.event.MaterialRegistryEvent
import hiiragi283.api.event.ShapeRegistryEvent
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialElements
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.core.RagiMaterials
import hiiragi283.core.config.RMJSonHandler
import hiiragi283.integration.RMIntegrationCore
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RMEventHandler {

    /**
     * @see <a href = https://github.com/ACGaming/UniversalTweaks/blob/main/src/main/java/mod/acgaming/universaltweaks/config/UTConfig.java>Reference </a>
     */

    @SubscribeEvent
    fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
        if (event.modID != RMReference.MOD_ID) return
        ConfigManager.sync(RMReference.MOD_ID, Config.Type.INSTANCE)
        RagiMaterials.LOGGER.info("Config Reloaded!")
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: MaterialRegistryEvent) {
        event.registry.run {

            RagiMaterials.LOGGER.info("Registering Elemental Materials...")
            MaterialElements.register(this)

            RagiMaterials.LOGGER.info("Registering Common Materials...")
            MaterialCommon.register(this)

            RagiMaterials.LOGGER.info("Registering Materials for Integration...")
            RMIntegrationCore.INSTANCE.registerMaterial(this)

            RagiMaterials.LOGGER.info("Registering Materials from JSON...")
            RMJSonHandler.register(this)

        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerShapes(event: ShapeRegistryEvent) {
        RagiMaterials.LOGGER.info("Registering Shapes...")
        HiiragiShapes.register(event.registry)
    }

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

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun onTooltip(event: ItemTooltipEvent) {
        PartRegistry.getParts(event.itemStack).toSet().forEach { it.addTooltip(event.toolTip) }
    }

}