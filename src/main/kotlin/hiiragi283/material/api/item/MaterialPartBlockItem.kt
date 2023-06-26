package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialPartBlock
import hiiragi283.material.common.RagiResourcePack
import net.minecraft.util.Identifier

class MaterialPartBlockItem(private val materialBlock: MaterialPartBlock) : HiiragiBlockItem(materialBlock) {

    //    HiiragiBlockItem    //

    override val identifier: Identifier = materialBlock.identifier

    override fun registerModel() {
        RagiResourcePack.addItemModel(materialBlock.identifier, materialBlock.part.model)
    }

}