package hiiragi283.material.api.item

import kotlinx.serialization.Serializable
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun toolMaterialOf(init: HiiragiToolMaterial.() -> Unit = {}): HiiragiToolMaterial {
    val material = HiiragiToolMaterial()
    material.init()
    return material
}

@Serializable
class HiiragiToolMaterial(
    var toolDurability: Int = 0,
    var toolMiningSpeed: Float = 0.0f,
    var toolAttackDamage: Float = 0.0f,
    var toolMiningLevel: Int = 0,
    var toolEnchantability: Int = 0,
    var item: Pair<String, String> = "minecraft" to "air"
) : ToolMaterial {

    companion object {
        @JvmField
        val EMPTY = HiiragiToolMaterial()

        @JvmStatic
        fun copy(material: ToolMaterial): HiiragiToolMaterial {
            return toolMaterialOf {
                toolDurability = material.durability
                toolMiningSpeed = material.miningSpeedMultiplier
                toolAttackDamage = material.attackDamage
                toolMiningLevel = material.miningLevel
                toolEnchantability = material.enchantability
            }
        }
    }

    override fun getDurability(): Int = toolDurability

    override fun getMiningSpeedMultiplier(): Float = toolMiningSpeed

    override fun getAttackDamage(): Float = toolAttackDamage

    override fun getMiningLevel(): Int = toolMiningLevel

    override fun getEnchantability(): Int = toolEnchantability

    override fun getRepairIngredient(): Ingredient =
        Ingredient.ofItems(Registry.ITEM.get(Identifier(item.first, item.second)))

}