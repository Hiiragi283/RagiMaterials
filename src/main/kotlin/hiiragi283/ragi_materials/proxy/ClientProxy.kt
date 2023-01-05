package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiInit
import hiiragi283.ragi_materials.render.ColorMaterial
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        RagiModel.setModel(RagiInit.ItemBookDebug)
        RagiModel.setModelSame(RagiInit.ItemDust)
        RagiModel.setModelSame(RagiInit.ItemIngot)
        RagiModel.setModelSame(RagiInit.ItemPlate)
        RagiModel.setModelFluids()
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        RagiColor.setColor(ColorMaterial(), RagiInit.ItemDust)
        RagiColor.setColor(ColorMaterial(), RagiInit.ItemIngot)
        RagiColor.setColor(ColorMaterial(), RagiInit.ItemPlate)
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}