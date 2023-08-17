package hiiragi283.material.block

import hiiragi283.api.block.HiiragiBlockContainer
import hiiragi283.api.item.HiiragiItemBlock
import hiiragi283.material.tile.TileEntityTest
import net.minecraft.block.material.Material

object BlockInventoryTest : HiiragiBlockContainer.Holdable<TileEntityTest>(
    Material.IRON,
    "inventory_test",
    TileEntityTest::class.java
) {

    override val itemBlock: HiiragiItemBlock = HiiragiItemBlock(this, 0)

}