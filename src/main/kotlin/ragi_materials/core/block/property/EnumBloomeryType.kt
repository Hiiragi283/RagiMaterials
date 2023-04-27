package ragi_materials.core.block.property

import net.minecraft.util.IStringSerializable
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial

enum class EnumBloomeryType(val type: String, val index: Int, val material: RagiMaterial) : IStringSerializable {

    EMPTY("empty", 0, RagiMaterial.EMPTY),
    IRON("iron", 1, MaterialRegistry.IRON),
    COPPER("copper", 2, MaterialRegistry.COPPER),
    TIN("tin", 3, MaterialRegistry.TIN),
    GOLD("gold", 4, MaterialRegistry.GOLD),
    LEAD("lead", 5, MaterialRegistry.LEAD);

    override fun getName() = type

    companion object {

        fun getType(material: RagiMaterial): EnumBloomeryType {
            for (type in EnumBloomeryType.values()) {
                if (type.material.name == material.name) return type
            }
            return EMPTY
        }

        fun getType(index: Int): EnumBloomeryType {
            for (type in EnumBloomeryType.values()) {
                if (type.index == index) return type
            }
            return EMPTY
        }
    }
}