package hiiragi283.material.compat.crt.bracket

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.BracketHandler
import crafttweaker.annotations.ZenRegister
import crafttweaker.zenscript.IBracketHandler
import hiiragi283.material.api.shape.HiiragiShape
import stanhebben.zenscript.compiler.IEnvironmentGlobal
import stanhebben.zenscript.expression.ExpressionCallStatic
import stanhebben.zenscript.expression.ExpressionString
import stanhebben.zenscript.parser.Token
import stanhebben.zenscript.symbols.IZenSymbol
import stanhebben.zenscript.type.natives.IJavaMethod

@Suppress("unused")
@BracketHandler
@ZenRegister
class HiiragiShapeBracketHandler : IBracketHandler {

    companion object {

        @JvmField
        val method: IJavaMethod = CraftTweakerAPI.getJavaMethod(
            HiiragiShapeBracketHandler::class.java,
            "getShape",
            String::class.java
        )

        @JvmStatic
        fun getShape(name: String): HiiragiShape {
            val result: HiiragiShape? = HiiragiShape.REGISTRY[name]
            if (result == null) {
                CraftTweakerAPI.logError("Could not find shape with name $name")
            }
            return result!!
        }

    }

    override fun resolve(environment: IEnvironmentGlobal, tokens: MutableList<Token>): IZenSymbol? = when {
        tokens.size < 3 -> null
        tokens[0].value != "shape" -> null
        tokens[1].value != ":" -> null
        else -> IZenSymbol { position ->
            ExpressionCallStatic(position, environment, method, ExpressionString(position, tokens[2].value))
        }
    }

}