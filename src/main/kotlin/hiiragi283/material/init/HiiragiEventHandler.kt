package hiiragi283.material.init

import hiiragi283.material.RMReference
import hiiragi283.material.api.part.PartStack
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.tile.HiiragiProvider
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
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("unused", "UNUSED_PARAMETER")
object HiiragiEventHandler {

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        HiiragiEntry.BLOCK.REGISTRY.getValues().forEach(event.registry::register)
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        HiiragiEntry.ITEM.REGISTRY.getValues().forEach(event.registry::register)
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
            HiiragiEntry.BLOCK.REGISTRY.getValues()
                .filterIsInstance<HiiragiEntry.BLOCK>()
                .filterNot { it.getBlockColor() == null }
                .forEach { event.blockColors.registerBlockColorHandler(it.getBlockColor()!!, it.getObject()) }
        }

        @SubscribeEvent
        fun registerItemColor(event: ColorHandlerEvent.Item) {
            HiiragiEntry.BLOCK.REGISTRY.getValues()
                .filterIsInstance<HiiragiEntry.BLOCK>()
                .filterNot { it.getItemColor() == null }
                .forEach { event.itemColors.registerItemColorHandler(it.getItemColor()!!, it.getObject()) }
            HiiragiEntry.ITEM.REGISTRY.getValues()
                .filterIsInstance<HiiragiEntry.ITEM>()
                .filterNot { it.getItemColor() == null }
                .forEach { event.itemColors.registerItemColorHandler(it.getItemColor()!!, it.getObject()) }
        }

        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            HiiragiEntry.BLOCK.REGISTRY.getValues()
                .filterIsInstance<HiiragiEntry.BLOCK>()
                .forEach(HiiragiEntry.BLOCK::registerModel)
            HiiragiEntry.ITEM.REGISTRY.getValues()
                .filterIsInstance<HiiragiEntry.ITEM>()
                .forEach(HiiragiEntry.ITEM::registerModel)
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        fun onTooltip(event: ItemTooltipEvent) {
            if (event.itemStack.isEmpty) return

            PartStack.fromStack(event.itemStack)?.addTooltip(event)

            /*event.itemStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                ?.tankProperties
                ?.mapNotNull(IFluidTankProperties::getContents)
                ?.mapNotNull(MaterialStack::of)
                ?.toSet()
                ?.forEach { it.addTooltip(event) }*/

        }

    }

}