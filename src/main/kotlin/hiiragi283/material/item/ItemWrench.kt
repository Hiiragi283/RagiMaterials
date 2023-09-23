package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.*
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
        return if (player.isSneaking) {
            when (side.axis) {
                EnumFacing.Axis.X -> {
                    world.setBlockState(pos, state.withMirror(Mirror.FRONT_BACK))
                    EnumActionResult.SUCCESS
                }

                EnumFacing.Axis.Z -> {
                    world.setBlockState(pos, state.withMirror(Mirror.LEFT_RIGHT))
                    EnumActionResult.SUCCESS
                }

                else -> EnumActionResult.FAIL
            }
        } else {
            world.setBlockState(pos, state.withRotation(Rotation.CLOCKWISE_90))
            EnumActionResult.SUCCESS
        }
    }

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun isFull3D(): Boolean = true

}