package hiiragi283.material.api.block

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RMResourcePack
import hiiragi283.material.common.RagiMaterials
import net.minecraft.block.Block
import net.minecraft.util.Identifier

fun registerMaterialStorageBlocks(material: HiiragiMaterial) {

    val list: MutableList<HiiragiPart> = mutableListOf()
    when {
        material.isMetal() -> list.add(HiiragiPart(ShapeRegistry.BLOCK_METAL, material))
        material.isGem() -> list.add(HiiragiPart(ShapeRegistry.BLOCK_GEM, material))
        else -> {
            RagiMaterials.LOGGER.warn("$material cannot create storage blocks!")
            return
        }
    }

    list.map(::MaterialStorageBlock).forEach { it.register() }

    list.map { it.getCommonId() }.forEach { RMResourcePack.addBlockTag(Identifier("mineable/pickaxe"), it, true) }

}

class MaterialStorageBlock(part: HiiragiPart) : MaterialBlock(part) {

    override fun getMinableType(): MinableType = MinableType.PICKAXE

    override fun register(): Block {

        RMResourcePack.addBlockAndItemTag(part.getCommonId(), getIdentifier())

        return super.register()
    }

}