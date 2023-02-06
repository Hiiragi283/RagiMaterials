package hiiragi283.ragi_materials.event

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.util.RagiModel
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModelRegistry {

    @SubscribeEvent
    fun modelRegistry(event: ModelRegistryEvent) {
        RagiModel.setModel(
            RagiInit.ItemBlockForgeFurnace, RagiInit.ItemBlockOreDictConv, RagiInit.ItemBookDebug, RagiInit.ItemForgeHammer, RagiInit.ItemToolBellow
        )
        RagiModel.setModelSame(RagiInit.ItemIngotHot)
        RagiModel.setModelMaterial(
            RagiInit.ItemBlockMetal, RagiInit.ItemDust, RagiInit.ItemIngot, RagiInit.ItemNugget, RagiInit.ItemPlate
        )
    }
}