package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.materials.MaterialRegistry
import net.minecraft.client.resources.I18n
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*

class ItemTooltip {
    @SubscribeEvent
    fun onItemTooltip(event: ItemTooltipEvent) {
        //プレイヤーがnullでない場合
        if (Objects.nonNull(event.entityPlayer)) {
            //クライアント側のみで実行
            if (event.entityPlayer!!.world.isRemote) {
                //各値の取得
                val player = event.entityPlayer
                val stack = event.itemStack
                val tag = stack.tagCompound
                val tooltip = event.toolTip
                //tagがullでない場合
                if (tag !== null && tag.getString("FluidName") !== null) {
                    //tagから液体名を取得
                    val nameFluid = tag.getString("FluidName")
                    //nameFluidからEnumMaterialsを取得
                    val material = MaterialRegistry.getMaterial(nameFluid)
                    //materialがWILDCARDでない場合
                    if (material !== MaterialRegistry.WILDCARD) {
                        //tooltipの追加
                        tooltip.add("§e===Property===")
                        tooltip.add(I18n.format("text.ragi_materials.property.mol", material.mol))
                        tooltip.add(I18n.format("text.ragi_materials.property.melt", material.melting!! + 273))
                        tooltip.add(I18n.format("text.ragi_materials.property.boil", material.boiling + 273))
                    }
                }
            }
        }
    }
}