package ragi_materials.main.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.RagiMaterials
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileItemHandlerBase
import ragi_materials.core.util.dropInventoryItems
import ragi_materials.main.container.ContainerLaboTable

abstract class TileLaboBase : TileItemHandlerBase(), ITileProvider.Inventory {

    lateinit var input: RagiItemHandler
    lateinit var catalyst: RagiItemHandler

    //    Capability    //

    override fun createInventory(): RagiCapabilityProvider<IItemHandler> {
        input = RagiItemHandler(5, this).setIOType(EnumIOType.INPUT)
        catalyst = RagiItemHandler(1, this).setIOType(EnumIOType.CATALYST)
        inventory = RagiItemHandlerWrapper(input, catalyst)
        return RagiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, inventory)
    }

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterials.INSTANCE, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    override fun onTileRemoved(world: World, pos: BlockPos, state: IBlockState) {
        dropInventoryItems(world, pos, inventory)
    }

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerLaboTable(player, this)

}