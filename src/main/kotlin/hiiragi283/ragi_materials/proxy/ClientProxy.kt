package hiiragi283.ragi_materials.proxy

import hiiragi283.ragi_materials.init.RagiInitBlock
import hiiragi283.ragi_materials.init.RagiInitItem
import hiiragi283.ragi_materials.render.ColorMaterial
import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RagiModel

class ClientProxy : CommonProxy() {
    //Pre-Initializationで読み込むメソッド
    override fun loadPreInit() {
        RagiModel.setModel(RagiInitItem.ItemBookDebug)
        //RagiModel.setModelSame(RagiInitItem.ItemBlockBlock)
        RagiModel.setModelSame(RagiInitItem.ItemBlockMetal)
        RagiModel.setModelSame(RagiInitItem.ItemDust)
        RagiModel.setModelSame(RagiInitItem.ItemIngot)
        RagiModel.setModelSame(RagiInitItem.ItemNugget)
        RagiModel.setModelSame(RagiInitItem.ItemPlate)
        RagiModel.setModelFluids()
    }

    //Initializationで読み込むメソッド
    override fun loadInit() {
        //RagiColor.setColor(ColorMaterial(), RagiInitBlock.BlockMetal)
        RagiColor.setColor(
            ColorMaterial(),
            //RagiInitItem.ItemBlockBlock,
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