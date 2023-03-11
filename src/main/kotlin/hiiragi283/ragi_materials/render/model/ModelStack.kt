package hiiragi283.ragi_materials.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.CrystalBuilder
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader

object ModelStack {

    init {

        /*
         Thanks to kojin15!
          Source: https://qiita.com/kojin15/items/8e5dec722f74c23e9865
        */

        //Debug Book
        ModelLoader.registerItemVariants(
                RagiInit.ItemBookDebug,
                ModelResourceLocation(RagiInit.ItemBookDebug.registryName!!, "0"),
                ModelResourceLocation(RagiInit.ItemBookDebug.registryName!!, "1"),
                ModelResourceLocation(RagiInit.ItemBookDebug.registryName!!, "2"),
                ModelResourceLocation(RagiInit.ItemBookDebug.registryName!!, "3")
        )
        ModelLoader.setCustomMeshDefinition(RagiInit.ItemBookDebug) { stack ->
            ModelResourceLocation(stack.item.registryName!!, stack.metadata.toString())
        }

        //ItemBlockMaterial
        val blockCrystal = ModelResourceLocation("${Reference.MOD_ID}:part", "block_crystal")
        val blockMaterial = ModelResourceLocation("${Reference.MOD_ID}:part", "block_material")
        val blockMetal = ModelResourceLocation("${Reference.MOD_ID}:part", "block_metal")

        ModelLoader.registerItemVariants(RagiInit.ItemBlockMaterial, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(RagiInit.ItemBlockMaterial) { stack ->
            //metadataからmaterialを取得
            val material = MaterialUtil.getMaterial(stack.metadata)
            val parts = material.type.list
            if (EnumMaterialType.BLOCK_MATERIAL in parts) {
                if (EnumMaterialType.CRYSTAL in parts) {
                    if ((material as CrystalBuilder).system == "coal") {
                        blockMaterial
                    } else blockCrystal
                } else if (EnumMaterialType.INGOT in parts) {
                    blockMetal
                } else blockMaterial
            } else blockMaterial
        }

        //ItemCrystal
        ModelLoader.registerItemVariants(
                RagiInit.ItemCrystal,
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "amorphous"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "coal"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "cubic"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "diamond"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "hexagonal"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "monoclinic"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "orthorhombic"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "tetragonal"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "trigonal")
        )
        ModelLoader.setCustomMeshDefinition(RagiInit.ItemCrystal) { stack ->
            //metadataからmaterialを取得
            val material = MaterialUtil.getMaterial(stack.metadata)
            //materialがGemBuilderの場合
            if (material is CrystalBuilder) {
                //gemTypeに応じたモデルを割り当てる
                ModelResourceLocation("${Reference.MOD_ID}:crystal", material.system)
            } else ModelResourceLocation("${Reference.MOD_ID}:crystal", "cubic")
        }

        //Part
        for (part in listOf(
                RagiInit.ItemDust,
                RagiInit.ItemDustTiny,
                RagiInit.ItemGear,
                RagiInit.ItemIngot,
                RagiInit.ItemIngotHot,
                RagiInit.ItemNugget,
                RagiInit.ItemPlate,
                RagiInit.ItemStick)
        ) {
            val location = ModelResourceLocation("${Reference.MOD_ID}:part", part.part.name)
            //アイテムとモデルの紐づけ
            ModelLoader.registerItemVariants(part, location)
            //アイテムにモデルを登録
            ModelLoader.setCustomMeshDefinition(part) { location }
        }

        //Ore
        RagiModel.setModelAlt(RagiInit.ItemOre, ModelResourceLocation("${Reference.MOD_ID}:ore", "stone"))
        RagiModel.setModelAlt(RagiInit.ItemOreNether, ModelResourceLocation("${Reference.MOD_ID}:ore", "nether"))
        RagiModel.setModelAlt(RagiInit.ItemOreEnd, ModelResourceLocation("${Reference.MOD_ID}:ore", "end"))

        RagiModel.setModelAlt(RagiInit.ItemBlockOreRainbow, ModelResourceLocation("${Reference.MOD_ID}:ore", "stone_rainbow"))

    }
}