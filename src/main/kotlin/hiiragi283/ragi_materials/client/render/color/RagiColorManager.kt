package hiiragi283.ragi_materials.client.render.color

import java.awt.Color

object RagiColorManager {

    //複数の色を混合するメソッド
    fun mixColor(colors: Set<Color>): Color {
        //変数の宣言・初期化
        var redAve = 0
        var greenAve = 0
        var blueAve = 0
        //colorsの各colorに対して実行
        for (color in colors) {
            //RGB値のそれぞれの総和をとる
            redAve += color.red
            greenAve += color.green
            blueAve += color.blue
        }
        //各RGB値の平均値からColorを生成
        return Color(redAve / colors.size, greenAve / colors.size, blueAve / colors.size)
    }

    fun mixColor(vararg colors: Color): Color {
        return mixColor(colors.toSet())
    }

    //複数の色を比率を指定して混合するメソッド
    fun mixColor(vararg colors: Pair<Color, Int>): Color {
        //変数の宣言・初期化
        var redSum = 0
        var greenSum = 0
        var blueSum = 0
        var weightSum = 0
        //colorsの各keyに対して実行
        for (pair in colors) {
            val color = pair.first
            val weight = pair.second
            //RGB値にweightをかけた値を加算していく
            redSum += color.red * weight
            greenSum += color.green * weight
            blueSum += color.blue * weight
            weightSum += weight
        }
        //色の個数が0でない場合
        return if (weightSum != 0) {
            //加算した各RGB値をweightの合計で割った値からColorを生成
            Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum)
        } else RagiColor.WHITE
    }

    fun mixColor(colors: Map<Color, Int>): Color {
        //変数の宣言・初期化
        var redSum = 0
        var greenSum = 0
        var blueSum = 0
        val weightSum = colors.values.sum()
        //colorsの各keyに対して実行
        for (key in colors.keys) {
            //RGB値にweightをかけた値を加算していく
            redSum += key.red * colors[key]!!
            greenSum += key.green * colors[key]!!
            blueSum += key.blue * colors[key]!!
        }
        //色の個数が0でない場合
        return if (weightSum != 0) {
            //加算した各RGB値をweightの合計で割った値からColorを生成
            Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum)
        } else RagiColor.WHITE
    }

    //混合色から元の色を取得するメソッド
    fun getColorDif(colorMixed: Color, colorBase1: Color): Color {
        //colorMixedとcolorBase1の各RGB値の差分を計算
        val red2 = colorMixed.red * 2 - colorBase1.red
        val green2 = colorMixed.green * 2 - colorBase1.green
        val blue2 = colorMixed.blue * 2 - colorBase1.blue
        //混成前の色を返す
        return Color(red2, green2, blue2)
    }
}