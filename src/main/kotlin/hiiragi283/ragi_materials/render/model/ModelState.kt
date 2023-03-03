package hiiragi283.ragi_materials.render.model

import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraftforge.client.model.ModelLoader

object ModelState {

    //ブロックの特殊なモデル登録
    fun init() {

        ModelLoader.setCustomStateMapper(RagiInit.BlockSaltPond, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation {
                return ModelResourceLocation((state.block.registryName!!), "multipart")
            }
        })

        ModelLoader.setCustomStateMapper(RagiInit.BlockLaboratoryTable, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation {
                return ModelResourceLocation((state.block.registryName!!), "multipart")
            }
        })
    }
}