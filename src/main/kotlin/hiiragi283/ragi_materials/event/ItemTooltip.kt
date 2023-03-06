package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.util.RegexStatics.camelToSnakeCase
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.oredict.OreDictionary

class ItemTooltip {

    private val listPrefix = listOf(
        "block",
        "clump",
        "coin",
        "crystal",
        "dustDirty",
        "dustTiny",
        "dust",
        "gear",
        "gem",
        "ingotHot",
        "ingot",
        "nugget",
        "ore",
        "plate",
        "shard",
        "stick"
    )

    @SubscribeEvent
    fun onItemTooltip(event: ItemTooltipEvent) {
        //プレイヤーがnullでない場合
        if (event.entityPlayer !== null) {
            //クライアント側のみで実行
            if (event.entityPlayer!!.world.isRemote) {
                //各値の取得
                val stack = event.itemStack
                var material: MaterialBuilder? = null
                //鉱石辞書用
                val arrayID = OreDictionary.getOreIDs(stack)
                for (id in arrayID) {
                    val oreDict = OreDictionary.getOreName(id)
                    for (prefix in listPrefix) {
                        if (oreDict.contains(prefix)) {
                            val name = oreDict.substring(prefix.length).camelToSnakeCase()
                            //RagiLogger.infoDebug(name)
                            material = MaterialUtil.getMaterial(name)
                            break
                        }
                    }
                }
                //液体アイテム用
                val fluidItem = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                if ((fluidItem !== null) && (fluidItem.tankProperties[0].contents?.fluid?.name !== null)) {
                    val name = fluidItem.tankProperties[0].contents?.fluid?.name!!
                    material = MaterialUtil.getMaterial(name)
                }
                //tooltipの追加
                if (material !== null) {
                    MaterialUtil.materialInfo(material, event.toolTip)
                }
            }
        }
    }
}