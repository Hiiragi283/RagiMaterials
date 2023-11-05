package hiiragi283.material.compat.crt.shape

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.util.IEventHandler
import hiiragi283.material.RMReference
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.crt.HiiragiAction
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.shape.ShapeFactory")
@ZenRegister
object CTShapeFactory {

    private val actions: MutableList<HiiragiAction> = mutableListOf()

    fun registerShape() {
        actions.forEach(CraftTweakerAPI::apply)
    }

    @JvmStatic
    @ZenMethod
    fun createShape(name: String, handler: IEventHandler<HiiragiShape.Builder>) {
        actions.add(HiiragiAction("Registering new shape: $name") {
            HiiragiShape.build(name) {
                handler.handle(this)
            }
        })
    }

}