package hiiragi283.material.api.block

import hiiragi283.material.api.item.MaterialBlockItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags
import net.minecraft.block.Block
import net.minecraft.tag.BlockTags
import net.minecraft.util.registry.Registry

fun registerMaterialOreBlocks(material: HiiragiMaterial) {

    if (!material.hasOre) {
        RagiMaterials.LOGGER.warn("$material cannot create ores!")
        return
    }

    val list: List<HiiragiPart> = listOf(
        HiiragiPart(ShapeRegistry.ORE_STONE, material),
        HiiragiPart(ShapeRegistry.ORE_NETHER, material),
        HiiragiPart(ShapeRegistry.ORE_END, material),
        HiiragiPart(ShapeRegistry.ORE_DEEP, material)
    )

    list.map(::MaterialOreBlock).forEach { it.register() }

    list.map { it.getId() }.forEach {
        RMResourcePack.addBlockTag(HiiragiPart(ShapeRegistry.ORE_STONE, material).getTagKey(Registry.BLOCK_KEY)) {
            this.add(it)
        }
        RMResourcePack.addBlockTag(ConventionalBlockTags.ORES) {
            this.add(it)
        }
        RMResourcePack.addBlockTag(BlockTags.PICKAXE_MINEABLE) {
            this.add(it)
        }

        RMResourcePack.addItemTag(HiiragiPart(ShapeRegistry.ORE_STONE, material).getTagKey(Registry.ITEM_KEY)) {
            this.add(it)
        }
    }

}

class MaterialOreBlock(part: HiiragiPart) : MaterialBlock(part) {

    override fun register(): Block {
        MaterialBlockItem(this).register()
        return super.register()
    }

}