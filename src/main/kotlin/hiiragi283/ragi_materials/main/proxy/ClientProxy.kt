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
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        RagiColor.setColor(ColorMaterial(), RagiMaterialsInit.ItemDust)
        RagiColor.setColor(ColorMaterial(), RagiMaterialsInit.ItemIngot)
    }

    //Post-Initializationで読み込むメソッド
    override fun loadPostInit() {}
}