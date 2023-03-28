package hiiragi283.ragi_materials.client.render.model

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object ModelManager {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModel(item: Item) {
        //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
        if (item.getMaxDamage(ItemStack(item)) == 0 && item.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            for (i in 0..item.getMetadata(32768)) {
                val location = ModelResourceLocation(item.registryName.toString() + "_" + i, "inventory")
                ModelLoader.setCustomModelResourceLocation(item, i, location)
            }
        } else {
            //1つだけ登録する
            ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
        }
    }

    @SideOnly(Side.CLIENT)
    fun setModel(vararg items: Item) {
        items.forEach { setModel(it) }
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @SideOnly(Side.CLIENT)
    fun setModelSame(item: Item) {
        ModelLoader.setCustomMeshDefinition(item) { ModelResourceLocation(item.registryName!!, "inventory") }
    }

    @SideOnly(Side.CLIENT)
    fun setModelSame(vararg items: Item) {
        items.forEach { setModelSame(it) }
    }

    //新規でモデルのパスを紐づけてモデルを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModelAlt(item: Item, location: ModelResourceLocation) {
        ModelLoader.registerItemVariants(item, location)
        ModelLoader.setCustomMeshDefinition(item) { location }
    }

}