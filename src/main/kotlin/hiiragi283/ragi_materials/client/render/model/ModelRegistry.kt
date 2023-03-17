package hiiragi283.ragi_materials.client.render.model

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.builder.CrystalBuilder
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fluids.Fluid

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
        /*
          Thanks to defeatedcrow!
          Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/client/JsonRegister.java#L463
        */
        for (material in MaterialRegistry.list) {
            if (material.getFluid() !== null) {
                //Fluidを取得
                val fluid: Fluid = material.getFluid()!!
                val model = ModelResourceLocation(("${Reference.MOD_ID}:${fluid.name}"), "fluid")
                //アイテムとしての描画処理
                ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.block)) { model }
                //液体ブロックがnullでない場合, ブロックとしての描画処理を実装する
                if (fluid.block !== null) {
                    ModelLoader.setCustomStateMapper(fluid.block, object : StateMapperBase() {
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

        //Debug Book
        ModelLoader.registerItemVariants(
                RagiItem.ItemBookDebug,
                ModelResourceLocation(RagiItem.ItemBookDebug.registryName!!, "0"),
                ModelResourceLocation(RagiItem.ItemBookDebug.registryName!!, "1"),
                ModelResourceLocation(RagiItem.ItemBookDebug.registryName!!, "2"),
                ModelResourceLocation(RagiItem.ItemBookDebug.registryName!!, "3")
        )
        ModelLoader.setCustomMeshDefinition(RagiItem.ItemBookDebug) { stack ->
            ModelResourceLocation(stack.item.registryName!!, stack.metadata.toString())
        }

        //ItemBlockMaterial
        val blockCrystal = ModelResourceLocation("${Reference.MOD_ID}:part", "block_crystal")
        val blockMaterial = ModelResourceLocation("${Reference.MOD_ID}:part", "block_material")
        val blockMetal = ModelResourceLocation("${Reference.MOD_ID}:part", "block_metal")

        ModelLoader.registerItemVariants(RagiItem.ItemBlockMaterial, blockCrystal, blockMaterial, blockMetal)

        ModelLoader.setCustomMeshDefinition(RagiItem.ItemBlockMaterial) { stack ->
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
                RagiItem.ItemCrystal,
                //ModelResourceLocation("${Reference.MOD_ID}:crystal", "amorphous"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "coal"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "cubic"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "diamond"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "emerald"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "lapis"),
                //ModelResourceLocation("${Reference.MOD_ID}:crystal", "monoclinic"),
                //ModelResourceLocation("${Reference.MOD_ID}:crystal", "orthorhombic"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "quartz"),
                //ModelResourceLocation("${Reference.MOD_ID}:crystal", "tetragonal"),
                ModelResourceLocation("${Reference.MOD_ID}:crystal", "ruby")
        )
        ModelLoader.setCustomMeshDefinition(RagiItem.ItemCrystal) { stack ->
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
        RagiModelManager.setModelAlt(RagiItem.ItemOre, ModelResourceLocation("${Reference.MOD_ID}:ore", "stone"))
        RagiModelManager.setModelAlt(RagiItem.ItemOreNether, ModelResourceLocation("${Reference.MOD_ID}:ore", "nether"))
        RagiModelManager.setModelAlt(RagiItem.ItemOreEnd, ModelResourceLocation("${Reference.MOD_ID}:ore", "end"))

        RagiModelManager.setModelAlt(RagiItem.ItemBlockOre1, ModelResourceLocation("${Reference.MOD_ID}:ore", "stone"))
    }
}