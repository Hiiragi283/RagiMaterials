package hiiragi283.material.item

import hiiragi283.material.material.RagiMaterial
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ItemGroup

class MaterialItemSettings(val material: RagiMaterial, val part: String, val scale: Float = 1.0f) :
    FabricItemSettings() {

    init {
        this.group(ItemGroup.MATERIALS)
    }

}