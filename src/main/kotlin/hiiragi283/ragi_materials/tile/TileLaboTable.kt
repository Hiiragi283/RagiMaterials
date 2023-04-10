package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.packet.MessageTile
import hiiragi283.ragi_materials.packet.RagiNetworkWrapper
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.util.*
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler

class TileLaboTable : TileLaboBase(100) {

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
        inputs.clear() //反応結果によらずインベントリを空にする
        RagiNetworkWrapper.sendToAll(MessageTile(this.pos)) //クライアント側にパケットを送る
    }

    //    TileItemHandlerBase    //

    override fun getGuiID() = "${Reference.MOD_ID}:laboratory_table"

    override fun getName() = "gui.${Reference.MOD_ID}.labo_table"

}