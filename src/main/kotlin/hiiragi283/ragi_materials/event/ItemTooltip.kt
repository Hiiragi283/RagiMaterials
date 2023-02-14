package hiiragi283.ragi_materials.event

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
                    //tagから液体名を取得
                    val nameFluid = fluidItem.tankProperties[0].contents?.fluid?.name
                    //nameFluidからmaterialを取得
                    val material = MaterialRegistry.getMaterial(nameFluid!!)
                    //materialがWILDCARDでない場合
                    if (material !== MaterialRegistry.WILDCARD) {
                        //tooltipの追加
                        MaterialUtils.materialInfo(material, event.toolTip)
                    }
                }
            }
        }
    }
}