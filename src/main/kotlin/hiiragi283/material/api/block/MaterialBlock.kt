package hiiragi283.material.api.block

import hiiragi283.material.api.HiiragiBlock
import hiiragi283.material.api.RMItemColorProvider
import hiiragi283.material.api.item.MaterialItemConvertible
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RMResourcePack
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.server.BlockLootTableGenerator
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

abstract class MaterialBlock(
    val part: HiiragiPart,
    settings: Settings = part.shape.getBlockSettings()
) : HiiragiBlock(settings), BlockColorProvider, RMItemColorProvider, MaterialItemConvertible {

    override fun asPart(): HiiragiPart = part

    override fun getColor(state: BlockState, world: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int =
        part.material.color

    override fun getColor(stack: ItemStack, tintIndex: Int): Int = part.material.color

    fun getName(stack: ItemStack): Text = part.getText()

    override fun getIdentifier(): Identifier = part.getId()

    override fun register(): Block {

        val block = super.register()

        RMResourcePack.addBlockState(
            getIdentifier(),
            BlockStateModelGenerator.createSingletonBlockState(block, part.shape.getState())
        )

        RMResourcePack.addBlockLootTable(
            getIdentifier(),
            BlockLootTableGenerator.drops(this).build()
        )

        RMResourcePack.addItemModel(getIdentifier(), part.shape.getModel())

        return block
    }

}