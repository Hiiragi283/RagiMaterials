package hiiragi283.material.compat.crt.event

import crafttweaker.annotations.ZenRegister
import crafttweaker.api.event.IEventHandle
import crafttweaker.api.event.IEventManager
import crafttweaker.util.EventList
import crafttweaker.util.IEventHandler
import hiiragi283.material.RMReference
import hiiragi283.material.api.event.MaterialBuildEvent
import hiiragi283.material.api.event.ShapeBuildEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenExpansion
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused", "UNUSED_PARAMETER")
@ZenClass("${RMReference.MOD_ID}.event.HiiragiEventManager")
@ZenExpansion("crafttweaker.events.IEventManager")
@ZenRegister
object HiiragiEventManager {

    private val shapeBuildEventList: EventList<CTShapeBuildEvent> = EventList()
    private val materialBuildEventList: EventList<CTMaterialBuildEvent> = EventList()

    @JvmStatic
    @ZenMethod
    fun onShapeBuild(manager: IEventManager, event: IEventHandler<CTShapeBuildEvent>): IEventHandle =
        shapeBuildEventList.add(event)

    @JvmStatic
    @ZenMethod
    fun onMaterialBuild(manager: IEventManager, event: IEventHandler<CTMaterialBuildEvent>): IEventHandle =
        materialBuildEventList.add(event)

    object EventHandler {

        @SubscribeEvent
        fun onShapeBuild(event: ShapeBuildEvent) {
            if (shapeBuildEventList.hasHandlers()) {
                shapeBuildEventList.publish(CTShapeBuildEvent(event))
            }
        }

        @SubscribeEvent
        fun onMaterialBuild(event: MaterialBuildEvent) {
            if (materialBuildEventList.hasHandlers()) {
                materialBuildEventList.publish(CTMaterialBuildEvent(event))
            }
        }

    }

}