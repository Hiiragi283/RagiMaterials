package hiiragi283.material.init

import hiiragi283.material.RMReference
import hiiragi283.material.api.event.MaterialBuiltEvent
import hiiragi283.material.api.event.MaterialRegistryEvent
import hiiragi283.material.api.event.ShapeRegistryEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialStack
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.tile.HiiragiProvider
import hiiragi283.material.compat.RagiMaterialsPlugin
import hiiragi283.material.config.HiiragiJSonHandler
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialElements
import hiiragi283.material.util.HiiragiLogger
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

@Suppress("unused", "UNUSED_PARAMETER", "DEPRECATION", "UNUSED_VARIABLE")
object HiiragiEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun registerShapes(event: ShapeRegistryEvent) {
        HiiragiLogger.info("Registering Shapes...")
        HiiragiShapes.register()

        HiiragiLogger.info("Registering Shapes from JSON...")
        HiiragiJSonHandler.registerShape()
    }

    fun modifyMaterial(event: MaterialBuiltEvent) {
        val builder: HiiragiMaterial.Builder = event.builder
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: MaterialRegistryEvent) {

        HiiragiMaterial.RUSSELL.register()

        HiiragiLogger.info("Registering Elemental Materials...")
        MaterialElements.register()

        HiiragiLogger.info("Registering Common Materials...")
        MaterialCommons.register()

        HiiragiLogger.info("Registering Materials for Integration...")
        RagiMaterialsPlugin.registerMaterial()

        HiiragiLogger.info("Registering Materials from JSON...")
        HiiragiJSonHandler.registerMaterial()

    }

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        HiiragiRegistries.BLOCK.getValues().forEach(event.registry::register)
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        HiiragiRegistries.ITEM.getValues().forEach(event.registry::register)
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
        HiiragiLogger.info("Config Reloaded!")
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    object Client {

        @SubscribeEvent
        fun registerBlockColor(event: ColorHandlerEvent.Block) {
            HiiragiRegistries.BLOCK.getValues()
                .filterIsInstance<HiiragiEntry.BLOCK>()
                .filterNot { it.getBlockColor() == null }
                .forEach { event.blockColors.registerBlockColorHandler(it.getBlockColor()!!, it.getObject()) }
        }

        @SubscribeEvent
        fun registerItemColor(event: ColorHandlerEvent.Item) {
            HiiragiRegistries.BLOCK.getValues()
                .filterIsInstance<HiiragiEntry.BLOCK>()
                .filterNot { it.getItemColor() == null }
                .forEach { event.itemColors.registerItemColorHandler(it.getItemColor()!!, it.getObject()) }
            HiiragiRegistries.ITEM.getValues()
                .filterIsInstance<HiiragiEntry.ITEM>()
                .filterNot { it.getItemColor() == null }
                .forEach { event.itemColors.registerItemColorHandler(it.getItemColor()!!, it.getObject()) }
        }

        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            HiiragiRegistries.BLOCK.getValues()
                .filterIsInstance<HiiragiEntry.BLOCK>()
                .forEach(HiiragiEntry.BLOCK::registerModel)
            HiiragiRegistries.ITEM.getValues()
                .filterIsInstance<HiiragiEntry.ITEM>()
                .forEach(HiiragiEntry.ITEM::registerModel)
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        fun onTooltip(event: ItemTooltipEvent) {
            if (event.itemStack.isEmpty) return

            PartDictionary.getPart(event.itemStack)?.addTooltip(event)

            event.itemStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                ?.tankProperties
                ?.mapNotNull(IFluidTankProperties::getContents)
                ?.mapNotNull(MaterialStack::of)
                ?.toSet()
                ?.forEach { it.addTooltip(event) }

        }

    }

}