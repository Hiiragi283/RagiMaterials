package ragi_materials.core.event

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import ragi_materials.core.RagiMaterials
import ragi_materials.core.tile.ITileProvider

/**
 * Thanks to SkyTheory!
 * Source: https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/event/CapabilityEvent.java
 */

object CapabilityEvent {

    private val keyInventory = ResourceLocation(RagiMaterials.MOD_ID, "inventory")
    private val keyTank = ResourceLocation(RagiMaterials.MOD_ID, "tank")
    private val keyEnergy = ResourceLocation(RagiMaterials.MOD_ID, "energy")

    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<TileEntity>) {
        val tile = event.`object`
        if (tile is ITileProvider.Inventory) event.addCapability(keyInventory, tile.createInventory())
        if (tile is ITileProvider.Tank) event.addCapability(keyTank, tile.createTank())
        if (tile is ITileProvider.Energy) event.addCapability(keyEnergy, tile.createBattery())
    }

}