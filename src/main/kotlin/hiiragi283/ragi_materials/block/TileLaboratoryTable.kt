package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.recipe.laboratory.LTRegistry
import hiiragi283.ragi_materials.util.RagiLogger
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class TileLaboratoryTable : TileEntity() {

    val inventory = ItemStackHandler(5)

    //    NBT tag    //

    override fun getUpdateTag(): NBTTagCompound {
        //オーバーライドしないと正常に更新されない
        return writeToNBT(NBTTagCompound())
    }

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag("inventory", inventory.serializeNBT()) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag("inventory")) //tagからインベントリを読み込む
    }

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/base/TileIBC.java#L93
    */

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newState: IBlockState): Boolean {
        return oldState.block != newState.block //更新の前後でBlockが変化する場合のみtrue
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) this.inventory as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
    }

    //    Event    //

    fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand) {
        //サーバー側の場合
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            //手持ちのItemStackが空の場合
            if (stack.isEmpty) {
                /*for (i in 0 until inventory.slots) {
                    val numReverse = inventory.slots - (i + 1)
                    //スロット内のItemStackを取得 (逆順)
                    val stackSlot = inventory.getStackInSlot(numReverse)
                    //ItemStackが空でない場合
                    if (!stackSlot.isEmpty) {
                        //プレイヤーの手持ちを上書き
                        player.setHeldItem(hand, inventory.extractItem(i, stackSlot.count, false))
                        markDirty() //内部データの変化を送信
                        RagiLogger.infoDebug("Stack extracted from slot$numReverse!")
                        break
                    } else RagiLogger.infoDebug("The slot$numReverse is empty!")
                }*/
            } else {
                for (i in 0 until inventory.slots) {
                    //スロットにItemStackを入れた際の余りを取得
                    val stackRemain = inventory.insertItem(i, stack, true)
                    //投入の前後でItemStackが変化しない -> スロットは埋まっている
                    //投入の前後でItemStackが変化した -> スロットに空きがある
                    if (!RagiUtils.isSameStack(stack, stackRemain)) {
                        //プレイヤーの手持ちを上書き
                        player.setHeldItem(hand, inventory.insertItem(i, stack, false))
                        markDirty() //内部データの変化を送信
                        RagiLogger.infoDebug("Stack Inserted to slot$i!")
                        break
                    } else RagiLogger.infoDebug("The slot$i is full!")
                }
            }
            updateState(world, pos) //モデルの更新
        }
    }

    private fun updateState(world: World, pos: BlockPos) {
        if (!world.isRemote) {
            val state = world.getBlockState(pos)
            val block = state.block
            if (block is BlockLaboratoryTable) {
                val slot1 = this.inventory.getStackInSlot(0).isEmpty
                val slot2 = this.inventory.getStackInSlot(1).isEmpty
                val slot3 = this.inventory.getStackInSlot(2).isEmpty
                val slot4 = this.inventory.getStackInSlot(3).isEmpty
                val slot5 = this.inventory.getStackInSlot(4).isEmpty
                val result = state.withProperty(BlockLaboratoryTable.SLOT1, !slot1)
                        .withProperty(BlockLaboratoryTable.SLOT2, !slot2)
                        .withProperty(BlockLaboratoryTable.SLOT3, !slot3)
                        .withProperty(BlockLaboratoryTable.SLOT4, !slot4)
                        .withProperty(BlockLaboratoryTable.SLOT5, !slot5)
                world.setBlockState(pos, result, 2)
            }
        }
    }

    //    Recipe    //

    fun chemicalReaction(world: World, pos: BlockPos) {
        for (recipe in LTRegistry.list) {
            if (recipe.match(this.inventory)) {
                val drop = EntityItem(world, pos.x.toDouble() + 0.5, pos.y.toDouble() + 1.0, pos.z.toDouble() + 0.5, recipe.output)
                drop.setPickupDelay(0) //即座に回収できるようにする
                drop.motionX = 0.0
                drop.motionY = 0.0
                drop.motionZ = 0.0 //ドロップ時の飛び出しを防止
                world.spawnEntity(drop) //ドロップアイテムをスポーン
                RagiUtils.soundHypixel(world, pos)
                for (i in 0 until this.inventory.slots) {
                    this.inventory.setStackInSlot(i, ItemStack.EMPTY) //スロットを空にする
                    updateState(world, pos) //モデルの更新
                }
                break
            }
        }
    }
}