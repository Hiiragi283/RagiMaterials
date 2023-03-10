package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.render.color.ColorRegistry
import hiiragi283.ragi_materials.render.model.*
import hiiragi283.ragi_materials.render.tile.RenderLaboratoryTable
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraftforge.fml.client.registry.ClientRegistry

class ClientProxy : CommonProxy() {

    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        //アイテムのモデル登録
        RagiModel.setModel(
                RagiInit.ItemBlockForgeFurnace,
                RagiInit.ItemBlockFullBottleStation,
                RagiInit.ItemBlockLaboratoryTable,
                RagiInit.ItemBlockOreDictConv,
                RagiInit.ItemBlockSaltPond,

                RagiInit.ItemBlazingCube,
                RagiInit.ItemFullBottle,
                RagiInit.ItemSeedCoal,
                RagiInit.ItemSeedLignite,
                RagiInit.ItemSeedPeat,
                RagiInit.ItemWaste
        )
        //アイテムのモデル登録 (メタデータ無視)
        //RagiModel.setModelSame()
        //道具のモデル登録
        RagiModel.setModelTool(
                *RagiInit.ItemsAxe,
                *RagiInit.ItemsHammer,
                *RagiInit.ItemsPickaxe,
                *RagiInit.ItemsSpade,
                *RagiInit.ItemsSword
        )
        //液体ブロックのモデル登録
        ModelFluid
        //ItemStackを用いたモデル登録
        ModelStack
        //IBlockStateを用いたモデル登録
        ModelState
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        //着色システムの登録
        ColorRegistry
        //タイルエンティティの登録
        super.registerTile()
        //TESRの登録
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaboTable::class.java, RenderLaboratoryTable())
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}