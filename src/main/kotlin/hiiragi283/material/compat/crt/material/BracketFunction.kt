package hiiragi283.material.compat.crt.material

import crafttweaker.annotations.ZenRegister
import hiiragi283.material.RMReference
import stanhebben.zenscript.annotations.ZenClass

@ZenClass("${RMReference.MOD_ID}.material.BracketFunction")
@ZenRegister
interface BracketFunction {

    fun apply(formula: String): String

}