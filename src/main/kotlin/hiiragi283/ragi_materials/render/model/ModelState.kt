package hiiragi283.ragi_materials.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraftforge.client.model.ModelLoader

object ModelState {

    init {

        ModelLoader.setCustomStateMapper(RagiInit.BlockOreRainbow, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = ModelResourceLocation("${Reference.MOD_ID}:ore", "stone_rainbow")
        })

    }
}