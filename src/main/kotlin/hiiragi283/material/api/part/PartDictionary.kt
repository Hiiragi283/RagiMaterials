package hiiragi283.material.api.part

import hiiragi283.material.api.material.MaterialDictionary
import hiiragi283.material.api.registry.HiiragiDictionary
import hiiragi283.material.api.registry.MetaItem
import hiiragi283.material.api.shape.ShapeDictionary
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object PartDictionary : HiiragiDictionary<HiiragiPart>("Part") {

    override fun onRegistered(pair: MetaItem, obj: HiiragiPart) {
        MaterialDictionary.register(pair, obj.material)
        ShapeDictionary.register(pair, obj.shape)
    }

    fun init() {
        HiiragiPart.createAllParts().forEach { part: HiiragiPart ->
            OreDictionary.getOres(part.getOreDict()).forEach { stack: ItemStack ->
                register(stack, part)
            }
            part.getOreDictAlts().forEach { oreDict: String ->
                OreDictionary.getOres(oreDict).forEach { stack: ItemStack ->
                    register(stack, part)
                }
            }
        }
    }

}