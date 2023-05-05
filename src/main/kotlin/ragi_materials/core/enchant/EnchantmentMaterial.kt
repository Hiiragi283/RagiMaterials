package ragi_materials.core.enchant

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnumEnchantmentType
import net.minecraft.inventory.EntityEquipmentSlot
import ragi_materials.core.RagiMaterials

object EnchantmentMaterial : Enchantment(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE, arrayOf(EntityEquipmentSlot.MAINHAND)) {

    init {
        name = "${RagiMaterials.MOD_ID}.material"
        setRegistryName(RagiMaterials.MOD_ID, name)
    }

    override fun getMaxLevel() = 1

}