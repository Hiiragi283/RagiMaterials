package hiiragi283.ragi_materials.block

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import net.minecraft.item.Item

class BlockCropCoal : BlockCropPeat("crop_coal") {

    override fun getDropMain(): Int {
        return MaterialRegistry.COAL.index
    }

    override fun getDropSeed(): Item {
        return RagiInit.ItemSeedCoal
    }

    override fun getDropSub(): Int {
        return MaterialRegistry.ANTHRACITE.index
    }

}