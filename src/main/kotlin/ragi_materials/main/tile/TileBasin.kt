package ragi_materials.main.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.fluid.RagiTank
import ragi_materials.core.capability.fluid.RagiTankWrapper
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.dropItemAtPlayer
import ragi_materials.core.util.failed
import ragi_materials.core.util.playSound
import ragi_materials.core.util.succeeded

class TileBasin : TileBase(), ITileProvider.Tank {

    lateinit var tankBasin: RagiTank

    //    Capability    //

    override fun createTank(): RagiCapabilityProvider<IFluidHandler> {
        tankBasin = object : RagiTank(4000) {
            override fun onContentsChanged() {
                markDirty()
            }
        }.setIOType(EnumIOType.GENERAL)
        tank = RagiTankWrapper(tankBasin)
        return RagiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank, tank)
    }

    //    TileBase  //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        var result = false
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            if (!stack.isEmpty) {
                //液体の搬出入が優先される
                FluidUtil.getFluidHandler(stack)?.let {
                    return FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing)
                }
                for (recipe in RagiRegistry.BASIN_RECIPE.valuesCollection) {
                    if (recipe.match(stack, tankBasin.fluid)) {
                        stack.shrink(recipe.getInput().count) //ItemStackを減らす
                        for (output in recipe.getOutputs()) {
                            dropItemAtPlayer(player, output)
                        }
                        tankBasin.drain(recipe.getInputFluid(), true) //FluidStackを減らす
                        playSound(this, SoundEvents.ITEM_BUCKET_EMPTY)
                        result = true
                        break
                    }
                }
            }
            if (result) succeeded(this) else failed(this)
            syncData()
        }
        return result
    }
}