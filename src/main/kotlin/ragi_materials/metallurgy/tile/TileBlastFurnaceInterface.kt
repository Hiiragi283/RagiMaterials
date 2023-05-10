package ragi_materials.metallurgy.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
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
import ragi_materials.core.recipe.BlastFurnaceRecipe
import ragi_materials.core.tile.ITileCachable
import ragi_materials.core.tile.ITileContainer
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileBase
import ragi_materials.core.util.dropInventoryItems
import ragi_materials.metallurgy.container.ContainerBlastFurnace

class TileBlastFurnaceInterface : TileBase(), ITileCachable<BlastFurnaceRecipe>, ITileContainer, ITileProvider.Inventory, ITileProvider.Tank {

    lateinit var inputOre: RagiItemHandler<TileBlastFurnaceInterface>
    lateinit var inputFuel: RagiItemHandler<TileBlastFurnaceInterface>
    lateinit var inputFlux: RagiItemHandler<TileBlastFurnaceInterface>
    lateinit var output: RagiItemHandler<TileBlastFurnaceInterface>
    lateinit var outputTank: RagiTank

    override var cache: BlastFurnaceRecipe? = null

    //    Capability    //

    override fun createInventory(): RagiCapabilityProvider<IItemHandler> {
        inputOre = RagiItemHandler(1, this).setIOType(EnumIOType.INPUT)
        inputFuel = RagiItemHandler(1, this).setIOType(EnumIOType.INPUT)
        inputFlux = RagiItemHandler(1, this).setIOType(EnumIOType.INPUT)
        output = RagiItemHandler(1, this).setIOType(EnumIOType.OUTPUT)
        inventory = RagiItemHandlerWrapper(inputOre, inputFuel, inputFlux, output)
        return RagiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, inventory)
    }

    override fun createTank(): RagiCapabilityProvider<IFluidHandler> {
        outputTank = object : RagiTank(144 * 64) {
            override fun onContentsChanged() {
                markDirty()
            }
        }.setIOType(EnumIOType.OUTPUT)
        tank = RagiTankWrapper(outputTank)
        return RagiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank, tank)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing) = false

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropInventoryItems(world, pos, inventory)
    }

    //    ITileContainer    //

    override fun getDisplayName() = super<ITileContainer>.getDisplayName()

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerBlastFurnace(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:blast_furnace"

    //    Recipe    //

    fun doProcess() {
        if (cacheRecipe(inventory, RagiRegistry.BF_RECIPE.valuesCollection)) {
            RagiMaterials.LOGGER.debug("The recipe cached is ${cache!!.registryName}")
            val dummyStack = output.insertItem(0, cache!!.getOutputSlag(), true)
            val dummyFluid = outputTank.fill(cache!!.getOutput(), false)
            //仮想的に搬入した量とレシピの出力量が一致する場合
            if (dummyStack.isEmpty && dummyFluid == cache!!.getOutput().amount) {
                //実際にレシピを実行
                inputOre.extractItem(0, cache!!.getInputOre().count, false)
                inputFuel.extractItem(0, cache!!.getInputFuel().count, false)
                inputFlux.extractItem(0, cache!!.getInputFlux().count, false)
                outputTank.fill(cache!!.getOutput(), true)
                output.insertItem(0, cache!!.getOutputSlag(), false)
                syncData() //クライアント側と同期
            }
        }
    }
}