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

    //2つの色の中間色を取得するメソッド
    fun getColorAve(color1: Color, color2: Color): Color {
        //color1とcolor2の各RGB値の平均値を計算
        val aveR = (color1.red + color2.red) / 2
        val aveG = (color1.green + color2.green) / 2
        val aveB = (color1.blue + color2.blue) / 2
        //中間 (管理) 色を返す
        return Color(aveR, aveG, aveB)
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
    fun setColor(color: IBlockColor?, block: Block?) {
        Minecraft.getMinecraft().blockColors.registerBlockColorHandler(color!!, block)
    }

    //代入されたIItemColorをItemに登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setColor(color: IItemColor?, item: Item?) {
        Minecraft.getMinecraft().itemColors.registerItemColorHandler(color!!, item)
    }
}