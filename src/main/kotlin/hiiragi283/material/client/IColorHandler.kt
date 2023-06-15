package hiiragi283.material.client

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import java.awt.Color

object IColorHandler {

    interface Block {

        fun getColor(stack: ItemStack, tintIndex: Int): Color
        fun getColor(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Color

    }

    interface Item {

        fun getColor(stack: ItemStack, tintIndex: Int): Color

    }

}