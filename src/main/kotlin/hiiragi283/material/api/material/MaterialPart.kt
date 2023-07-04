package hiiragi283.material.api.material

import hiiragi283.material.api.part.HiiragiPart
import net.minecraft.block.BlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockRenderView

interface MaterialPart<T : Any> {

    fun getColor(state: BlockState, view: BlockRenderView?, pos: BlockPos?, tintIndex: Int): Int = -1

    fun getColor(stack: ItemStack, tintIndex: Int): Int = -1

    fun getMaterial(obj: T): HiiragiMaterial

    fun getPart(obj: T): HiiragiPart

}