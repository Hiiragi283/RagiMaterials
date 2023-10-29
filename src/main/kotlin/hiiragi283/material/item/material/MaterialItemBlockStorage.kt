package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.block.MaterialBlockStorage
import hiiragi283.material.init.HiiragiShapes

object MaterialItemBlockStorage : MaterialItem(HiiragiShapes.BLOCK) {

    override fun registerRecipe(material: HiiragiMaterial) {
        MaterialBlockStorage.registerRecipe(material)
    }

}