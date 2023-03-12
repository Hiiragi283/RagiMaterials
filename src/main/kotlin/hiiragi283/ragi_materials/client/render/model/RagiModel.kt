package hiiragi283.ragi_materials.client.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.base.IMaterialTool
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object RagiModel {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModel(vararg items: Item) {
        for (item in items) {
            //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
            if (item.getMaxDamage(ItemStack(item)) == 0 && item.hasSubtypes) {
                //メタデータが最大値になるまで処理を繰り返す
                for (i in 0..item.getMetadata(32768)) {
                    val location = ModelResourceLocation(item.registryName.toString() + "_" + i, "inventory")
                    ModelLoader.setCustomModelResourceLocation(item, i, location)
                }
            } else {
                //1つだけ登録する
                val location = ModelResourceLocation(item.registryName!!, "inventory")
                ModelLoader.setCustomModelResourceLocation(item, 0, location)
            }
        }
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @SideOnly(Side.CLIENT)
    fun setModelSame(vararg items: Item) {
        for (item in items) {
            val model = ModelResourceLocation(item.registryName!!, "inventory")
            ModelLoader.setCustomMeshDefinition(item) { model }
        }
    }

    //道具用のモデル登録メソッド
    @SideOnly(Side.CLIENT)
    fun setModelTool(vararg tools: Item) {
        for (tool in tools) {
            if (tool is IMaterialTool) {
                val model = ModelResourceLocation("${Reference.MOD_ID}:tools", tool.getToolID())
                ModelLoader.registerItemVariants(tool, model)
                ModelLoader.setCustomMeshDefinition(tool) { model }
            }
        }
    }

    //新規でモデルのパスを紐づけてモデルを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModelAlt(item: Item, location: ModelResourceLocation) {
        ModelLoader.registerItemVariants(item, location)
        ModelLoader.setCustomMeshDefinition(item) { location }
    }

}