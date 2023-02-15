package hiiragi283.ragi_materials.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.CrystalBuilder
import hiiragi283.ragi_materials.material.MaterialManager
import net.minecraft.client.renderer.block.model.ModelBakery
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader

object ModelStack {

    fun init() {

        //ItemGem
        ModelBakery.registerItemVariants(RagiInit.ItemCrystal,
            ModelResourceLocation("${Reference.MOD_ID}:gem_coal", "inventory"),
            ModelResourceLocation("${Reference.MOD_ID}:gem_cubic", "inventory"),
            ModelResourceLocation("${Reference.MOD_ID}:gem_diamond", "inventory"),
            ModelResourceLocation("${Reference.MOD_ID}:gem_hexagonal", "inventory")
        )
        ModelLoader.setCustomMeshDefinition(RagiInit.ItemCrystal) { stack ->
            //metadataからmaterialを取得
            val material = MaterialManager.getMaterial(stack.metadata)
            //materialがGemBuilderの場合
            if (material is CrystalBuilder) {
                //gemTypeに応じたモデルを割り当てる
                ModelResourceLocation("${Reference.MOD_ID}:gem_${material.gemType}", "inventory")
            } else ModelResourceLocation("${Reference.MOD_ID}:gem_cubic", "inventory")
        }
    }
}