package hiiragi283.material

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.block.MaterialBlockCasing
import hiiragi283.material.api.block.createBlockMaterial
import hiiragi283.material.api.registry.HiiragiEntry.BLOCK
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.block.BlockMachineExtender
import hiiragi283.material.block.BlockModuleMachine
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.CraftingBuilder

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
    val MACHINE_TEST = BlockModuleMachine("machine_test")

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

        MATERIAL_BLOCK.registerTileEntity()
        MACHINE_EXTENDER.registerTileEntity()
        MACHINE_TEST.registerTileEntity()

        HiiragiRegistries.ITEM.registerAll(HiiragiRegistries.BLOCK.getValues().mapNotNull(BLOCK::itemBlock))

    }

}