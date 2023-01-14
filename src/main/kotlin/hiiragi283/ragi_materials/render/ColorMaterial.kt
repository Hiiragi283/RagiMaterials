package hiiragi283.ragi_materials.render

import hiiragi283.ragi_materials.items.ItemCraftingTool
import hiiragi283.ragi_materials.materials.MaterialHelper
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class ColorMaterial : IItemColor, IBlockColor {

    override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int {
        //stackからitemを取得
        val item = stack.item
        //itemがItemCraftingToolクラスの派生物の場合
        return if (item is ItemCraftingTool) {
            //NBTタグからEnumMaterialsを取得
            val tag = stack.tagCompound!!
            val material = MaterialHelper.getMaterial(tag.getString("material"))
            //tintIndexが1ならばEnumMaterials.color，そうでないなら白を返す
            if (tintIndex == 1) material.color.rgb else 0xFFFFFF
        }
        //itemがItemCraftingToolクラスの派生物でない場合
        else {
            //メタデータからEnumMaterialsを取得
            val material = MaterialHelper.getMaterial(stack.metadata)
            //tintIndexが0ならばEnumMaterials.color，そうでないなら白を返す
            if (tintIndex == 0) material.color.rgb else 0xFFFFFF
        }
    }

    override fun colorMultiplier(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Int {
        val material = MaterialHelper.getMaterial(state.block.getMetaFromState(state))
        return if (tintIndex == 0) material.color.rgb else 0xFFFFFF
    }
}