package hiiragi283.material

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.api.tile.MaterialTileEntity
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.block.*
import hiiragi283.material.config.RMConfig
import hiiragi283.material.tile.TileEntityMachineExtender
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry

object HiiragiBlocks {

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK: MaterialBlock = HiiragiRegistries.BLOCK.registerOptional(
        MaterialBlock(HiiragiShapes.BLOCK, recipe = getRecipeBlock())
    ) { RMConfig.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_CASING =
        HiiragiRegistries.BLOCK.registerOptional(MaterialBlockCasing) { RMConfig.EXPERIMENTAL.enableMetaTileBlock }

    @JvmField
    val MATERIAL_FRAME =
        HiiragiRegistries.BLOCK.registerOptional(MaterialBlockFrame) { RMConfig.EXPERIMENTAL.enableMetaTileBlock }

    //    Machine    //

    @JvmField
    val MACHINE_EXTENDER = HiiragiRegistries.BLOCK.register(BlockMachineExtender)

    @JvmField
    val MACHINE_WORKBENCH = HiiragiRegistries.BLOCK.register(BlockMachineWorkbench)

    @JvmField
    val MACHINE_TEST = HiiragiRegistries.BLOCK.register(BlockModuleMachine(IMachineRecipe.Type.TEST))

    @JvmField
    val MACHINE_SMELTER = HiiragiRegistries.BLOCK.register(BlockModuleMachine(IMachineRecipe.Type.SMELTER))

    init {
        registerTileEntity(MaterialTileEntity::class.java, "material")
        registerTileEntity(TileEntityMachineExtender::class.java, "machine_extender")
        registerTileEntity(TileEntityModuleMachine::class.java, "module_machine")
    }

    private fun <T : TileEntity> registerTileEntity(clazz: Class<T>, name: String) {
        GameRegistry.registerTileEntity(clazz, hiiragiLocation(name))
    }

}