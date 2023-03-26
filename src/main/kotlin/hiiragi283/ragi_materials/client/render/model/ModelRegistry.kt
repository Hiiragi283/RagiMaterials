package hiiragi283.ragi_materials.client.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader

object ModelRegistry {

    fun load() {
        blocks()
        fluids()
        items()
    }

    fun blocks() {

        //Ore
        ModelLoader.setCustomStateMapper(RagiBlock.BlockOre1, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = ModelResourceLocation("${Reference.MOD_ID}:ore", "stone")
        })

        ModelLoader.setCustomStateMapper(RagiBlock.BlockOreRainbow, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = ModelResourceLocation("${Reference.MOD_ID}:ore", "stone_rainbow")
        })

    }

    fun fluids() {
        for (material in RagiMaterial.list) {
            material.getFluid()?.let {
                val model = ModelResourceLocation(("${Reference.MOD_ID}:${it.name}"), "fluid")
                //アイテムとしての描画処理
                ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(it.block)) { model }
                //液体ブロックがnullでない場合, ブロックとしての描画処理を実装する
                if (it.block !== null) {
                    ModelLoader.setCustomStateMapper(it.block, object : StateMapperBase() {
                        override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation = model
                    })
                }
            }
        }
    }

    fun items() {

        /*
         Thanks to kojin15!
          Source: https://qiita.com/kojin15/items/8e5dec722f74c23e9865
        */

        //ItemBlockMaterial
        val blockCrystal = ModelResourceLocation("${Reference.MOD_ID}:part", "block_crystal")
        val blockMaterial = ModelResourceLocation("${Reference.MOD_ID}:part", "block_material")
        val blockMetal = ModelResourceLocation("${Reference.MOD_ID}:part", "block_metal")

        ModelLoader.registerItemVariants(RagiItem.ItemBlockMaterial, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(RagiItem.ItemBlockMaterial) { stack ->
            var result = blockMaterial
            val item = stack.item
            if (item is IMaterialItem) {
                val material = item.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) {
                    if (material.crystalType != EnumCrystalType.COAL) result = blockCrystal
                } else if (EnumMaterialType.INGOT in material.type.list) result = blockMetal
            }
            result
        }

        //ItemCrystal
        ModelLoader.registerItemVariants(
                RagiItem.ItemCrystal,
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "coal"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "cubic"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "diamond"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "emerald"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "lapis"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "quartz"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "ruby")
        )
        ModelLoader.setCustomMeshDefinition(RagiItem.ItemCrystal) { stack ->
            var result = ModelResourceLocation("${Reference.MOD_ID}:crystal", EnumCrystalType.CUBIC.texture)
            val item = stack.item
            if (item is IMaterialItem) {
                val material = item.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) result = ModelResourceLocation("${Reference.MOD_ID}:crystal", material.crystalType.texture)
            }
            result
        }

        //Part
        for (part in listOf(
                RagiItem.ItemDust,
                RagiItem.ItemDustTiny,
                RagiItem.ItemGear,
                RagiItem.ItemIngot,
                RagiItem.ItemIngotHot,
                RagiItem.ItemNugget,
                RagiItem.ItemPlate,
                RagiItem.ItemStick)
        ) {
            val location = ModelResourceLocation("${Reference.MOD_ID}:part", part.part.name)
            //アイテムとモデルの紐づけ
            ModelLoader.registerItemVariants(part, location)
            //アイテムにモデルを登録
            ModelLoader.setCustomMeshDefinition(part) { location }
        }

        //Ore
        RagiModelManager.setModelAlt(RagiItem.ItemBlockOre1, ModelResourceLocation("${Reference.MOD_ID}:ore", "stone"))
        RagiModelManager.setModelAlt(RagiItem.ItemOreCrushed, ModelResourceLocation("${Reference.MOD_ID}:ore", "gravel"))
        RagiModelManager.setModelAlt(RagiItem.ItemOreCrushedVanilla, ModelResourceLocation("${Reference.MOD_ID}:ore", "gravel"))
    }
}