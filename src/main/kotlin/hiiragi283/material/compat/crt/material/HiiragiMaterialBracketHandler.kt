package hiiragi283.material.compat.crt.material

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.BracketHandler
import crafttweaker.annotations.ZenRegister
import crafttweaker.zenscript.IBracketHandler
import hiiragi283.material.api.material.HiiragiMaterial
import stanhebben.zenscript.compiler.IEnvironmentGlobal
import stanhebben.zenscript.expression.ExpressionCallStatic
import stanhebben.zenscript.expression.ExpressionString
import stanhebben.zenscript.parser.Token
import stanhebben.zenscript.symbols.IZenSymbol
import stanhebben.zenscript.type.natives.IJavaMethod

@Suppress("unused")
@BracketHandler
@ZenRegister
class HiiragiMaterialBracketHandler : IBracketHandler {

    companion object {

        @JvmField
        val method: IJavaMethod = CraftTweakerAPI.getJavaMethod(
            HiiragiMaterialBracketHandler::class.java,
            "getMaterial",
            String::class.java
        )

        @JvmStatic
        fun getMaterial(name: String): HiiragiMaterial {
            val result: HiiragiMaterial? = HiiragiMaterial.REGISTRY[name]
            if (result == null) {
                CraftTweakerAPI.logError("Could not find material with name $name")
            }
            return result!!
        }

    }

    override fun resolve(environment: IEnvironmentGlobal, tokens: MutableList<Token>): IZenSymbol? = when {
        tokens.size < 3 -> null
        tokens[0].value != "material" -> null
        tokens[1].value != ":" -> null
        else -> IZenSymbol { position ->
            ExpressionCallStatic(position, environment, method, ExpressionString(position, tokens[2].value))
        }
    }

}