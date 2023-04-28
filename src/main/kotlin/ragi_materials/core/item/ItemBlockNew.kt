package ragi_materials.core.item

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.block.state.IBlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import ragi_materials.core.RagiMaterials
import ragi_materials.core.block.BlockBase
import ragi_materials.core.util.playSound

class ItemBlockNew(val block: BlockBase, maxMeta: Int) : ItemBase(RagiMaterials.MOD_ID, block.registryName!!.namespace, maxMeta) {

    //    General    //

    override fun getCreativeTab(): CreativeTabs? = block.creativeTab

    override fun getTranslationKey(): String = block.translationKey

    override fun getTranslationKey(stack: ItemStack): String = translationKey + if (maxMeta == 0) "" else ".${stack.metadata}"

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        block.addInformation(stack, worldIn, tooltip, flagIn)
    }

    //    Event    //

    override fun onItemUse(player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {

        val stack = player.getHeldItem(hand)
        val state = world.getBlockState(pos)
        val block = state.block
        val pos1 = if (!block.isReplaceable(world, pos)) pos.offset(facing) else pos

        return if (!stack.isEmpty && player.canPlayerEdit(pos1, facing, stack) && world.mayPlace(this.block, pos1, false, facing, player)) {
            val meta = this.getMetadata(stack.metadata)
            var state1 = this.block.getStateForPlacement(world, pos1, facing, hitX, hitY, hitZ, meta, player, hand)
            if (placeBlockAt(stack, player, world, pos1, state1)) {
                state1 = world.getBlockState(pos1)
                val sound = state1.block.getSoundType(state1, world, pos1, player)
                playSound(world, pos, sound.placeSound, (sound.getVolume() + 1.0f) / 2.0f, sound.getPitch() * 0.8f, SoundCategory.BLOCKS)
                stack.shrink(1)
            }
            EnumActionResult.SUCCESS
        } else {
            EnumActionResult.FAIL
        }
    }

    private fun placeBlockAt(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, newState: IBlockState): Boolean {
        if (!world.setBlockState(pos, newState, 11)) return false
        val state = world.getBlockState(pos)
        if (state.block === block) {
            block.onBlockPlacedBy(world, pos, state, player, stack)
            if (player is EntityPlayerMP) CriteriaTriggers.PLACED_BLOCK.trigger(player, pos, stack)
        }
        return true
    }

}