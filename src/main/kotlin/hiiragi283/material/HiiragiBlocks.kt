package hiiragi283.material

import hiiragi283.material.api.registry.HiiragiRegistries
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

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK =
        HiiragiRegistries.BLOCK.registerOptional(MaterialBlockStorage)
        { HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_CASING =
        HiiragiRegistries.BLOCK.registerOptional(MaterialBlockCasing)
        { HiiragiConfigs.EXPERIMENTAL.enableMetaTileBlock }


    //    Machine    //

    @JvmField
    val MACHINE_EXTENDER = HiiragiRegistries.BLOCK.register(BlockMachineExtender)

    @JvmField
    val MACHINE_WORKBENCH = HiiragiRegistries.BLOCK.register(BlockMachineWorkbench)

    //    Common    //

    @JvmField
    val CAPABILITY_RAIL = HiiragiRegistries.BLOCK.register(BlockCapabilityRail)

    @JvmField
    val ITEM_PIPE = HiiragiRegistries.BLOCK.register(
        BlockTransferPipe("item") { listOf(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) }
    )

    @JvmField
    val FLUID_PIPE = HiiragiRegistries.BLOCK.register(
        BlockTransferPipe("fluid") { listOf(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) }
    )

    @JvmField
    val ENERGY_PIPE = HiiragiRegistries.BLOCK.register(
        BlockTransferPipe("energy") { listOf(CapabilityEnergy.ENERGY) }
    )

    @JvmField
    val ALL_PIPE = HiiragiRegistries.BLOCK.register(
        BlockTransferPipe("all") {
            listOf(
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
                CapabilityEnergy.ENERGY
            )
        }
    )

    @JvmField
    val ITEM_STATION = HiiragiRegistries.BLOCK.register(
        BlockTransferStation("item") { TileTransferStationItem() }
    )

    @JvmField
    val FLUID_STATION = HiiragiRegistries.BLOCK.register(
        BlockTransferStation("fluid") { TileTransferStationFluid() }
    )

    @JvmField
    val ENERGY_STATION = HiiragiRegistries.BLOCK.register(
        BlockTransferStation("energy") { TileTransferStationEnergy() }
    )

    //    TEST    //

    @JvmField
    val CHUNK_LOADER = HiiragiRegistries.BLOCK.registerOptional(BlockTestChunkLoader) { isDeobf() }

    @JvmField
    val TEST_CORE = HiiragiRegistries.BLOCK.registerOptional(BlockTestMultiblock) { isDeobf() }

    init {
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