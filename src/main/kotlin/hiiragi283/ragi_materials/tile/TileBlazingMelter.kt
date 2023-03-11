package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.util.RagiSoundEvent
import hiiragi283.ragi_materials.util.RagiTank
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

class TileBlazingMelter: TileBase(103) {

    private var tankLava = object: RagiTank(16000) {
        //溶岩のみを受け付ける
        override fun canFillFluidType(fluid: FluidStack?): Boolean = fluid?.fluid == FluidRegistry.LAVA
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyTank, this.tankLava.serializeNBT()) //燃料をNBTタグに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        this.tankLava.deserializeNBT(tag.getCompoundTag(keyTank))  //NBTタグから燃料を読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, null)) this.tankLava as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        val stack =  player.getHeldItem(hand)
        return if (FluidUtil.getFluidHandler(stack) !== null) {
            FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing)
        } else doProcess(player, hand)
    }

    //    Recipe    //

    private fun canProcess(): Boolean {
        val amount = this.tankLava.fluid?.amount
        return amount !== null && amount >= 10
    } //入っている燃料が消費量より多いなら実行可能

    private fun doProcess(player: EntityPlayer, hand: EnumHand): Boolean {
        val stack = player.getHeldItem(hand)
        val stackResult = TileForgeFurnace.getResult(stack)
        var result = false
        if (canProcess() && !stackResult.isEmpty) {
            this.tankLava.drain(10, true) //溶岩を10 mb消費する

            stack.shrink(1) //手持ちのアイテムを1つ減らす
            RagiUtil.dropItemAtPlayer(player, stackResult) //完成品をプレイヤーに渡す

            RagiSoundEvent.playSound(this, RagiSoundEvent.getSound("minecraft:block.fire.extinguish"))
            result = true
        }
        return result
    }

}