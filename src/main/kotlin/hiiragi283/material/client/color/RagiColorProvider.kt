package hiiragi283.material.client.color

import hiiragi283.material.util.RagiColor
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.color.block.BlockColor
import net.minecraft.client.color.item.ItemColor
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.state.BlockState

@Environment(value = EnvType.CLIENT)
object RagiColorProvider : BlockColor, ItemColor {

    //    BlockColor    //

    override fun getColor(
        state: BlockState,
        blockAndTintGetter: BlockAndTintGetter?,
        pos: BlockPos?,
        tintIndex: Int
    ): Int {
        val block = state.block
        return if (block is IColorHandler.BLOCK && blockAndTintGetter !== null && pos !== null) block.getColor(
            state,
            blockAndTintGetter,
            pos,
            tintIndex
        ).rgb else RagiColor.WHITE.rgb
    }

    //    ItemColor    //

    override fun getColor(stack: ItemStack, tintIndex: Int): Int {
        val item = stack.item
        return if (item is IColorHandler.ITEM) item.getColor(stack, tintIndex).rgb else RagiColor.WHITE.rgb
    }
}