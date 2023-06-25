package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.appendBefore

class MaterialPartBlockItem(private val materialBlock: MaterialPartBlock) : HiiragiBlockItem(materialBlock) {

    fun register() = register(materialBlock.identifier.path)

    override fun registerModel() {
        RagiMaterials.RESOURCE_PACK.addModel(materialBlock.part.model, materialBlock.identifier.appendBefore("item/"))
    }

}