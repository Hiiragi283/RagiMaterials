package hiiragi283.material.client.color

import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView
import java.awt.Color

object IColorHandler {

    interface BLOCK {
        fun getColor(state: BlockState, world: BlockRenderView, pos: BlockPos, tintIndex: Int): Color
    }

    interface ITEM {
        fun getColor(stack: ItemStack, tintIndex: Int): Color
    }

}