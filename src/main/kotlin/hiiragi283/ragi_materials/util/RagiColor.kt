package hiiragi283.ragi_materials.util

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

object RagiColor {
    //ひいらぎさんのテーマカラー
    val RAGI_RED = Color(255, 0, 31)
    val CLEAR = Color(255, 255, 255, 0)

    //複数の色を混合するメソッド
    fun mixColor(vararg colors: Color): Color {
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

    //複数の色を比率を指定して混合するメソッド
    fun mixColor(colors: Map<Color, Int>): Color {
        //変数の宣言・初期化
        var redSum = 0
        var greenSum = 0
        var blueSum = 0
        //colorsの各keyに対して実行
        for (i in colors.keys) {
            //RGB値にweightをかけた値を加算していく
            redSum += i.red * colors[i]!!
            greenSum += i.green * colors[i]!!
            blueSum += i.blue * colors[i]!!
        }
        //加算した各RGB値をweightの合計で割った値からColorを生成
        return Color(redSum / colors.values.sum(), greenSum / colors.values.sum(), blueSum / colors.values.sum())
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

    //代入されたIBlockColorをBlockに登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setColor(color: IBlockColor?, vararg blocks: Block?) {
        Minecraft.getMinecraft().blockColors.registerBlockColorHandler(color!!, *blocks)
    }

    //代入されたIItemColorをItemに登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setColor(color: IItemColor?, vararg items: Item?) {
        Minecraft.getMinecraft().itemColors.registerItemColorHandler(color!!, *items)
    }
}