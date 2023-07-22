package hiiragi283.material.api.block

import hiiragi283.material.api.item.MaterialBlockItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.commonId
import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.json.blockstate.JState
import net.devtech.arrp.json.models.JModel
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

fun createOreBlock(material: HiiragiMaterial): MaterialOreBlock? {

    if (!material.hasOre()) {
        RagiMaterials.LOGGER.warn("$material cannot create Ore Block!")
        return null
    }

    return object : MaterialOreBlock(
        FabricBlockSettings.of(Material.STONE)
            .hardness(3.0f)
            .nonOpaque()
            .resistance(6.0f)
    ) {

        private val tag: Identifier = commonId("@_ores".replace("@", material.name))

        override fun getIdentifier(): Identifier = hiiragiId("@_ores".replace("@", material.name))

        override fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
            material.color

        override fun getColor(stack: ItemStack, tintIndex: Int): Int = material.color

        override fun register(): Block {

            val block = super.register()

            object : MaterialBlockItem(this) {

                override fun getName(stack: ItemStack): TranslatableText =
                    TranslatableText("shape.ore", material.getTranslatedName())

            }.register()


            RMResourcePack.addBlockState(
                getIdentifier(),
                JState.state(JState.variant(JState.model(hiiragiId("block/ore_stone"))))
            )
            RMResourcePack.addItemModel(getIdentifier(), JModel.model().parent(hiiragiId("block/ore_stone").toString()))
            val jTag = JTag().add(getIdentifier())
            RMResourcePack.addBlockTag(tag, jTag)
            RMResourcePack.addItemTag(tag, jTag)

            return block
        }

    }

}

abstract class MaterialOreBlock(settings: FabricBlockSettings) : MaterialBlock(settings)