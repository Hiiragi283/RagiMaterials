package hiiragi283.material.api.block

import hiiragi283.material.api.HiiragiEntry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import java.util.*

abstract class HiiragiBlock(material: Material, modid: String, id: String) : Block(material), HiiragiEntry.BLOCK {

    init {
        setRegistryName(modid, id)
        creativeTab = CreativeTabs.MISC
        translationKey = id
    }

    //    Block    //

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item = asItem()

}