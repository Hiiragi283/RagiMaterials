package ragi_materials.metallurgy.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.RagiTank
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.*

class TileBlazingForge : TileBase() {

    private val mapFuel = convertArrayTomMap(RagiConfig.recipeMap.fuelBlazingForge)

    private var tank = object : RagiTank(16000) {
        //コンフィグの燃料一覧に登録された液体のみ受け付ける
        override fun canFillFluidType(fluid: FluidStack?): Boolean = fluid?.fluid?.name in mapFuel.keys
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound) = tag.also {
        super.writeToNBT(it)
        it.setTag(keyTank, tank.serializeNBT()) //燃料をNBTタグに書き込む
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        tank.deserializeNBT(tag.getCompoundTag(keyTank))  //NBTタグから燃料を読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, null)) CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank) else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
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

    override fun onTilePlaced(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        readNBTFromStack(stack)
    }

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropItemFromTile(world, pos, ItemStack(RagiRegistry.BlockBlazingForge), this)
    }

    //    Recipe    //

    private fun getFuelConsumption(): Int? {
        val name = tank.fluid?.fluid?.name
        return mapFuel[name]?.toInt()
    }

    private fun canProcess(): Boolean {
        val amount = tank.fluid?.amount
        return amount !== null && getFuelConsumption() !== null && amount >= getFuelConsumption()!!
    } //入っている燃料がコンフィグで指定された量より多いなら実行可能

    private fun doProcess(player: EntityPlayer, hand: EnumHand): Boolean {
        var result = false
        val stack = player.getHeldItem(hand)
        for (recipe in RagiRegistry.FF_RECIPE.valuesCollection) {
            if (recipe.match(stack, Integer.MAX_VALUE) && canProcess()) {
                tank.drain(getFuelConsumption()!!, true) //燃料を消費する

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