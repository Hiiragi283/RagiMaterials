package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.TileItemHandlerBase
import hiiragi283.ragi_materials.capability.EnumIOType
import hiiragi283.ragi_materials.capability.itemhandler.RagiItemHandler
import hiiragi283.ragi_materials.capability.itemhandler.RagiItemHandlerWrapper
import hiiragi283.ragi_materials.container.ContainerOreDictConv
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.util.SoundManager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.oredict.OreDictionary

class TileOreDictConv : TileItemHandlerBase(107), ITickable {

    val input = RagiItemHandler(1).setIOType(EnumIOType.INPUT)
    val output = RagiItemHandler(1).setIOType(EnumIOType.OUTPUT)
    val inventory = RagiItemHandlerWrapper(input, output)
    private var count = 0


    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, inventory.serializeNBT()) //インベントリをtagに書き込む
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory)) //tagからインベントリを読み込む
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) inventory as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY


    //    ITileActivatable    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterialsCore.INSTANCE!!, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    TileLockableBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerOreDictConv(player, this)

    override fun getGuiID() = "${Reference.MOD_ID}:oredict_converter"

    override fun getName() = "gui.${Reference.MOD_ID}.oredict_converter"

    //    ITickable    //

    override fun update() {
        //countが20以上の場合
        if (count >= 20) {
            //搬出スロットが空の場合実行する
            val stackOut = output.getStackInSlot(0)
            if (stackOut.isEmpty) {
                val stack = input.getStackInSlot(0)
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
                        //NonNullList内の各stackOreに対して実行
                        for (stackOre in listStacks) {
                            if (stack.item.registryName?.namespace != Reference.MOD_ID) {
                                //他mod -> RagiMaterials
                                if (stackOre.item.registryName?.namespace == Reference.MOD_ID) {
                                    stackResult = ItemStack(stackOre.item, count, stackOre.metadata) //resultにstackOreを代入し終了
                                    break
                                }
                            } else {
                                //RagiMaterials -> Minecraft
                                if (stackOre.item.registryName?.namespace == "minecraft") {
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
                        input.setStackInSlot(0, ItemStack.EMPTY)
                        output.setStackInSlot(0, stackResult)
                        SoundManager.playSoundHypixel(world, pos)
                    }
                }
            }
            count = 0 //countをリセット
        } else count++ //countを追加
    }
}