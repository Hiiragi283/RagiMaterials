package hiiragi283.material.material

import hiiragi283.material.material.part.MaterialPart
import hiiragi283.material.material.part.PartRegistry
import hiiragi283.material.registry.RagiRegistry
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary
import rechellatek.camelToSnakeCase

data class PartToMaterial(
    val part: MaterialPart = PartRegistry.INGOT,
    val material: RagiMaterial = RagiMaterial.EMPTY
) {

    companion object {
        //鉱石辞書 -> PartToMaterial
        @JvmStatic
        fun fromOreDict(ore: String): PartToMaterial {
            val name = ore.camelToSnakeCase()
            val list = name.split("_", limit = 2)
            return PartToMaterial(PartRegistry.getPart(list[0]), MaterialRegistry.getMaterial(list[1]))
        }

        //ItemStack -> 鉱石辞書 -> PartToMaterial
        @JvmStatic
        fun fromOreDict(stack: ItemStack): PartToMaterial {
            val list = OreDictionary.getOreIDs(stack)
            if (list.isNotEmpty()) {
                val id = list[0]
                return fromOreDict(OreDictionary.getOreName(id))
            }
            return PartToMaterial()
        }
    }

    //PartToMaterialから鉱石辞書を生成し，ItemStackに登録する
    fun registerStack(stack: ItemStack) {
        OreDictionary.registerOre(this.toOreDict(), stack)
    }

    //PartToMaterial -> 鉱石辞書
    fun toOreDict(): String = part.name + material.getOreDict()

    //PartToMaterial -> ItemStack
    fun toStack(amount: Int = 1): ItemStack {
        return if (material.isValidPart(part)) ItemStack(RagiRegistry.getPart(part), amount, material.index) else ItemStack.EMPTY
    }

}