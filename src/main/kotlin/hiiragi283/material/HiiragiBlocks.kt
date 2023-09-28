package hiiragi283.material

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.api.tile.MaterialTileEntity
import hiiragi283.material.block.*
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.tile.TileEntityCapabilityRail
import hiiragi283.material.tile.TileEntityMachineExtender
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.tile.TileTransferStation
import hiiragi283.material.util.hiiragiLocation
import hiiragi283.material.util.isDeobf
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry

object HiiragiBlocks {

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK: MaterialBlock =
        HiiragiRegistries.BLOCK.registerOptional(MaterialBlock(HiiragiShapes.BLOCK))
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

    //    TEST    //

    @JvmField
    val CHUNK_LOADER = HiiragiRegistries.BLOCK.registerOptional(BlockTestChunkLoader) { isDeobf() }

    @JvmField
    val TEST_CORE = HiiragiRegistries.BLOCK.registerOptional(BlockTestMultiblock) { isDeobf() }

    @JvmField
    val TEST_PIPE = HiiragiRegistries.BLOCK.registerOptional(BlockTransferPipe) { isDeobf() }

    @JvmField
    val TEST_STATION = HiiragiRegistries.BLOCK.registerOptional(BlockTransferStation) { isDeobf() }

    init {
        registerTileEntity(MaterialTileEntity::class.java, "material")
        registerTileEntity(TileEntityCapabilityRail::class.java, "capability_rail")
        registerTileEntity(TileEntityMachineExtender::class.java, "machine_extender")
        registerTileEntity(TileEntityModuleMachine::class.java, "module_machine")
        registerTileEntity(TileTransferStation::class.java, "transfer_station")
    }

    private fun <T : TileEntity> registerTileEntity(clazz: Class<T>, name: String) {
        GameRegistry.registerTileEntity(clazz, hiiragiLocation(name))
    }

}