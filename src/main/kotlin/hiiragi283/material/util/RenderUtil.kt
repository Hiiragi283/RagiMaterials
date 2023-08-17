package hiiragi283.material.util

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.DefaultVertexFormats

fun drawFluid(minecraft: Minecraft, x: Double, y: Double, spr: TextureAtlasSprite) {
    //TextureAtlasSpriteのx座標の左端と右端，y座標の下端と上端をDoubleに変換する
    val uMin = spr.minU.toDouble()
    val uMax = spr.maxU.toDouble()
    val vMin = spr.minV.toDouble()
    val vMax = spr.maxV.toDouble()
    //GUiは2次元なのでz座標は適当?
    val z = 100.0
    //Tessellatorに設定を書き込んでいく
    val tessellator = Tessellator.getInstance()
    val vertexBuffer = tessellator.buffer
    vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX)
    vertexBuffer.pos(x, y + 16, z).tex(uMin, vMax).endVertex() //左下
    vertexBuffer.pos(x + 16, y + 16, z).tex(uMax, vMax).endVertex() //右下
    vertexBuffer.pos(x + 16, y, z).tex(uMax, vMin).endVertex() //左上
    vertexBuffer.pos(x, y, z).tex(uMin, vMin).endVertex() //右上
    //いざ描画!!
    tessellator.draw()
}