package hiiragi283.material.client.color

import hiiragi283.material.util.RagiColor
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.BlockState
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

@Environment(value = EnvType.CLIENT)
object RagiColorProvider : BlockColorProvider, ItemColorProvider {

    //    BlockColorProvider    //

    override fun getColor(state: BlockState, world: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int {
        val block = state.block
        return if (block is IColorHandler.BLOCK && world !== null && pos !== null) block.getColor(state, world, pos, tintIndex).rgb else RagiColor.WHITE.rgb
    }

    //    ItemColorProvider    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Int {
        val item = stack.item
        return if (item is IColorHandler.ITEM) item.getColor(stack, tintIndex).rgb else RagiColor.WHITE.rgb
    }
}