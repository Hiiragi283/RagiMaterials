package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.item.Item

class BlockCropLignite : BlockCropPeat("crop_lignite") {

    //主産物のメタデータを取得するメソッド
    override fun getDropMain(): Int {
        return MaterialRegistry.LIGNITE.index
    }

    //種を取得するメソッド
    override fun getDropSeed(): Item {
        return RagiInit.ItemSeedLignite
    }

    //副産物のメタデータを取得するメソッド
    override fun getDropSub(): Int {
        return MaterialRegistry.COAL.index
    }

}