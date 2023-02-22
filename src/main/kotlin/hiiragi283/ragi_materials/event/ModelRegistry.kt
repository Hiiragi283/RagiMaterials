package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.render.model.ModelFluid
import hiiragi283.ragi_materials.render.model.ModelStack
import hiiragi283.ragi_materials.render.model.ModelState
import hiiragi283.ragi_materials.render.model.RagiModel
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModelRegistry {

    @SubscribeEvent
    fun modelRegistry(event: ModelRegistryEvent) {
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
            RagiInit.ItemFullBottle,
            RagiInit.ItemSeedCoal,
            RagiInit.ItemSeedLignite,
            RagiInit.ItemSeedPeat
        )
        //アイテムのモデル登録 (メタデータ無視)
        RagiModel.setModelSame(
            RagiInit.ItemBlockCrystal,
            RagiInit.ItemBlockMetal,
            RagiInit.ItemDust,
            RagiInit.ItemDustTiny,
            RagiInit.ItemGear,
            RagiInit.ItemIngot,
            RagiInit.ItemIngotHot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate,
            RagiInit.ItemStick
        )
        //アイテムの特殊なモデル登録
        ModelStack.init()
        //ブロックの特殊なモデル登録
        ModelState.init()
        //液体ブロックのモデル登録
        ModelFluid.init()
    }

}