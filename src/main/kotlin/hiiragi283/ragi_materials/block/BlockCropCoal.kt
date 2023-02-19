package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.item.Item

class BlockCropCoal : BlockCropPeat("crop_coal") {

    //主産物のメタデータを取得するメソッド
    override fun getDropMain(): Int {
        return MaterialRegistry.COAL.index
    }

    //種を取得するメソッド
    override fun getDropSeed(): Item {
        return RagiInit.ItemSeedCoal
    }

    //副産物のメタデータを取得するメソッド
    override fun getDropSub(): Int {
        return MaterialRegistry.ANTHRACITE.index
    }

}