package hiiragi283.material.util

import net.minecraft.client.renderer.GlStateManager
import java.awt.Color

object HiiragiColor {

    //Minecraftのカラーコードと同じ色
    @JvmField
    val BLACK = Color(0x00, 0x00, 0x00)

    @JvmField
    val DARK_BLUE = Color(0x00, 0x00, 0xAA)

    @JvmField
    val DARK_GREEN = Color(0x00, 0xAA, 0x00)

    @JvmField
    val DARK_AQUA = Color(0x00, 0xAA, 0xAA)

    @JvmField
    val DARK_RED = Color(0xAA, 0x00, 0x00)

    @JvmField
    val DARK_PURPLE = Color(0xAA, 0x00, 0xAA)

    @JvmField
    val GOLD = Color(0xFF, 0xAA, 0x00)

    @JvmField
    val GRAY = Color(0xAA, 0xAA, 0xAA)

    @JvmField
    val DARK_GRAY = Color(0x55, 0x55, 0x55)

    @JvmField
    val BLUE = Color(0x55, 0x55, 0xFF)

    @JvmField
    val GREEN = Color(0x55, 0xFF, 0x55)

    @JvmField
    val AQUA = Color(0x55, 0xFF, 0xFF)

    @JvmField
    val RED = Color(0xFF, 0x55, 0x55)

    @JvmField
    val LIGHT_PURPLE = Color(0xFF, 0x55, 0xFF)

    @JvmField
    val YELLOW = Color(0xFF, 0xFF, 0x55)

    @JvmField
    val WHITE = Color(0xFF, 0xFF, 0xFF)

    //複数の色を混合するメソッド
    @JvmStatic
    fun mixColor(colors: Collection<Color>): Color {
        var redAve = 0
        var greenAve = 0
        var blueAve = 0
        colors.forEach {
            redAve += it.red
            greenAve += it.green
            blueAve += it.blue
        }
        return Color(redAve / colors.size, greenAve / colors.size, blueAve / colors.size)
    }

    //可変長配列用
    @JvmStatic
    fun mixColor(vararg colors: Color): Color = mixColor(colors.toList())

    //複数の色を比率を指定して混合するメソッド
    @JvmStatic
    fun mixColor(colors: Map<Color, Int>): Color {
        var redSum = 0
        var greenSum = 0
        var blueSum = 0
        var weightSum = 0
        colors.forEach {
            val color = it.key
            val weight = it.value
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
        ) else WHITE
    }

    //List用
    @JvmStatic
    fun mixColor(colors: List<Pair<Color, Int>>): Color = mixColor(colors.toMap())

    //可変長配列用
    @JvmStatic
    fun mixColor(vararg colors: Pair<Color, Int>): Color = mixColor(colors.toMap())

    //混合色から元の色を取得するメソッド
    @JvmStatic
    fun getColorDif(colorMixed: Color, colorBase1: Color): Color {
        //colorMixedとcolorBase1の各RGB値の差分を計算
        val red2 = colorMixed.red * 2 - colorBase1.red
        val green2 = colorMixed.green * 2 - colorBase1.green
        val blue2 = colorMixed.blue * 2 - colorBase1.blue
        //混成前の色を返す
        return Color(red2, green2, blue2)
    }

    @JvmStatic
    fun setGLColor(color: Int) {
        val red = (color shr 16 and 255) / 255.0f
        val green = (color shr 8 and 255) / 255.0f
        val blue = (color and 255) / 255.0f
        GlStateManager.color(red, green, blue, 1.0f)
    }

    @JvmStatic
    fun setGLColor(color: Color) {
        setGLColor(color.rgb)
    }

}