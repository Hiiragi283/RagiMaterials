package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.render.ColorMaterial
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        RagiModel.setModel(RagiInit.ItemBookDebug)
        RagiModel.setModelSame(RagiInit.ItemDust)
        RagiModel.setModelSame(RagiInit.ItemIngot)
        RagiModel.setModelSame(RagiInit.ItemNugget)
        RagiModel.setModelSame(RagiInit.ItemPlate)
        RagiModel.setModelFluids()
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        RagiColor.setColor(
            ColorMaterial(),
            RagiInit.ItemDust,
            RagiInit.ItemIngot,
            RagiInit.ItemNugget,
            RagiInit.ItemPlate
        )
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}