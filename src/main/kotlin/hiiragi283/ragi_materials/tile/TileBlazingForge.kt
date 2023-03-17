package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.base.TileBase
import hiiragi283.ragi_materials.capability.RagiTank
import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.util.RagiSoundEvent
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler

class TileBlazingForge : TileBase(103) {

    private val mapFuel = RagiUtil.convertArrayTomMap(RagiConfig.recipeMap.fuelBlazingForge)

    private var tank = object : RagiTank(16000) {
        //コンフィグの燃料一覧に登録された液体のみ受け付ける
        override fun canFillFluidType(fluid: FluidStack?): Boolean = fluid?.fluid?.name in mapFuel.keys
    }

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyTank, this.tank.serializeNBT()) //燃料をNBTタグに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        this.tank.deserializeNBT(tag.getCompoundTag(keyTank))  //NBTタグから燃料を読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, null)) this.tank as T else null
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
        }
        return result
    }

    //    Recipe    //

    private fun getFuelConsumption(): Int? {
        val name = this.tank.fluid?.fluid?.name
        return mapFuel[name]?.toInt()
    }

    private fun canProcess(): Boolean {
        val amount = this.tank.fluid?.amount
        return amount !== null && getFuelConsumption() !== null && amount >= getFuelConsumption()!!
    } //入っている燃料がコンフィグで指定された量より多いなら実行可能

    private fun doProcess(player: EntityPlayer, hand: EnumHand): Boolean {
        var result = false
        val stack = player.getHeldItem(hand)
        val stackResult = TileForgeFurnace.getResult(stack)
        if (canProcess() && !stackResult.isEmpty) {
            this.tank.drain(getFuelConsumption()!!, true) //燃料を消費する

            stack.shrink(1) //手持ちのアイテムを1つ減らす
            RagiUtil.dropItemAtPlayer(player, stackResult) //完成品をプレイヤーに渡す

            RagiSoundEvent.playSound(this, RagiSoundEvent.getSound("minecraft:block.fire.extinguish"))
            result = true
        }
        return result
    }
}