package hiiragi283.material.material

import hiiragi283.material.material.part.MaterialPart
import hiiragi283.material.material.part.PartRegistry
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary
import rechellatek.camelToSnakeCase

data class PartToMaterial(val part: MaterialPart, val material: RagiMaterial) {

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
            val id = OreDictionary.getOreIDs(stack)[0]
            return fromOreDict(OreDictionary.getOreName(id))
        }
    }

    //PartToMaterialから鉱石辞書を生成し，ItemStackに登録する
    fun registerStack(stack: ItemStack, pair: PartToMaterial) {
        OreDictionary.registerOre(pair.toOreDict(), stack)
    }

    //MaterialPartとRagiMaterialから鉱石辞書を生成し，ItemStackに登録する
    fun registerStack(stack: ItemStack, part: MaterialPart, material: RagiMaterial) {
        registerStack(stack, PartToMaterial(part, material))
    }

    //PartToMaterial -> 鉱石辞書
    fun toOreDict(): String = part.name + material.getOreDict()

}