package hiiragi283.material.integration

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.part.with
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.tag.BlockTags
import net.minecraft.tag.ItemTags

object RagiIntegrationCore {

    fun init() {

        //    Minecraft    //

        PartRegistry.registerTag(BlockTags.PLANKS, HiiragiShape.WILDCARD with MaterialCommon.WOOD)
        PartRegistry.registerTag(BlockTags.LOGS, HiiragiShape.WILDCARD with MaterialCommon.WOOD)

        PartRegistry.registerTag(ItemTags.PLANKS, HiiragiShape.WILDCARD with MaterialCommon.WOOD)
        PartRegistry.registerTag(ItemTags.LOGS, HiiragiShape.WILDCARD with MaterialCommon.WOOD)

    }

}