package hiiragi283.material.compat.crt.material

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.util.IEventHandler
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.materialOf
import hiiragi283.material.compat.crt.HiiragiAction
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.material.MaterialFactory")
@ZenRegister
object CTMaterialFactory {

    private val actions: MutableList<HiiragiAction> = mutableListOf()

    fun registerMaterial() {
        actions.forEach(CraftTweakerAPI::apply)
    }

    @JvmStatic
    @ZenMethod
    fun createMaterial(name: String, index: Int, handler: IEventHandler<HiiragiMaterial.Builder>) {
        actions.add(HiiragiAction("Registering new material: $name with index: $index") {
            materialOf(name, index) { handler.handle(this) }
        })
    }

}