package hiiragi283.ragi_materials.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fluids.Fluid

object ModelFluid {

    //液体ブロックのモデル登録
    init {
        /*
          Thanks to defeatedcrow!
          Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/client/JsonRegister.java#L463
        */
        for (material in MaterialRegistry.mapIndex.values) {
            if (material.getFluid() !== null) {
                //Fluidを取得
                val fluid: Fluid = material.getFluid()!!
                val model = ModelResourceLocation(("${Reference.MOD_ID}:${fluid.name}"), "fluid")
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
    }
}