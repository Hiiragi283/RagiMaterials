package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.event.ColorHandler
import hiiragi283.ragi_materials.event.ItemTooltip
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.render.model.ModelFluid
import hiiragi283.ragi_materials.render.model.ModelStack
import hiiragi283.ragi_materials.render.model.ModelState
import hiiragi283.ragi_materials.render.model.RagiModel
import net.minecraftforge.common.MinecraftForge

class ClientProxy : CommonProxy() {

    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        //Eventの登録
        MinecraftForge.EVENT_BUS.register(ColorHandler())
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

            RagiInit.ItemFullBottle
        )
        //アイテムのモデル登録 (メタデータ無視)
        RagiModel.setModelSame(
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemDustTiny,
            RagiInit.ItemIngot,
            RagiInit.ItemIngotHot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate
        )
        //アイテムの特殊なモデル登録
        ModelStack.init()
        //ブロックの特殊なモデル登録
        ModelState.init()
        //液体ブロックのモデル登録
        ModelFluid.init()
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {}

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}