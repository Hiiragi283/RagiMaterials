package hiiragi283.ragi_materials.util

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RagiModel {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModel(item: Item) {
        //itemがメタデータを使用する場合
        if (item.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            for (i in 0 until item.getMetadata(283) + 1) {
                val location = ModelResourceLocation(item.registryName.toString() + "_" + i, "inventory")
                ModelLoader.setCustomModelResourceLocation(item, i, location)
            }
        } else {
            //itemがメタデータを使用しない場合，IDから設定
            val location = ModelResourceLocation(item.registryName!!, "inventory")
            ModelLoader.setCustomModelResourceLocation(item, 0, location)
        }
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @SideOnly(Side.CLIENT)
    fun setModelSame(item: Item) {
        //メタデータが最大値になるまで処理を繰り返す
        for (i in 0 until item.getMetadata(283) + 1) {
            val location = ModelResourceLocation(item.registryName!!, "inventory")
            ModelLoader.setCustomModelResourceLocation(item, i, location)
        }
    }
}