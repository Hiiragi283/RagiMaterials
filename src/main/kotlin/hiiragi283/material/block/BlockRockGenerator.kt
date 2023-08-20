package hiiragi283.material.block

import hiiragi283.api.block.HiiragiBlockContainer
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.material.tile.TileEntityRockGenerator
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material

object BlockRockGenerator : HiiragiBlockContainer<TileEntityRockGenerator>(
    Material.IRON,
    "rock_generator",
    TileEntityRockGenerator::class.java
) {

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, 0)

    init {
        blockHardness = 5.0F
        blockResistance = 5.0F
        setHarvestLevel("pickaxe", 2)
        soundType = SoundType.METAL
    }

}