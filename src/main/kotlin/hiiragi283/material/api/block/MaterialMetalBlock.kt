package hiiragi283.material.api.block

import hiiragi283.material.api.item.MaterialBlockItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.*
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JResult
import net.devtech.arrp.json.tags.JTag
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.item.ItemStack
import net.minecraft.text.TranslatableText
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

fun createMetalBlock(material: HiiragiMaterial): MaterialMetalBlock? {

    if (!material.isMetal()) {
        RagiMaterials.LOGGER.warn("$material cannot create Metal Block!")
        return null
    }

    return object : MaterialMetalBlock(
        FabricBlockSettings.of(Material.METAL)
            .hardness(5.0f)
            .nonOpaque()
            .requiresTool()
            .resistance(5.0f)
    ) {

        private val tag: Identifier = commonId("@_blocks".replace("@", material.name))

        override fun getIdentifier(): Identifier = hiiragiId("@_blocks".replace("@", material.name))

        override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
            material.color

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

        override fun register(): Block {

            val block = super.register()

            object : MaterialBlockItem(this) {

                override fun getName(stack: ItemStack): TranslatableText =
                    TranslatableText("shape.block", material.getTranslatedName())

            }.register()

            RMResourcePack.addBlockState(
                getIdentifier(),
                JState.state(JState.variant(JState.model(hiiragiId("block/block_metal"))))
            )
            RMResourcePack.addItemModel(
                getIdentifier(),
                JModel.model().parent(hiiragiId("block/block_metal").toString())
            )
            RMResourcePack.addRecipe(
                getIdentifier().append("_shaped"),
                JRecipe.shaped(
                    get3x3('A'),
                    JKeys.keys().addTag("A", HiiragiPart.of(ShapeRegistry.INGOT, material).getTadId()),
                    JResult.result(getIdentifier().toString())
                )
            )

            val jTag = JTag().add(getIdentifier())
            RMResourcePack.addBlockTag(tag, jTag)
            RMResourcePack.addItemTag(tag, jTag)

            return block
        }

    }

}

abstract class MaterialMetalBlock(settings: FabricBlockSettings) : MaterialBlock(settings)