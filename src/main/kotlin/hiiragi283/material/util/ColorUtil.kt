package hiiragi283.material.util

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.item.EnumDyeColor
import java.awt.Color

fun colorWrapper(color: EnumDyeColor): Color = Color(color.colorValue)

object ColorUtil {

    //複数の色を混合するメソッド
    fun mixColor(colors: Collection<Color>): Color {
        var redAve = 0
        var greenAve = 0
        var blueAve = 0
        for (color in colors) {
            redAve += color.red
            greenAve += color.green
            blueAve += color.blue
        }
        return Color(redAve / colors.size, greenAve / colors.size, blueAve / colors.size)
    }

    //可変長配列用
    fun mixColor(vararg colors: Color): Color {
        return mixColor(colors.toSet())
    }

    //複数の色を比率を指定して混合するメソッド
    fun mixColor(vararg colors: Pair<Color, Int>): Color {
        var redSum = 0
        var greenSum = 0
        var blueSum = 0
        var weightSum = 0
        for (pair in colors) {
            val color = pair.first
            val weight = pair.second
            //RGB値にweightをかけた値を加算していく
            redSum += color.red * weight
            greenSum += color.green * weight
            blueSum += color.blue * weight
            weightSum += weight
        }
        return if (weightSum != 0) Color(
            redSum / weightSum,
            greenSum / weightSum,
            blueSum / weightSum
        ) else colorWrapper(EnumDyeColor.WHITE)
    }

    //Map用
    fun mixColor(colors: Map<Color, Int>): Color = mixColor(*colors.toList().toTypedArray())

    //混合色から元の色を取得するメソッド
    fun getColorDif(colorMixed: Color, colorBase1: Color): Color {
        //colorMixedとcolorBase1の各RGB値の差分を計算
        val red2 = colorMixed.red * 2 - colorBase1.red
        val green2 = colorMixed.green * 2 - colorBase1.green
        val blue2 = colorMixed.blue * 2 - colorBase1.blue
        //混成前の色を返す
        return Color(red2, green2, blue2)
    }

    fun setGLColor(color: Int) {
        val red = (color shr 16 and 255) / 255.0f
        val green = (color shr 8 and 255) / 255.0f
        val blue = (color and 255) / 255.0f
        GlStateManager.color(red, green, blue, 1.0f)
    }

    fun setGLColor(color: Color) {
        setGLColor(color.rgb)
    }

}