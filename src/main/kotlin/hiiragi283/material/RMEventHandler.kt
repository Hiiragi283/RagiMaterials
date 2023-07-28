package hiiragi283.material

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.integration.RMIntegrationCore
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
        if (event.modID != RagiMaterials.MODID) return
        ConfigManager.sync(RagiMaterials.MODID, Config.Type.INSTANCE)
        RagiMaterials.LOGGER.info("Config Reloaded!")
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: RegistryEvent.Register<HiiragiMaterial>) {
        event.registry.run {

            RagiMaterials.LOGGER.info("Registering Elemental Materials...")
            MaterialElements.register(this)

            RagiMaterials.LOGGER.info("Registering Elemental Materials...")
            MaterialCommon.register(this)

            RagiMaterials.LOGGER.info("Registering Materials for Integration...")
            RMIntegrationCore.register(this)

            RagiMaterials.LOGGER.info("Registering Materials from JSON...")
            RMJSonHandler.register(this)

        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerShapes(event: RegistryEvent.Register<HiiragiShape>) {
        ShapeRegistry.register(event.registry)
    }

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

        PartRegistry.getParts(event.itemStack).forEach { it.getTooltip(event.toolTip) }

    }

}