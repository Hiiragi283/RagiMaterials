package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.RagiMaterialsInit
import hiiragi283.ragi_materials.render.ColorMaterial
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        RagiModel.setModel(RagiMaterialsInit.ItemBookDebug)
        RagiModel.setModelSame(RagiMaterialsInit.ItemDust)
        RagiModel.setModelSame(RagiMaterialsInit.ItemIngot)
        RagiModel.setModelSame(RagiMaterialsInit.ItemPlate)
        RagiModel.setModelFluid(RagiMaterialsInit.FluidRagi)
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