package hiiragi283.ragi_materials.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.CrystalBuilder
import hiiragi283.ragi_materials.material.MaterialManager
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader

object ModelStack {

    fun init() {

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
            val material = MaterialManager.getMaterial(stack.metadata)
            //materialがGemBuilderの場合
            if (material is CrystalBuilder) {
                //gemTypeに応じたモデルを割り当てる
                ModelResourceLocation("${Reference.MOD_ID}:crystal", material.system)
            } else ModelResourceLocation("${Reference.MOD_ID}:crystal", "cubic")
        }

        //Part
        for (part in listOf(
                RagiInit.ItemBlockCrystal,
                RagiInit.ItemBlockMetal,
                RagiInit.ItemDust,
                RagiInit.ItemDustTiny,
                RagiInit.ItemGear,
                //RagiInit.ItemCrystal,
                RagiInit.ItemIngot,
                RagiInit.ItemIngotHot,
                RagiInit.ItemNugget,
                //RagiInit.ItemOre,
                //RagiInit.ItemOreNether,
                //RagiInit.ItemOreEnd,
                RagiInit.ItemPlate,
                RagiInit.ItemStick)
        ) {
            val location = ModelResourceLocation("${Reference.MOD_ID}:parts", part.registryName!!.resourcePath)
            //アイテムとモデルの紐づけ
            ModelLoader.registerItemVariants(part, location)
            //アイテムにモデルを登録
            ModelLoader.setCustomMeshDefinition(part) { location }
        }

        //Ore
        val locationOverworld = ModelResourceLocation("${Reference.MOD_ID}:ore", "stone")
        val locationNether = ModelResourceLocation("${Reference.MOD_ID}:ore", "nether")
        val locationEnd = ModelResourceLocation("${Reference.MOD_ID}:ore", "end")

        ModelLoader.registerItemVariants(RagiInit.ItemOre, locationOverworld)
        ModelLoader.registerItemVariants(RagiInit.ItemOreNether, locationNether)
        ModelLoader.registerItemVariants(RagiInit.ItemOreEnd, locationEnd)

        ModelLoader.setCustomMeshDefinition(RagiInit.ItemOre) {locationOverworld}
        ModelLoader.setCustomMeshDefinition(RagiInit.ItemOreNether) {locationNether}
        ModelLoader.setCustomMeshDefinition(RagiInit.ItemOreEnd) {locationEnd}

    }
}