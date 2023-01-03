package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.RagiMaterialsInit
import hiiragi283.ragi_materials.Reference
import net.minecraft.client.renderer.ItemMeshDefinition
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.block.state.IBlockState

import net.minecraft.client.renderer.block.statemap.StateMapperBase




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

    //液体ブロックにmodelを割り当てるメソッド
    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/client/JsonRegister.java
    */
    @SideOnly(Side.CLIENT)
    fun setModelFluid(fluid: Fluid) {
        val fluidModel = ModelResourceLocation((Reference.MOD_ID + ":" + fluid.name), "fluid")
        //アイテムとしての描画処理
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.block)) { fluidModel }
        //ブロックとしての描画処理
        ModelLoader.setCustomStateMapper(fluid.block, object : StateMapperBase() {
            override fun getModelResourceLocation(p_178132_1_: IBlockState): ModelResourceLocation {
                return fluidModel
            }
        })
    }
}