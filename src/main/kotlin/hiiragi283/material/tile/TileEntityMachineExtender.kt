package hiiragi283.material.tile

import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.util.getTile
import net.minecraft.block.BlockDirectional
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidUtil

class TileEntityMachineExtender : HiiragiTileEntity() {

    //    Capability    //

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return getTile<TileEntity>(world, getPos().offset(getState().getValue(BlockDirectional.FACING)))
            ?.takeUnless { it is TileEntityMachineExtender }
            ?.hasCapability(capability, facing)
            ?: super.hasCapability(capability, facing)
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return getTile<TileEntity>(world, getPos().offset(getState().getValue(BlockDirectional.FACING)))
            ?.takeUnless { it is TileEntityMachineExtender }
            ?.getCapability(capability, facing)
            ?: super.getCapability(capability, facing)
    }

    //    Event    //

    override fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean {
        return if (!world.isRemote) {
            FluidUtil.interactWithFluidHandler(
                player,
                hand,
                world,
                pos.offset(getState().getValue(BlockDirectional.FACING)),
                getState().getValue(BlockDirectional.FACING).opposite
            )
        } else true
    }

}