package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterialsMod
import hiiragi283.ragi_materials.base.TileLockableBase
import hiiragi283.ragi_materials.block.BlockStoneMill
import hiiragi283.ragi_materials.capability.RagiInventory
import hiiragi283.ragi_materials.client.container.ContainerStoneMill
import hiiragi283.ragi_materials.init.RagiGuiHandler
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.util.RagiResult
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper

class TileStoneMill : TileLockableBase(105), ISidedInventory {

    override val inventory = RagiInventory("gui.ragi_materials.stone_mill", 2)
    private val invWrapperIn = SidedInvWrapper(this, EnumFacing.UP) //搬入
    private val invWrapperOut = SidedInvWrapper(this, EnumFacing.DOWN) //搬出

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        ItemStackHelper.saveAllItems(tag, inventory.inventory) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        ItemStackHelper.loadAllItems(tag, inventory.inventory) //tagからインベントリを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) {
            if (facing != EnumFacing.DOWN) invWrapperIn as T else invWrapperOut as T
        } else super.getCapability(capability, facing)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY

    //    TileBase    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) {
            if (player.isSneaking) {
                player.openGui(RagiMaterialsMod.INSTANCE!!, RagiGuiHandler.RagiID, world, pos.x, pos.y, pos.z)
            } else {
                val state = world.getBlockState(pos)
                val count = state.getValue(BlockStoneMill.COUNT)
                if (count == 7) doProcess()
                world.setBlockState(pos, state.withProperty(BlockStoneMill.COUNT, (count + 1) % 8), 2) //8で割った余りにすることでオーバーフローを防止
            }
        }
        return true
    }

    //    Recipe    //

    private fun canProcess(recipe: MillRecipe): Boolean {
        var result = false
        val stack = inventory.getStackInSlot(0)
        val output = recipe.getOutput()
        //スロットが空ならtrue
        if (stack.isEmpty) {
            inventory.setInventorySlotContents(0, output)
            inventory.decrStackSize(1, recipe.getInput().count)
            result = true
        }
        //スロットにあるstackと完成品が同じ場合
        else if (RagiUtil.isSameStack(output, stack, false)) {
            stack.grow(output.count) //スロット内のアイテムを増やす
            inventory.decrStackSize(1, recipe.getInput().count)
            result = true
        }
        return result
    }

    private fun doProcess() {
        val stack = inventory.getStackInSlot(1)
        var result = false
        for (recipe in MillRecipe.Registry.list) {
            if (recipe.match(stack)) {
                result = canProcess(recipe)
                break
            }
        }
        if (result) RagiResult.succeeded(this) else RagiResult.failed(this)
    }

    //    TileLockableBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerStoneMill(player, this)

    override fun getGuiID() = "ragi_materials:stone_mill"

    //    ISidedInventory    //

    override fun getSlotsForFace(side: EnumFacing): IntArray = if (side == EnumFacing.DOWN) intArrayOf(0) else intArrayOf(1)

    override fun canInsertItem(index: Int, itemStackIn: ItemStack, direction: EnumFacing) = direction != EnumFacing.DOWN && index == 1

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing) = direction == EnumFacing.DOWN && index == 0

}