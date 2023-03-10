package hiiragi283.ragi_materials.unused

import net.minecraft.item.Item

class ToolProperty(val name: String, val level: Int, val durability: Int, val efficiency: Float, val damage: Float, val enchantability: Int)  {

        constructor(name: String, material: Item.ToolMaterial): this(name, material.harvestLevel, material.maxUses, material.efficiency, material.attackDamage, material.enchantability)

}