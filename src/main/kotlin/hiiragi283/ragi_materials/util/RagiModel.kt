package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.items.ItemMaterial
import hiiragi283.ragi_materials.materials.MaterialRegistry
import hiiragi283.ragi_materials.materials.MaterialRegistry.getFluid
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.block.state.IBlockState

import net.minecraft.client.renderer.block.statemap.StateMapperBase

object RagiModel {

    //代入されたItemに応じてモデルファイルのパスを登録するメソッド
    @SideOnly(Side.CLIENT)
    fun Item.setModel(): Item {
        //itemがメタデータを使用する場合
        if (this.hasSubtypes) {
            //メタデータが最大値になるまで処理を繰り返す
            for (i in 0 until this.getMetadata(283) + 1) {
                val location = ModelResourceLocation(this.registryName.toString() + "_" + i, "inventory")
                ModelLoader.setCustomModelResourceLocation(this, i, location)
            }
        } else {
            //itemがメタデータを使用しない場合，IDから設定
            val location = ModelResourceLocation(this.registryName!!, "inventory")
            ModelLoader.setCustomModelResourceLocation(this, 0, location)
        }
        return this
    }

    //メタデータによらず特定のモデルファイルだけを利用させるメソッド
    @SideOnly(Side.CLIENT)
    fun Item.setModelSame(): Item {
        //メタデータが最大値になるまで処理を繰り返す
        for (i in 0 until this.getMetadata(283) + 1) {
            val location = ModelResourceLocation(this.registryName!!, "inventory")
            ModelLoader.setCustomModelResourceLocation(this, i, location)
        }
        return this
    }

    //液体ブロックにmodelを割り当てるメソッド
    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/client/JsonRegister.java
    */
    @SideOnly(Side.CLIENT)
    fun setModelFluids() {
        //EnumMaterialsの各enumに対して実行
        for (material in MaterialRegistry.list) {
            //Fluidを取得
            val fluid = material.getFluid()
            val fluidModel = ModelResourceLocation((Reference.MOD_ID + ":" + fluid.name), "fluid")
            //アイテムとしての描画処理
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.block)) { fluidModel }
            //液体ブロックがnullでない場合, ブロックとしての描画処理を実装する
            if (fluid.block !== null) {
                ModelLoader.setCustomStateMapper(fluid.block, object : StateMapperBase() {
                    override fun getModelResourceLocation(p_178132_1_: IBlockState): ModelResourceLocation {
                        return fluidModel
                    }
                })
            }
        }
    }

    //素材のモデルを一括で登録するメソッド
    fun ItemMaterial.setModelMaterial(): ItemMaterial {
        val item: ItemMaterial = this
        //EnumMaterials内の各materialに対して実行
        for (material in MaterialRegistry.list) {
            //白金族のindexとindexが一致する場合
            if (listOf(44, 45, 46, 76, 77, 78).contains(material.index)) {
                //専用のモデルを割り当てる
                ModelLoader.setCustomModelResourceLocation(
                    this, material.index, ModelResourceLocation(this.registryName.toString() + "_precious", "inventory")
                )
            }
            //一般庶民の場合
            else {
                ModelLoader.setCustomModelResourceLocation(
                    this, material.index, ModelResourceLocation(this.registryName.toString(), "inventory")
                )
            }
        }
        return item
    }
}