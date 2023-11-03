package hiiragi283.material.compat.crt.bracket

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.BracketHandler
import crafttweaker.annotations.ZenRegister
import crafttweaker.zenscript.IBracketHandler
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.crt.part.IHiiragiPart
import stanhebben.zenscript.compiler.IEnvironmentGlobal
import stanhebben.zenscript.expression.ExpressionCallStatic
import stanhebben.zenscript.expression.ExpressionString
import stanhebben.zenscript.parser.Token
import stanhebben.zenscript.symbols.IZenSymbol
import stanhebben.zenscript.type.natives.IJavaMethod

@Suppress("unused")
@BracketHandler
@ZenRegister
class HiiragiPartBracketHandler : IBracketHandler {

    companion object {

        @JvmField
        val method: IJavaMethod = CraftTweakerAPI.getJavaMethod(
            HiiragiPartBracketHandler::class.java,
            "getPart",
            String::class.java,
            String::class.java
        )

        @JvmStatic
        fun getPart(shapeName: String, materialName: String): IHiiragiPart {
            val shape: HiiragiShape? = HiiragiShape.REGISTRY[shapeName]
            val material: HiiragiMaterial? = HiiragiMaterial.REGISTRY[materialName]
            if (shape == null || material == null) {
                CraftTweakerAPI.logError("Could not find part with shape: $shapeName and material: $materialName")
            }
            return IHiiragiPart.Impl(shape!!, material!!)
        }

    }

    override fun resolve(environment: IEnvironmentGlobal, tokens: MutableList<Token>): IZenSymbol? = when {
        tokens.size < 5 -> null
        tokens[0].value != "part" -> null
        tokens[1].value != ":" -> null
        tokens[3].value != ":" -> null
        else -> IZenSymbol { position ->
            ExpressionCallStatic(
                position,
                environment,
                method,
                ExpressionString(position, tokens[2].value),
                ExpressionString(position, tokens[4].value)
            )
        }
    }

}