package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.Rotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ItemWrench : HiiragiItem("wrench") {

    init {
        maxStackSize = 1
        maxDamage = 1109
    }

    //    Crafting    //

    override fun getContainerItem(stack: ItemStack): ItemStack = stack.copy().also { it.itemDamage += 1 }

    override fun hasContainerItem(stack: ItemStack): Boolean = true

    //    Event    //

    override fun onItemUseFirst(
        player: EntityPlayer,
        world: World,
        pos: BlockPos,
        side: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float,
        hand: EnumHand
    ): EnumActionResult {
        val state: IBlockState = world.getBlockState(pos)
        return if (!world.isRemote) {
            if (player.isSneaking) {
                world.setBlockState(pos, state.withRotation(Rotation.COUNTERCLOCKWISE_90))
                EnumActionResult.SUCCESS
            } else {
                world.setBlockState(pos, state.withRotation(Rotation.CLOCKWISE_90))
                EnumActionResult.SUCCESS
            }
        } else super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand)
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean = true

}