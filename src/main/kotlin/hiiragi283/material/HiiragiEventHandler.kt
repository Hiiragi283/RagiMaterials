package hiiragi283.material

import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.event.ShapeRegistryEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.part.getParts
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.api.tile.HiiragiProvider
import hiiragi283.material.compat.HiiragiPlugin
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidTankProperties
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object HiiragiEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: MaterialRegistryEvent) {

        HiiragiMaterial.RUSSELL.register()

        RagiMaterials.LOGGER.info("Registering Elemental Materials...")
        MaterialElements.register()

        RagiMaterials.LOGGER.info("Registering Common Materials...")
        MaterialCommon.register()

        RagiMaterials.LOGGER.info("Registering Materials for Integration...")
        HiiragiPlugin.registerMaterial()

        RagiMaterials.LOGGER.info("Registering Materials from JSON...")
        RMJSonHandler.register()

    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerShapes(event: ShapeRegistryEvent) {
        RagiMaterials.LOGGER.info("Registering Shapes...")
        HiiragiShapes.register()
    }

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        HiiragiRegistries.BLOCK.register(event.registry)
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        HiiragiRegistries.ITEM.registerAll(
            HiiragiRegistries.BLOCK.getValues().mapNotNull(HiiragiEntry.BLOCK::itemBlock)
        )
        HiiragiRegistries.ITEM.register(event.registry)

    }

    private val keyInventory = hiiragiLocation("inventory")
    private val keyTank = hiiragiLocation("tank")
    private val keyEnergy = hiiragiLocation("energy")

    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<TileEntity>) {
        val tile: TileEntity = event.`object`
        if (tile is HiiragiProvider.Inventory) event.addCapability(keyInventory, tile.createInventory())
        if (tile is HiiragiProvider.Tank) event.addCapability(keyTank, tile.createTank())
        if (tile is HiiragiProvider.Energy) event.addCapability(keyEnergy, tile.createBattery())
    }

    /**
     * [Reference](https://github.com/ACGaming/UniversalTweaks/blob/main/src/main/java/mod/acgaming/universaltweaks/config/UTConfig.java)
     */

    @SubscribeEvent
    fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
        if (event.modID != RMReference.MOD_ID) return
        ConfigManager.sync(RMReference.MOD_ID, Config.Type.INSTANCE)
        RagiMaterials.LOGGER.info("Config Reloaded!")
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    object Client {

        @SubscribeEvent
        fun registerBlockColor(event: ColorHandlerEvent.Block) {
            HiiragiRegistries.BLOCK.registerBlockColor(event.blockColors)
        }

        @SubscribeEvent
        fun registerItemColor(event: ColorHandlerEvent.Item) {
            HiiragiRegistries.BLOCK.registerItemColor(event.itemColors)
            HiiragiRegistries.ITEM.registerItemColor(event.itemColors)
        }

        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            HiiragiRegistries.BLOCK.registerModel()
            HiiragiRegistries.ITEM.registerModel()
        }

        @SubscribeEvent
        fun onTooltip(event: ItemTooltipEvent) {
            if (event.itemStack.isEmpty) return

            event.itemStack.getParts().toSet().forEach { it.addTooltip(event) }

            event.itemStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                ?.tankProperties
                ?.mapNotNull(IFluidTankProperties::getContents)
                ?.mapNotNull(MaterialStack::of)
                ?.toSet()
                ?.forEach { it.addTooltip(event) }

        }

    }

}