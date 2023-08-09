package hiiragi283.material

import hiiragi283.api.event.MaterialRegistryEvent
import hiiragi283.api.event.ShapeRegistryEvent
import hiiragi283.api.item.ICrusherItem
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialElements
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.api.tileentity.HiiragiProvider
import hiiragi283.integration.RMIntegrationCore
import hiiragi283.material.config.RMJSonHandler
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Mod.EventBusSubscriber(modid = RMReference.MOD_ID)
object RMEventHandler {

    @SubscribeEvent
    fun createRegistry(event: RegistryEvent.NewRegistry) {
        HiiragiRegistry.init()
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

    @SubscribeEvent
    fun onHarvestDrop(event: BlockEvent.HarvestDropsEvent) {
        //爆破や機械による採掘ではnullになるので，それを回避する
        val player: EntityPlayer = event.harvester ?: return
        val stack: ItemStack = player.getHeldItem(player.activeHand)
        if (stack.isEmpty || stack.item !is ICrusherItem) return
        val recipe: CrushingRecipe = HiiragiRegistry.CRUSHING.valuesCollection
            .firstOrNull { it.matches(event.state) } ?: return
        val list: MutableList<ItemStack> = event.drops
        list.clear() //既存のドロップ品を一度リセットする
        list.addAll(recipe.getWeightedOutputs())
    }

    /**
     * @see <a href = https://github.com/ACGaming/UniversalTweaks/blob/main/src/main/java/mod/acgaming/universaltweaks/config/UTConfig.java>Reference </a>
     */

    @SubscribeEvent
    fun onConfigChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
        if (event.modID != RMReference.MOD_ID) return
        ConfigManager.sync(RMReference.MOD_ID, Config.Type.INSTANCE)
        RagiMaterials.LOGGER.info("Config Reloaded!")
    }

    //    Client    //

    @Mod.EventBusSubscriber(modid = RMReference.MOD_ID)
    @SideOnly(Side.CLIENT)
    object Client {

        @SubscribeEvent
        fun registerBlockColor(event: ColorHandlerEvent.Block) {
            RMBlocks.registerColorBlock(event.blockColors)
        }

        @SubscribeEvent
        fun registerItemColor(event: ColorHandlerEvent.Item) {
            RMBlocks.registerColorItem(event.itemColors)
            RMItems.registerColorItem(event.itemColors)
        }

        @SubscribeEvent
        fun registerModel(event: ModelRegistryEvent) {
            RMBlocks.registerModel()
            RMItems.registerModel()
        }

        @SubscribeEvent
        fun onTooltip(event: ItemTooltipEvent) {
            PartRegistry.getParts(event.itemStack).toSet().forEach { it.addTooltip(event.toolTip) }
        }

    }

}