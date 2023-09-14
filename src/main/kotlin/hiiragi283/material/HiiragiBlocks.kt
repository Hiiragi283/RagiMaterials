package hiiragi283.material

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.block.MaterialBlockCasing
import hiiragi283.material.api.block.createBlockMaterial
import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiEntry.BLOCK
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.api.tile.MaterialTileEntity
import hiiragi283.material.block.BlockMachineExtender
import hiiragi283.material.block.BlockModuleMachine
import hiiragi283.material.config.RMConfig
import hiiragi283.material.tile.TileEntityMachineExtender
import hiiragi283.material.tile.TileEntityModuleMachine
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.hiiragiLocation
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry

object HiiragiBlocks {

    //    Material    //

    @JvmField
    val MATERIAL_BLOCK: MaterialBlock = createBlockMaterial(
        HiiragiShapes.BLOCK,
        recipe = getRecipeBlock()
    )

    @JvmField
    val MATERIAL_CASING: MaterialBlockCasing = MaterialBlockCasing

    @JvmField
    val MATERIAL_FRAME: MaterialBlock = createBlockMaterial(
        HiiragiShapes.FRAME,
        recipe = { entry, material ->
            CraftingBuilder(entry.getItemStack(material, 2))
                .setPattern("AAA", "A A", "AAA")
                .setIngredient('A', HiiragiShapes.STICK.getOreDict(material))
                .build()
        }
    )

    //    Machine    //

    @JvmField
    val MACHINE_EXTENDER: BlockMachineExtender = BlockMachineExtender

    @JvmField
    val MACHINE_TEST = BlockModuleMachine(IMachineRecipe.Type.TEST)

    fun init() {

        if (RMConfig.EXPERIMENTAL.enableMetaTileBlock) {
            HiiragiRegistries.BLOCK.registerAll(
                MATERIAL_BLOCK,
                MATERIAL_CASING,
                MATERIAL_FRAME
            )
        }

        HiiragiRegistries.BLOCK.registerAll(
            MACHINE_EXTENDER,
            MACHINE_TEST
        )

        registerTileEntity(MaterialTileEntity::class.java, "material")
        registerTileEntity(TileEntityMachineExtender::class.java, "machine_extender")
        registerTileEntity(TileEntityModuleMachine::class.java, "module_machine")

        HiiragiRegistries.ITEM.registerAll(HiiragiRegistries.BLOCK.getValues().mapNotNull(BLOCK::itemBlock))

    }

    fun <T : TileEntity> registerTileEntity(clazz: Class<T>, name: String) {
        GameRegistry.registerTileEntity(clazz, hiiragiLocation(name))
    }

}