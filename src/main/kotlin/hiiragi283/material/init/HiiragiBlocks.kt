package hiiragi283.material.init

import hiiragi283.material.api.tile.MaterialTileEntity
import hiiragi283.material.block.*
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.tile.*
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.isDeobf
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.items.CapabilityItemHandler

object HiiragiBlocks {

    //    Machine    //

    @JvmField
    val MACHINE_EXTENDER = BlockMachineExtender.register()

    @JvmField
    val MACHINE_WORKBENCH = BlockMachineWorkbench.register()

    //    Common    //

    @JvmField
    val CAPABILITY_RAIL = BlockCapabilityRail.register()

    @JvmField
    val ITEM_PIPE = BlockTransferPipe("item") { listOf(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) }.register()

    @JvmField
    val FLUID_PIPE = BlockTransferPipe("fluid") { listOf(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) }.register()

    @JvmField
    val ENERGY_PIPE = BlockTransferPipe("energy") { listOf(CapabilityEnergy.ENERGY) }.register()

    @JvmField
    val ALL_PIPE = BlockTransferPipe("all") {
        listOf(
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
            CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
            CapabilityEnergy.ENERGY
        )
    }.register()

    @JvmField
    val ITEM_STATION = BlockTransferStation("item") { TileTransferStationItem() }.register()

    @JvmField
    val FLUID_STATION = BlockTransferStation("fluid") { TileTransferStationFluid() }.register()

    @JvmField
    val ENERGY_STATION = BlockTransferStation("energy") { TileTransferStationEnergy() }.register()

    //    Mineral    //

    @JvmField
    val ORE_GENERATED = BlockOreGenerated.register()

    //    TEST    //

    @JvmField
    val CHUNK_LOADER = BlockTestChunkLoader.registerOptional { isDeobf() }

    @JvmField
    val TEST_CORE = BlockTestMultiblock.registerOptional { isDeobf() }

    init {

        MaterialBlockStorage.registerOptional { HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }
        MaterialBlockCasing.registerOptional { HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }

        registerTileEntity(MaterialTileEntity::class.java, "material")
        registerTileEntity(TileEntityCapabilityRail::class.java, "capability_rail")
        registerTileEntity(TileEntityMachineExtender::class.java, "machine_extender")
        registerTileEntity(TileEntityModuleMachine::class.java, "module_machine")
        registerTileEntity(TileTransferStationEnergy::class.java, "energy_station")
        registerTileEntity(TileTransferStationFluid::class.java, "fluid_station")
        registerTileEntity(TileTransferStationItem::class.java, "item_station")

    }

    private fun <T : TileEntity> registerTileEntity(clazz: Class<T>, name: String) {
        GameRegistry.registerTileEntity(clazz, hiiragiLocation(name))
    }

}