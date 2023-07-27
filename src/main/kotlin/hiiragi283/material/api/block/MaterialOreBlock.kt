package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.RagiMaterials
import net.minecraft.util.Identifier

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
        RMResourcePack.addBlockAndItemTag(HiiragiPart(ShapeRegistry.ORE_STONE, material).getCommonId(), it)
    }


    RMResourcePack.addBlockTag(
        Identifier("mineable/pickaxe"),
        HiiragiPart(ShapeRegistry.ORE_STONE, material).getCommonId(),
        true
    )

}

class MaterialOreBlock(part: HiiragiPart) : MaterialBlock(part) {

    override fun getMinableType(): MinableType = MinableType.PICKAXE

}