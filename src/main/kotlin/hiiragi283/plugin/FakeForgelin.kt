package hiiragi283.plugin

import com.google.common.eventbus.EventBus
import net.minecraftforge.fml.common.DummyModContainer
import net.minecraftforge.fml.common.LoadController
import net.minecraftforge.fml.common.ModMetadata

class FakeForgelin : DummyModContainer(ModMetadata()) {

    private val metadata = this.getMetadata()

    init {
        metadata.modId = "forgelin"
        metadata.name = "Forgelin Bridge"
        metadata.version = "1.8.4"
        metadata.description = "A Minecraft mod to disguise Forgelin Continuous as Shadowfact's Forgelin."
        metadata.authorList = mutableListOf("Hiiragi283")
    }

    override fun registerBus(bus: EventBus, controller: LoadController): Boolean {
        bus.register(this)
        RagiMaterialsPlugin.LOGGER.info("Forgelin Bridge registered!")
        return true
    }

}