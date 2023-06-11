package hiiragi283.material.material_part

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.HiiragiPart
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistryEntry

data class MaterialPart(val part: HiiragiPart, val material: HiiragiMaterial) : IForgeRegistryEntry<MaterialPart>,
    IMaterialPart<MaterialPart> {

    companion object {
        @JvmField
        val EMPTY = MaterialPart(HiiragiPart.EMPTY, HiiragiMaterial.EMPTY)
    }

    fun isEmpty(): Boolean = this == EMPTY

    fun getOreDictName(): String = StringBuilder().also {
        it.append(part.getOreDictPrefix())
        it.append(material.getOreDictName())
    }.toString()

    //    IMaterialPart    //

    override fun getMaterialPart(obj: MaterialPart): MaterialPart = this

    //    IForgeRegistryEntry    //

    private val location = ResourceLocation(part.name, material.getName())

    //上書き不可能
    override fun setRegistryName(name: ResourceLocation): MaterialPart = this

    override fun getRegistryName(): ResourceLocation = location

    override fun getRegistryType(): Class<MaterialPart> = javaClass

}