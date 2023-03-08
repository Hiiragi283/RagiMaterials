package hiiragi283.ragi_materials.base

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ItemMaterialTool(ID: String, private val toolClass: String, private var efficiency: Float = 4.0f) : ItemMaterialToolBase(ID) {

    //    Efficiency    //

    override fun canHarvestBlock(state: IBlockState, stack: ItemStack): Boolean {
        val block = state.block
        val toolClass = block.getHarvestTool(state)
        val toolLevel = block.getHarvestLevel(state)
        return this.toolClass == toolClass
    }

    override fun getDestroySpeed(stack: ItemStack, state: IBlockState): Float {
        val block = state.block
        val toolClass = block.getHarvestTool(state)
        return if (this.toolClass == toolClass) efficiency else 1.0f
    }

    override fun getToolClasses(stack: ItemStack): MutableSet<String> = mutableSetOf(toolClass)

    override fun onBlockDestroyed(stack: ItemStack, world: World, state: IBlockState, pos: BlockPos, entityLiving: EntityLivingBase) = run {
        //サーバー側，かつ破壊後のブロックが空気の場合
        if (!world.isRemote && state.block.isAir(state, world, pos)) setDamage(stack, getDamage(stack) - 1) //耐久値を1つ減らす
        true
    }
}