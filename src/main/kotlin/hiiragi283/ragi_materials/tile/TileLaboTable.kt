package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiMaterialsCore
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.base.TileItemHandlerBase
import hiiragi283.ragi_materials.capability.EnumIOType
import hiiragi283.ragi_materials.capability.itemhandler.RagiItemHandler
import hiiragi283.ragi_materials.capability.itemhandler.RagiItemHandlerWrapper
import hiiragi283.ragi_materials.inventory.container.ContainerLaboTableNew
import hiiragi283.ragi_materials.packet.MessageTile
import hiiragi283.ragi_materials.proxy.CommonProxy
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.util.*
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler

class TileLaboTable : TileItemHandlerBase(100) {

    val inputs = RagiItemHandler(5).setIOType(EnumIOType.INPUT)
    val catalyst = RagiItemHandler(1).setIOType(EnumIOType.GENERAL)
    val inventory = RagiItemHandlerWrapper(inputs, catalyst)

    //    NBT tag    //

    override fun writeToNBT(tag: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(tag)
        tag.setTag(keyInventory, inventory.serializeNBT())
        return tag
    }

    override fun readFromNBT(tag: NBTTagCompound) {
        super.readFromNBT(tag)
        inventory.deserializeNBT(tag.getCompoundTag(keyInventory))
    }

    //    Capability    //

    override fun <T : Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) inventory as T else null
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?) = capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY

    //    Recipe    //

    fun chemicalReaction(world: World, pos: BlockPos) {
        var isFailed = true
        //サーバー側，かつインベントリが空でない場合
        if (!world.isRemote && !inputs.isEmpty()) {
            //レシピチェック
            for (recipe in LaboRecipe.Registry.list) {
                if (recipe.match(inventory, true)) {
                    isFailed = false
                    for (i in recipe.getOutputs().indices) {
                        RagiUtil.dropItem(world, pos.add(0, 1, 0), recipe.getOutput(i), 0.0, 0.25, 0.0)
                        RagiLogger.infoDebug("The output is ${recipe.getOutput(i).toBracket()}")
                    }
                    SoundManager.playSoundHypixel(this)
                    RagiResult.succeeded(this)
                    break
                }
            }
            RagiLogger.infoDebug("$isFailed")
            //失敗時の処理
            if (isFailed) {
                RagiUtil.dropItem(world, pos.add(0, 1, 0), ItemStack(RagiRegistry.ItemWaste, 1, 0), 0.0, 0.25, 0.0)
                SoundManager.playSound(this, SoundManager.getSound("minecraft:entity.generic.explode"))
                RagiResult.failed(this)
            }
        }
        inputs.clear(0..4) //反応結果によらずインベントリを空にする
        RagiRegistry.RagiNetworkWrapper.sendToAll(MessageTile(this.pos)) //クライアント側にパケットを送る
    }

    //    ITileActivatable    //

    override fun onTileActivated(world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, facing: EnumFacing): Boolean {
        if (!world.isRemote) player.openGui(RagiMaterialsCore.INSTANCE!!, CommonProxy.TileID, world, pos.x, pos.y, pos.z)
        return true
    }

    //    TileItemHandlerBase    //

    override fun createContainer(playerInventory: InventoryPlayer, player: EntityPlayer) = ContainerLaboTableNew(player, this)

    override fun getGuiID() = "ragi_materials:laboratory_table"

    override fun getName() = "gui.ragi_materials.labo_table"

}