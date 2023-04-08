package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.TileLockableBase
import hiiragi283.ragi_materials.inventory.RagiInventory
import hiiragi283.ragi_materials.inventory.container.ContainerOreDictConv
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.util.SoundManager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.inventory.ItemStackHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.wrapper.SidedInvWrapper
import net.minecraftforge.oredict.OreDictionary

class TileOreDictConv : TileLockableBase(107), ISidedInventory, ITickable {

    override val inventory = RagiInventory("gui.ragi_materials.oredict_converter", 2)
    private val invWrapperIn = SidedInvWrapper(this, EnumFacing.UP) //搬入
    private val invWrapperOut = SidedInvWrapper(this, EnumFacing.DOWN) //搬出
    private var count = 0


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
        if (!world.isRemote) player.openGui(RagiMaterialsCore.INSTANCE!!, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    TileLockableBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerOreDictConv(player, this)

    override fun getGuiID() = "ragi_materials:oredict_converter"

    //    ISidedInventory    //

    override fun getSlotsForFace(side: EnumFacing): IntArray = if (side == EnumFacing.DOWN) intArrayOf(0) else intArrayOf(1)

    override fun canInsertItem(index: Int, itemStackIn: ItemStack, direction: EnumFacing) = direction != EnumFacing.DOWN && index == 1

    override fun canExtractItem(index: Int, stack: ItemStack, direction: EnumFacing) = direction == EnumFacing.DOWN && index == 0

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //搬出スロットが空の場合実行する
            val stackOut = inventory.getStackInSlot(0)
            if (stackOut.isEmpty) {
                val stack = inventory.getStackInSlot(1)
                val count = stack.count
                var stackResult = ItemStack.EMPTY
                //stackががEMPTYでない場合
                if (!stack.isEmpty) {
                    //鉱石辞書の数値IDの配列を取得
                    val arrayIDs = OreDictionary.getOreIDs(stack)
                    //配列内の各IDに対して実行
                    for (id in arrayIDs) {
                        //IDからString型の鉱石辞書を取得
                        val oreDict = OreDictionary.getOreName(id)
                        //鉱石辞書から紐づいたstackのNonNullListを取得
                        val listStacks = OreDictionary.getOres(oreDict)
                        if (stack.item.registryName?.namespace != Reference.MOD_ID) {
                            //NonNullList内の各stackOreに対して実行
                            for (stackOre in listStacks) {
                                //他mod -> RagiMaterials
                                if (stackOre.item.registryName?.namespace == Reference.MOD_ID) {
                                    stackResult = ItemStack(stackOre.item, count, stackOre.metadata) //resultにstackOreを代入し終了
                                    break
                                }
                            }
                        } else {
                            for (stackOre in listStacks) {
                                //RagiMaterials -> Minecraft
                                if (stackOre.item.registryName.toString().split(":")[0] == "minecraft") {
                                    stackResult = ItemStack(stackOre.item, count, stackOre.metadata) //resultにstackOreを代入し終了
                                    break
                                }
                            }
                        }
                        //resultが埋まっているならbreak
                        if (!stackResult.isEmpty) break
                    }
                    //resultがEMPTYでない場合
                    if (!stackResult.isEmpty) {
                        inventory.setInventorySlotContents(0, stackResult)
                        inventory.setInventorySlotContents(1, ItemStack.EMPTY)
                        SoundManager.playSoundHypixel(world, pos)
                    }
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }
}