package hiiragi283.material.api.part

import net.minecraft.block.BlockState
import net.minecraft.fluid.Fluid
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

interface IHiiragiPart<T : Any> {

    fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int = -1

    fun getColor(stack: ItemStack, tintIndex: Int): Int = -1

    interface BLOCK : IHiiragiPart<BlockState>

    interface FLUID : IHiiragiPart<Fluid>

    interface ITEM : IHiiragiPart<ItemStack>

}