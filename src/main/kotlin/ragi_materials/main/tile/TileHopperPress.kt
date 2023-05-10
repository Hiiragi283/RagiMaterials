package ragi_materials.main.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.fluid.RagiTank
import ragi_materials.core.capability.fluid.RagiTankWrapper
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.recipe.HopperPressRecipe
import ragi_materials.core.tile.ITileCachable
import ragi_materials.core.tile.ITileContainer
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileTickableBase
import ragi_materials.core.util.dropInventoryItems
import ragi_materials.core.util.toBracket
import ragi_materials.main.container.ContainerHopperPress

class TileHopperPress : TileTickableBase(20), ITileCachable<HopperPressRecipe>, ITileContainer, ITileProvider.Inventory, ITileProvider.Tank {

    override var cache: HopperPressRecipe? = null

    lateinit var input: RagiItemHandler<TileHopperPress>
    lateinit var output: RagiTank

    //    Capability    //

    override fun createInventory(): RagiCapabilityProvider<IItemHandler> {
        input = object : RagiItemHandler<TileHopperPress>(1, this) {
            override fun onContentsChanged(slot: Int) {
                super.onContentsChanged(slot)
                if (tile.cacheRecipe(this, RagiRegistry.HP_RECIPE.valuesCollection)) {
                    tile.world.scheduleUpdate(tile.pos, tile.getState().block, 20 * 10) //10秒後にレシピを実行
                }
            }
        }.setIOType(EnumIOType.CATALYST)
        inventory = RagiItemHandlerWrapper(input)
        return RagiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, inventory)
    }

    override fun createTank(): RagiCapabilityProvider<IFluidHandler> {
        output = object : RagiTank(4000) {
            override fun onContentsChanged() {
                markDirty()
            }
        }.setIOType(EnumIOType.OUTPUT)
        tank = RagiTankWrapper(output)
        return RagiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank, tank)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropInventoryItems(world, pos, input)
    }

    //    Recipe    //

    fun doProcess() {
        //レシピが保存済み，かつ内部タンクが空の場合
        if (cache !== null && output.fluid == null) {
            //まとめて処理を行う関係上，完成品に倍率をかける必要がある
            val multiply = input.getStackInSlot(0).count / cache!!.getInput().count
            RagiMaterials.LOGGER.debug("multiply: x$multiply")
            val fluid = cache!!.getOutput()
            val result = FluidStack(fluid, fluid.amount * multiply)
            RagiMaterials.LOGGER.debug(result.toBracket())
            input.setStackInSlot(0, ItemStack.EMPTY) //inventoryを空にする
            output.fluid = result //tankを埋める
            cache = null //cacheをリセットする
        }
    }

    //    TileTickableBase    //

    override fun onUpdateServer() {
        world.getTileEntity(pos.down())?.let { tileTo ->
            tileTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP)?.let {
                FluidUtil.tryFluidTransfer(it, output, output.fluid, true)
            }
        }
    }

    //    ITileContainer    //

    override fun getDisplayName() = super<ITileContainer>.getDisplayName()

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerHopperPress(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:hopper_press"

}