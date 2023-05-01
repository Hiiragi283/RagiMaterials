package ragi_materials.main.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.RagiMaterials
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.fluid.RagiTank
import ragi_materials.core.capability.fluid.RagiTankWrapper
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileItemHandlerBase
import ragi_materials.core.util.getBottle
import ragi_materials.main.container.ContainerFullBottle

class TileFullBottleStation : TileItemHandlerBase(), ITickable, ITileProvider.Inventory, ITileProvider.Tank {

    lateinit var output: RagiItemHandler
    lateinit var inputTank: RagiTank
    var count = 0

    //    Capability    //

    override fun createInventory(): RagiCapabilityProvider<IItemHandler> {
        output = RagiItemHandler(1, this).setIOType(EnumIOType.OUTPUT)
        inventory = RagiItemHandlerWrapper(output)
        return RagiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, inventory)
    }

    override fun createTank(): RagiCapabilityProvider<IFluidHandler> {
        inputTank = RagiTank(64000).setIOType(EnumIOType.INPUT)
        tank = RagiTankWrapper(inputTank)
        return RagiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank, tank)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        val fluidHandler = FluidUtil.getFluidHandler(player.getHeldItem(hand))
        return if (fluidHandler !== null) FluidUtil.interactWithFluidHandler(player, hand, world, pos, facing) else {
            player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
            return true
        }
    }

    //    ITickable    //

    override fun update() {
        if (!world.isRemote) {
            //countが20以上の場合
            if (count >= 20) {
                val amountRemain = inputTank.fluidAmount % 1000 //タンクに残る液体量
                val countBottle = inputTank.fluidAmount / 1000 //生成するフルボトルの個数
                //作成個数が0より多い場合
                if (countBottle > 0 && inputTank.fluid !== null && output.getStackInSlot(0).isEmpty) {
                    output.insertItem(0, getBottle(FluidStack(inputTank.fluid!!, 1000), countBottle), false) //フルボトルを製造
                    if (amountRemain > 0) {
                        inputTank.fluid = FluidStack(inputTank.fluid!!, amountRemain) //タンクの内容量を上書き
                    } else {
                        inputTank.fluid = null //液体量が0ならば空にする
                    }
                }
                count = 0 //countをリセット
            } else count++ //countを追加
        }
    }

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerFullBottle(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:fullbottle_station"

}