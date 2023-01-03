package hiiragi283.ragi_materials.render

import hiiragi283.ragi_materials.materials.MaterialHelper
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class ColorMaterial : IItemColor, IBlockColor {

    override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int {
        val material = MaterialHelper.getMaterial(stack.metadata)
        return material.color.rgb
    }

    override fun colorMultiplier(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Int {
        val material = MaterialHelper.getMaterial(state.block.getMetaFromState(state))
        return material.color.rgb
    }
}