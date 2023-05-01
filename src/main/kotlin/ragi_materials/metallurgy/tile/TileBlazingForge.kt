package ragi_materials.metallurgy.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.fluid.RagiTank
import ragi_materials.core.capability.fluid.RagiTankWrapper
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.*

class TileBlazingForge : TileBase(), ITileProvider.Tank {

    private val mapFuel = convertArrayTomMap(RagiConfig.recipeMap.fuelBlazingForge)

    lateinit var inputTank: RagiTank

    //    Capability    //

    override fun createTank(): RagiCapabilityProvider<IFluidHandler> {
        inputTank = object : RagiTank(16000) {
            //コンフィグの燃料一覧に登録された液体のみ受け付ける
            override fun canFillFluidType(fluid: FluidStack?): Boolean = fluid?.fluid?.name in mapFuel.keys
        }.setIOType(EnumIOType.INPUT)
        tank = RagiTankWrapper(inputTank)
        return RagiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank, tank)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        var result = false
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            result = if (FluidUtil.getFluidHandler(stack) !== null) {
                FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing)
            } else doProcess(player, hand)
            if (result) succeeded(this) else failed(this)
        }
        return result
    }

    //    Recipe    //

    private fun getFuelConsumption(): Int? {
        val name = inputTank.fluid?.fluid?.name
        return mapFuel[name]?.toInt()
    }

    private fun canProcess(): Boolean {
        val amount = inputTank.fluid?.amount
        return amount !== null && getFuelConsumption() !== null && amount >= getFuelConsumption()!!
    } //入っている燃料がコンフィグで指定された量より多いなら実行可能

    private fun doProcess(player: EntityPlayer, hand: EnumHand): Boolean {
        var result = false
        val stack = player.getHeldItem(hand)
        for (recipe in RagiRegistry.FF_RECIPE.valuesCollection) {
            if (recipe.match(stack, Integer.MAX_VALUE) && canProcess()) {
                inputTank.drain(getFuelConsumption()!!, true) //燃料を消費する

                stack.shrink(1) //手持ちのアイテムを1つ減らす
                dropItemAtPlayer(player, recipe.getOutput()) //完成品をプレイヤーに渡す

                playSound(this, SoundEvents.BLOCK_FIRE_EXTINGUISH)
                result = true
                break
            }
        }
        return result
    }
}