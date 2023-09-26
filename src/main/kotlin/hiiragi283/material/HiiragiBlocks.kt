package hiiragi283.material

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.api.tile.MaterialTileEntity
import hiiragi283.material.block.*
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.tile.TileEntityCapabilityRail
import hiiragi283.material.tile.TileEntityMachineExtender
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.hiiragiLocation
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

    @JvmField
    val CHUNK_LOADER = HiiragiRegistries.BLOCK.register(BlockTestChunkLoader)

    init {
        MachineType.createMachineBlock()
        registerTileEntity(MaterialTileEntity::class.java, "material")
        registerTileEntity(TileEntityCapabilityRail::class.java, "capability_rail")
        registerTileEntity(TileEntityMachineExtender::class.java, "machine_extender")
        registerTileEntity(TileEntityModuleMachine::class.java, "module_machine")
    }

    private fun <T : TileEntity> registerTileEntity(clazz: Class<T>, name: String) {
        GameRegistry.registerTileEntity(clazz, hiiragiLocation(name))
    }

}