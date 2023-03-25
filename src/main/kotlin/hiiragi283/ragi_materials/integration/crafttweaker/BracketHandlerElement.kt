package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.CraftTweakerAPI
import crafttweaker.zenscript.IBracketHandler
import hiiragi283.ragi_materials.material.MaterialUtil
import stanhebben.zenscript.compiler.IEnvironmentGlobal
import stanhebben.zenscript.expression.ExpressionCallStatic
import stanhebben.zenscript.expression.ExpressionString
import stanhebben.zenscript.parser.Token
import stanhebben.zenscript.symbols.IZenSymbol
import stanhebben.zenscript.type.natives.IJavaMethod
import stanhebben.zenscript.util.ZenPosition

class BracketHandlerElement : IBracketHandler {

    //  <element:hydrogen>

    companion object {
        val method: IJavaMethod = CraftTweakerAPI.getJavaMethod(BracketHandlerElement::class.java, "getElement", String::class.java)

        @JvmStatic
        fun getElement(name: String) = MaterialUtil.getElement(name)
    }

    override fun resolve(environment: IEnvironmentGlobal, tokens: MutableList<Token>?): IZenSymbol? {
        //tokensがnullでない，かつ要素の個数が3，かつ初めの要素が"element"，かつ2つ目の要素が":"の場合
        return if (tokens !== null && tokens.size == 3 && tokens[0].value.equals("element", true) && tokens[1].value.equals(":", true)) {
            ZenSymbol(environment, tokens[2].value)
        } else null
    }

    private class ZenSymbol(val environment: IEnvironmentGlobal, val name: String) : IZenSymbol {

        override fun instance(position: ZenPosition) = ExpressionCallStatic(position, environment, method, ExpressionString(position, name))

    }
}