package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.material.MaterialManager
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.MaterialUtils
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ItemTooltip {

    @SubscribeEvent
    fun onItemTooltip(event: ItemTooltipEvent) {
        //プレイヤーがnullでない場合
        if (event.entityPlayer !== null) {
            //クライアント側のみで実行
            if (event.entityPlayer!!.world.isRemote) {
                //各値の取得
                val stack = event.itemStack
                val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents?.fluid?.name !== null)) {
                    val nameFluid = fluidItem.tankProperties[0].contents?.fluid?.name
                    val material = MaterialManager.getMaterial(nameFluid!!)
                    //tooltipの追加
                    if(material != MaterialRegistry.WILDCARD) MaterialUtils.materialInfo(material, event.toolTip)
                }
            }
        }
    }
}