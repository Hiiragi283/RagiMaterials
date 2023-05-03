package ragi_materials.core.enchant

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnumEnchantmentType
import net.minecraft.inventory.EntityEquipmentSlot
import ragi_materials.core.RagiMaterials

object EnchantmentMaterial : Enchantment(Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, arrayOf(EntityEquipmentSlot.MAINHAND)) {

    init {
        name = "material"
        setRegistryName(RagiMaterials.MOD_ID, name)
    }

    override fun getMaxLevel() = 1

    override fun isTreasureEnchantment() = true

}