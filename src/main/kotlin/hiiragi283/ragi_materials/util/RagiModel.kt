package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.item.ItemMaterial
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialRegistry.getFluid
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelBakery

import net.minecraft.client.renderer.block.statemap.StateMapperBase

object RagiModel {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModel(vararg items: Item) {
        for (item in items) {
            //itemが耐久値を使用しない，かつhasSubtypesがtrueの場合
            if (item.maxDamage == 0 && item.hasSubtypes) {
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

    //液体ブロックにmodelを割り当てるメソッド
    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/client/JsonRegister.java#L463
    */
    @SideOnly(Side.CLIENT)
    fun setModelFluids() {
        //EnumMaterialsの各enumに対して実行
        for (material in MaterialRegistry.list) {
            //Fluidを取得
            val fluid = material.getFluid()
            val model = ModelResourceLocation((Reference.MOD_ID + ":" + fluid.name), "fluid")
            //アイテムとしての描画処理
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.block)) { model }
            //液体ブロックがnullでない場合, ブロックとしての描画処理を実装する
            if (fluid.block !== null) {
                ModelLoader.setCustomStateMapper(fluid.block, object : StateMapperBase() {
                    override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation {
                        return model
                    }
                })
            }
        }
    }

    //素材のモデルを一括で登録するメソッド
    @SideOnly(Side.CLIENT)
    fun setModelMaterial(vararg items: ItemMaterial) {
        for (item in items) {
            //変数の宣言
            val location = ModelResourceLocation(item.registryName.toString(), "inventory")
            val locationPrecious = ModelResourceLocation("${item.registryName.toString()}_precious", "inventory")
            ModelBakery.registerItemVariants(item, location, locationPrecious) //modelの登録
            ModelLoader.setCustomMeshDefinition(item) { stack ->
                if (listOf(44, 45, 46, 76, 77, 78).contains(stack.metadata)) {
                    ModelResourceLocation("${stack.item.registryName.toString()}_precious", "inventory")
                } else ModelResourceLocation(stack.item.registryName.toString(), "inventory")
            }
        }
    }
}