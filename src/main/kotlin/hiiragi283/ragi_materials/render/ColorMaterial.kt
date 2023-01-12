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
        return if (tintIndex == 0) material.color.rgb else 0xFFFFFF
    }

    override fun colorMultiplier(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Int {
        val material = MaterialHelper.getMaterial(state.block.getMetaFromState(state))
        return if (tintIndex == 0) material.color.rgb else 0xFFFFFF
    }
}

class ColorMaterialTool : IItemColor {

    override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int {
        return if (stack.tagCompound == null) 0xFFFFFF else {
            val tag = stack.tagCompound!!
            val material = MaterialHelper.getMaterial(tag.getString("material"))
            return if (tintIndex == 1) material.color.rgb else 0xFFFFFF
        }
    }
}