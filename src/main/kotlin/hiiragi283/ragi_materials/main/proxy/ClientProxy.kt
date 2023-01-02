package hiiragi283.ragi_materials.main.proxy

import hiiragi283.ragi_lib.main.util.RagiColor
import hiiragi283.ragi_lib.main.util.RagiModel
import hiiragi283.ragi_materials.main.RagiMaterialsInit
import hiiragi283.ragi_materials.main.render.ColorMaterial

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        RagiModel.setModelSame(RagiMaterialsInit.ItemDust)
        RagiModel.setModelSame(RagiMaterialsInit.ItemIngot)
        RagiModel.setModelSame(RagiMaterialsInit.ItemPlate)
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        RagiColor.setColor(ColorMaterial(), RagiMaterialsInit.ItemDust)
        RagiColor.setColor(ColorMaterial(), RagiMaterialsInit.ItemIngot)
        RagiColor.setColor(ColorMaterial(), RagiMaterialsInit.ItemPlate)
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}