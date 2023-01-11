package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.init.RagiInitItem
import hiiragi283.ragi_materials.render.ColorMaterial
import hiiragi283.ragi_materials.render.ModelMaterial
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        ModelMaterial.setModelMaterial()
        RagiModel.setModel(RagiInitItem.ItemBookDebug)
        RagiModel.setModel(RagiInitItem.ItemCraftingHammer)
        RagiModel.setModelFluids()
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        RagiColor.setColor(
            ColorMaterial(),
            RagiInitItem.ItemBlockMetal,
            RagiInitItem.ItemDust,
            RagiInitItem.ItemIngot,
            RagiInitItem.ItemNugget,
            RagiInitItem.ItemPlate
        )
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}