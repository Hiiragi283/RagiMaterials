package hiiragi283.material.item

import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.entity.EntityMinecartTank
import hiiragi283.material.util.getBlockImplemented
import net.minecraft.block.BlockDispenser
import net.minecraft.block.BlockRailBase
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import kotlin.math.floor

/**
 * Thanks to defeatedcrow!
 * [: Source](https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/machine/item/ItemMinecartMotor.java)
 */
object ItemMinecartTank : HiiragiItem("minecart_tank") {

    init {

        creativeTab = CreativeTabs.TRANSPORTATION

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, object : BehaviorDefaultDispenseItem() {

            override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
                val facing: EnumFacing = source.blockState.getValue(BlockDispenser.FACING)
                val world: World = source.world
                val d0: Double = source.getX() + facing.xOffset.toDouble() * 1.125 //x座標
                val d1: Double = floor(source.getY()) + facing.yOffset.toDouble() //y座標の基準
                val d2: Double = source.getZ() + facing.zOffset.toDouble() * 1.125 //z座標
                val pos: BlockPos = source.blockPos.offset(facing)
                val state: IBlockState = world.getBlockState(pos)
                //レールの方角を取得
                val railDirection: BlockRailBase.EnumRailDirection = state.getBlockImplemented<BlockRailBase>()
                    ?.getRailDirection(world, pos, state, null)
                    ?: BlockRailBase.EnumRailDirection.NORTH_SOUTH
                //y座標の修正値を取得する
                val d3: Double = if (BlockRailBase.isRailBlock(state)) {
                    if (railDirection.isAscending) 0.6 else 0.1
                } else {
                    val pos1: BlockPos = pos.down()
                    val state1: IBlockState = world.getBlockState(pos1)
                    if (state.material != Material.AIR || BlockRailBase.isRailBlock(state1)) {
                        return this.dispense(source, stack)
                    }
                    val railDirection1: BlockRailBase.EnumRailDirection = state.getBlockImplemented<BlockRailBase>()
                        ?.getRailDirection(world, pos1, state1, null)
                        ?: BlockRailBase.EnumRailDirection.NORTH_SOUTH
                    if (facing != EnumFacing.DOWN && railDirection1.isAscending) -0.4 else -0.9
                }
                val minecart = EntityMinecartTank(world, d0, d1 + d3, d2)
                if (stack.hasDisplayName()) {
                    minecart.customNameTag = stack.displayName //名前を引き継ぐ
                }
                world.spawnEntity(minecart)
                stack.shrink(1)
                return stack
            }

            override fun playDispenseSound(source: IBlockSource) {
                source.getWorld().playEvent(1000, source.getBlockPos(), 0)
            }

        })

    }

    override fun onItemUse(
        player: EntityPlayer,
        world: World,
        pos: BlockPos,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): EnumActionResult {
        val state: IBlockState = world.getBlockState(pos)
        if (!BlockRailBase.isRailBlock(state)) return EnumActionResult.FAIL
        else {
            val stack: ItemStack = player.getHeldItem(hand)
            if (!world.isRemote) {
                val railDirection: BlockRailBase.EnumRailDirection = state.getBlockImplemented<BlockRailBase>()
                    ?.getRailDirection(world, pos, state, null)
                    ?: BlockRailBase.EnumRailDirection.NORTH_SOUTH
                val d0: Double = if (railDirection.isAscending) 0.5 else 0.0
                val minecart = EntityMinecartTank(
                    world,
                    pos.x.toDouble() + 0.5,
                    pos.y.toDouble() + 0.0625 + d0,
                    pos.z.toDouble() + 0.5
                )
                if (stack.hasDisplayName()) {
                    minecart.customNameTag = stack.displayName
                }
                world.spawnEntity(minecart)
            }
            stack.shrink(1)
            return EnumActionResult.SUCCESS
        }
    }


}