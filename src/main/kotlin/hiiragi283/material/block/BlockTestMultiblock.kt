package hiiragi283.material.block

import hiiragi283.material.api.block.HiiragiBlock
import hiiragi283.material.api.multiblock.IMultiblockCore
import hiiragi283.material.api.multiblock.MultiblockComponent
import hiiragi283.material.api.multiblock.MultiblockPattern
import hiiragi283.material.util.failed
import hiiragi283.material.util.succeeded
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.monster.EntitySnowman
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object BlockTestMultiblock : HiiragiBlock(Material.TNT, "test_core"), IMultiblockCore {

    //    Event    //

    override fun onBlockActivated(
        world: World,
        pos: BlockPos,
        state: IBlockState,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing,
        hitX: Float,
        hitY: Float,
        hitZ: Float
    ): Boolean {
        return if (!world.isRemote) {
            if (hand == EnumHand.OFF_HAND) return false
            if (pattern.test(world, pos)) {
                onSucceeded(world, pos, player, hand, facing)
                true
            } else {
                onFailed(world, pos, player, hand, facing)
                false
            }
        } else true
    }

    //    IMultiblockCore    //

    private val pattern: MultiblockPattern = MultiblockPattern(
        BlockPos.ORIGIN to MultiblockComponent.Block(this),
        BlockPos(0, -1, 0) to MultiblockComponent.Block(Blocks.SNOW),
        BlockPos(0, -2, 0) to MultiblockComponent.Block(Blocks.SNOW)
    )

    override fun getPattern(world: World, pos: BlockPos): MultiblockPattern = pattern

    override fun onSucceeded(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ) {
        if (!world.isRemote) {
            succeeded(world, pos, player)
            pattern.getPoses(pos).forEach(world::setBlockToAir)
            val snowman = EntitySnowman(world)
            snowman.setLocationAndAngles(pos.x + 0.5, pos.y - 2 + 0.05, pos.z + 0.5, 0f, 0f)
            world.spawnEntity(snowman)
        }
    }

    override fun onFailed(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ) {
        if (!world.isRemote) {
            failed(world, pos, player)
        }
    }

}