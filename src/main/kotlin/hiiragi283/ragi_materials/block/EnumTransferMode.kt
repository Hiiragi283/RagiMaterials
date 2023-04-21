package hiiragi283.ragi_materials.block

import net.minecraft.util.IStringSerializable

enum class EnumTransferMode(private val mode: String, val meta: Int) : IStringSerializable {
    NEAREST("nearest", 0), FARTHEST("farthest", 1);

    override fun getName() = mode

    fun reverse(): EnumTransferMode = if (this == NEAREST) FARTHEST else NEAREST

    companion object {
        fun getValue(meta: Int): EnumTransferMode = if (meta % 2 == 0) NEAREST else FARTHEST
    }
}