package hiiragi283.material.api.block

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.item.HiiragiItemBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs

abstract class HiiragiBlock(material: Material, modid: String, id: String) : Block(material), HiiragiEntry.BLOCK {

    abstract val itemBlock: HiiragiItemBlock

    init {
        setRegistryName(modid, id)
        creativeTab = CreativeTabs.MISC
        translationKey = id
    }

}