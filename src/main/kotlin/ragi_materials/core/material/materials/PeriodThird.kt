package ragi_materials.core.material.materials

import net.minecraft.item.EnumRarity
import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.ColorUtil
import ragi_materials.core.util.RagiColor

object PeriodThird {

    fun load() {
        //110 ~ 119: Sodium
        MaterialRegistry.SODIUM_HYDROXIDE = RagiMaterial.Builder(111, "sodium_hydroxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 1, MaterialRegistry.HYDROXIDE to 1)).build()

        MaterialRegistry.SALT = RagiMaterial.Builder(112, "salt", TypeRegistry.DUST.enableOre()).setComponents(listOf(ElementRegistry.SODIUM to 1, ElementRegistry.CHLORINE to 1)).apply {
            color = RagiColor.WHITE
        }.buildAndRegister()

        //120 ~ 129: Magnesium
        MaterialRegistry.MAGNESIUM = RagiMaterial.Builder(120, "magnesium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.MAGNESIUM to 1).buildAndRegister()

        MaterialRegistry.MAGNESITE = RagiMaterial.Builder(121, "magnesite", TypeRegistry.DUST.enableOre()).setComponents(listOf(ElementRegistry.MAGNESIUM to 1, MaterialRegistry.CARBONATE to 1)).buildAndRegister()

        MaterialRegistry.MAGNESIUM_CHLORIDE = RagiMaterial.Builder(122, "magnesium_chloride", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.MAGNESIUM to 1, ElementRegistry.CHLORINE to 2)).build()

        //130 ~ 139: Aluminium
        MaterialRegistry.ALUMINIUM = RagiMaterial.Builder(130, "aluminium", TypeRegistry.METAL).setSimple(ElementRegistry.ALUMINIUM to 1).apply {
            oredictAlt = "Aluminum"
        }.buildAndRegister()

        MaterialRegistry.ALUMINA = RagiMaterial.Builder(131, "alumina", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3)).build()

        MaterialRegistry.ALUMINA_SOLUTION = RagiMaterial.Builder(132, "alumina_solution", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.SODIUM to 1, RagiMaterial.Formula("[").build() to 1, ElementRegistry.ALUMINIUM to 1, MaterialRegistry.HYDROXIDE.setBracket() to 4, RagiMaterial.Formula("]").build() to 1)).build()

        MaterialRegistry.BAUXITE = RagiMaterial.Builder(133, "bauxite", TypeRegistry.DUST.enableOre()).setComponents(listOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3)).apply {
            color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1)
        }.buildAndRegister()

        MaterialRegistry.RUBY = RagiMaterial.Builder(134, "ruby", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(ElementRegistry.CHROMIUM to 1, MaterialRegistry.ALUMINA to 1)).apply {
            color = RagiColor.RED
            crystalType = EnumCrystalType.RUBY
        }.buildAndRegister()

        MaterialRegistry.SAPPHIRE = RagiMaterial.Builder(135, "sapphire", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(ElementRegistry.IRON to 1, MaterialRegistry.ALUMINA to 1)).apply {
            color = RagiColor.BLUE
            crystalType = EnumCrystalType.RUBY
        }.buildAndRegister()

        //140 ~ 149: Silicon
        MaterialRegistry.SILICON = RagiMaterial.Builder(140, "silicon", TypeRegistry.METALLOID).setSimple(ElementRegistry.SILICON to 1).buildAndRegister()

        MaterialRegistry.SILICON_DIOXIDE = RagiMaterial.Builder(141, "silicon_dioxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2)).build()

        MaterialRegistry.GLASS = RagiMaterial.Builder(142, "glass", TypeRegistry.CRYSTAL).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.WHITE
            crystalType = EnumCrystalType.RUBY
        }.buildAndRegister()

        MaterialRegistry.QUARTZ = RagiMaterial.Builder(143, "quartz", TypeRegistry.CRYSTAL.enableOre()).setSimple(MaterialRegistry.SILICON_DIOXIDE to 1).apply {
            color = RagiColor.WHITE
            crystalType = EnumCrystalType.QUARTZ
        }.buildAndRegister()

        //150 ~ 159: Phosphorus
        MaterialRegistry.PHOSPHORUS = RagiMaterial.Builder(150, "phosphorus", TypeRegistry.DUST).setSimple(ElementRegistry.PHOSPHORUS to 1).buildAndRegister()

        //160 ~ 169: Sulfur
        MaterialRegistry.SULFUR = RagiMaterial.Builder(160, "sulfur", TypeRegistry.DUST.enableOre()).setSimple(ElementRegistry.SULFUR to 8).buildAndRegister()

        MaterialRegistry.SULFURIC_ACID = RagiMaterial.Builder(161, "sulfuric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.HYDROGEN to 2, MaterialRegistry.SULFATE to 1)).apply {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW)
        }.buildAndRegister()

        //170 ~ 179: Chlorine
        MaterialRegistry.CHLORINE = RagiMaterial.Builder(170, "chlorine", TypeRegistry.GAS).setSimple(ElementRegistry.CHLORINE to 2).buildAndRegister()

        MaterialRegistry.HYDROGEN_CHLORIDE = RagiMaterial.Builder(171, "hydrogen_chloride", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.CHLORINE to 1)).buildAndRegister()

        //180 ~ 189: Argon
        MaterialRegistry.ARGON = RagiMaterial.Builder(22, "argon", TypeRegistry.GAS).setSimple(ElementRegistry.ARGON to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

    }
}