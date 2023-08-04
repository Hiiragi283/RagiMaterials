package hiiragi283.chemistry

import hiiragi283.api.fluid.DelegatedFluidStack
import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.tileentity.HiiragiProvider
import hiiragi283.material.RMReference
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.RegistryBuilder

object RCEventHandler {

    private val keyInventory = ResourceLocation(RMReference.MOD_ID, "inventory")
    private val keyTank = ResourceLocation(RMReference.MOD_ID, "tank")
    private val keyEnergy = ResourceLocation(RMReference.MOD_ID, "energy")

    @SubscribeEvent
    fun attachCapability(event: AttachCapabilitiesEvent<TileEntity>) {
        val tile = event.`object`
        if (tile is HiiragiProvider.Inventory) event.addCapability(keyInventory, tile.createInventory())
        if (tile is HiiragiProvider.Tank) event.addCapability(keyTank, tile.createTank())
        if (tile is HiiragiProvider.Energy) event.addCapability(keyEnergy, tile.createBattery())
    }

    @SubscribeEvent
    fun createRegistry(event: RegistryEvent.NewRegistry) {
        //Crucible Recipe
        RegistryBuilder<CrucibleRecipe>()
            .allowModification()
            .disableSaving()
            .setName(ResourceLocation(RMReference.MOD_ID, "crucible"))
            .setType(CrucibleRecipe::class.java)
            .create()
    }

    @SubscribeEvent
    fun registerBlock(event: RegistryEvent.Register<Block>) {
        RCBlocks.register(event.registry)
    }

    @SubscribeEvent
    fun registerItem(event: RegistryEvent.Register<Item>) {
        RCItems.register(event.registry)
    }

    @SubscribeEvent
    fun registerCrucibleRecipe(event: RegistryEvent.Register<CrucibleRecipe>) {
        event.registry.register(
            CrucibleRecipe.Impl("ingotIron", DelegatedFluidStack("iron", 144))
                .setRegistryName(RMReference.MOD_ID, "test")
        )
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerBlockColorHandler(event: ColorHandlerEvent.Block) {
        RCBlocks.registerColorBlock(event.blockColors)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerItemColorHandler(event: ColorHandlerEvent.Item) {
        RCBlocks.registerColorItem(event.itemColors)
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun registerModel(event: ModelRegistryEvent) {
        RCBlocks.registerModel()
    }

}