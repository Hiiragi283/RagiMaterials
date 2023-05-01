package ragi_materials.main.tile

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.oredict.OreDictionary
import ragi_materials.core.RagiMaterials
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.RagiCapabilityProvider
import ragi_materials.core.capability.item.RagiItemHandler
import ragi_materials.core.capability.item.RagiItemHandlerWrapper
import ragi_materials.core.proxy.CommonProxy
import ragi_materials.core.tile.ITileProvider
import ragi_materials.core.tile.TileItemHandlerBase
import ragi_materials.core.util.dropInventoryItems
import ragi_materials.core.util.playSoundHypixel
import ragi_materials.main.container.ContainerOreDictConv

class TileOreDictConv : TileItemHandlerBase(), ITickable, ITileProvider.Inventory {

    lateinit var input: RagiItemHandler
    lateinit var output: RagiItemHandler
    private var count = 0

    //    Capability    //

    override fun createInventory(): RagiCapabilityProvider<IItemHandler> {
        input = RagiItemHandler(1, this).setIOType(EnumIOType.INPUT)
        output = RagiItemHandler(1, this).setIOType(EnumIOType.OUTPUT)
        inventory = RagiItemHandlerWrapper(input, output)
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

    //    TileLockableBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerOreDictConv(player, this)

    override fun getGuiID() = "${RagiMaterials.MOD_ID}:oredict_converter"

    //    ITickable    //

    override fun update() {
        if (!world.isRemote) {
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
                                if (stack.item.registryName?.namespace != RagiMaterials.MOD_ID) {
                                    //他mod -> RagiMaterials
                                    if (stackOre.item.registryName?.namespace == RagiMaterials.MOD_ID) {
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
                            playSoundHypixel(world, pos)
                        }
                    }
                }
                count = 0 //countをリセット
            } else count++ //countを追加
        }
    }
}