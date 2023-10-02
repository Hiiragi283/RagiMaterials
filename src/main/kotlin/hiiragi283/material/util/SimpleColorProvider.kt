package hiiragi283.material.util

import hiiragi283.material.api.material.HiiragiMaterial
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import java.awt.Color

class SimpleColorProvider(val color: Int) : IBlockColor, IItemColor {

    constructor(color: Color) : this(color.rgb)

    constructor(stack: ItemStack) : this(
        stack.getItemImplemented<HiiragiMaterial.ITEM>()?.getMaterial(stack)?.color ?: -1
    )

    override fun colorMultiplier(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Int =
        color

    override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int = color

}