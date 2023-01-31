package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.materials.MaterialRegistry
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color

object RagiColor {
    //ひいらぎさんのテーマカラー
    val RAGI_RED = Color(255, 0, 31)

    //Minecraftのカラーコードと同じ色
    val BLACK = Color(0x00, 0x00, 0x00)
    val DARK_BLUE = Color(0x00, 0x00, 0xAA)
    val DARK_GREEN = Color(0x00, 0xAA, 0x00)
    val DARK_AQUA = Color(0x00, 0xAA, 0xAA)
    val DARK_RED = Color(0xAA, 0x00, 0x00)
    val DARK_PURPLE = Color(0xAA, 0x00, 0xAA)
    val GOLD = Color(0xFF, 0xAA, 0x00)
    val GRAY = Color(0xAA, 0xAA, 0xAA)
    val DARK_GRAY = Color(0x55, 0x55, 0x55)
    val BLUE = Color(0x55, 0x55, 0xFF)
    val GREEN = Color(0x55, 0xFF, 0x55)
    val AQUA = Color(0x55, 0xFF, 0xFF)
    val RED = Color(0xFF, 0x55, 0x55)
    val LIGHT_PURPLE = Color(0xFF, 0x55, 0xFF)
    val YELLOW = Color(0xFF, 0xFF, 0x55)
    val WHITE = Color(0xFF, 0xFF, 0xFF)

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
        val weightSum = colors.values.sum()
        //colorsの各keyに対して実行
        for (key in colors.keys) {
            //RGB値にweightをかけた値を加算していく
            redSum += key.red * colors[key]!!
            greenSum += key.green * colors[key]!!
            blueSum += key.blue * colors[key]!!
        }
        //加算した各RGB値をweightの合計で割った値からColorを生成
        return Color(redSum / weightSum, greenSum / weightSum, blueSum / weightSum)
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

    class ColorMaterial : IItemColor, IBlockColor {

        override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int {
            //メタデータからMaterialBuilderを取得
            val material = MaterialRegistry.getMaterial(stack.metadata)
            //tintIndexが0ならばEnumMaterials.color，そうでないなら白を返す
            return if (tintIndex == 0) material.getColor().rgb else 0xFFFFFF
        }

        override fun colorMultiplier(state: IBlockState, worldIn: IBlockAccess?, pos: BlockPos?, tintIndex: Int): Int {
            val material = MaterialRegistry.getMaterial(state.block.getMetaFromState(state))
            return if (tintIndex == 0) material.getColor().rgb else 0xFFFFFF
        }
    }

    class ColorNBT : IItemColor {

        override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int {
            //NBTタグがnullでない場合
            return if (stack.tagCompound !== null) {
                //NBTタグからEnumMaterialsを取得
                val tag = stack.tagCompound!!
                val material = MaterialRegistry.getMaterial(tag.getString("material"))
                //tintIndexが1ならばEnumMaterials.color，そうでないなら白を返す
                if (tintIndex == 1) material.getColor().rgb else 0xFFFFFF
            }
            //NBTタグがnullの場合
            else 0xFFFFFF //白色を返す
        }
    }
}