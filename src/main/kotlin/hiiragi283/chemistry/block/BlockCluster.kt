package hiiragi283.chemistry.block

import hiiragi283.api.HiiragiEntry
import hiiragi283.api.block.HiiragiBlock
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.core.util.OreDictUtil
import net.minecraft.block.material.Material

class BlockCluster(material: Material) : HiiragiBlock(material, "ore1"), HiiragiEntry.BLOCK {

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, "ore1", -1)

    //    HiiragiEntry    //

    override fun registerOreDict() {
        OreDictUtil.register("oreCopper", getObject(), 0)
    }

}