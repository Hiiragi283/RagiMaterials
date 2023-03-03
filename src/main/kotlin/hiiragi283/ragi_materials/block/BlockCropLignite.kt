package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.item.Item

class BlockCropLignite : BlockCropPeat("crop_lignite") {

    override fun getDropMain(): Int {
        return MaterialRegistry.LIGNITE.index
    }

    override fun getDropSeed(): Item {
        return RagiInit.ItemSeedLignite
    }

    override fun getDropSub(): Int {
        return MaterialRegistry.COAL.index
    }

}