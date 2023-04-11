package hiiragi283.ragi_materials.client.model

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.item.IMaterialItem
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader

object ModelRegistry {

    private val locationOreStone = ModelResourceLocation("${RagiMaterials.MOD_ID}:ore", "stone")
    private val locationOreStoneRainbow = ModelResourceLocation("${RagiMaterials.MOD_ID}:ore", "stone_rainbow")
    private val locationOreGravel = ModelResourceLocation("${RagiMaterials.MOD_ID}:ore", "stone")

    fun load() {
        blocks()
        fluids()
        items()
    }

    fun blocks() {

        //Ore
        ModelManager.setStateMapperAlt(RagiRegistry.BlockOre1, locationOreStone)
        ModelManager.setStateMapperAlt(RagiRegistry.BlockOreRainbow, locationOreStoneRainbow)
    }

    fun fluids() {
        for (material in RagiMaterial.list) {
            material.getFluid()?.let {
                val model = ModelResourceLocation(("${RagiMaterials.MOD_ID}:${it.name}"), "fluid")
                //アイテムとしての描画処理
                ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(it.block)) { model }
                //液体ブロックがnullでない場合, ブロックとしての描画処理を実装する
                if (it.block !== null) ModelManager.setStateMapperAlt(it.block, model)
            }
        }
    }

    fun items() {

        /**
        Thanks to kojin15!
        Source: https://qiita.com/kojin15/items/8e5dec722f74c23e9865
         */

        //ItemBlockMaterial
        val blockCrystal = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_crystal")
        val blockMaterial = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_material")
        val blockMetal = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", "block_metal")

        ModelLoader.registerItemVariants(RagiRegistry.ItemBlockMaterial, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(RagiRegistry.ItemBlockMaterial) { stack ->
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
                RagiRegistry.ItemCrystal,
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "coal"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "cubic"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "diamond"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "emerald"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "lapis"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "quartz"),
                ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", "ruby")
        )
        ModelLoader.setCustomMeshDefinition(RagiRegistry.ItemCrystal) { stack ->
            var result = ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", EnumCrystalType.CUBIC.texture)
            val item = stack.item
            if (item is IMaterialItem) {
                val material = item.getMaterial(stack)
                if (material.crystalType != EnumCrystalType.NONE) result = ModelResourceLocation("${RagiMaterials.MOD_ID}:crystal", material.crystalType.texture)
            }
            result
        }

        //Part
        for (part in listOf(
                RagiRegistry.ItemDust,
                RagiRegistry.ItemDustTiny,
                RagiRegistry.ItemGear,
                RagiRegistry.ItemIngot,
                RagiRegistry.ItemIngotHot,
                RagiRegistry.ItemNugget,
                RagiRegistry.ItemPlate,
                RagiRegistry.ItemStick)
        ) {
            val location = ModelResourceLocation("${RagiMaterials.MOD_ID}:part", part.part.name)
            //アイテムとモデルの紐づけ
            ModelLoader.registerItemVariants(part, location)
            //アイテムにモデルを登録
            ModelLoader.setCustomMeshDefinition(part) { location }
        }

        //Ore
        ModelManager.setModelAlt(RagiRegistry.ItemBlockOre1, locationOreStone)
        ModelManager.setModelAlt(RagiRegistry.ItemOreCrushed, locationOreGravel)
        ModelManager.setModelAlt(RagiRegistry.ItemOreCrushedVanilla, locationOreGravel)

        //Transfer
        ModelManager.setModelAlt(RagiRegistry.ItemBlockTransferEnergy, ModelResourceLocation("${RagiMaterials.MOD_ID}:transfer", "inventory"))
        ModelManager.setModelAlt(RagiRegistry.ItemBlockTransferFluid, ModelResourceLocation("${RagiMaterials.MOD_ID}:transfer", "inventory"))

    }
}