package hiiragi283.material.integration

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.tag.BlockTags
import net.minecraft.tag.ItemTags

object RagiIntegrationCore {

    fun init() {

        //    Minecraft    //

        PartRegistry.registerTag(BlockTags.PLANKS, HiiragiPart(HiiragiShape.EMPTY, MaterialCommon.WOOD))
        PartRegistry.registerTag(BlockTags.LOGS, HiiragiPart(HiiragiShape.EMPTY, MaterialCommon.WOOD))

        PartRegistry.registerTag(ItemTags.PLANKS, HiiragiPart(HiiragiShape.EMPTY, MaterialCommon.WOOD))
        PartRegistry.registerTag(ItemTags.LOGS, HiiragiPart(HiiragiShape.EMPTY, MaterialCommon.WOOD))

    }

}