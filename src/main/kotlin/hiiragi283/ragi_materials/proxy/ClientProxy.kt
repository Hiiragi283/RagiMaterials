package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.event.ItemTooltip
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.block.statemap.StateMapperBase
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.common.MinecraftForge

class ClientProxy : CommonProxy() {

    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(ItemTooltip())
        //アイテムのモデル登録
        RagiModel.setModel(
            RagiInit.ItemBlockBellow,
            RagiInit.ItemBlockBlazeHeater,
            RagiInit.ItemBlockForgeFurnace,
            RagiInit.ItemBlockOreDictConv,
            RagiInit.ItemBlockSaltPond,

            RagiInit.ItemBlazingCube,
            RagiInit.ItemBookDebug,
            RagiInit.ItemForgeHammer,

            RagiInit.ItemCellTest
        )
        //アイテムのモデル登録 (メタデータ無視)
        RagiModel.setModelSame(RagiInit.ItemIngotHot)
        //アイテムのモデル登録 (material専用)
        RagiModel.setModelMaterial(
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemDustTiny,
            RagiInit.ItemIngot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate
        )

        //ブロックの特殊なモデル登録
        ModelLoader.setCustomStateMapper(RagiInit.BlockSaltPond, object : StateMapperBase() {
            override fun getModelResourceLocation(state: IBlockState): ModelResourceLocation {
                return ModelResourceLocation((state.block.registryName!!), "multipart")
            }
        })

        //液体ブロックのモデル登録
        /*
          Thanks to defeatedcrow!
          Source: https://github.com/defeatedcrow/HeatAndClimateMod/blob/1.12.2_v3/main/java/defeatedcrow/hac/main/client/JsonRegister.java#L463
        */
        for (material in MaterialRegistry.list) {
            //Fluidを取得
            val fluid = material.getFluid()
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

    //Initializationで読み込むメソッド
    override fun loadInit() {
        //BlockとItemの着色
        RagiColor.setColor(
            RagiColor.ColorMaterial(),
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemDustTiny,
            RagiInit.ItemIngot,
            RagiInit.ItemIngotHot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate
        )
        RagiColor.setColor(RagiColor.ColorNBT(), RagiInit.ItemForgeHammer)
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}