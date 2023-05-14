package hiiragi283.material.client.color

import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.state.BlockState
import java.awt.Color

object IColorHandler {

    interface BLOCK {
        fun getColor(state: BlockState, world: BlockAndTintGetter, pos: BlockPos, tintIndex: Int): Color
    }

    interface ITEM {
        fun getColor(stack: ItemStack, tintIndex: Int): Color
    }

}